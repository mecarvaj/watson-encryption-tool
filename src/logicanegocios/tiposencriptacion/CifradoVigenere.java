/**
 * 
 */
package logicanegocios.tiposencriptacion;

import logicanegocios.metodosencriptacion.Sustitucion;

/**
 * @author GMG418
 *
 */
public class CifradoVigenere extends Sustitucion {

	@Override
	public String encriptar(Object[] argumentos) {
		String frase = ((String) argumentos[1]).replace(">","");
		int cifraOriginal = Integer.parseInt((String) argumentos[2]);
		String cifraAjustada = CifradoVigenere.ajustarCifra(cifraOriginal,frase.replace(" ","").length());		
		return cifradoVigenere(frase.toCharArray(),cifraAjustada,1);
	}

	@Override
	public String desencriptar(Object[] argumentos) {
		String frase = ((String) argumentos[1]).replace(">","");
		int cifraOriginal = Integer.parseInt((String) argumentos[2]);
		String cifraAjustada = CifradoVigenere.ajustarCifra(cifraOriginal,frase.replace(" ","").length());		
		return cifradoVigenere(frase.toCharArray(),cifraAjustada,-1);
	
	}
	private static String ajustarCifra(int cifra, int tamano){
		String cifraAjustada="";
		while(!(cifraAjustada.length()>=tamano)) {
			cifraAjustada=cifraAjustada + ( cifra / 10 );
			if (cifraAjustada.length()>=tamano) {
				return cifraAjustada;  
			}
			cifraAjustada=cifraAjustada + ( cifra % 10 );

		} 
		
		return cifraAjustada;
	}
	private String cifradoVigenere(char[] frase, String cifra,int tipo) {
		int posiciones,j=0,digito=0;
		
		for (int i = 0; i < frase.length; i++) {
			if (frase[i]!=' ') {
				digito=Integer.parseInt((String)""+cifra.charAt(j));
				posiciones = tipo * ( digito); 
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
