package logicanegocios.metodosencriptacion;

import logicanegocios.IEncriptable;

public abstract class Binario implements IEncriptable{
	
	protected static String obtenerCodigoBinario(int letra) {
		String letraBinaria="*";
		if (letra!=32) {
			int indice = letra - 97;
			letraBinaria = String.format("%05d", Integer.parseInt(Integer.toString(indice,2)));
		}
		return letraBinaria;
	}
	
	protected static char obtenerLetra(String letraBinaria) {
		char decimalValue=' ';
		if (!letraBinaria.equals("*"))
			decimalValue = (char) (Integer.parseInt(letraBinaria, 2) + 97);
		return decimalValue;
	}
}
