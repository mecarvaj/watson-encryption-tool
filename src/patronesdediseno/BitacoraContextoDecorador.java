package patronesdediseno;


import java.util.ArrayList;

import logicanegocios.bitacora.Bitacora;
import logicanegocios.bitacora.BitacoraCSV;
import logicanegocios.bitacora.BitacoraTXT;
import logicanegocios.bitacora.BitacoraXML;

/**
 * 
 */
public class BitacoraContextoDecorador extends ContextoDecorador {


    private ArrayList<Bitacora> bitacoras;

    /**
     * @param contexto
     */
    public BitacoraContextoDecorador(Contexto contexto) throws Exception  {
    	super(contexto);
        bitacoras = new ArrayList<>();
        attach();
    }

    /**
     * @param argumentos 
     * @return
     */
    private void attach() {
    	bitacoras.add(new BitacoraCSV());
        bitacoras.add(new BitacoraTXT());
        bitacoras.add(new BitacoraXML());
    }
    
    public String estrategiaDesencriptar(Object[] argumentos) {
    	String respuesta= "";
    	respuesta = super.contextoDecorado.estrategiaDesencriptar(argumentos);    			
    	notificarObservadores(argumentos);
    	return respuesta;
    }

    /**
     * @param argumentos 
     * @return
     */
    @Override
    public String estrategiaEncriptar(Object[] argumentos) {
    	String respuesta= "";
    	respuesta = super.contextoDecorado.estrategiaEncriptar(argumentos);
    	notificarObservadores(argumentos);
    	return respuesta;
    }

    /**
     * @param argumentos 
     * @return
     */
    private void notificarObservadores(Object[] argumentos) {
    	for (Bitacora bitacora:bitacoras)
    		bitacora.agregarRegistro(argumentos);
    }

}