package vista;

import misc.Observador;
import modelo.BonusAtaque;
import modelo.TanqueHeroe;
import titiritero.Posicionable;
import titiritero.SuperficieDeDibujo;
import titiritero.vista.Animacion;
import titiritero.vista.Imagen;

public class VistaBonusAtaque extends Vista implements Observador {
	private BonusAtaque bonusAtaque;
	

	private Animacion spriteActual;
	

	private final String RUTA_SPRITE = "/sprites/SpriteBonusAtaque.png";
	
	private static final int ALTO_SPRITE = 50;
	private static final int ANCHO_SPRITE = 50;
	
	private static final double FPS_BONUSATAQUE= 5.0;
	
	public VistaBonusAtaque(BonusAtaque bonusAtaque){
		this.bonusAtaque = bonusAtaque;
		bonusAtaque.adscribir(this);
		

		Imagen spriteBonus = new Imagen(RUTA_SPRITE, bonusAtaque);
		
		Imagen subImagen = spriteBonus.getSubimagen( 0 , 0,
				spriteBonus.getAncho(),ALTO_SPRITE);
		
		spriteActual = new Animacion(subImagen,ANCHO_SPRITE,ALTO_SPRITE);
		spriteActual.setFps(FPS_BONUSATAQUE);
		spriteActual.reproducir();

		actualizar();
		
	}
	
	public void dibujar(SuperficieDeDibujo sup) {
		spriteActual.dibujar(sup);
	}

	public Posicionable getPosicionable() {
		return this.bonusAtaque;
	}

	public void setPosicionable(Posicionable bonus) {
		this.bonusAtaque = (BonusAtaque) bonus;
		spriteActual.setPosicionable(bonus);

	}

	public void setBonusAtaque(BonusAtaque bonusAtaque) {
		setPosicionable(bonusAtaque);
	}

	public BonusAtaque getBonusAtaque() {
		return this.bonusAtaque;
	}

	@Override
	public void actualizar() {
		
		if (this.bonusAtaque.estaBorrado()) {
			VistaEscenario.getInstancia().borrarVista(this);
		}


	}
	
}
