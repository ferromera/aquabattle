package controlador;

import java.awt.event.KeyEvent;
import pantallas.PantallaActual;
import pantallas.PantallaPausa;


import modelo.TanqueHeroe;

import titiritero.KeyPressedObservador;


public class ControladorPantallaJuego implements KeyPressedObservador {
	private ControladorTanqueHeroe controlTanque;
	private static ControladorPantallaJuego instancia = null;
	boolean pausado = false;

	public static ControladorPantallaJuego getInstancia() {
		if (instancia == null)
			instancia = new ControladorPantallaJuego();
		return instancia;
	}
	public static ControladorPantallaJuego nuevaInstancia() {
		instancia = new ControladorPantallaJuego();
		return instancia;
	}

	private ControladorPantallaJuego() {
		controlTanque = new ControladorTanqueHeroe(TanqueHeroe.getInstancia());
	}

	

	@Override
	public void keyPressed(KeyEvent event) {
		controlTanque.keyPressed(event);
		int key = event.getKeyCode();
		if (key == KeyEvent.VK_ENTER) {
			PantallaActual.getInstancia().cambiarA(PantallaPausa.getInstancia());
		}
	}

	@Override
	public void keyReleased(KeyEvent event) {
		controlTanque.keyReleased(event);

	}

	public void actualizar() {
		controlTanque = new ControladorTanqueHeroe(TanqueHeroe.getInstancia());
	}

}
