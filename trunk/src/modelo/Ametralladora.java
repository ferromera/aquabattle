package modelo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import utils.Direccion;
import javax.swing.Timer;

public class Ametralladora implements ActionListener {
	private Tanque tanque;
	private boolean cargada;
	private final int TIEMPO_CARGA=1000;
	public Ametralladora(Tanque tanque){
		this.tanque=tanque;
		cargada=true;
	}
	public void diparar(){
		/* Falta FabricaElementos !!!
		 if(!cargada)
		 
			return;
		descargar();
		BalaAmetralladora bala= FabricaElementos.crearBalaAmetralladora();
		int xBala= tanque.getX();
		int yBala= tanque.getY();
		switch(tanque.getOrientacion().get()){
		case Direccion.NORTE:
				yBala-= bala.getAlto() + 1;
				break;
		case Direccion.SUR:
				yBala+= tanque.getAlto() + 1;
				break;
		case Direccion.ESTE:
				xBala+= tanque.getAncho() + 1;
				break;
		case Direccion.OESTE:
				xBala-= bala.getAncho() + 1;
				break;
		}
		bala.setX(xBala);
		bala.setY(yBala);
		*/
	}
	private void descargar(){
		cargada=false;
		Timer timer = new Timer(TIEMPO_CARGA, this);
		timer.start();
	}
	public void actionPerformed(ActionEvent e){
		cargada=true;
	}
	
}
