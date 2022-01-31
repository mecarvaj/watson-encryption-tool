package worshop.chat;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.lang.reflect.*;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.json.JSONObject;

import com.ibm.watson.developer_cloud.assistant.v1.Assistant;
import com.ibm.watson.developer_cloud.assistant.v1.model.Context;
import com.ibm.watson.developer_cloud.assistant.v1.model.InputData;
import com.ibm.watson.developer_cloud.assistant.v1.model.MessageOptions;
import com.ibm.watson.developer_cloud.assistant.v1.model.MessageResponse;
import com.ibm.watson.developer_cloud.service.security.IamOptions;

import logicanegocios.analisissentimientos.AnalisisSentimiento;
import patronesdediseno.BitacoraContextoDecorador;
import patronesdediseno.Contexto;

@Path("/chatservice")
public class ChatService {

	private String urlDB;
	private String userDB;
	private String passwordDB;
	private String apiKey="oyHynwifFUvR2QydiynlPMm5IVYXqmzgw5Wv1IWvZq+p8le2O/LoMRtKmZdyq4apZL9oSEj/pm8=";//=
	private String assistantURL="https://api.us-south.assistant.watson.cloud.ibm.com/instances/00c04eb1-f486-40cd-b46f-5cb1fab2853d";
	private static String workspaceId = "a7a72d5f-2309-4dca-91c1-ed32764735c9";
	
	public ChatService(){
		try {
			//loadProperties();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void loadProperties() throws Exception{
		
		Map<String,String> env = System.getenv();
		
		JSONObject vcap = null;
		
		//VCAP_SERVICES is loaded as an environment variable if running in from IBM Cloud.
		//Otherwise,  it is read locally from configuration.properties.
		if (env.containsKey("VCAP_SERVICES")) {
			vcap = new JSONObject(env.get("VCAP_SERVICES"));
		} else {
			InputStream in = null;

			try {
				Properties localEnv = new Properties();
				in = getClass().getResourceAsStream("/configuration.properties");
				localEnv.load(in);
				in.close();
				vcap = new JSONObject(localEnv.getProperty("VCAP_SERVICES"));
			} finally {
				if (in != null) {
					in.close();
				}
			}
		}

		// DB2 entry
		JSONObject service = (JSONObject) vcap.getJSONArray("dashDB For Transactions").getJSONObject(0);

		if (service != null) {
			JSONObject creds = service.getJSONObject("credentials");
			urlDB = creds.getString("jdbcurl");
			userDB = creds.getString("username");
			passwordDB = creds.getString("password");
		}
		
		// Watson Assistant entry
		service = (JSONObject) vcap.getJSONArray("conversation").getJSONObject(0);

		if (service != null) {
			JSONObject creds = service.getJSONObject("credentials");
			apiKey = creds.getString("apikey");
			assistantURL = creds.getString("url");
		}
		
		System.out.println(urlDB);
		System.out.println(userDB);
		System.out.println(passwordDB);
		System.out.println(apiKey);
		System.out.println(assistantURL);
		
		
	}
	
	@GET
	@Produces("application/json")
	public Response getResponse(@QueryParam("conversationMsg") String conversationMsg, @QueryParam("conversationCtx") String conversationCtx) throws Exception {
		
		IamOptions iAmOptions = new IamOptions.Builder()
			.apiKey(apiKey)
		    .build();

		Assistant service = new Assistant("2018-09-20", iAmOptions);
		service.setEndPoint(assistantURL);
		
		// Initialize with empty value to start the conversation.
		JSONObject ctxJsonObj = new JSONObject(conversationCtx);
		Context context = new Context();
		context.putAll(ctxJsonObj.toMap());
		
		String currentAction = "";
		
		InputData input = new InputData.Builder(conversationMsg).build();
		MessageOptions options = new MessageOptions.Builder(workspaceId).input(input).context(context).build();
		
		MessageResponse assistantResponse = service.message(options).execute();
		
		// Print the output from dialog, if any.
		List<String> assistantResponseList = assistantResponse.getOutput().getText();
		JSONObject object = new JSONObject();
		
		String assistantResponseText = "";
		for (String tmpMsg : assistantResponseList)
			assistantResponseText = assistantResponseText + System.lineSeparator() + tmpMsg;
		
		//Administrar respuesta en caso que se quiera consultar la bitacora
		if (assistantResponseText.contains("###"))
			assistantResponseText = administraRespuestaBitacora(assistantResponseText);
		
		//Acá se encripta
		else if (assistantResponseText.contains("¬¬"))
			assistantResponseText = administrarRespuesta(assistantResponseText);
		
		
		
		object.put("response", assistantResponseText);
		object.put("context", assistantResponse.getContext());
		return Response.status(Status.OK).entity(object.toString()).build();
	}
	
	
	//En este método se toma el resultado del mensaje y se transforma en un mensaje des/encriptado. 
	private String administrarRespuesta(String assistantResponseText) throws Exception {
		
		// assistantResponse = >¬¬tarea programada encriptacion¬¬"no object"¬¬CifradoTelefonico¬¬Encriptar
		Object[] argumentos = (Object[]) assistantResponseText.split("¬¬"); // [">","asda", "asdad"]
		//argumentos = {"tarea programada encriptacion","no object", "CifradoTelefonico","Encriptar"};
		Contexto contexto;
		String frase = (String) argumentos[1];
		String tipo = (String) argumentos[3];
		String accion = (String) argumentos[4];
		String resultadoEncriptado="*El texto tras "+ accion + " es: ";
		
		String sentimientos = AnalisisSentimiento.getSentimiento(frase);
		contexto= new Contexto(tipo);
		BitacoraContextoDecorador contextoBitacora = new BitacoraContextoDecorador(contexto);
		if (sentimientos.contains("Anger")) {
			resultadoEncriptado = "Se determinó la existencia de Anger"
					+ " en el texto por encriptar, le recomendamos modificar el mensaje "
					+ "con el fin de apegarse a las reglas de Netiquette";
		}
		else if (accion.contains("desencriptar"))
			resultadoEncriptado+=contextoBitacora.estrategiaDesencriptar(argumentos);
		else//restultado si es accion = Encriptar
			resultadoEncriptado+=contextoBitacora.estrategiaEncriptar(argumentos);
		
		return resultadoEncriptado +" "+ sentimientos+" Presione Intro para continuar...";
		
	}
	 private String administraRespuestaBitacora(String assistantResponseText) {
		
		String[] responseArray = assistantResponseText.split("###"); 
		String metodo= responseArray[1], clase="logicanegocios.bitacora."+responseArray[2];
		String consulta = "";
		Class<?> bitacora;
		 
		try {
			bitacora = Class.forName(clase);
			Object objBitacora = bitacora.newInstance();
			java.lang.reflect.Method getNameMethod = objBitacora.getClass().getMethod(metodo);
			consulta = (String) getNameMethod.invoke(objBitacora);
		} catch (Exception e) {
			e.printStackTrace();
		}
			
	    return consulta;
	 }
	
}
