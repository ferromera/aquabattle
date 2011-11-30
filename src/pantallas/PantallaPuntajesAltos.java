package pantallas;

import titiritero.ControladorJuego;
import vista.pantallas.VistaPantallaPuntajesAltos;

public class PantallaPuntajesAltos extends Pantalla {
	
	private static PantallaPuntajesAltos instancia = null;
	
	public PantallaPuntajesAltos(){
		new VistaPantallaPuntajesAltos(this);
	}
	

	
	public static PantallaPuntajesAltos getInstancia(){
		if(instancia==null)
			instancia=new PantallaPuntajesAltos();
		return instancia;
	}
	
	@Override
	public void vivir() {
		
		
	}

	@Override
	public void pausar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reanudar() {
		// TODO Auto-generated method stub
		
	}
	


	

	@Override
	public void convertirEnActual() {
		ControladorJuego.getInstancia().agregarDibujable(VistaPantallaPuntajesAltos.getInstancia());
		ControladorJuego.getInstancia().agregarObjetoVivo(this);
		//ControladorJuego.getInstancia().agregarKeyPressObservador(ControladorPantallaPuntajesAltos.getInstancia());
	}

	@Override
	public void dejarDeSerActual() {
		ControladorJuego.getInstancia().removerDibujable(VistaPantallaPuntajesAltos.getInstancia());
		ControladorJuego.getInstancia().removerObjetoVivo(this);
		//ControladorJuego.getInstancia().removerKeyPressObservador(ControladorPantallaPuntajesAltos.getInstancia());
		
		
	}
	
	
	
	
	
	
	
	
}
