package controlador;

import java.awt.event.KeyEvent;

import pantallas.MenuPrincipal;
import titiritero.KeyPressedObservador;

public class ControladorMenuPrincipal implements KeyPressedObservador{
	
	private boolean continuar, nuevoJuego, puntajesAltos, salir;
	MenuPrincipal menu;
	private static ControladorMenuPrincipal instancia=null;
	
	public ControladorMenuPrincipal (MenuPrincipal menu){
		this.menu = menu;
		continuar = false;
		nuevoJuego = false;
		puntajesAltos = false;
		salir = false;
	}
	
	public static ControladorMenuPrincipal getInstancia(){
		if(instancia==null)
			instancia=new ControladorMenuPrincipal(MenuPrincipal.getInstance());
		return instancia;
	}
	
	
	public void keyPressed(KeyEvent e){
		
	}
	

	public void keyReleased(KeyEvent e){
	}
	
	private void actualizarMenuPrincipal(){
		if (continuar){
			
		}
		
		if (nuevoJuego){
			
		}
		
		if (puntajesAltos){
			
		}
		
		if (salir){
			
		}
		
	}
	

}
