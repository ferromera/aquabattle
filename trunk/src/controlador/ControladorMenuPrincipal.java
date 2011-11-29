package controlador;

import java.awt.event.KeyEvent;

import pantallas.MenuPrincipal;
import titiritero.KeyPressedObservador;

public class ControladorMenuPrincipal implements KeyPressedObservador{
	
	private boolean seleccionadoContinuar, seleccionadoNuevoJuego, seleccionadoPuntajesAltos, seleccionadoSalir;
	MenuPrincipal menu;
	private static ControladorMenuPrincipal instancia=null;
	
	public ControladorMenuPrincipal (MenuPrincipal menu){
		this.menu = menu;
		seleccionadoContinuar = false;
		seleccionadoNuevoJuego = false;
		seleccionadoPuntajesAltos = false;
		seleccionadoSalir = false;
	}
	
	public static ControladorMenuPrincipal getInstancia(){
		if(instancia == null)
			instancia = new ControladorMenuPrincipal(MenuPrincipal.getInstance());
		return instancia;
	}
	
	
	public void keyPressed(KeyEvent e){
		int key= e.getKeyCode();

	}
	

	public void keyReleased(KeyEvent e){
	}
	
	private void actualizarMenuPrincipal(){
		if (seleccionadoContinuar){
			
		}
		
		if (seleccionadoNuevoJuego){
			
		}
		
		if (seleccionadoPuntajesAltos){
			
		}
		
		if (seleccionadoSalir){
			
		}
		
	}
	

}
