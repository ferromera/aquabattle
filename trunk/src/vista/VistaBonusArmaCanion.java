package vista;

import misc.Observador;
import modelo.BonusArmaCanion;
import modelo.TanqueHeroe;
import titiritero.Posicionable;
import titiritero.SuperficieDeDibujo;
import titiritero.vista.Animacion;
import titiritero.vista.Imagen;

public class VistaBonusArmaCanion extends Vista implements Observador {
	private BonusArmaCanion bonusArmaCanion;
	

	private Animacion spriteActual;
	

	private final String RUTA_SPRITE = "/sprites/SpriteBonusArmaCanion.png";
	
	private static final int ALTO_SPRITE = 50;
	private static final int ANCHO_SPRITE = 50;
	
	private static final double FPS_NORMAL_BONUS= 5.0;
	
	public VistaBonusArmaCanion(BonusArmaCanion bonusArmaCanion){
		this.bonusArmaCanion = bonusArmaCanion;
		bonusArmaCanion.adscribir(this);
		

		Imagen spriteBonus = new Imagen(RUTA_SPRITE, bonusArmaCanion);
		
		Imagen subImagen = spriteBonus.getSubimagen( 0 , 0,
				spriteBonus.getAncho(),ALTO_SPRITE);
		
		spriteActual = new Animacion(subImagen,ANCHO_SPRITE,ALTO_SPRITE);
		spriteActual.setFps(FPS_NORMAL_BONUS);
		

		actualizar();
		
	}
	
	public void dibujar(SuperficieDeDibujo sup) {
		spriteActual.dibujar(sup);
	}

	public Posicionable getPosicionable() {
		return this.bonusArmaCanion;
	}

	public void setPosicionable(Posicionable bonus) {
		this.bonusArmaCanion = (BonusArmaCanion) bonus;
		spriteActual.setPosicionable(bonus);

	}

	public void setBonusArmaCanion(BonusArmaCanion bonusArmaCanion) {
		setPosicionable(bonusArmaCanion);
	}

	public BonusArmaCanion getBonusArmaCanion() {
		return this.bonusArmaCanion;
	}

	@Override
	public void actualizar() {
		spriteActual.reproducir();
		TanqueHeroe tanque = TanqueHeroe.getInstancia();
		if (this.bonusArmaCanion.superpuestoCon(tanque)) {
			
			//Destruir Bonus
		}


	}
	

}
