package vista;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import excepciones.NoPudoLeerXMLExeption;

import misc.ContadorDeInstancias;
import misc.DiccionarioDeSerializables;
import misc.Observador;
import modelo.BonusAtaque;
import modelo.TanqueHeroe;
import modelo.armamento.BalaCanion;
import titiritero.Posicionable;
import titiritero.SuperficieDeDibujo;
import titiritero.vista.Animacion;
import titiritero.vista.Imagen;

public class VistaBonusAtaque extends Vista implements Observador {
	private long id = ContadorDeInstancias.getId();

	private BonusAtaque bonusAtaque;

	private Animacion spriteActual;

	private final String RUTA_SPRITE = "/sprites/SpriteBonusAtaque.png";

	private static final int ALTO_SPRITE = 50;
	private static final int ANCHO_SPRITE = 50;

	private static final double FPS_BONUSATAQUE = 5.0;

	public static final String TAG = "objeto-vista-bonus-ataque";

	private static final String TAG_BONUS_ATAQUE = "bonus-ataque";

	public VistaBonusAtaque(BonusAtaque bonusAtaque) {
		this.bonusAtaque = bonusAtaque;
		bonusAtaque.adscribir(this);

		Imagen spriteBonus = new Imagen(RUTA_SPRITE, bonusAtaque);

		Imagen subImagen = spriteBonus.getSubimagen(0, 0,
				spriteBonus.getAncho(), ALTO_SPRITE);

		spriteActual = new Animacion(subImagen, ANCHO_SPRITE, ALTO_SPRITE);
		spriteActual.setFps(FPS_BONUSATAQUE);
		spriteActual.reproducir();

		actualizar();

	}

	public VistaBonusAtaque(Element element) throws NoPudoLeerXMLExeption {
		NodeList hijos;
		Element elem;
		hijos = element.getChildNodes();
		if (hijos != null && hijos.getLength() > 0) {
			for (int i = 0; i < hijos.getLength(); i++) {
				elem = (Element) hijos.item(i);
				if (elem.getTagName().equals(TAG_BONUS_ATAQUE))
					bonusAtaque = (BonusAtaque) DiccionarioDeSerializables
							.getInstancia((Element) elem.getFirstChild());
			}
		}
		bonusAtaque.adscribir(this);
		Imagen spriteBonus = new Imagen(RUTA_SPRITE, bonusAtaque);

		Imagen subImagen = spriteBonus.getSubimagen(0, 0,
				spriteBonus.getAncho(), ALTO_SPRITE);

		spriteActual = new Animacion(subImagen, ANCHO_SPRITE, ALTO_SPRITE);
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
