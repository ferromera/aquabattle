package controlador;

import java.awt.event.KeyEvent;

import misc.Observador;
import modelo.TanqueHeroe;

import titiritero.KeyPressedObservador;

public class ControladorPantallaJuego implements KeyPressedObservador {
	private ControladorTanqueHeroe controlTanque;
	private static ControladorPantallaJuego instancia=null;
	public static ControladorPantallaJuego getInstancia(){
		if(instancia==null)
			instancia=new ControladorPantallaJuego();
		return instancia;
	}
	private ControladorPantallaJuego(){
		controlTanque=new ControladorTanqueHeroe(TanqueHeroe.getInstancia());
	}
	
	@Override
	public void keyPressed(KeyEvent event) {
		controlTanque.keyPressed(event);
		
	}

	@Override
	public void keyReleased(KeyEvent event) {
		controlTanque.keyReleased(event);
		
	}


	public void actualizar() {
		controlTanque=new ControladorTanqueHeroe(TanqueHeroe.getInstancia());
	}

}
