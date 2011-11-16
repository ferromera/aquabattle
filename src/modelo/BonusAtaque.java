package modelo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import titiritero.ObjetoVivo;
import titiritero.Posicionable;

public class BonusAtaque extends ElementoRectangularIntangible implements
ObjetoVivo, Posicionable, ActionListener {
	
	public BonusAtaque(){
		setX(0.0);
		setY(0.0);
	}
	
	public BonusAtaque(double posicionEnX, double posicionEnY){
		setX(posicionEnX);
		setY(posicionEnY);
	}
	
	public void vivir(){
		boolean estaEnContacto = false;
		Escenario escenario = Escenario.getActual();
		Iterator<ElementoRectangular> it = escenario.getObjetos();
		ElementoRectangular elem;
		
		while(!estaEnContacto && it.hasNext()){
			elem = it.next();
			if ((this.superpuestoCon(elem)) && (elem instanceof Tanque)) {
				estaEnContacto = true;
				((Tanque)elem).mejorarVelocidad(20.0);
				((Tanque)elem).mejorarVelocidadDisparo(30.0);
				Escenario.getActual().borrarObjeto(this);
			}
		}
	}
	
	public void actionPerformed(ActionEvent e){
		Escenario.getActual().borrarObjeto(this);
	}
	
	

}
