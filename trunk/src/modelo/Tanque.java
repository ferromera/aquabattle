package modelo;

import titiritero.ObjetoVivo;

public abstract class Tanque extends ElementoRectangularSolido implements ObjetoVivo{
	private Arma arma;
	private int vida;
	private boolean moviendose;
	
	public Tanque(Arma arma){
		
		moviendose=false;
		orientarNorte();
		vida=100;
		this.arma=arma;
	}
	public abstract void vivir();
	public void disparar(){
		arma.disparar();
	}
	public void moverNorte(){
		orientarNorte();
		moviendose=true;
	}
	public void moverSur(){
		orientarNorte();
		moviendose=true;
	}
	public void moverOeste(){
		orientarNorte();
		moviendose=true;
	}
	public void moverEste(){
		orientarNorte();
		moviendose=true;
	}
	public void detener(){
		moviendose=false;
	}
	protected void setVida(int vida){
		this.vida=vida;
	}
	public int getVida(){
		return vida;
	}
	public boolean enMovimiento(){
		return moviendose;
	}
	public void cambiarArma(Arma armaNueva){
		arma=armaNueva;
	}
	
}
