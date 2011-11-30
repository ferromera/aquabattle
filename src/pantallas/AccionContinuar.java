package pantallas;

public class AccionContinuar implements AccionBoton {
	private static AccionContinuar instancia=null;
	private AccionContinuar(){}
	public static AccionContinuar getInstancia(){
		if(instancia==null)
			instancia=new AccionContinuar();
		return instancia;
	}
	@Override
	public void actuar() {
		// TODO Auto-generated method stub
		
	}
}
