package vista;

import misc.Observador;
import modelo.ArmaTiradaLanzaCohetes;
import modelo.TanqueHeroe;
import titiritero.Posicionable;
import titiritero.SuperficieDeDibujo;
import titiritero.vista.Animacion;
import titiritero.vista.Imagen;

public class VistaArmaTiradaLanzaCohetes extends Vista implements Observador {
	private ArmaTiradaLanzaCohetes ArmaTiradaLanzaCohetes;
	

	private Animacion spriteActual;
	

	private final String RUTA_SPRITE = "/sprites/SpriteBonusArmaLanzaCohetes.png";
	
	private static final int ALTO_SPRITE = 50;
	private static final int ANCHO_SPRITE = 50;
	
	private static final double FPS_NORMAL_BONUS= 5.0;
	
	public VistaArmaTiradaLanzaCohetes(ArmaTiradaLanzaCohetes ArmaLanzaCohetes){
		this.ArmaTiradaLanzaCohetes = ArmaLanzaCohetes;
		ArmaLanzaCohetes.adscribir(this);
		

		Imagen spriteBonus = new Imagen(RUTA_SPRITE, ArmaLanzaCohetes);
		
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
		return this.ArmaTiradaLanzaCohetes;
	}

	public void setPosicionable(Posicionable bonus) {
		this.ArmaTiradaLanzaCohetes = (ArmaTiradaLanzaCohetes) bonus;
		spriteActual.setPosicionable(bonus);

	}

	public void setBonusArmaLanzaCohetes(ArmaTiradaLanzaCohetes bonusArmaLanzaCohetes) {
		setPosicionable(bonusArmaLanzaCohetes);
	}

	public ArmaTiradaLanzaCohetes getBonusArmaLanzaCohetes() {
		return this.ArmaTiradaLanzaCohetes;
	}

	@Override
	public void actualizar() {
		spriteActual.reproducir();
		TanqueHeroe tanque = TanqueHeroe.getInstancia();
		if (this.ArmaTiradaLanzaCohetes.superpuestoCon(tanque)) {
			
			//Destruir Bonus
		}


	}

}
