package pantallas;

public class AccionSalirPausa implements AccionBoton {
	private static AccionSalirPausa instancia=null;
	private AccionSalirPausa(){}
	public static AccionSalirPausa getInstancia(){
		if(instancia==null)
			instancia=new AccionSalirPausa();
		return instancia;
	}
	@Override
	public void actuar() {
		PantallaActual.getInstancia().cambiarA(MenuPrincipal.getInstance());
		
	}
}
