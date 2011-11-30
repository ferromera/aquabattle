package pantallas;

import java.util.ArrayList;
import java.util.Iterator;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import excepciones.NoPudoLeerXMLExeption;

import titiritero.Posicionable;
import misc.ContadorDeInstancias;
import misc.DiccionarioDeSerializables;
import misc.Observable;
import misc.Observador;
import misc.SerializableXML;

public class Vida implements Observable, Posicionable, SerializableXML {
	private long id = ContadorDeInstancias.getId();
	private static final int ANCHO = 15;
	private static final int ALTO = 15;
	private static final String TAG_X = "x";
	private static final String TAG_Y = "y";
	private static final String TAG_BORRADA = "borrada";
	private static final String TAG_OBSERVADORES = "observadores";
	public static final String TAG = "objeto-vida";

	private int x, y;
	private boolean borrada;
	private ArrayList<Observador> observadores = new ArrayList<Observador>();

	public Vida() {

	}

	public Vida(int x, int y) {
		this.x = x;
		this.y = y;
		borrada = false;
	}

	public int getAncho() {
		return ANCHO;
	}

	public int getAlto() {
		return ALTO;
	}

	@Override
	public double getX() {
		return x;
	}

	@Override
	public double getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean estaBorrada() {
		return borrada;
	}

	public void borrar() {
		borrada = true;
		notificar();
	}

	public void adscribir(Observador observador) {
		if (!observadores.contains(observador))
			observadores.add(observador);
	}

	public void quitar(Observador observador) {
		observadores.remove(observador);
	}

	public void notificar() {
		Iterator<Observador> it = observadores.iterator();
		while (it.hasNext()) {
			it.next().actualizar();
		}
	}


	@Override
	public Element getElementoXML(Document doc) {
		Element element = doc.createElement(TAG);
		Element eId = doc.createElement(ContadorDeInstancias.TAG_ID);
		element.appendChild(eId);
		eId.setTextContent(Long.toString(id));
		if (DiccionarioDeSerializables.fueSerializado(id))
			return element;
		DiccionarioDeSerializables.marcarSerializado(id);

		Element eX = doc.createElement(TAG_X);
		element.appendChild(eX);
		eX.setTextContent(Integer.toString(x));
		Element eY = doc.createElement(TAG_Y);
		element.appendChild(eY);
		eY.setTextContent(Integer.toString(y));
		Element eBorrada = doc.createElement(TAG_BORRADA);
		element.appendChild(eBorrada);
		eBorrada.setTextContent(Boolean.toString(borrada));
		Iterator<Observador> it = observadores.iterator();
		while (it.hasNext()) {
			Element eObs = doc.createElement(TAG_OBSERVADORES);
			element.appendChild(eObs);
			eObs.appendChild(it.next().getElementoXML(doc));
		}
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
				if (elem.getTagName().equals(TAG_X))
					x = Integer.parseInt(elem.getTextContent());
				else if (elem.getTagName().equals(TAG_Y))
					y = Integer.parseInt(elem.getTextContent());
				else if (elem.getTagName().equals(TAG_BORRADA))
					borrada = Boolean.parseBoolean(elem.getTextContent());
				else if (elem.getTagName().equals(TAG_OBSERVADORES)){
					NodeList nodes=elem.getChildNodes();
					int j;
					for(j=0;j<nodes.getLength();j++)
						if (nodes.item(j).getNodeType() == Node.ELEMENT_NODE)
							break;
					observadores.add((Observador) DiccionarioDeSerializables.getInstancia((Element)nodes.item(j)));
				}
			}
		}

	}
}
