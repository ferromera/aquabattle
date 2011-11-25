package vista;

import misc.Observador;
import modelo.Explosion;
import modelo.armamento.BalaAmetralladora;
import titiritero.Posicionable;
import titiritero.SuperficieDeDibujo;
import titiritero.vista.Imagen;
import utils.Direccion;

public class VistaExplosion extends Vista implements Observador {
	private Explosion explosion;
	private Imagen sprite;
	private static final int ORDEN=4;
	
	
	private final String RUTA_SPRITE = "/sprites/explosion.png"; 
	
	public VistaExplosion(Explosion explosion) {
		this.explosion = explosion;
		explosion.adscribir(this);
		sprite = new Imagen(RUTA_SPRITE, explosion);
		orden=ORDEN;
		actualizar();
		
	}

	public void dibujar(SuperficieDeDibujo sup) {
		sprite.dibujar(sup);
	}

	public Posicionable getPosicionable() {
		return explosion;
	}

	public void setPosicionable(Posicionable explosion) {
		this.explosion = (Explosion) explosion;
		sprite.setPosicionable(explosion);
	}

	public void setExplosion(Explosion explosion) {
		setPosicionable(explosion);
	}

	public Explosion getExplosion() {
		return explosion;
	}

	@Override
	public void actualizar() {
		if(explosion.estaDestruida()){
			VistaEscenario.getInstancia().borrarVista(this);
		}
	}




}
