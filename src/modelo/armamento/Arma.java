package modelo.armamento;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import modelo.Tanque;

import utils.Direccion;



public abstract class Arma implements ActionListener  {
	private Tanque tanque;
	private boolean cargada=true;
	protected int tiempoCarga;
	protected int cantidadDeBalas;
	private Timer timer;
	
	public boolean equals(Arma a){
		return (getClass().equals(a.getClass()));
	}
	
	public void mejorarTiempoCarga(double porcentaje){
		tiempoCarga*=(1+porcentaje);
	}
	public void empeorarTiempoCarga(double porcentaje){
		tiempoCarga*=1-porcentaje;
	}
	
	public void disparar(){
		
		 if(!cargada)	 
			return;
		
		descargar();
		Bala bala= crearBala();
		posicionarBala(bala);
		
		
	}
	private void posicionarBala(Bala bala){
		double xBala= tanque.getX();
		double yBala= tanque.getY();
		switch(tanque.getOrientacion().get()){
		case Direccion.NORTE:
				yBala-= bala.getAlto() + 1;
				//centrada en X
				xBala+= (tanque.getAncho()-bala.getAncho())/2;
				bala.setOrientacion(Direccion.Norte());
				break;
		case Direccion.SUR:
				yBala+= tanque.getAlto() + 1;
				//centrada en X
				xBala+= (tanque.getAncho()-bala.getAncho())/2; 
				bala.setOrientacion(Direccion.Sur());
				break;
		case Direccion.ESTE:
				xBala+= tanque.getAncho() + 1;
				//centrada en Y
				yBala+= (tanque.getAlto()-bala.getAlto())/2; 
				bala.setOrientacion(Direccion.Este());
				break;
		case Direccion.OESTE:
				xBala-= bala.getAncho() + 1;
				//centrada en Y
				yBala+= (tanque.getAlto()-bala.getAlto())/2;
				bala.setOrientacion(Direccion.Oeste());
				break;
		}
		bala.setX(xBala);
		bala.setY(yBala);
	}
	/*
	 * Debe crear la instancia de bala correspondiente
	 * y actualizar la municion del arma.
	 */
	protected abstract Bala crearBala();
	
	public abstract boolean tieneMunicion();
	
	private void destruir(){
		if(tanque!=null)
			tanque.quitarArma();
		tanque=null;
	}	
	private void descargar(){
		cargada=false;
		timer = new Timer(tiempoCarga, this);
		timer.start();
	}
	public void actionPerformed(ActionEvent e){
		cargada=true;
		 if(!tieneMunicion()){
				destruir();
		 }
		 timer.stop();
		
	}
	public void setTanque(Tanque tanque){
		this.tanque=tanque;
		tanque.agregarArma(this);
	}
	public Tanque getTanque(){
		return tanque;
	}
	
	
}
