package modelo;

import java.util.ArrayList;
import java.util.Iterator;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import misc.ContadorDeInstancias;
import misc.DiccionarioDeSerializables;
import misc.Observable;
import misc.Observador;
import misc.SerializableXML;
import titiritero.Posicionable;
import utils.Direccion;

public class ElementoRectangular implements Posicionable, Observable,
		SerializableXML {
	private long id = ContadorDeInstancias.getId();

	private static final String TAG_POS_Y = "posicion-y";
	private static final String TAG_POS_X = "posicion-x";
	private static final String TAG_ALTO = "alto";
	private static final String TAG_ANCHO = "ancho";
	public static final String TAG = "objeto-elemento-rectangular";
	private static final String TAG_ORIENTACION = "orientacion";
	private static final String TAG_OBSERVADORES = "observadores";

	private double posX;
	private double posY;
	private double alto;
	private double ancho;
	private ArrayList<Observador> observadores;
	private Direccion orientacion;

	public ElementoRectangular() {
		posX = 0.0;
		posY = 0.0;
		alto = 30.0;
		ancho = 30.0;
		observadores = new ArrayList<Observador>();
		orientacion = Direccion.Norte();
	}

	public ElementoRectangular(double x, double y) {
		posX = x;
		posY = y;
		alto = 30;
		ancho = 30;
		observadores = new ArrayList<Observador>();
		orientacion = Direccion.Norte();
	}

	public ElementoRectangular(double x, double y, double alto, double ancho) {

		posX = x;
		posY = y;
		this.alto = alto;
		this.ancho = ancho;
		observadores = new ArrayList<Observador>();
		orientacion = Direccion.Norte();
	}

	public double getX() {
		return posX;
	}

	public double getY() {
		return posY;
	}

	public void setX(double x) {
		posX = x;
	}

	public void setY(double y) {
		posY = y;
	}

	public double getAlto() {
		return alto;
	}

	public double getAncho() {
		return ancho;
	}

	public void setAlto(double alto) {
		this.alto = alto;
	}

	public void setAncho(double ancho) {
		this.ancho = ancho;
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

	public boolean superpuestoCon(ElementoRectangular rect) {
		if (rect == this)
			return false;
		if (posX < rect.posX + rect.ancho && posY < rect.posY + rect.alto
				&& posX + ancho > rect.posX && posY + alto > rect.posY)
			return true;
		return false;
	}

	public void avanzarEste(double dx) {
		posX += dx;
	}

	public void avanzarEste() {
		posX++;
	}

	public void avanzarOeste(double dx) {
		posX -= dx;
	}

	public void avanzarOeste() {
		posX--;
	}

	public void avanzarNorte(double dy) {
		posY -= dy;
	}

	public void avanzarNorte() {
		posY--;
	}

	public void avanzarSur(double dy) {
		posY += dy;
	}

	public void avanzarSur() {
		posY++;
	}

	public void avanzar() {
		avanzar(1);
	}

	public void avanzar(double espacio) {
		switch (orientacion.get()) {
		case Direccion.NORTE:
			avanzarNorte(espacio);
			break;
		case Direccion.SUR:
			avanzarSur(espacio);
			break;
		case Direccion.ESTE:
			avanzarEste(espacio);
			break;
		case Direccion.OESTE:
			avanzarOeste(espacio);
			break;
		}
	}

	public void avanzarEnDireccion(Direccion dir) {
		avanzarEnDireccion(dir, 1);
	}

	public void avanzarEnDireccion(Direccion dir, double espacio) {
		switch (dir.get()) {
		case Direccion.NORTE:
			avanzarNorte(espacio);
			break;
		case Direccion.SUR:
			avanzarSur(espacio);
			break;
		case Direccion.ESTE:
			avanzarEste(espacio);
			break;
		case Direccion.OESTE:
			avanzarOeste(espacio);
			break;
		}
	}

	public void retroceder() {
		retroceder(1);
	}

	public void retroceder(double espacio) {
		switch (orientacion.get()) {
		case Direccion.NORTE:
			avanzarSur(espacio);
			break;
		case Direccion.SUR:
			avanzarNorte(espacio);
			break;
		case Direccion.ESTE:
			avanzarOeste(espacio);
			break;
		case Direccion.OESTE:
			avanzarEste(espacio);
			break;
		}
	}

	public void retrocederEnDireccion(Direccion dir) {
		retrocederEnDireccion(dir, 1);
	}

	public void retrocederEnDireccion(Direccion dir, double espacio) {
		switch (dir.get()) {
		case Direccion.NORTE:
			avanzarSur(espacio);
			break;
		case Direccion.SUR:
			avanzarNorte(espacio);
			break;
		case Direccion.ESTE:
			avanzarOeste(espacio);
			break;
		case Direccion.OESTE:
			avanzarEste(espacio);
			break;
		}
	}

	public void setOrientacion(Direccion dir) {
		switch (dir.get()) {
		case Direccion.NORTE:
			orientarNorte();
			break;
		case Direccion.SUR:
			orientarSur();
			break;
		case Direccion.ESTE:
			orientarEste();
			break;
		case Direccion.OESTE:
			orientarOeste();
			break;
		}
	}

	public void orientarNorte() {
		if (!orientacion.esNorte()) {
			orientacion.setNorte();
			notificar();
		}
	}

	public void orientarSur() {
		if (!orientacion.esSur()) {
			orientacion.setSur();
			notificar();
		}
	}

	public void orientarOeste() {
		if (!orientacion.esOeste()) {
			orientacion.setOeste();
			notificar();
		}
	}

	public void orientarEste() {
		if (!orientacion.esEste()) {
			orientacion.setEste();
			notificar();
		}
	}

	public Direccion getOrientacion() {
		return orientacion;
	}

	public boolean fueraDeEscenario() {

		Escenario escenario = Escenario.getActual();

		if (posX < escenario.getX()
				|| posX + getAncho() > escenario.getX() + escenario.getAncho()
				|| posY < escenario.getY()
				|| posY + getAlto() > escenario.getY() + escenario.getAlto())
			return true;

		return false;
	}

	public boolean estaSuperpuesto() {

		Escenario escenario = Escenario.getActual();
		Iterator<ElementoRectangular> it = escenario.getObjetos();
		ElementoRectangular elem;
		while (it.hasNext()) {
			elem = it.next();
			if (superpuestoCon(elem)) {
				return true;
			}
		}
		return false;
	}

	public double getCentroX() {
		return posX + ancho / 2.0;
	}

	public double getCentroY() {
		return posY + alto / 2.0;
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
		elem = doc.createElement(TAG_POS_X);
		element.appendChild(elem);
		elem.setTextContent(Double.toHexString(posX));

		elem = doc.createElement(TAG_POS_Y);
		element.appendChild(elem);
		elem.setTextContent(Double.toHexString(posY));

		Iterator<Observador> it = observadores.iterator();
		while (it.hasNext()) {
			elem = doc.createElement(TAG_OBSERVADORES);
			element.appendChild(elem);
			elem.appendChild(it.next().getElementoXML(doc));
		}
		elem = doc.createElement(TAG_ALTO);
		element.appendChild(elem);
		elem.setTextContent(Double.toString(alto));

		elem = doc.createElement(TAG_ANCHO);
		element.appendChild(elem);
		elem.setTextContent(Double.toString(alto));

		elem = doc.createElement(TAG_ORIENTACION);
		element.appendChild(elem);
		elem.appendChild(orientacion.getElementoXML(doc));

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
				if (elem.getTagName().equals(TAG_POS_X))
					posX = Double.parseDouble(elem.getTextContent());
				else if (elem.getTagName().equals(TAG_POS_Y))
					posY = Double.parseDouble(elem.getTextContent());
				else if (elem.getTagName().equals(TAG_OBSERVADORES)){
					NodeList nodes=elem.getChildNodes();
					int j;
					for(j=0;j<nodes.getLength();j++)
						if (nodes.item(j).getNodeType() == Node.ELEMENT_NODE)
							break;
					observadores.add((Observador) DiccionarioDeSerializables.getInstancia((Element)nodes.item(j)));
				}
				else if (elem.getTagName().equals(TAG_ALTO))
					alto = Double.parseDouble(elem.getTextContent());
				else if (elem.getTagName().equals(TAG_ANCHO))
					ancho = Double.parseDouble(elem.getTextContent());
				else if (elem.getTagName().equals(TAG_ORIENTACION)){
					NodeList nodes=elem.getChildNodes();
					int j;
					for(j=0;j<nodes.getLength();j++)
						if (nodes.item(j).getNodeType() == Node.ELEMENT_NODE)
							break;
					orientacion=(Direccion)DiccionarioDeSerializables.getInstancia((Element)nodes.item(j));
				}

			}
		}

	}

}
