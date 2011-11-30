package vista;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import misc.ContadorDeInstancias;
import misc.DiccionarioDeSerializables;
import misc.Observador;
import modelo.ArmaTiradaCanion;
import titiritero.Posicionable;
import titiritero.SuperficieDeDibujo;
import titiritero.vista.Animacion;
import titiritero.vista.Imagen;

public class VistaArmaTiradaCanion extends Vista implements Observador {
	private ArmaTiradaCanion armaTiradaCanion;
	private long id = ContadorDeInstancias.getId();

	private Animacion spriteActual;

	private static final String RUTA_SPRITE = "/sprites/SpriteBonusArmaCanion.png";

	private static final int ALTO_SPRITE = 50;
	private static final int ANCHO_SPRITE = 50;

	private static final double FPS_NORMAL_BONUS = 5.0;
	public static final String TAG = "objeto-vista-arma-tirada-canion";
	private static final String TAG_ARMA_CANION = "arma-canion";

	public VistaArmaTiradaCanion() {

	}

	public VistaArmaTiradaCanion(ArmaTiradaCanion armaCanion) {
		this.armaTiradaCanion = armaCanion;
		armaCanion.adscribir(this);

		Imagen spriteBonus = new Imagen(RUTA_SPRITE, armaCanion);

		Imagen subImagen = spriteBonus.getSubimagen(0, 0,
				spriteBonus.getAncho(), ALTO_SPRITE);

		spriteActual = new Animacion(subImagen, ANCHO_SPRITE, ALTO_SPRITE);
		spriteActual.setFps(FPS_NORMAL_BONUS);

		actualizar();

	}


	public void dibujar(SuperficieDeDibujo sup) {
		spriteActual.dibujar(sup);
	}

	public Posicionable getPosicionable() {
		return this.armaTiradaCanion;
	}

	public void setPosicionable(Posicionable bonus) {
		this.armaTiradaCanion = (ArmaTiradaCanion) bonus;
		spriteActual.setPosicionable(bonus);

	}

	public void setBonusArmaCanion(ArmaTiradaCanion bonusArmaCanion) {
		setPosicionable(bonusArmaCanion);
	}

	public ArmaTiradaCanion getBonusArmaCanion() {
		return this.armaTiradaCanion;
	}

	@Override
	public void actualizar() {
		spriteActual.reproducir();
		if (this.armaTiradaCanion.estaBorrado()) {
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

		elem = doc.createElement(TAG_ARMA_CANION);
		element.appendChild(elem);
		elem.appendChild(armaTiradaCanion.getElementoXML(doc));

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
				if (elem.getTagName().equals(TAG_ARMA_CANION)){
					NodeList nodes=elem.getChildNodes();
					int j;
					for(j=0;j<nodes.getLength();j++)
						if (nodes.item(j).getNodeType() == Node.ELEMENT_NODE)
							break;
					armaTiradaCanion=(ArmaTiradaCanion) DiccionarioDeSerializables.getInstancia((Element)nodes.item(j));
				}
				
			}
		}
		armaTiradaCanion.adscribir(this);
		Imagen spriteBonus = new Imagen(RUTA_SPRITE, armaTiradaCanion);

		Imagen subImagen = spriteBonus.getSubimagen(0, 0,
				spriteBonus.getAncho(), ALTO_SPRITE);

		spriteActual = new Animacion(subImagen, ANCHO_SPRITE, ALTO_SPRITE);
		spriteActual.setFps(FPS_NORMAL_BONUS);

		actualizar();

	}
}
