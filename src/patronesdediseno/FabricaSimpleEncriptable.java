package patronesdediseno;

import logicanegocios.IEncriptable;

/**
 * @author GMG418
 *
 */
public class FabricaSimpleEncriptable {

	public IEncriptable crearEncriptable(String type) throws Exception{
		IEncriptable encriptable = (IEncriptable) Class.forName("logicanegocios.tiposencriptacion."+type).newInstance();
		return encriptable;
	}
	
}
