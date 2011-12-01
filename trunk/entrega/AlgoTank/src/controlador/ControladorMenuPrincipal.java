package controlador;

import java.awt.event.KeyEvent;

import pantallas.MenuPrincipal;
import titiritero.KeyPressedObservador;

public class ControladorMenuPrincipal implements KeyPressedObservador{
	
	MenuPrincipal menu;
	private static ControladorMenuPrincipal instancia=null;
	
	public ControladorMenuPrincipal (MenuPrincipal menu){
		this.menu = menu;
		instancia=this;
	}
	
	public static ControladorMenuPrincipal getInstancia(){
		if(instancia == null)
			instancia = new ControladorMenuPrincipal(MenuPrincipal.getInstance());
		return instancia;
	}
	
	
	public void keyPressed(KeyEvent e){
		int key= e.getKeyCode();
		 if(key==KeyEvent.VK_DOWN)
			menu.seleccionarSiguienteBoton();	 
	     if(key==KeyEvent.VK_UP)
	        menu.seleccionarBotonAnterior();
	     if(key==KeyEvent.VK_ENTER)
		    menu.presionarBoton();

	}
	

	public void keyReleased(KeyEvent e){
	}
	
	
	

}
