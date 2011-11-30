package pantallas;

public class PantallaActual extends Pantalla {
	
	private static PantallaActual instancia;
	public static PantallaActual getInstancia(){
		if(instancia==null)
			instancia =new PantallaActual(new MenuPrincipal());
		return instancia;
	}
	
	Pantalla estadoPantalla;

	public PantallaActual(Pantalla primeraPantalla){
		estadoPantalla=primeraPantalla;
		estadoPantalla.convertirEnActual();
	}
	
	@Override
	public void vivir() {
		estadoPantalla.vivir();
	}
	
	public void cambiarA(Pantalla nuevaPantalla){
		estadoPantalla.dejarDeSerActual();
		estadoPantalla=nuevaPantalla;
		estadoPantalla.convertirEnActual();
	}

	@Override
	public void pausar() {
		estadoPantalla.pausar();
		
	}

	@Override
	public void convertirEnActual() {
	}

	@Override
	public void dejarDeSerActual() {
	}

	@Override
	public void reanudar() {
		estadoPantalla.reanudar();
		
	}

}
