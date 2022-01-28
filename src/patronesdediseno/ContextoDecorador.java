package patronesdediseno;


public class ContextoDecorador extends Contexto {

	protected Contexto contextoDecorado;
	
    public ContextoDecorador(Contexto contexto) throws Exception {
		contextoDecorado= contexto;
	}

}