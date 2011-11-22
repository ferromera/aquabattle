package vista;

import misc.Observador;

import modelo.armamento.BalaCanion;
import titiritero.ControladorJuego;
import titiritero.Dibujable;
import titiritero.Posicionable;
import titiritero.SuperficieDeDibujo;
import titiritero.vista.Imagen;
import utils.Direccion;

public class VistaBalaCanion extends Vista implements Observador {
	private BalaCanion bala;
	private Imagen sprite;
	private static final int ORDEN=4;
	
	
	
	private final String RUTA_SPRITE = "/sprites/SpriteBalaCanion2.png"; 
	
	public VistaBalaCanion(BalaCanion bala) {
		this.bala = bala;
		bala.adscribir(this);
		sprite = new Imagen(RUTA_SPRITE, bala);
		orden=ORDEN;
		actualizar();
		
	}

	public void dibujar(SuperficieDeDibujo sup) {
		sprite.dibujar(sup);
		System.out.println("Dibujado canion");
	}

	public Posicionable getPosicionable() {
		return bala;
	}

	public void setPosicionable(Posicionable bala) {
		this.bala = (BalaCanion) bala;
		sprite.setPosicionable(bala);
	}

	public void setBala(BalaCanion bala) {
		setPosicionable(bala);
	}

	public BalaCanion getBala() {
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
