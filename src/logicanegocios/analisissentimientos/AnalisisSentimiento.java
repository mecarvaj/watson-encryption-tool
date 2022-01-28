package logicanegocios.analisissentimientos;

import com.ibm.watson.developer_cloud.service.security.IamOptions;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.ToneAnalyzer;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.DocumentAnalysis;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ToneOptions;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ToneScore;

public class AnalisisSentimiento {

	//public static void main(String[] args) {
		
	public static String getSentimiento(String frase) {
		// se accede al servicio usando el password y el URL 
		//String frase = "Today was a good day but I think it could be better if I have more time to spend with my friend"; 
		IamOptions iAmOptions = new IamOptions.Builder()
				.apiKey("FQxnWq-quZ1L1KKzu6BXoP0co6OucRQnrMb0vZsXq9B5")
			    .build();
	
		ToneAnalyzer toneAnalyzer = new ToneAnalyzer("2017-09-21", iAmOptions);
		toneAnalyzer.setEndPoint("https://api.us-south.tone-analyzer.watson.cloud.ibm.com/instances/837c6b92-9364-485a-b155-2441a0ba2c61");
		
		
		// funcion que realiza el analisis del msj
		ToneOptions toneOptions = new ToneOptions.Builder()
				  .text(frase)
				  .build();
		
		// se retorna los sentimientos encontrado en formato JSON 
		DocumentAnalysis toneAnalysis = toneAnalyzer.tone(toneOptions).execute().getDocumentTone();
		String sentimientos = "";
		for (ToneScore sentimiento : toneAnalysis.getTones()) {
			sentimientos += "Sentimiento: " + sentimiento.getToneName() + System.lineSeparator()+
							"Score:  "+ String.valueOf((int)(sentimiento.getScore()*100))+ "%"+System.lineSeparator();
		}
		return sentimientos;
		
	}

}
