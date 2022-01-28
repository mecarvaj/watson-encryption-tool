package logicanegocios.bitacora;

import java.io.IOException;

/**
 * 
 */
public abstract class Bitacora {

    /**
     * Default constructor
     */
    public Bitacora() {
    }

    /**
     * @param argumentos 
     * @return
     * @throws IOException 
     */
    public abstract void agregarRegistro(Object[] argumentos);

    /**
     * @return
     */
    public abstract String verRegistrosHoy();

    /**
     * @return
     */
    public abstract String verRegistrosEncriptar();

    /**
     * @return
     */
    public abstract String verRegistrosDesencriptar();

    /**
     * @return
     */
    public abstract String verTodos();

}