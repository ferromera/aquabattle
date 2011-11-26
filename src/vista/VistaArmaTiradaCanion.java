package vista;

import misc.Observador;
import modelo.ArmaTiradaCanion;
import modelo.TanqueHeroe;
import titiritero.Posicionable;
import titiritero.SuperficieDeDibujo;
import titiritero.vista.Animacion;
import titiritero.vista.Imagen;

public class VistaArmaTiradaCanion extends Vista implements Observador {
	private ArmaTiradaCanion ArmaTiradaCanion;
	

	private Animacion spriteActual;
	

	private final String RUTA_SPRITE = "/sprites/SpriteBonusArmaCanion.png";
	
	private static final int ALTO_SPRITE = 50;
	private static final int ANCHO_SPRITE = 50;
	
	private static final double FPS_NORMAL_BONUS= 5.0;
	
	public VistaArmaTiradaCanion(ArmaTiradaCanion ArmaCanion){
		this.ArmaTiradaCanion = ArmaCanion;
		ArmaCanion.adscribir(this);
		

		Imagen spriteBonus = new Imagen(RUTA_SPRITE, ArmaCanion);
		
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
		return this.ArmaTiradaCanion;
	}

	public void setPosicionable(Posicionable bonus) {
		this.ArmaTiradaCanion = (ArmaTiradaCanion) bonus;
		spriteActual.setPosicionable(bonus);

	}

	public void setBonusArmaCanion(ArmaTiradaCanion bonusArmaCanion) {
		setPosicionable(bonusArmaCanion);
	}

	public ArmaTiradaCanion getBonusArmaCanion() {
		return this.ArmaTiradaCanion;
	}

	@Override
	public void actualizar() {
		spriteActual.reproducir();
		TanqueHeroe tanque = TanqueHeroe.getInstancia();
		if (this.ArmaTiradaCanion.estaBorrado()) {
			VistaEscenario.getInstancia().borrarVista(this);
		}


	}
	

}
