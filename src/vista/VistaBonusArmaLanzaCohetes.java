package vista;

import misc.Observador;
import modelo.BonusArmaLanzaCohetes;
import modelo.TanqueHeroe;
import titiritero.Posicionable;
import titiritero.SuperficieDeDibujo;
import titiritero.vista.Animacion;
import titiritero.vista.Imagen;

public class VistaBonusArmaLanzaCohetes extends Vista implements Observador {
	private BonusArmaLanzaCohetes bonusArmaLanzaCohetes;
	

	private Animacion spriteActual;
	

	private final String RUTA_SPRITE = "/sprites/SpriteBonusArmaLanzaCohetes.png";
	
	private static final int ALTO_SPRITE = 50;
	private static final int ANCHO_SPRITE = 50;
	
	private static final double FPS_NORMAL_BONUS= 5.0;
	
	public VistaBonusArmaLanzaCohetes(BonusArmaLanzaCohetes bonusArmaLanzaCohetes){
		this.bonusArmaLanzaCohetes = bonusArmaLanzaCohetes;
		bonusArmaLanzaCohetes.adscribir(this);
		

		Imagen spriteBonus = new Imagen(RUTA_SPRITE, bonusArmaLanzaCohetes);
		
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
		return this.bonusArmaLanzaCohetes;
	}

	public void setPosicionable(Posicionable bonus) {
		this.bonusArmaLanzaCohetes = (BonusArmaLanzaCohetes) bonus;
		spriteActual.setPosicionable(bonus);

	}

	public void setBonusArmaLanzaCohetes(BonusArmaLanzaCohetes bonusArmaLanzaCohetes) {
		setPosicionable(bonusArmaLanzaCohetes);
	}

	public BonusArmaLanzaCohetes getBonusArmaLanzaCohetes() {
		return this.bonusArmaLanzaCohetes;
	}

	@Override
	public void actualizar() {
		spriteActual.reproducir();
		TanqueHeroe tanque = TanqueHeroe.getInstancia();
		if (this.bonusArmaLanzaCohetes.superpuestoCon(tanque)) {
			
			//Destruir Bonus
		}


	}

}
