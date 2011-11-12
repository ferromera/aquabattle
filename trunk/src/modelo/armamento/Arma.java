package modelo.armamento;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import modelo.Tanque;

import utils.Direccion;



public abstract class Arma implements ActionListener  {
	private Tanque tanque;
	private boolean cargada;
	protected int tiempoCarga;
	protected int cantidadDeBalas;
	
	public boolean equals(Arma a){
		return (getClass().equals(a.getClass()));
	}
	
	public void disparar(){
		
		 if(!cargada)	 
			return;
		descargar();
		Bala bala= crearBala();
		posicionarBala(bala);
		if(tieneMunicion())
			destruir();
		
	}
	public void posicionarBala(Bala bala){
		double xBala= tanque.getX();
		double yBala= tanque.getY();
		switch(tanque.getOrientacion().get()){
		case Direccion.NORTE:
				yBala-= bala.getAlto() + 1;
				//centrada en X
				xBala+= (tanque.getAncho()-bala.getAncho())/2; 
				break;
		case Direccion.SUR:
				yBala+= tanque.getAlto() + 1;
				//centrada en X
				xBala+= (tanque.getAncho()-bala.getAncho())/2; 
				break;
		case Direccion.ESTE:
				xBala+= tanque.getAncho() + 1;
				//centrada en Y
				yBala+= (tanque.getAlto()-bala.getAlto())/2; 
				break;
		case Direccion.OESTE:
				xBala-= bala.getAncho() + 1;
				//centrada en Y
				yBala+= (tanque.getAlto()-bala.getAlto())/2;
				break;
		}
		bala.setX(xBala);
		bala.setY(yBala);
	}
	/*
	 * Debe crear la instancia de bala correspondiente
	 * y actualizar la municion del arma.
	 */
	public abstract Bala crearBala();
	
	public abstract boolean tieneMunicion();
	
	public void destruir(){
		if(tanque!=null)
			tanque.quitarArma();
		tanque=null;
	}	
	private void descargar(){
		cargada=false;
		Timer timer = new Timer(tiempoCarga, this);
		timer.start();
	}
	public void actionPerformed(ActionEvent e){
		cargada=true;
	}
	public void setTanque(Tanque tanque){
		this.tanque=tanque;
	}
	public Tanque getTanque(){
		return tanque;
	}
	
	
}
