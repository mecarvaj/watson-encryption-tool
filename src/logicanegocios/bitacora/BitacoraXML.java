package logicanegocios.bitacora;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Attr;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * 
 */
public class BitacoraXML extends Bitacora {

    /**
     * Default constructor
     */
    public BitacoraXML() {
    }

    /**
     * @param argumentos 
     * @return void 
     */
    public void agregarRegistro(Object[] argumentos) {
    	
    	String[] registroBitacora = {(String) argumentos[1],(String) argumentos[2],
				 (String) argumentos[3],(String) argumentos[4],
				 new SimpleDateFormat("dd/MM/yyyy").format(new Date())};
    	Document documento = null;
    	DocumentBuilder builder=null;
    	try {
	  		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	  		builder = factory.newDocumentBuilder();
	  		documento = builder.parse(new File("BitacoraXML.xml"));
	  		// root element
	    	Element raiz = documento.getDocumentElement();
			Element registro = documento.createElement("registro");
			 
			 // set an attribute - frase
				Attr attrFrase = documento.createAttribute("frase");
		      attrFrase.setValue(registroBitacora[0]);
		      registro.setAttributeNode(attrFrase);
				 
		      // set an attribute - llave o numero
		      Attr attrLlave = documento.createAttribute("llave");
		      attrLlave.setValue(registroBitacora[1]);
		      registro.setAttributeNode(attrLlave);
		      
		      // set an attribute - tipo
		      Attr attrTipo = documento.createAttribute("tipo");
		      attrTipo.setValue(registroBitacora[2]);
		      registro.setAttributeNode(attrTipo);
		      
		      // set an attribute - accion
		      Attr attrAccion = documento.createAttribute("accion");
		      attrAccion.setValue(registroBitacora[3]);
		      registro.setAttributeNode(attrAccion);
		      
		      // set an attribute - fecha
		      Attr attrFecha = documento.createAttribute("fecha");
		      attrFecha.setValue(registroBitacora[4]);
		      registro.setAttributeNode(attrFecha);
		      raiz.appendChild(registro);
		     
		     Transformer transformer;
	     try {
			transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		    transformer.setOutputProperty(OutputKeys.METHOD, "xml");
		    transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

		    DOMSource domSource = new DOMSource(documento);
		    StreamResult result = new StreamResult(new File("BitacoraXML.xml"));
		    transformer.transform(domSource, result);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
    	}catch (Exception x) {
    		crearXML(registroBitacora);
    		try {
    			documento = builder.parse(new File("BitacoraXML.xml"));
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
       }
    	
    }

    /**
     * @return
     */
    public String verRegistrosHoy() {
    	String fecha =new SimpleDateFormat("dd/MM/yyyy").format(new Date()),registrosHoy;
    	registrosHoy="<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>" + 
    			System.getProperty("line.separator")+ "<xml>";
    	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    	Document documento;
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			documento = builder.parse(new File("BitacoraXML.xml"));
			// root element
	    	Element raiz = documento.getDocumentElement();
	    	NodeList nodosRegistros = raiz.getElementsByTagName("registro");
	    	Element registro;
	    	for (int i=0;i<nodosRegistros.getLength();i++){  // En este caso sólo devolverá uno ya que sólo hay un pedido
	    		registro = (Element) nodosRegistros.item(i); 
	    		if(registro.getAttribute("fecha").contains(fecha)) {
	    			registrosHoy+="<registro frase='"+ registro.getAttribute("frase") + "' " +
	    								 " llave='"+ registro.getAttribute("llave") + "' " +
	    								 " tipo='"+ registro.getAttribute("tipo") + "' " +
	    								 " accion='"+ registro.getAttribute("accion") + "' " +
	    								 " fecha='"+ registro.getAttribute("fecha") + "' " +
	    								 " />";
	    		}
	        	}
	    	registrosHoy+="</xml>";
			} catch (Exception e) {
				e.printStackTrace();
			} 
        return prettyFormat(registrosHoy);
    }

    /**
     * @return
     */
    public String verRegistrosEncriptar() {
    	String registrosEncriptar;
    	registrosEncriptar="<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>" + 
    			System.getProperty("line.separator")+ "<xml>";
    	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    	Document documento;
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			documento = builder.parse(new File("BitacoraXML.xml"));
			// root element
	    	Element raiz = documento.getDocumentElement();
	    	NodeList nodosRegistros = raiz.getElementsByTagName("registro");
	    	Element registro;
	    	for (int i=0;i<nodosRegistros.getLength();i++){  // En este caso sólo devolverá uno ya que sólo hay un pedido
	    		registro = (Element) nodosRegistros.item(i); 
	    		if(registro.getAttribute("accion").equals("encriptar")) {
	    			registrosEncriptar+="<registro frase='"+ registro.getAttribute("frase") + "' " +
	    								 " llave='"+ registro.getAttribute("llave") + "' " +
	    								 " tipo='"+ registro.getAttribute("tipo") + "' " +
	    								 " accion='"+ registro.getAttribute("accion") + "' " +
	    								 " fecha='"+ registro.getAttribute("fecha") + "' " +
	    								 " />";
	    		}
	        	}
	    	registrosEncriptar+="</xml>";
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
        return prettyFormat(registrosEncriptar);
    }

    /**
     * @return
     */
    public String verRegistrosDesencriptar() {
    	String registrosDesencriptar;
    	registrosDesencriptar="<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>" + 
    			System.getProperty("line.separator")+ "<xml>";
    	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    	Document documento;
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			documento = builder.parse(new File("BitacoraXML.xml"));
			// Elemento raiz <xml>
	    	Element raiz = documento.getDocumentElement();
	    	NodeList nodosRegistros = raiz.getElementsByTagName("registro");
	    	Element registro;
	    	for (int i=0;i<nodosRegistros.getLength();i++){  // En este caso sólo devolverá uno ya que sólo hay un pedido
	    		registro = (Element) nodosRegistros.item(i); 
	    		if(registro.getAttribute("accion").equals("desencriptar")) {
	    			registrosDesencriptar+="<registro frase='"+ registro.getAttribute("frase") + "' " +
	    								 " llave='"+ registro.getAttribute("llave") + "' " +
	    								 " tipo='"+ registro.getAttribute("tipo") + "' " +
	    								 " accion='"+ registro.getAttribute("accion") + "' " +
	    								 " fecha='"+ registro.getAttribute("fecha") + "' " +
	    								 " />";
	    		}
	        	}
	    	registrosDesencriptar+="</xml>";
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
        return prettyFormat(registrosDesencriptar);
    
    }

    /**
     * @return
     */
    public String verTodos() {
    	String registrosTodos;
    	registrosTodos="<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>" + 
    					System.getProperty("line.separator")+ "<xml>";
    	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    	Document documento;
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			documento = builder.parse(new File("BitacoraXML.xml"));
			// root element
	    	Element raiz = documento.getDocumentElement();
	    	NodeList nodosRegistros = raiz.getElementsByTagName("registro");
	    	Element registro;
	    	for (int i=0;i<nodosRegistros.getLength();i++){  
	    		registro = (Element) nodosRegistros.item(i); 
	    		registrosTodos+="<registro frase='"+ registro.getAttribute("frase") + "' " +
	    							" llave='"+ registro.getAttribute("llave") + "' " +
	    							" tipo='"+ registro.getAttribute("tipo") + "' " +
	    							" accion='"+ registro.getAttribute("accion") + "' " +
	    							" fecha='"+ registro.getAttribute("fecha") + "' " +
	    							" />";
	    	}
	    	registrosTodos+="</xml>";
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
        return prettyFormat(registrosTodos);
    }
    
    private static String prettyFormat(String xmlString) {
    	//Codigo tomado de https://stackoverflow.com/questions/139076/how-to-pretty-print-xml-from-java
    	int indent =2;
    	try {
	        Source xmlInput = new StreamSource(new StringReader(xmlString));
	        StringWriter stringWriter = new StringWriter();
	        StreamResult xmlOutput = new StreamResult(stringWriter);
	        TransformerFactory transformerFactory = TransformerFactory.newInstance();
	        transformerFactory.setAttribute("indent-number", indent);
	        Transformer transformer = transformerFactory.newTransformer(); 
	        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	        transformer.transform(xmlInput, xmlOutput);
	        return xmlOutput.getWriter().toString();
	    } catch (Exception e) {
	        throw new RuntimeException(e); 
	    }
	}

    
    private static void crearXML(String[] pRegistro) {
    	Document documento = null;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try{
           DocumentBuilder builder = factory.newDocumentBuilder();
           DOMImplementation implementation = builder.getDOMImplementation();
           documento = implementation.createDocument(null, "xml", null);
           
           Element raiz = documento.getDocumentElement();//xml
           
           Element registro = documento.createElement("registro"); 
           

        // set an attribute - frase
           Attr attrFrase = documento.createAttribute("frase");
           attrFrase.setValue(pRegistro[0]);
           registro.setAttributeNode(attrFrase);
  		 
           // set an attribute - llave o numero
           Attr attrLlave = documento.createAttribute("llave");
           attrLlave.setValue(pRegistro[1]);
           registro.setAttributeNode(attrLlave);
           
           // set an attribute - tipo
           Attr attrTipo = documento.createAttribute("tipo");
           attrTipo.setValue(pRegistro[2]);
           registro.setAttributeNode(attrTipo);
           
           // set an attribute - accion
           Attr attrAccion = documento.createAttribute("accion");
           attrAccion.setValue(pRegistro[3]);
           registro.setAttributeNode(attrAccion);
           
           // set an attribute - fecha
           Attr attrFecha = documento.createAttribute("fecha");
           attrFecha.setValue(pRegistro[4]);
           registro.setAttributeNode(attrFecha);
           raiz.appendChild(registro);  
           
           //Asignamos la versión de nuestro XML
           documento.setXmlVersion("1.0"); 
           //Añadimos la casa al documento
           documento.getDocumentElement().appendChild(registro); 
           //Añadimos el elemento hijo a la raíz

           
           TransformerFactory transFact = TransformerFactory.newInstance();

           //Formateamos el fichero. Añadimos sangrado y la cabecera de XML
           transFact.setAttribute("indent-number", new Integer(3));
           Transformer trans = transFact.newTransformer();
           trans.setOutputProperty(OutputKeys.INDENT, "yes");
           trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");

           //Hacemos la transformación
           StringWriter sw = new StringWriter();
           StreamResult sr = new StreamResult(sw);
           DOMSource domSource = new DOMSource(documento);
           trans.transform(domSource, sr);
           
           //Creamos fichero para escribir en modo texto
           PrintWriter writer = new PrintWriter(new FileWriter("BitacoraXML.xml"));

           //Escribimos todo el árbol en el fichero
           writer.println(sw.toString());

           //Cerramos el fichero
           writer.close();
        }catch(Exception e){
            System.err.println("Error: "+e);
        }
   }
}