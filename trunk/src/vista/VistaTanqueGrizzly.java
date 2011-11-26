package vista;

import misc.Observador;
import modelo.TanqueGrizzly;
import titiritero.Posicionable;
import titiritero.SuperficieDeDibujo;
import titiritero.vista.Animacion;
import titiritero.vista.Imagen;
import utils.Direccion;

public class VistaTanqueGrizzly extends Vista implements Observador {
private TanqueGrizzly tanque;
	
	private static final int ORDEN=3;
	
	private Animacion sprite;

	private final String RUTA_SPRITE = "/sprites/SpriteGrizzly.png";
	
	private static final int ALTO_SPRITE = 50;
	private static final int ANCHO_SPRITE = 50;


	// FPS DE CADA SPRITE
	private static final double FPS = 25.0;

	public VistaTanqueGrizzly(TanqueGrizzly tanque) {
		this.tanque = tanque;
		orden=ORDEN;
		tanque.adscribir(this);
		
		sprite=new Animacion(new Imagen(RUTA_SPRITE, tanque),ANCHO_SPRITE,ALTO_SPRITE);
		sprite.setFps(FPS);
		
		actualizar();
	}

	public void dibujar(SuperficieDeDibujo sup) {
		sprite.dibujar(sup);
	}

	public Posicionable getPosicionable() {
		return tanque;
	}

	public void setPosicionable(Posicionable tanque) {
		this.tanque = (TanqueGrizzly) tanque;
		sprite.setPosicionable(tanque);
	}

	public void setTanque(TanqueGrizzly tanque) {
		setPosicionable(tanque);
	}

	public TanqueGrizzly getTanque() {
		return tanque;
	}

	@Override
	public void actualizar() {
		sprite.detener();
		if(tanque.estaDestruido()){
			VistaEscenario.getInstancia().borrarVista(this);
		}
		if(tanque.enMovimiento())
			sprite.reproducir();
		else
			sprite.detener();
	
		actualizarOrientacion();
	}

	private void actualizarOrientacion(){
			switch(tanque.getOrientacion().get()){
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
