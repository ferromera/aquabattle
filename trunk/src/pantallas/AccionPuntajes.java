package pantallas;

public class AccionPuntajes implements AccionBoton{
	private static AccionPuntajes instancia=null;
	private AccionPuntajes(){}
	public static AccionPuntajes getInstancia(){
		if(instancia==null)
			instancia=new AccionPuntajes();
		return instancia;
	}
	@Override
	public void actuar() {
		//PantallaActual.getInstancia().cambiarA(PantallaPuntajes.getInstancia());
		
	}

}
