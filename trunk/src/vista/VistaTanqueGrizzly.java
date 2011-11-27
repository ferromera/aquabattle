package vista;

import misc.Observador;
import modelo.TanqueGrizzly;
import titiritero.Posicionable;
import titiritero.SuperficieDeDibujo;
import titiritero.vista.Animacion;
import titiritero.vista.Imagen;
import utils.Direccion;

public class VistaTanqueGrizzly extends VistaTanque implements Observador {
	
	private static final int ORDEN=3;
	

	private final String RUTA_SPRITE = "/sprites/SpriteGrizzly.png";
	
	private static final int ALTO_SPRITE = 50;
	private static final int ANCHO_SPRITE = 50;


	// FPS DE CADA SPRITE
	private static final double FPS = 25.0;

	public VistaTanqueGrizzly(TanqueGrizzly tanque) {
		super(tanque);

		orden=ORDEN;
		tanque.adscribir(this);
		
		spriteActual=new Animacion(new Imagen(RUTA_SPRITE, tanque),ANCHO_SPRITE,ALTO_SPRITE);
		spriteActual.setFps(FPS);
		
		actualizar();
	}


	public Posicionable getPosicionable() {
		return tanque;
	}

	public void setPosicionable(Posicionable tanque) {
		this.tanque = (TanqueGrizzly) tanque;
		spriteActual.setPosicionable(tanque);
	}

	public void setTanque(TanqueGrizzly tanque) {
		setPosicionable(tanque);
	}

	public TanqueGrizzly getTanque() {
		return (TanqueGrizzly) tanque;
	}

	@Override
	public void actualizar() {
		spriteActual.detener();
		if(tanque.estaDestruido()){
			VistaEscenario.getInstancia().borrarVista(this);
		}
		if(tanque.enMovimiento())
			spriteActual.reproducir();
		else
			spriteActual.detener();
	
		actualizarOrientacion();
	}

	private void actualizarOrientacion(){
			switch(tanque.getOrientacion().get()){
			case Direccion.NORTE:
				spriteActual.orientarArriba();
				break;
			case Direccion.SUR:
				spriteActual.orientarAbajo();
				break;
			case Direccion.ESTE:
				spriteActual.orientarDerecha();
				break;
			case Direccion.OESTE:
				spriteActual.orientarIzquierda();
				break;
				
			}
	}
}
