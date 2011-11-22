package vista;

import misc.Observador;
import modelo.armamento.BalaCanion;
import modelo.armamento.Cohete;
import titiritero.ControladorJuego;
import titiritero.Dibujable;
import titiritero.Posicionable;
import titiritero.SuperficieDeDibujo;
import titiritero.vista.Imagen;
import utils.Direccion;

public class VistaCohete extends Vista implements Observador {
	private Cohete bala;
	private Imagen sprite;
	private static final int ORDEN=8;
	
	
	
	private final String RUTA_SPRITE = "/sprites/SpriteCohete.png"; 
	
	public VistaCohete(Cohete bala) {
		this.bala = bala;
		bala.adscribir(this);
		sprite = new Imagen(RUTA_SPRITE, bala);
		orden=ORDEN;
		actualizar();
		
	}

	public void dibujar(SuperficieDeDibujo sup) {
		sprite.dibujar(sup);
		System.out.println("Dibujado cohete");
	}

	public Posicionable getPosicionable() {
		return bala;
	}

	public void setPosicionable(Posicionable bala) {
		this.bala = (Cohete) bala;
		sprite.setPosicionable(bala);
	}

	public void setBala(Cohete bala) {
		setPosicionable(bala);
	}

	public Cohete getBala() {
		return bala;
	}

	@Override
	public void actualizar() {
		if(bala.estaDestruida()){
			//TODO: 
			//borrarVista
			ControladorJuego.getInstancia().removerDibujable(this);
		}
		else
			actualizarOrientacion();
	}

	private void actualizarOrientacion(){
			switch(bala.getOrientacion().get()){
			case Direccion.NORTE:
				sprite.orientarArriba();
				break;
			case Direccion.SUR:
				sprite.orientarAbajo();
				break;
			case Direccion.ESTE:
				sprite.orientarDerecha();
				break;
			case Direccion.OESTE:
				sprite.orientarIzquierda();
				break;
				
			}
	}
}
