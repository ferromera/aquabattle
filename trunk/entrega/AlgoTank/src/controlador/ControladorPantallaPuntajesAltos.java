package controlador;

import java.awt.event.KeyEvent;

import pantallas.MenuPrincipal;
import pantallas.PantallaActual;
import titiritero.KeyPressedObservador;

public class ControladorPantallaPuntajesAltos implements KeyPressedObservador {
	private static ControladorPantallaPuntajesAltos instancia=null;
	
	private ControladorPantallaPuntajesAltos(){
	}
	
	@Override
	public void keyPressed(KeyEvent event) {
		PantallaActual.getInstancia().cambiarA(MenuPrincipal.getInstance());
	}

	@Override
	public void keyReleased(KeyEvent event) {
		// TODO Auto-generated method stub
		
	}

	public static ControladorPantallaPuntajesAltos getInstancia() {
		if(instancia==null)
			instancia=new ControladorPantallaPuntajesAltos();
		return instancia;
	}

}
