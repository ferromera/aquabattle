package controlador;

import java.awt.event.KeyEvent;

import modelo.TanqueHeroe;

import titiritero.KeyPressedObservador;
import utils.Direccion;

public class ControladorTanqueHeroe implements KeyPressedObservador{
	private boolean upKey,downKey,rightKey,leftKey;
	TanqueHeroe tanque;
	private boolean spaceKey;
	public ControladorTanqueHeroe(TanqueHeroe tanque){
		this.tanque=tanque;
		upKey=false;
		downKey=false;
		rightKey=false;
		leftKey=false;
		spaceKey=false;
			
	}
	public void keyPressed(KeyEvent e){
		int key= e.getKeyCode();
        if(key==KeyEvent.VK_DOWN)
        	downKey=true;
        if(key==KeyEvent.VK_UP)
        	upKey=true;
        if(key==KeyEvent.VK_RIGHT)
        	rightKey=true;
        if(key==KeyEvent.VK_LEFT)
        	leftKey=true;
        if(key==KeyEvent.VK_SPACE)
        	spaceKey=true;
        actualizarTanque();
	}
	public void keyReleased(KeyEvent e){
		int key= e.getKeyCode();
        if(key==KeyEvent.VK_DOWN)
        	downKey=false;
        if(key==KeyEvent.VK_UP)
        	upKey=false;
        if(key==KeyEvent.VK_RIGHT)
        	rightKey=false;
        if(key==KeyEvent.VK_LEFT)
        	leftKey=false;
        if(key==KeyEvent.VK_SPACE)
    		spaceKey=false;
        actualizarTanque();
	}
	private void actualizarTanque(){
		
		if(downKey)
			tanque.mover(Direccion.Sur());
		else
		if(upKey)
			tanque.mover(Direccion.Norte());
		else
		if(leftKey)
			tanque.mover(Direccion.Oeste());
		else
		if(rightKey)
			tanque.mover(Direccion.Este());
		else
			tanque.detener();
		if(spaceKey)
			tanque.disparar();
			
	}
}
