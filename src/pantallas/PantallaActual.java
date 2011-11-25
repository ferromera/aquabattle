package pantallas;

public class PantallaActual extends Pantalla {
	
	private static PantallaActual instancia;
	public static PantallaActual getInstancia(){
		if(instancia==null)
			instancia =new PantallaActual(new MenuPrincipal());
		return instancia;
	}
	
	Pantalla estadoPantalla;

	private PantallaActual(Pantalla primeraPantalla){
		estadoPantalla=primeraPantalla;
	}
	
	@Override
	public void vivir() {
		estadoPantalla.vivir();
	}
	
	public void cambiarA(Pantalla nuevaPantalla){
		estadoPantalla.cambiarA(nuevaPantalla);
	}

	@Override
	public void pausar() {
		estadoPantalla.pausar();
		
	}

}
