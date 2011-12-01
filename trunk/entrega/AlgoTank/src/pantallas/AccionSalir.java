package pantallas;

public class AccionSalir implements AccionBoton{
	private static AccionSalir instancia=null;
	private AccionSalir(){}
	public static AccionSalir getInstancia(){
		if(instancia==null)
			instancia=new AccionSalir();
		return instancia;
	}
	@Override
	public void actuar() {
		System.exit(0);
		
	}

}
