package vista;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import misc.ContadorDeInstancias;
import misc.DiccionarioDeSerializables;
import misc.Observador;
import modelo.ParedConcreto;
import titiritero.Posicionable;
import titiritero.SuperficieDeDibujo;
import titiritero.vista.Imagen;

public class VistaParedConcreto extends Vista implements Observador {
	public static final String TAG = "objeto-vista-pared-concreto";

	private static final int ORDEN = 1;

	private static final String TAG_PARED = "pared";

	private long id = ContadorDeInstancias.getId();

	private ParedConcreto pared;
	private Imagen sprite;

	private final String RUTA_SPRITE_ParedConcretoNormal = "/sprites/SpriteParedConcretoNormal.png";
	private final String RUTA_SPRITE_ParedConcretoDestruida = "/sprites/SpriteParedConcretoDestruida.png";

	public VistaParedConcreto(ParedConcreto paredConcreto) {
		this.pared = paredConcreto;
		paredConcreto.adscribir(this);
		sprite = new Imagen(RUTA_SPRITE_ParedConcretoNormal, paredConcreto);
		orden = ORDEN;
		actualizar();

	}

	public VistaParedConcreto() {

	}

	public void dibujar(SuperficieDeDibujo sup) {
		sprite.dibujar(sup);
	}

	public Posicionable getPosicionable() {
		return this.pared;
	}

	public void setPosicionable(Posicionable paredPos) {
		this.pared = (ParedConcreto) paredPos;
		sprite.setPosicionable(paredPos);
	}

	public void setParedConcreto(ParedConcreto pared) {
		setPosicionable(pared);
	}

	public ParedConcreto getParedConcreto() {
		return pared;
	}

	@Override
	public void actualizar() {
		if (pared.estaDestruida()) {
			Imagen nuevaImagen = new Imagen(RUTA_SPRITE_ParedConcretoDestruida,
					pared);
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
					pared=(ParedConcreto) DiccionarioDeSerializables.getInstancia((Element)nodes.item(j));
				}
			}
		}
		pared.adscribir(this);
		sprite = new Imagen(RUTA_SPRITE_ParedConcretoNormal, pared);
		orden = ORDEN;
		actualizar();

	}

}
