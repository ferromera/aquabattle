package pantallas;

import titiritero.ControladorJuego;
import vista.pantallas.VistaFinDelJuego;

public class FinDelJuego extends Pantalla {
	
	boolean pausado;
	static FinDelJuego instancia;
	
	
	public FinDelJuego(){
		new VistaFinDelJuego(this);
		pausado = false;
		
		
	}
	public static FinDelJuego getInstancia(){
		if(instancia == null){
			instancia = new FinDelJuego();
		}
		
		return instancia;
	}
	

	@Override
	public void vivir() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cambiarA(Pantalla pantalla) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void pausar() {
		pausado=true;
		
	}

	@Override
	public void convertirEnActual() {
		ControladorJuego.getInstancia().agregarDibujable(VistaFinDelJuego.getInstancia());
		ControladorJuego.getInstancia().agregarObjetoVivo(this);
		//ControladorJuego.getInstancia().agregarKeyPressObservador(ControladorMenuPrincipal.getInstancia());
		reanudar();
		
	}

	@Override
	public void dejarDeSerActual() {
		pausar();
		ControladorJuego.getInstancia().removerDibujable(VistaFinDelJuego.getInstancia());
		ControladorJuego.getInstancia().removerObjetoVivo(this);
		//ControladorJuego.getInstancia().removerKeyPressObservador(ControladorMenuPrincipal.getInstancia());
		
		
		
	}

	@Override
	public void reanudar() {
		pausado=false;
		
	}

}
