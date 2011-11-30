package vista;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import excepciones.NoPudoLeerXMLExeption;

import misc.ContadorDeInstancias;
import misc.DiccionarioDeSerializables;
import misc.Observador;
import modelo.Explosion;
import modelo.armamento.BalaAmetralladora;
import modelo.armamento.Cohete;
import titiritero.Posicionable;
import titiritero.SuperficieDeDibujo;
import titiritero.vista.Imagen;
import utils.Direccion;

public class VistaExplosion extends Vista implements Observador {
	private long id = ContadorDeInstancias.getId();

	private Explosion explosion;
	private Imagen sprite;
	private static final int ORDEN = 4;

	public static final String TAG = "objeto-vista-explosion";

	private static final String TAG_EXPLOSION = "explosion";

	private final String RUTA_SPRITE = "/sprites/explosion.png";

	public VistaExplosion(Explosion explosion) {
		this.explosion = explosion;
		explosion.adscribir(this);
		sprite = new Imagen(RUTA_SPRITE, explosion);
		orden = ORDEN;
		actualizar();

	}

	public VistaExplosion() {

	}


	public void dibujar(SuperficieDeDibujo sup) {
		sprite.dibujar(sup);
	}

	public Posicionable getPosicionable() {
		return explosion;
	}

	public void setPosicionable(Posicionable explosion) {
		this.explosion = (Explosion) explosion;
		sprite.setPosicionable(explosion);
	}

	public void setExplosion(Explosion explosion) {
		setPosicionable(explosion);
	}

	public Explosion getExplosion() {
		return explosion;
	}

	@Override
	public void actualizar() {
		if (explosion.estaDestruida()) {
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

		elem = doc.createElement(TAG_EXPLOSION);
		element.appendChild(elem);
		elem.appendChild(explosion.getElementoXML(doc));

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
				if (elem.getTagName().equals(TAG_EXPLOSION)){
					NodeList nodes=elem.getChildNodes();
					int j;
					for(j=0;j<nodes.getLength();j++)
						if (nodes.item(j).getNodeType() == Node.ELEMENT_NODE)
							break;
					explosion=(Explosion) DiccionarioDeSerializables.getInstancia((Element)nodes.item(j));
				}
					
			}
		}
		explosion.adscribir(this);
		sprite = new Imagen(RUTA_SPRITE, explosion);
		orden = ORDEN;
		actualizar();

	}

}
