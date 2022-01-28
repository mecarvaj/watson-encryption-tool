/**
 * 
 */
package logicanegocios.metodosencriptacion;

import logicanegocios.IEncriptable;

/**
 * @author GMG418
 *
 */
public abstract class Telefonico implements IEncriptable{
	
	protected static char[][] teclado= {{},{'a','b','c'}, {'d','e','f'},
			{'g','h','i'},{'j','k','l'},{'m','n','o'},
			{'p','q','r','s'},{'t','u','v'},
			{'w','x','y','z'},{' '}};
	
	protected  String obtenerCodigoNumerico(char letra) {
		
		String resultado="*";
		if (letra!=' ') {
			for (int j=0;j<teclado.length || resultado.equals("");j++) {
				for (int i=0;i<teclado[j].length;i++) {
					if (teclado[j][i]==(letra)) {
						resultado =""+(j+1)+ "" + (i+1);
						break;
					}
				}
			}
		}
		
		return resultado;
	}
	protected char obtenerCaracter(String codigoNumerico) {
		int tecla, posicion;
		char resultado=' ';
		if (!codigoNumerico.equals("*")) {
			tecla=Integer.parseInt(codigoNumerico.split("")[0]);
			posicion= Integer.parseInt(codigoNumerico.split("")[1]);
			resultado=teclado [tecla-1][posicion-1];
		}
		
		return resultado;
		
	}
}
