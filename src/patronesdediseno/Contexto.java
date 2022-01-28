package patronesdediseno;

import logicanegocios.IEncriptable;
import logicanegocios.bitacora.*;


public class Contexto {
	private IEncriptable estrategia;
	private FabricaSimpleEncriptable fabrica;
	
	public Contexto (String tipo) throws Exception {
		this.fabrica = new FabricaSimpleEncriptable();
		this.estrategia = fabrica.crearEncriptable(tipo);
	}
	public Contexto() {
		// TODO Auto-generated constructor stub
	}
	public String estrategiaEncriptar(Object[] argumentos) {
		return this.estrategia.encriptar(argumentos);
	}
	public String estrategiaDesencriptar(Object[] argumentos) {
		return this.estrategia.desencriptar(argumentos);
	}
	
	
	//
	public static void main(String[] args) throws Exception {
		
		/*
		String tipo = "CifradoVigenere";//El valor de esta variable lo define el usuario por interacción
		Contexto contexto = new Contexto(tipo);
		ContextoDecorador contextoDecorado = new BitacoraContextoDecorador(contexto);
		
		Object[] argumentos = {">","Texto para encriptar","12",tipo,"encriptar"};//tarea programada criptografia de datos","3"}; 
		System.out.println(contextoDecorado.estrategiaEncriptar(argumentos));
		*/
		//System.out.println(contextoDecorado.estrategiaDesencriptar(argumentos));
		
		
		//**********************************
		//* Còdigo dentro del metodo de ChatService
		String response= ">###verRegistrosEncriptar###BitacoraXML###";
		
		String[] responseArray = response.split("###"); 
		String metodo= responseArray[1], clase="logicanegocios.bitacora."+responseArray[2];
		
		
		Class<?> bitacora = Class.forName(clase);
		Object objBitacora = bitacora.newInstance();
		java.lang.reflect.Method getNameMethod = objBitacora.getClass().getMethod(metodo);
        
		
		String consulta = (String) getNameMethod.invoke(objBitacora);
        System.out.println(consulta);//*/
        }
		
	
		
        
}
















