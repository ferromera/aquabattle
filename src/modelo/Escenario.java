package modelo;

import java.util.*;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import excepciones.NoExisteBaseException;
import excepciones.YaExisteBaseException;

import titiritero.ObjetoVivo;
import titiritero.Posicionable;
import misc.ContadorDeInstancias;
import misc.DiccionarioDeSerializables;
import misc.Observable;
import misc.Observador;

public class Escenario implements ObjetoVivo, Posicionable, Observable {
	private long id = ContadorDeInstancias.getId();

	private static final int ALTO = 720;
	private static final int ANCHO = 960;

	public static final String TAG = "objeto-escenario";
	private static final String TAG_OBJETOS_VIVOS = "objetos-vivos";
	private static final String TAG_OBJETOS_SOLIDOS = "objetos-solidos";
	private static final String TAG_OBSERVADORES = "observadores";
	private static final String TAG_ELEMENTOS = "elementos";
	private static final String TAG_BASE = "base";

	private static Escenario escenarioActual = null;
	private ArrayList<ObjetoVivo> objetosVivos = new ArrayList<ObjetoVivo>();
	private ArrayList<ElementoRectangularSolido> objetosSolidos = new ArrayList<ElementoRectangularSolido>();
	private ArrayList<Observador> observadores = new ArrayList<Observador>();
	private ArrayList<ElementoRectangular> elementos = new ArrayList<ElementoRectangular>();
	private Base base;

	public Escenario() {

	}


	public int getAlto() {
		return ALTO;
	}

	public int getAncho() {
		return ANCHO;
	}

	public double getX() {
		return 0.0;
	}

	public double getY() {
		return 0.0;
	}

	public static Escenario getActual() {
		if (escenarioActual == null) {
			escenarioActual = new Escenario();
		}
		return escenarioActual;
	}

	public static Escenario nuevaInstancia() {
		escenarioActual = new Escenario();
		return escenarioActual;
	}

	public void agregarBase(Base base) throws YaExisteBaseException {
		if (this.base != null)
			throw new YaExisteBaseException("ya existe una base");
		this.base = base;
		agregarObjetoSolido(base);
		agregarObjeto(base);
	}

	public void borrarBase() throws NoExisteBaseException {
		if (this.base == null)
			throw new NoExisteBaseException("no hay una base que borrar");
		borrarSolido(this.base);
		borrarObjeto(this.base);
		this.base = null;

	}

	public boolean tieneBase() {
		return this.base != null;
	}

	public Base getBase() throws NoExisteBaseException {
		if (this.base == null)
			throw new NoExisteBaseException(
					"no hay una base asignada al escenario");
		return this.base;
	}

	public void agregarObjetoVivo(ObjetoVivo objetoAgregar) {
		objetosVivos.add(objetoAgregar);
	}

	public void agregarObjetoSolido(ElementoRectangularSolido objetoAgregar) {
		objetosSolidos.add(objetoAgregar);
	}

	public void agregarObjeto(ElementoRectangular objetoAgregar) {
		elementos.add(objetoAgregar);
	}

	public void borrarObjetoVivo(ObjetoVivo objetoBorrar) {
		objetosVivos.remove(objetoBorrar);
	}

	public void borrarSolido(ElementoRectangularSolido objetoBorrar) {
		objetosSolidos.remove(objetoBorrar);
	}

	public void borrarObjeto(ElementoRectangular objetoBorrar) {
		elementos.remove(objetoBorrar);
	}

