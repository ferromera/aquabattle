package pantallas;

public class AccionReanudar implements AccionBoton {
	private static AccionReanudar instancia=null;
	private AccionReanudar(){}
	public static AccionReanudar getInstancia(){
		if(instancia==null)
			instancia=new AccionReanudar();
		return instancia;
	}
	@Override
	public void actuar() {
		PantallaActual.getInstancia().cambiarA(PantallaJuego.getInstancia());
		
	}

}
