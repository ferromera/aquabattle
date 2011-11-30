package vista;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import misc.ContadorDeInstancias;
import misc.DiccionarioDeSerializables;
import misc.Observador;
import modelo.Base;
import titiritero.Posicionable;
import titiritero.SuperficieDeDibujo;
import titiritero.vista.Imagen;

public class VistaBase extends Vista implements Observador {
	public static final String TAG = "objeto-vista-base";

	private static final int ORDEN = 3;

	private static final String TAG_BASE = "base";

	private long id = ContadorDeInstancias.getId();

	private Base base;
	private Imagen sprite;

	private final String RUTA_SPRITE_BaseNormal = "/sprites/SpriteBaseNormal.png";
	private final String RUTA_SPRITE_BaseConDisparo = "/sprites/SpriteBaseConDisparo.png";
	private final String RUTA_SPRITE_BaseDestruida = "/sprites/SpriteBaseDestruida.png";

	public VistaBase() {

	}

	public VistaBase(Base base) {
		this.base = base;
		orden=ORDEN;
		base.adscribir(this);
		sprite = new Imagen(RUTA_SPRITE_BaseNormal, base);

		actualizar();
	}


	public void dibujar(SuperficieDeDibujo sup) {
		sprite.dibujar(sup);
	}

	public Posicionable getPosicionable() {
		return base;
	}

	public void setPosicionable(Posicionable basePos) {
		this.base = (Base) basePos;
		sprite.setPosicionable(basePos);
	}

	public void setBase(Base base) {
		setPosicionable(base);
	}

	public Base getBase() {
		return base;
	}

	@Override
	public void actualizar() {
		if (base.impactosRecibidos() == 1) {
			Imagen nuevaImagen = new Imagen(RUTA_SPRITE_BaseConDisparo, base);
			sprite = nuevaImagen;
		} else if (base.impactosRecibidos() > 1) {
			Imagen nuevaImagen = new Imagen(RUTA_SPRITE_BaseDestruida, base);
			sprite = nuevaImagen;
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

		elem = doc.createElement(TAG_BASE);
		element.appendChild(elem);
		elem.appendChild(base.getElementoXML(doc));

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
				if (elem.getTagName().equals(TAG_BASE)){
					NodeList nodes=elem.getChildNodes();
					int j;
					for(j=0;j<nodes.getLength();j++)
						if (nodes.item(j).getNodeType() == Node.ELEMENT_NODE)
							break;
					base=(Base) DiccionarioDeSerializables.getInstancia((Element)nodes.item(j));
				}
			}
		}
		base.adscribir(this);
		sprite = new Imagen(RUTA_SPRITE_BaseNormal, base);

		actualizar();

	}
}
