package vista;

import titiritero.ControladorJuego;
import titiritero.Dibujable;
import titiritero.Posicionable;
import titiritero.SuperficieDeDibujo;
import titiritero.vista.Imagen;
import utils.Direccion;
import misc.Observador;
import modelo.armamento.BalaAmetralladora;

public class VistaBalaAmetralladora implements Dibujable,Observador {
	private BalaAmetralladora bala;
	private Imagen sprite;
	
	
	
	private final String RUTA_SPRITE = "/sprites/SpriteBalaAmetralladora.png"; 
	
	public VistaBalaAmetralladora(BalaAmetralladora bala) {
		this.bala = bala;
		bala.adscribir(this);
		sprite = new Imagen(RUTA_SPRITE, bala);
		actualizar();
		
	}

	public void dibujar(SuperficieDeDibujo sup) {
		sprite.dibujar(sup);
	}

	public Posicionable getPosicionable() {
		return bala;
	}

	public void setPosicionable(Posicionable bala) {
		this.bala = (BalaAmetralladora) bala;
		sprite.setPosicionable(bala);
	}

	public void setBala(BalaAmetralladora bala) {
		setPosicionable(bala);
	}

	public BalaAmetralladora getBala() {
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