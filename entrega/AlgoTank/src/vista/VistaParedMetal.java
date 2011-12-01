package vista;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import misc.ContadorDeInstancias;
import misc.DiccionarioDeSerializables;
import misc.Observador;
import modelo.ParedMetal;
import titiritero.Posicionable;
import titiritero.SuperficieDeDibujo;
import titiritero.vista.Imagen;

public class VistaParedMetal extends Vista implements Observador {
	public static final String TAG = "objeto-vista-pared-metal";

	private static final String TAG_PARED = "pared";

	private static final int ORDEN = 1;

	private long id = ContadorDeInstancias.getId();

	private ParedMetal pared;
	private Imagen sprite;

	private final String RUTA_SPRITE_ParedMetalNormal = "/sprites/SpriteParedMetalNormal1.png";
	private final String RUTA_SPRITE_ParedMetalConDisparo = "/sprites/SpriteParedMetalConDisparo.png";
	private final String RUTA_SPRITE_ParedMetalDestruida = "/sprites/SpriteParedMetalDestruida.png";

	public VistaParedMetal(ParedMetal paredMetal) {
		this.pared = paredMetal;
		paredMetal.adscribir(this);
		sprite = new Imagen(RUTA_SPRITE_ParedMetalNormal, paredMetal);
		orden = ORDEN;
		actualizar();

	}

	public VistaParedMetal() {

	}

	public void dibujar(SuperficieDeDibujo sup) {
		sprite.dibujar(sup);
	}

	public Posicionable getPosicionable() {
		return this.pared;
	}

	public void setPosicionable(Posicionable paredPos) {
		this.pared = (ParedMetal) paredPos;
		sprite.setPosicionable(paredPos);
	}

	public void setParedConcreto(ParedMetal pared) {
		setPosicionable(pared);
	}

	public ParedMetal getParedMetal() {
		return pared;
	}

	@Override
	public void actualizar() {
		if (pared.impactosRecibidos() == 1) {
			Imagen nuevaImagen = new Imagen(RUTA_SPRITE_ParedMetalConDisparo,
					pared);
			sprite = nuevaImagen;

		} else if (pared.impactosRecibidos() > 1) {
			Imagen nuevaImagen = new Imagen(RUTA_SPRITE_ParedMetalDestruida,
					pared);
			sprite = nuevaImagen;
			// Borrarla del escenario
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

		elem = doc.createElement(TAG_PARED);
		element.appendChild(elem);
		elem.appendChild(pared.getElementoXML(doc));

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
				if (elem.getTagName().equals(TAG_PARED)){
					NodeList nodes=elem.getChildNodes();
					int j;
					for(j=0;j<nodes.getLength();j++)
						if (nodes.item(j).getNodeType() == Node.ELEMENT_NODE)
							break;
					pared=(ParedMetal) DiccionarioDeSerializables.getInstancia((Element)nodes.item(j));
				}
				
			}
		}
		pared.adscribir(this);
		sprite = new Imagen(RUTA_SPRITE_ParedMetalNormal, pared);
		orden = ORDEN;
		actualizar();

	}

}
