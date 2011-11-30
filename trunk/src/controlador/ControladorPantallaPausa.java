package controlador;

import java.awt.event.KeyEvent;
import pantallas.PantallaPausa;
import titiritero.KeyPressedObservador;

public class ControladorPantallaPausa implements KeyPressedObservador{
	
	PantallaPausa pantalla;
	private static ControladorPantallaPausa instancia=null;
	
	public ControladorPantallaPausa (){
		pantalla = PantallaPausa.getInstancia();
	}
	
	public static ControladorPantallaPausa getInstancia(){
		if(instancia == null)
			instancia = new ControladorPantallaPausa();
		return instancia;
	}
	
	
	public void keyPressed(KeyEvent e){
		int key= e.getKeyCode();
		 if(key==KeyEvent.VK_DOWN)
			 pantalla.seleccionarSiguienteBoton();	 
	     if(key==KeyEvent.VK_UP)
	    	 pantalla.seleccionarBotonAnterior();
	     if(key==KeyEvent.VK_ENTER)
	    	 pantalla.presionarBoton();

	}
	
	public void keyReleased(KeyEvent e){
	}
	
}
