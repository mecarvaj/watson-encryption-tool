package logicanegocios.tiposencriptacion;

import logicanegocios.IEncriptable;
import logicanegocios.metodosencriptacion.Sustitucion;

public class CifradoLlave extends Sustitucion {
	
	@Override
	public String encriptar(Object[] argumentos) {
		String frase = ((String) argumentos[1]).replace(">","");
		String llave= CifradoLlave.ajustarLlave((String)argumentos[2],frase.replace(" ","").length());
		return cifradoLlave(frase.toCharArray(),llave.toCharArray(),1);
	}
	 
	@Override
	public String desencriptar(Object[] argumentos) {
		String frase = ((String) argumentos[1]).replace(">","");
		String llave= CifradoLlave.ajustarLlave((String)argumentos[2],frase.replace(" ","").length());
		return cifradoLlave(frase.toCharArray(),llave.toCharArray(),-1);
	}
	
	private static String ajustarLlave(String llave, int tamano){
		String llaveAjustada="";
		for(int i=0;llaveAjustada.length()<tamano;i++) {
			llaveAjustada= llaveAjustada + llave.toCharArray()[i];
			if (i==llave.length()-1)
				i=-1;
		}
		return llaveAjustada;
		
	}
	private String cifradoLlave(char[] frase, char[] llave,int tipo) {
		int posiciones,j=0;
		for (int i = 0; i < frase.length; i++) {
			if (frase[i]!=' ') {
				posiciones = tipo * ( llave[j] - 96 );
				frase[i] = (char) obtenerCaracterEncriptado(frase[i], posiciones);
				j++;
			}
			else {
				frase[i] = ' ';
			}
			
		}
		
		return new String(frase);
	}
}