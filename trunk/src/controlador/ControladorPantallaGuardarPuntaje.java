package controlador;

import java.awt.event.KeyEvent;

import pantallas.MenuPrincipal;
import pantallas.PantallaActual;
import pantallas.PantallaGuardarPuntaje;

import titiritero.KeyPressedObservador;

public class ControladorPantallaGuardarPuntaje implements KeyPressedObservador {
	private static ControladorPantallaGuardarPuntaje instancia=null;
	private PantallaGuardarPuntaje pantalla;
	
	private ControladorPantallaGuardarPuntaje(){
		pantalla=PantallaGuardarPuntaje.getInstancia();
	}
	
	@Override
	public void keyPressed(KeyEvent event) {
		int key= event.getKeyCode();
		if(key==KeyEvent.VK_BACK_SPACE)
			pantalla.borrarUltimaLetra();
		else if(key==KeyEvent.VK_ENTER){
			pantalla.guardar();
			PantallaActual.getInstancia().cambiarA(MenuPrincipal.getInstance());
		}
		else if(key!=KeyEvent.VK_SHIFT)
			pantalla.agregarLetra(event.getKeyChar());
		
	}

	@Override
	public void keyReleased(KeyEvent event) {
		// TODO Auto-generated method stub
		
	}

	public static ControladorPantallaGuardarPuntaje getInstancia() {
		if(instancia==null)
			instancia=new ControladorPantallaGuardarPuntaje();
		return instancia;
	}

}
