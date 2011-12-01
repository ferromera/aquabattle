package vista;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


import misc.ContadorDeInstancias;
import misc.DiccionarioDeSerializables;
import misc.Observador;
import modelo.ArmaTiradaLanzaCohetes;
import titiritero.Posicionable;
import titiritero.SuperficieDeDibujo;
import titiritero.vista.Animacion;
import titiritero.vista.Imagen;

public class VistaArmaTiradaLanzaCohetes extends Vista implements Observador {
	private ArmaTiradaLanzaCohetes armaTiradaLanzaCohetes;
	private long id = ContadorDeInstancias.getId();

	private Animacion spriteActual;

	private final String RUTA_SPRITE = "/sprites/SpriteBonusArmaLanzaCohetes.png";

	private static final int ALTO_SPRITE = 50;
	private static final int ANCHO_SPRITE = 50;

	private static final double FPS_NORMAL_BONUS = 5.0;
	public static final String TAG = "objeto-vista-arma-tirada-lanzacohetes";
	private static final String TAG_ARMA_LANZACOHETES = "arma-lanzacohetes";

	public VistaArmaTiradaLanzaCohetes() {

	}

	public VistaArmaTiradaLanzaCohetes(ArmaTiradaLanzaCohetes armaLanzaCohetes) {
		this.armaTiradaLanzaCohetes = armaLanzaCohetes;
		armaTiradaLanzaCohetes.adscribir(this);

		Imagen spriteBonus = new Imagen(RUTA_SPRITE, armaTiradaLanzaCohetes);

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
		return this.armaTiradaLanzaCohetes;
	}

	public void setPosicionable(Posicionable bonus) {
		this.armaTiradaLanzaCohetes = (ArmaTiradaLanzaCohetes) bonus;
		spriteActual.setPosicionable(bonus);

	}

	public void setBonusArmaLanzaCohetes(
			ArmaTiradaLanzaCohetes bonusArmaLanzaCohetes) {
		setPosicionable(bonusArmaLanzaCohetes);
	}

	public ArmaTiradaLanzaCohetes getBonusArmaLanzaCohetes() {
		return this.armaTiradaLanzaCohetes;
	}

	@Override
	public void actualizar() {
		spriteActual.reproducir();
		if (this.armaTiradaLanzaCohetes.estaBorrado()) {
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

		elem = doc.createElement(TAG_ARMA_LANZACOHETES);
		element.appendChild(elem);
		elem.appendChild(armaTiradaLanzaCohetes.getElementoXML(doc));

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
				if (elem.getTagName().equals(TAG_ARMA_LANZACOHETES)){
					NodeList nodes=elem.getChildNodes();
					int j;
					for(j=0;j<nodes.getLength();j++)
						if (nodes.item(j).getNodeType() == Node.ELEMENT_NODE)
							break;
					armaTiradaLanzaCohetes=(ArmaTiradaLanzaCohetes) DiccionarioDeSerializables.getInstancia((Element)nodes.item(j));
				}
			}
		}
		armaTiradaLanzaCohetes.adscribir(this);
		Imagen spriteBonus = new Imagen(RUTA_SPRITE, armaTiradaLanzaCohetes);

		Imagen subImagen = spriteBonus.getSubimagen(0, 0,
				spriteBonus.getAncho(), ALTO_SPRITE);

		spriteActual = new Animacion(subImagen, ANCHO_SPRITE, ALTO_SPRITE);
		spriteActual.setFps(FPS_NORMAL_BONUS);

		actualizar();

	}

}
