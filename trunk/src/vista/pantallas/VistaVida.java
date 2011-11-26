package vista.pantallas;

import pantallas.Vida;
import misc.Observador;
import titiritero.Posicionable;
import titiritero.SuperficieDeDibujo;
import titiritero.vista.Imagen;
import vista.Vista;

public class VistaVida extends Vista implements Observador {
	private Vida vida;
	

	private Imagen sprite;
	

	private final String RUTA_SPRITE = "/sprites/SpriteVida.png";
	
	private static final int ALTO_SPRITE = 15;
	private static final int ANCHO_SPRITE = 15;
	
	
	public VistaVida(Vida vida){
		this.vida = vida;
		vida.adscribir(this);
		

		sprite = new Imagen(RUTA_SPRITE, vida);

		actualizar();
		
	}
	
	public void dibujar(SuperficieDeDibujo sup) {
		sprite.dibujar(sup);
	}

	public Posicionable getPosicionable() {
		return this.vida;
	}

	public void setPosicionable(Posicionable vida) {
		this.vida = (Vida) vida;
		sprite.setPosicionable(vida);

	}

	public void setVida(Vida vida) {
		setPosicionable(vida);
	}

	public Vida getVida() {
		return this.vida;
	}

	@Override
	public void actualizar() {
		if (this.vida.estaBorrada()) {
			VistaPantallaJuego.getInstancia().borrarVistaVida(this);
		}

	}
	

}
