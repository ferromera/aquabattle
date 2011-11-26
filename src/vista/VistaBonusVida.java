package vista;

import misc.Observador;
import modelo.BonusVida;
import modelo.TanqueHeroe;
import titiritero.Posicionable;
import titiritero.SuperficieDeDibujo;
import titiritero.vista.Animacion;
import titiritero.vista.Imagen;

public class VistaBonusVida extends Vista implements Observador {
	private BonusVida bonusVida;
	

	private Animacion spriteActual;
	

	private final String RUTA_SPRITE = "/sprites/SpriteBonusVida.png";
	
	private static final int ALTO_SPRITE = 50;
	private static final int ANCHO_SPRITE = 50;
	
	private static final double FPS_BONUSVIDA = 5.0;
	
	public VistaBonusVida(BonusVida bonusVida){
		this.bonusVida = bonusVida;
		bonusVida.adscribir(this);
		

		Imagen spriteBonus = new Imagen(RUTA_SPRITE, bonusVida);
		
		Imagen subImagen = spriteBonus.getSubimagen( 0 , 0,
				spriteBonus.getAncho(),ALTO_SPRITE);
		
		spriteActual = new Animacion(subImagen,ANCHO_SPRITE,ALTO_SPRITE);
		spriteActual.setFps(FPS_BONUSVIDA);
		spriteActual.reproducir();
		
		spriteActual.reproducir();
		actualizar();
		
	}
	
	public void dibujar(SuperficieDeDibujo sup) {
		spriteActual.dibujar(sup);
	}

	public Posicionable getPosicionable() {
		return this.bonusVida;
	}

	public void setPosicionable(Posicionable bonus) {
		this.bonusVida = (BonusVida) bonus;
		spriteActual.setPosicionable(bonus);

	}

	public void setBonusVida(BonusVida bonusVida) {
		setPosicionable(bonusVida);
	}

	public BonusVida getBonusVida() {
		return this.bonusVida;
	}

	@Override
	public void actualizar() {
		if (this.bonusVida.estaBorrado()) {
			VistaEscenario.getInstancia().borrarVista(this);
		}


	}
	
	
}
