 package logicanegocios.bitacora;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 
 */
public class BitacoraCSV extends Bitacora {

    /**
     * Default constructor
     */
    public BitacoraCSV() {
    }

    /**
     * @param argumentos 
     * @return
     */
    public void agregarRegistro(Object[] argumentos) {
    	String[] registroBitacora = {(String) argumentos[1],(String) argumentos[2],
				 (String) argumentos[3],(String) argumentos[4],
				 new SimpleDateFormat("dd/MM/yyyy").format(new Date())};
   	
    	FileWriter fw = null;
    	BufferedWriter bw = null;
    	PrintWriter pw = null;
    	try { 
    		fw = new FileWriter("BitacoraCSV.csv", true);
    		bw = new BufferedWriter(fw);
    		pw = new PrintWriter(bw);
    		pw.println(registroBitacora[0]+", " +
           		   registroBitacora[1] +", " + 
           		   registroBitacora[2] +", " +
           		   registroBitacora[3] +", " +
           		   registroBitacora[4]);
			
    	}
    	catch (IOException io) {
    	}
    	try {
    		pw.close();
    		bw.close();
    		fw.close();
    	} catch (IOException io) {
    	}
    	 System.out.println("registro CSV agregado");
    }

    /**
     * @return
     */
    public String verRegistrosHoy() {
    	BufferedReader reader;
    	String fecha,registrosHoy,line="";
    	registrosHoy="-------Registros de hoy CSV------"+ System.lineSeparator();
    	registrosHoy+= "Frase, Llave/Cifra, Tipo Encriptado, Accion, Fecha";
		try {
			reader = new BufferedReader(new FileReader("BitacoraCSV.csv"));
			line = reader.readLine() + System.lineSeparator();
			while (line != null) { 
				fecha= line.split(", ")[4];
				if (fecha.contains(new SimpleDateFormat("dd/MM/yyyy").format(new Date())))
					registrosHoy+= line+ System.lineSeparator();
				line = reader.readLine();
				
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return registrosHoy;
    }

    /**
     * @return
     */
    public String verRegistrosEncriptar() {
    	BufferedReader reader;
    	String accion,registrosEncriptar,line="";
    	registrosEncriptar="-------Registros de Encriptación CSV------"+ System.lineSeparator()
    					+"Frase, Llave/Cifra, Tipo Encriptado, Accion, Fecha";;
		try {
			reader = new BufferedReader(new FileReader("BitacoraCSV.csv"));
			line = reader.readLine();
			while (line != null) {
				accion= line.split(", ")[3];
				if (accion.equals("encriptar")) {
					registrosEncriptar+= line+ System.lineSeparator();
					}
				line = reader.readLine();
				
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return registrosEncriptar;
    }

    /**
     * @return
     */
    public String verRegistrosDesencriptar() {
    	BufferedReader reader;
    	String accion,registrosDesencriptar,line="";
    	registrosDesencriptar="-------Registros de Desencriptar CSV------"+ System.lineSeparator()+
    			"Frase, Llave/Cifra, Tipo Encriptado, Accion, Fecha";
		try {
			reader = new BufferedReader(new FileReader("BitacoraCSV.csv"));
			line = reader.readLine();
			while (line != null) {
				accion= line.split(", ")[3];
				if (accion.equals("desencriptar")) {
					registrosDesencriptar+= line+ System.lineSeparator();
					}
				line = reader.readLine();
				
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return registrosDesencriptar;    }

    /**
     * @return
     */
    public String verTodos() {
    	BufferedReader reader;
    	String registrosTodos,line="";
    	registrosTodos="-------Todos los Registros  CSV------"+ System.lineSeparator()
    			+"Frase, Llave/Cifra, Tipo Encriptado, Accion, Fecha";;
		try {
			reader = new BufferedReader(new FileReader("BitacoraCSV.csv"));
			line = reader.readLine();
			while (line != null) {
				registrosTodos+= line+ System.lineSeparator();
				line = reader.readLine();
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return registrosTodos;
    }


}