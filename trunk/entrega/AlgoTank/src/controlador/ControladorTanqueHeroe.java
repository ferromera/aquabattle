package controlador;

import java.awt.event.KeyEvent;

import modelo.TanqueHeroe;

import titiritero.KeyPressedObservador;
import utils.Direccion;

public class ControladorTanqueHeroe implements KeyPressedObservador{
	private boolean norte,sur,este,oeste;
	TanqueHeroe tanque;
	private boolean disparo,sigArma;
	public ControladorTanqueHeroe(TanqueHeroe tanque){
		this.tanque=tanque;
		norte=false;
		sur=false;
		este=false;
		oeste=false;
		disparo=false;
		sigArma=false;
			
	}
	public void keyPressed(KeyEvent e){
		int key= e.getKeyCode();
        if(key==KeyEvent.VK_DOWN)
        	sur=true;
        if(key==KeyEvent.VK_UP)
        	norte=true;
        if(key==KeyEvent.VK_RIGHT)
        	este=true;
        if(key==KeyEvent.VK_LEFT)
        	oeste=true;
        if(key==KeyEvent.VK_SPACE)
        	disparo=true;
        if(key==KeyEvent.VK_C)
        	sigArma=true;
        actualizarTanque();
	}
	public void keyReleased(KeyEvent e){
		int key= e.getKeyCode();
        if(key==KeyEvent.VK_DOWN)
        	sur=false;
        if(key==KeyEvent.VK_UP)
        	norte=false;
        if(key==KeyEvent.VK_RIGHT)
        	este=false;
        if(key==KeyEvent.VK_LEFT)
        	oeste=false;
        if(key==KeyEvent.VK_SPACE)
    		disparo=false;
        if(key==KeyEvent.VK_C)
        	sigArma=false;
        actualizarTanque();
	}
	private void actualizarTanque(){
		
		if(sur)
			tanque.mover(Direccion.Sur());
		else
		if(norte)
			tanque.mover(Direccion.Norte());
		else
		if(oeste)
			tanque.mover(Direccion.Oeste());
		else
		if(este)
			tanque.mover(Direccion.Este());
		else
			tanque.detener();
		if(disparo)
			tanque.disparar();
		if(sigArma)
			tanque.siguienteArma();
			
	}
}
