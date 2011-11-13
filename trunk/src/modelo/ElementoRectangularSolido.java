package modelo;

import java.util.ArrayList;
import java.util.Iterator;

import excepciones.NoExisteElementoColisionadoException;

/*
 * Representa todos los elementos del modelo que pueden chocar
 * entre ellos.
 */
public abstract class ElementoRectangularSolido extends ElementoRectangular implements Impactable {
	public abstract void recibirImpacto(int fuerza);
	public abstract int getResistencia();
	
	private ElementoRectangularSolido elemColisionado=null;
	
	public ElementoRectangularSolido getColisionado() throws NoExisteElementoColisionadoException{
		if(!estaColisionado())
			throw new NoExisteElementoColisionadoException();
		return elemColisionado;
	}
	
	public boolean estaColisionado(){
		Escenario escenario = Escenario.getActual();
		Iterator<ElementoRectangularSolido> it= escenario.getSolidos();
		while(it.hasNext()){
			ElementoRectangularSolido solido= it.next();
			if( solido.equals(this))
				continue;
			if(superpuestoCon(solido)){
				elemColisionado=solido;
				return true;
			}
		}
		return false;
	}
}
