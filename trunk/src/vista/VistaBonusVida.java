package vista;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import excepciones.NoPudoLeerXMLExeption;

import misc.ContadorDeInstancias;
import misc.DiccionarioDeSerializables;
import misc.Observador;
import modelo.BonusAtaque;
import modelo.BonusVida;
import modelo.TanqueHeroe;
import titiritero.Posicionable;
import titiritero.SuperficieDeDibujo;
import titiritero.vista.Animacion;
import titiritero.vista.Imagen;

public class VistaBonusVida extends Vista implements Observador {
	private long id=ContadorDeInstancias.getId();
	
	private BonusVida bonusVida;
	

	private Animacion spriteActual;
	

	private final String RUTA_SPRITE = "/sprites/SpriteBonusVida.png";
	
	private static final int ALTO_SPRITE = 50;
	private static final int ANCHO_SPRITE = 50;
	
	private static final double FPS_BONUSVIDA = 5.0;

	public static final String TAG = "objeto-vista-bonus-vida";

	private static final String TAG_BONUS_VIDA = "bonus-vida";
	
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
	
	public VistaBonusVida(Element element) throws NoPudoLeerXMLExeption {
		NodeList hijos;
		Element elem;
		hijos = element.getChildNodes();
		if (hijos != null && hijos.getLength() > 0) {
			for (int i = 0; i < hijos.getLength(); i++) {
				elem = (Element) hijos.item(i);
				if (elem.getTagName().equals(TAG_BONUS_VIDA))
					bonusVida = (BonusVida) DiccionarioDeSerializables
							.getInstancia((Element) elem.getFirstChild());
			}
		}
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
