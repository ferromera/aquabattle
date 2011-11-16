package modelo;

import java.util.Iterator;

import titiritero.ObjetoVivo;
import titiritero.Posicionable;

public class BonusVida extends ElementoRectangularIntangible implements
ObjetoVivo, Posicionable{
	
	public BonusVida(double posicionEnX, double posicionEnY){
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
				((Tanque)elem).mejorarVida(40.0);
				Escenario.getActual().borrarObjeto(this);
			}
		}
	}
		


}
