package modelo;

import titiritero.ObjetoVivo;
import titiritero.Posicionable;
import utils.Direccion;

public abstract class Bala extends ElementoRectangularSolido implements ObjetoVivo, Posicionable{
	Direccion orientacion;
	boolean destruida;
	
	public Bala(){
		setX(0);
		setY(0);
		destruida=false;
		Direccion dir= new Direccion();
		dir.setNorte();
		setOrientacion(dir);
	}

	public abstract void vivir();
	public void avanzar(){
		avanzar(1);
	}
	public void avanzar(int pixels){
		switch(orientacion.get()){
		case Direccion.NORTE:
			avanzarNorte();
			break;
		case Direccion.SUR:
			avanzarSur();
			break;
		case Direccion.ESTE:
			avanzarEste();
			break;
		case Direccion.OESTE:
			avanzarOeste();
			break;
		}
	}
	
	public void setOrientacion(Direccion dir){
		orientacion=dir;
		switch(orientacion.get()){
		case Direccion.NORTE:
			moverNorte();
			break;
		case Direccion.SUR:
			moverSur();
			break;
		case Direccion.ESTE:
			moverEste();
			break;
		case Direccion.OESTE:
			moverOeste();
			break;
		}
	}
	private void moverNorte(){
		if(!orientacion.esNorte()){
			orientacion.setNorte();
			notificar();
		}
	}
	private void moverSur(){
		if(!orientacion.esSur()){
			orientacion.setSur();
			notificar();
		}
	}
	public void recibirImpacto(int fuerza){
		destruir();
	}
	public void destruir(){
		/*FabricaElementos.crearExplosion(posX,posY);
		escenario= Escenario.getActual();
		escenario.borrar(this);
		destruida=true;
		notificar();*/
	}
	public boolean estaDestruida(){
		return destruida;
	}
	private void moverOeste(){
		if(!orientacion.esOeste()){
			orientacion.setOeste();
			notificar();
		}
	}
	private void moverEste(){
		if(!orientacion.esEste()){
			orientacion.setEste();
			notificar();
		}
	}
	public Direccion getOrientacion(){
		return orientacion;
	}
}
