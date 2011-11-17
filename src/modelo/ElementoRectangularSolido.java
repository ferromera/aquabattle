package modelo;


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
			if(superpuestoCon(solido)){
				elemColisionado=solido;
				return true;
			}
		}
		return false;
	}
	public ElementoRectangularSolido getSolidoVistoSur(){
		Iterator<ElementoRectangularSolido> it=Escenario.getActual().getSolidos();
		ElementoRectangularSolido solido = null,solidoVisto = null;
		while(it.hasNext()){
			solido=it.next();
			if(estaViendoSur(solido))
				if(solidoVisto!=null && solidoVisto.getY()>solido.getY())
					solidoVisto=solido;
		}
		return solido;
	}
	public boolean estaViendoSur(ElementoRectangularSolido solido){
		if(getY()>=solido.getY())
			return false;
		if(getX()>solido.getX()+solido.getAncho())
			return false;
		if(getX()+getAncho() < solido.getX())
			return false;
		return true;
		
	}
	public ElementoRectangularSolido getSolidoVistoNorte(){
		Iterator<ElementoRectangularSolido> it=Escenario.getActual().getSolidos();
		ElementoRectangularSolido solido = null,solidoVisto = null;
		while(it.hasNext()){
			solido=it.next();
			if(estaViendoNorte(solido))
				if(solidoVisto!=null && solidoVisto.getY()<solido.getY())
					solidoVisto=solido;
		}
		return solido;
	}
	public boolean estaViendoNorte(ElementoRectangularSolido solido){
		if(getY()<=solido.getY())
			return false;
		if(getX()>solido.getX()+solido.getAncho())
			return false;
		if(getX()+getAncho() < solido.getX())
			return false;
		return true;
		
	}
	public ElementoRectangularSolido getSolidoVistoEste(){
		Iterator<ElementoRectangularSolido> it=Escenario.getActual().getSolidos();
		ElementoRectangularSolido solido = null,solidoVisto = null;
		while(it.hasNext()){
			solido=it.next();
			if(estaViendoEste(solido))
				if(solidoVisto!=null && solidoVisto.getX()>solido.getX())
					solidoVisto=solido;
		}
		return solido;
	}
	public boolean estaViendoEste(ElementoRectangularSolido solido){
		if(getX()>=solido.getX())
			return false;
		if(getY()>solido.getY()+solido.getAlto())
			return false;
		if(getY()+getAlto() < solido.getY())
			return false;
		return true;
		
	}
	public ElementoRectangularSolido getSolidoVistoOeste(){
		Iterator<ElementoRectangularSolido> it=Escenario.getActual().getSolidos();
		ElementoRectangularSolido solido = null,solidoVisto = null;
		while(it.hasNext()){
			solido=it.next();
			if(estaViendoOeste(solido))
				if(solidoVisto!=null && solidoVisto.getX()<solido.getX())
					solidoVisto=solido;
		}
		return solido;
	}
	public boolean estaViendoOeste(ElementoRectangularSolido solido){
		if(getX()<=solido.getX())
			return false;
		if(getY()>solido.getY()+solido.getAlto())
			return false;
		if(getY()+getAlto() < solido.getY())
			return false;
		return true;
		
	}
}
