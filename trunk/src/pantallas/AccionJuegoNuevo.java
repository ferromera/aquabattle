package pantallas;

public class AccionJuegoNuevo implements AccionBoton{
	private static AccionJuegoNuevo instancia=null;
	private AccionJuegoNuevo(){}
	public static AccionJuegoNuevo getInstancia(){
		if(instancia==null)
			instancia=new AccionJuegoNuevo();
		return instancia;
	}
	@Override
	public void actuar() {
		PantallaActual.getInstancia().cambiarA(PantallaJuego.nuevaInstancia());
		
	}

}
