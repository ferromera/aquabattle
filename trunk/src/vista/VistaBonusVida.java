package vista;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
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
	private long id = ContadorDeInstancias.getId();

	private BonusVida bonusVida;

	private Animacion spriteActual;

	private final String RUTA_SPRITE = "/sprites/SpriteBonusVida.png";

	private static final int ALTO_SPRITE = 50;
	private static final int ANCHO_SPRITE = 50;

	private static final double FPS_BONUSVIDA = 5.0;

	public static final String TAG = "objeto-vista-bonus-vida";

	private static final String TAG_BONUS_VIDA = "bonus-vida";

	public VistaBonusVida() {

	}

	public VistaBonusVida(BonusVida bonusVida) {
		this.bonusVida = bonusVida;
		bonusVida.adscribir(this);

		Imagen spriteBonus = new Imagen(RUTA_SPRITE, bonusVida);

		Imagen subImagen = spriteBonus.getSubimagen(0, 0,
				spriteBonus.getAncho(), ALTO_SPRITE);

		spriteActual = new Animacion(subImagen, ANCHO_SPRITE, ALTO_SPRITE);
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

	@Override
	public Element getElementoXML(Document doc) {
		Element element = doc.createElement(TAG);
		Element elem = doc.createElement(ContadorDeInstancias.TAG_ID);
		element.appendChild(elem);
		elem.setTextContent(Long.toString(id));
		if (DiccionarioDeSerializables.fueSerializado(id))
			return element;
		DiccionarioDeSerializables.marcarSerializado(id);

		elem = doc.createElement(TAG_BONUS_VIDA);
		element.appendChild(elem);
		elem.appendChild(bonusVida.getElementoXML(doc));

		return element;
	}

	@Override
	public void fromElementoXML(Element element) {
		NodeList hijos;
		Element elem;
		hijos = element.getChildNodes();
		if (hijos != null && hijos.getLength() > 0) {
			for (int i = 0; i < hijos.getLength(); i++) {
				if (hijos.item(i).getNodeType() != Node.ELEMENT_NODE)
					continue;
				elem = (Element) hijos.item(i);
				if (elem.getTagName().equals(TAG_BONUS_VIDA)){
					NodeList nodes=elem.getChildNodes();
					int j;
					for(j=0;j<nodes.getLength();j++)
						if (nodes.item(j).getNodeType() == Node.ELEMENT_NODE)
							break;
					bonusVida=(BonusVida) DiccionarioDeSerializables.getInstancia((Element)nodes.item(j));
				}
			}
		}
		bonusVida.adscribir(this);
		Imagen spriteBonus = new Imagen(RUTA_SPRITE, bonusVida);

		Imagen subImagen = spriteBonus.getSubimagen(0, 0,
				spriteBonus.getAncho(), ALTO_SPRITE);

		spriteActual = new Animacion(subImagen, ANCHO_SPRITE, ALTO_SPRITE);
		spriteActual.setFps(FPS_BONUSVIDA);
		spriteActual.reproducir();

		spriteActual.reproducir();
		actualizar();

	}

}