	public void vivir() {

		ArrayList<ObjetoVivo> vivos = new ArrayList<ObjetoVivo>(objetosVivos);

		Iterator<ObjetoVivo> iterador = vivos.iterator();

		while (iterador.hasNext()) {
			iterador.next().vivir();
		}

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

	public Iterator<ElementoRectangularSolido> getSolidos() {
		return objetosSolidos.iterator();
	}

	public Iterator<ElementoRectangular> getObjetos() {
		return elementos.iterator();
	}

	public int cantidadActualDeObjetosVivos() {
		return objetosVivos.size();
	}

	public int cantidadActualDeObjetosSolidos() {
		return objetosSolidos.size();
	}

	public void pausar() {
		Iterator<ObjetoVivo> iterador = objetosVivos.iterator();

		while (iterador.hasNext()) {
			iterador.next().pausar();
		}

	}

	public void reanudar() {
		Iterator<ObjetoVivo> iterador = objetosVivos.iterator();

		while (iterador.hasNext()) {
			iterador.next().reanudar();
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
		Iterator<ObjetoVivo> itVivo = objetosVivos.iterator();
		while (itVivo.hasNext()) {
			elem = doc.createElement(TAG_OBJETOS_VIVOS);
			element.appendChild(elem);
			elem.appendChild(itVivo.next().getElementoXML(doc));
		}
		Iterator<ElementoRectangularSolido> itSolido = objetosSolidos
				.iterator();
		while (itSolido.hasNext()) {
			elem = doc.createElement(TAG_OBJETOS_SOLIDOS);
			element.appendChild(elem);
			elem.appendChild(itSolido.next().getElementoXML(doc));
		}
		Iterator<Observador> itObs = observadores.iterator();
		while (itObs.hasNext()) {
			elem = doc.createElement(TAG_OBSERVADORES);
			element.appendChild(elem);
			elem.appendChild(itObs.next().getElementoXML(doc));
		}
		Iterator<ElementoRectangular> itElemento = elementos.iterator();
		while (itElemento.hasNext()) {
			elem = doc.createElement(TAG_ELEMENTOS);
			element.appendChild(elem);
			elem.appendChild(itElemento.next().getElementoXML(doc));
		}
		if (base != null) {
			elem = doc.createElement(TAG_BASE);
			element.appendChild(elem);
			elem.appendChild(base.getElementoXML(doc));
		}

		return element;
	}

	@Override
	public void fromElementoXML(Element element) {
		escenarioActual = this;
		NodeList hijos;
		Element elem;
		hijos = element.getChildNodes();
		if (hijos != null && hijos.getLength() > 0) {
			for (int i = 0; i < hijos.getLength(); i++) {
				if (hijos.item(i).getNodeType() != Node.ELEMENT_NODE)
					continue;
				elem = (Element) hijos.item(i);
				if (elem.getTagName().equals(TAG_OBJETOS_VIVOS)){
					NodeList nodes=elem.getChildNodes();
					int j;
					for(j=0;j<nodes.getLength();j++)
						if (nodes.item(j).getNodeType() == Node.ELEMENT_NODE)
							break;
					objetosVivos.add((ObjetoVivo) DiccionarioDeSerializables.getInstancia((Element)nodes.item(j)));
				}
				else if (elem.getTagName().equals(TAG_OBJETOS_SOLIDOS)){
					NodeList nodes=elem.getChildNodes();
					int j;
					for(j=0;j<nodes.getLength();j++)
						if (nodes.item(j).getNodeType() == Node.ELEMENT_NODE)
							break;
					objetosSolidos.add((ElementoRectangularSolido) DiccionarioDeSerializables.getInstancia((Element)nodes.item(j)));
				}
				else if (elem.getTagName().equals(TAG_OBSERVADORES)){
					NodeList nodes=elem.getChildNodes();
					int j;
					for(j=0;j<nodes.getLength();j++)
						if (nodes.item(j).getNodeType() == Node.ELEMENT_NODE)
							break;
					observadores.add((Observador) DiccionarioDeSerializables.getInstancia((Element)nodes.item(j)));
				}
				else if (elem.getTagName().equals(TAG_ELEMENTOS)){
					NodeList nodes=elem.getChildNodes();
					int j;
					for(j=0;j<nodes.getLength();j++)
						if (nodes.item(j).getNodeType() == Node.ELEMENT_NODE)
							break;
					elementos.add((ElementoRectangular) DiccionarioDeSerializables.getInstancia((Element)nodes.item(j)));
				}
				else if (elem.getTagName().equals(TAG_BASE)){
					NodeList nodes=elem.getChildNodes();
					int j;
					for(j=0;j<nodes.getLength();j++)
						if (nodes.item(j).getNodeType() == Node.ELEMENT_NODE)
							break;
					base=(Base) DiccionarioDeSerializables.getInstancia((Element)nodes.item(j));
				}
					

			}
		}

	}

}
