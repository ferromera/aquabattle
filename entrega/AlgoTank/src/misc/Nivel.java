package misc;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import modelo.Escenario;
import modelo.Flota;
import modelo.TanqueGrizzly;
import modelo.TanqueIFV;
import modelo.TanqueMirage;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import titiritero.ObjetoVivo;

import excepciones.NoPudoLeerXMLExeption;
import excepciones.NoSePudoPosicionarException;
import excepciones.ProbabilidadInvalidaException;
import excepciones.YaExisteBaseException;

public class Nivel implements ObjetoVivo {
	private long id = ContadorDeInstancias.getId();
	private boolean ganado;
	private int puntos;
	private int maxPuntos;
	private String rutaXML;
	private String tagNivel;
	private double xInicial;
	private double yInicial;
	private LinkedList<Flota> flotas;
	private LinkedList<FabricaBonus> fabricasBonus;
	private boolean pausado;

	private static final String TAG_TANQUE_HEROE = "heroe";
	private static final String TAG_PARED = "pared";
	private static final String TAG_FLOTAS = "flotas";
	private static final String TAG_POS_X = "pos-x";
	private static final String TAG_POS_Y = "pos-y";
	private static final String TAG_PARED_METAL = "pared-metal";
	private static final String TAG_PARED_CONCRETO = "pared-concreto";
	private static final String TAG_BONUS = "bonus";
	private static final String TAG_BOUNS_ATAQUE = "bonus-ataque";
	private static final String TAG_BONUS_VIDA = "bonus-vida";
	private static final String TAG_BERNOULLI = "bernoulli";
	private static final String TAG_BASE = "base";
	private static final String TAG_FLOTA = "flota";
	private static final String TAG_GRIZZLY = "grizzly";
	private static final String TAG_IFV = "ifv";
	private static final String TAG_MIRAGE = "mirage";

	public static final String TAG = "objeto-nivel";

	private static final String TAG_GANADO = "ganado";
	private static final String TAG_PUNTOS = "puntos";
	private static final String TAG_PAUSADO = "pausado";
	private static final String TAG_MAX_PUNTOS = "max-puntos";
	private static final String TAG_RUTA_XML = "ruta-xml";
	private static final String TAG_TAG_NIVEL = "tag-nivel";
	private static final String TAG_X_INICIAL = "x-inicial";
	private static final String TAG_Y_INICIAL = "y-inicial";
	private static final String TAG_FABRICAS_BONUS = "fabrica-bonus";

	public Nivel() {

	}

	public Nivel(int numeroDeNivel, String ruta, int puntosNivel) {
		tagNivel = "nivel";
		tagNivel.concat(Integer.toString(numeroDeNivel));
		rutaXML = ruta;
		maxPuntos = puntosNivel;
		flotas = new LinkedList<Flota>();
		fabricasBonus = new LinkedList<FabricaBonus>();
		pausado = true;

	}

	public void sumarPuntos(int puntosGanados) {
		puntos += puntosGanados;
		if (puntos >= maxPuntos)
			ganado = true;
	}

	public void cargar() throws NoPudoLeerXMLExeption {

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		Document docXML = null;
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();

			docXML = db.parse(rutaXML);

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (SAXException se) {
			se.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		Element raiz = docXML.getDocumentElement();
		if (raiz.getTagName().compareTo(tagNivel) == 0)
			throw new NoPudoLeerXMLExeption("La raiz " + raiz.getTagName()
					+ " no coincide con " + tagNivel);

		NodeList nodoTanqueHeroe = raiz.getElementsByTagName(TAG_TANQUE_HEROE);
		if (nodoTanqueHeroe == null)
			throw new NoPudoLeerXMLExeption("No se encontro el tag: "
					+ TAG_TANQUE_HEROE);
		if (nodoTanqueHeroe.getLength() > 1)
			throw new NoPudoLeerXMLExeption("Debe haber un (unico) TanqueHeroe");

		Element elemHeroe = (Element) nodoTanqueHeroe.item(0);
		cargarHeroe(elemHeroe);

		NodeList nodoBase = raiz.getElementsByTagName(TAG_BASE);
		if (nodoBase == null)
			throw new NoPudoLeerXMLExeption("No se encontro el tag: "
					+ TAG_BASE);
		if (nodoBase.getLength() > 1)
			throw new NoPudoLeerXMLExeption("Debe haber una (unica) base");

		Element elemBase = (Element) nodoBase.item(0);
		cargarBase(elemBase);

		NodeList nodosPared = raiz.getElementsByTagName(TAG_PARED);
		if (nodosPared != null)
			for (int i = 0; i < nodosPared.getLength(); i++) {
				Element elemPared = (Element) nodosPared.item(i);
				cargarPared(elemPared);
			}
		NodeList nodosFlotas = raiz.getElementsByTagName(TAG_FLOTAS);
		if (nodosFlotas != null)
			for (int i = 0; i < nodosFlotas.getLength(); i++) {
				Element elemFlotas = (Element) nodosFlotas.item(i);
				cargarFlotas(elemFlotas);
			}
		NodeList nodosBonus = raiz.getElementsByTagName(TAG_BONUS);
		if (nodosBonus != null)
			for (int i = 0; i < nodosBonus.getLength(); i++) {
				Element elemBonus = (Element) nodosBonus.item(i);
				cargarBonus(elemBonus);
			}

	}

	private void cargarBase(Element elemBase) throws NoPudoLeerXMLExeption {
		double x = getPosX(elemBase);
		double y = getPosY(elemBase);
		try {
			FabricaElementos.crearBase(x, y);
		} catch (NoSePudoPosicionarException e) {
			System.err.println("No se pudo posicionar base al cargar "
					+ tagNivel);
			System.err.println(e.getMessage());
			e.printStackTrace();
		} catch (YaExisteBaseException e) {
			e.printStackTrace();
		}

	}

	private void cargarBonus(Element elemBonus) throws NoPudoLeerXMLExeption {
		NodeList nodoBonusVida = elemBonus.getElementsByTagName(TAG_BONUS_VIDA);
		if (nodoBonusVida != null) {
			if (nodoBonusVida.getLength() > 1)
				throw new NoPudoLeerXMLExeption("Debe haber a lo sumo un tag: "
						+ TAG_BONUS_VIDA + " en el nodo "
						+ elemBonus.getTagName());
			if (nodoBonusVida.getLength() == 1) {
				Element elemBonusVida = (Element) nodoBonusVida.item(0);
				cargarBonusVida(elemBonusVida);
			}
		}
		NodeList nodoBonusAtaque = elemBonus
				.getElementsByTagName(TAG_BOUNS_ATAQUE);
		if (nodoBonusAtaque != null) {
			if (nodoBonusAtaque.getLength() > 1)
				throw new NoPudoLeerXMLExeption("Debe haber a lo sumo un tag: "
						+ TAG_BOUNS_ATAQUE + " en el nodo "
						+ elemBonus.getTagName());
			if (nodoBonusAtaque.getLength() == 1) {
				Element elemBonusAtaque = (Element) nodoBonusAtaque.item(0);
				cargarBonusAtaque(elemBonusAtaque);
			}
		}

	}

	private void cargarBonusAtaque(Element elemBonusAtaque)
			throws NoPudoLeerXMLExeption {
		SorteadorBinario sorteador = cargarSorteador(elemBonusAtaque);
		FabricaBonusAtaque fabAtaque = new FabricaBonusAtaque(sorteador);
		fabricasBonus.add(fabAtaque);

	}

	private SorteadorBinario cargarSorteador(Element elem)
			throws NoPudoLeerXMLExeption {
		NodeList nodoBernoulli = elem.getElementsByTagName(TAG_BERNOULLI);
		if (nodoBernoulli != null) {
			if (nodoBernoulli.getLength() > 1)
				throw new NoPudoLeerXMLExeption("Debe haber a lo sumo un tag: "
						+ TAG_BERNOULLI + " en el nodo " + elem.getTagName());
			if (nodoBernoulli.getLength() == 1) {
				Element elemBernoulli = (Element) nodoBernoulli.item(0);
				double probabilidad = Double.parseDouble(elemBernoulli
						.getTextContent());
				try {
					return new SorteadorBernoulli(probabilidad);
				} catch (ProbabilidadInvalidaException e) {
					throw new NoPudoLeerXMLExeption(
							"Probabilidad invalida en el nodo "
									+ elem.getTagName());
				}
			}
		}
		return new SorteadorBernoulli();
	}

	private void cargarBonusVida(Element elemBonusVida)
			throws NoPudoLeerXMLExeption {
		SorteadorBinario sorteador = cargarSorteador(elemBonusVida);
		FabricaBonusVida fabVida = new FabricaBonusVida(sorteador);
		fabricasBonus.add(fabVida);

	}

	private void cargarFlotas(Element elemFlotas) throws NoPudoLeerXMLExeption {
		NodeList nodosFlota = elemFlotas.getElementsByTagName(TAG_FLOTA);
		if (nodosFlota != null)
			for (int i = 0; i < nodosFlota.getLength(); i++) {
				Element elemFlota = (Element) nodosFlota.item(i);
				cargarFlota(elemFlota);
			}
		flotas.peek().agregar();

	}

	private void cargarFlota(Element elemFlota) throws NoPudoLeerXMLExeption {
		NodeList nodo;
		Element elem;
		Flota flota = new Flota();
		nodo = elemFlota.getElementsByTagName(TAG_GRIZZLY);
		if (nodo != null)
			for (int i = 0; i < nodo.getLength(); i++) {
				elem = (Element) nodo.item(i);
				cargarGrizzly(elem, flota);
			}
		nodo = elemFlota.getElementsByTagName(TAG_IFV);
		if (nodo != null)
			for (int i = 0; i < nodo.getLength(); i++) {
				elem = (Element) nodo.item(i);
				cargarIFV(elem, flota);
			}
		nodo = elemFlota.getElementsByTagName(TAG_MIRAGE);
		if (nodo != null)
			for (int i = 0; i < nodo.getLength(); i++) {
				elem = (Element) nodo.item(i);
				cargarMirage(elem, flota);
			}
		flotas.add(flota);

	}

	private void cargarMirage(Element elem, Flota flota)
			throws NoPudoLeerXMLExeption {
		double x = getPosX(elem);
		double y = getPosY(elem);
		flota.agregarTanque(new TanqueMirage(x, y));

	}

	private void cargarIFV(Element elem, Flota flota)
			throws NoPudoLeerXMLExeption {
		double x = getPosX(elem);
		double y = getPosY(elem);
		flota.agregarTanque(new TanqueIFV(x, y));

	}

	private void cargarGrizzly(Element elem, Flota flota)
			throws NoPudoLeerXMLExeption {
		double x = getPosX(elem);
		double y = getPosY(elem);
		flota.agregarTanque(new TanqueGrizzly(x, y));

	}

	private void cargarPared(Element elemPared) throws NoPudoLeerXMLExeption {
		NodeList nodosMetal = elemPared.getElementsByTagName(TAG_PARED_METAL);
		if (nodosMetal != null)
			for (int i = 0; i < nodosMetal.getLength(); i++) {
				Element elemMetal = (Element) nodosMetal.item(i);
				cargarParedMetal(elemMetal);
			}

		NodeList nodosConcreto = elemPared
				.getElementsByTagName(TAG_PARED_CONCRETO);
		if (nodosConcreto != null)
			for (int i = 0; i < nodosConcreto.getLength(); i++) {
				Element elemConcreto = (Element) nodosConcreto.item(i);
				cargarParedConcreto(elemConcreto);
			}
	}

	private void cargarParedMetal(Element elemMetal)
			throws NoPudoLeerXMLExeption {
		double x = getPosX(elemMetal);
		double y = getPosY(elemMetal);

		try {
			FabricaElementos.crearParedMetal(x, y);
		} catch (NoSePudoPosicionarException e) {
			System.err
					.println("No se pudo posicionar pared de metal al cargar "
							+ tagNivel);
			System.err.println(e.getMessage());
			e.printStackTrace();
		}

	}

	private void cargarParedConcreto(Element elemConcreto)
			throws NoPudoLeerXMLExeption {

		double x = getPosX(elemConcreto);
		double y = getPosY(elemConcreto);

		try {
			FabricaElementos.crearParedConcreto(x, y);
		} catch (NoSePudoPosicionarException e) {
			System.err
					.println("No se pudo posicionar pared de concreto al cargar "
							+ tagNivel);
			System.err.println(e.getMessage());
			e.printStackTrace();
		}

	}

	private double getPosY(Element elem) throws NoPudoLeerXMLExeption {

		NodeList nodoY = elem.getElementsByTagName(TAG_POS_Y);
		if (nodoY == null)
			throw new NoPudoLeerXMLExeption("No se encontro tag: " + TAG_POS_Y
					+ " en el nodo: " + elem.getTagName());
		if (nodoY.getLength() != 1)
			throw new NoPudoLeerXMLExeption("Debe haber solo un tag: "
					+ TAG_POS_Y + " en el nodo " + elem.getTagName());
		Element elemY = (Element) nodoY.item(0);
		double y = Double.parseDouble(elemY.getTextContent());
		return y;
	}

	private double getPosX(Element elem) throws NoPudoLeerXMLExeption {

		NodeList nodoX = elem.getElementsByTagName(TAG_POS_X);
		if (nodoX == null)
			throw new NoPudoLeerXMLExeption("No se encontro tag: " + TAG_POS_X
					+ " en el nodo: " + elem.getTagName());
		if (nodoX.getLength() != 1)
			throw new NoPudoLeerXMLExeption("Debe haber solo un tag: "
					+ TAG_POS_X + " en el nodo " + elem.getTagName());
		Element elemX = (Element) nodoX.item(0);
		double x = Double.parseDouble(elemX.getTextContent());
		return x;
	}

	private void cargarHeroe(Element elemHeroe) throws NoPudoLeerXMLExeption {

		xInicial = getPosX(elemHeroe);
		yInicial = getPosY(elemHeroe);

		try {
			FabricaElementos.crearTanqueHeroe(xInicial, yInicial);
		} catch (NoSePudoPosicionarException e) {
			System.err.println("No se pudo posicionar heroe al cargar "
					+ tagNivel);
			System.err.println(e.getMessage());
			e.printStackTrace();
		}

	}

	private void actualizarFlota() {
		if (!flotas.isEmpty())
			if (flotas.peek().estaDestruida()) {
				flotas.poll();
				if (!flotas.isEmpty())
					flotas.peek().agregar();
			}
	}

	public boolean estaGanado() {
		return ganado;
	}

	public double getXInicial() {
		return xInicial;
	}

	public double getYInicial() {
		return xInicial;
	}

	public void pausar() {
		if (pausado)
			return;
		pausado = true;
		Escenario.getActual().pausar();
		Iterator<FabricaBonus> it = fabricasBonus.iterator();
		while (it.hasNext()) {
			it.next().detenerProduccion();
		}
	}

	public void reanudar() {
		if (!pausado)
			return;
		pausado = false;
		Escenario.getActual().reanudar();
		Iterator<FabricaBonus> it = fabricasBonus.iterator();
		while (it.hasNext()) {
			it.next().comenzarProduccion();
		}
	}

	public void vivir() {
		if (pausado)
			return;
		Escenario.getActual().vivir();
		actualizarFlota();
	}

	@Override
	public Element getElementoXML(Document doc) {
		Element element = doc.createElement(TAG);
		Element elem;
		elem = doc.createElement(ContadorDeInstancias.TAG_ID);
		element.appendChild(elem);
		elem.setTextContent(Long.toString(id));
		if (DiccionarioDeSerializables.fueSerializado(id))
			return element;
		DiccionarioDeSerializables.marcarSerializado(id);

		elem = doc.createElement(TAG_GANADO);
		element.appendChild(elem);
		elem.setTextContent(Boolean.toString(ganado));
		elem = doc.createElement(TAG_PUNTOS);

		element.appendChild(elem);
		elem.setTextContent(Integer.toString(puntos));

		elem = doc.createElement(TAG_PAUSADO);
		element.appendChild(elem);
		elem.setTextContent(Boolean.toString(pausado));

		elem = doc.createElement(TAG_MAX_PUNTOS);
		element.appendChild(elem);
		elem.setTextContent(Integer.toString(maxPuntos));

		elem = doc.createElement(TAG_RUTA_XML);
		element.appendChild(elem);
		elem.setTextContent(rutaXML);

		elem = doc.createElement(TAG_TAG_NIVEL);
		element.appendChild(elem);
		elem.setTextContent(tagNivel);

		elem = doc.createElement(TAG_X_INICIAL);
		element.appendChild(elem);
		elem.setTextContent(Double.toString(xInicial));

		elem = doc.createElement(TAG_Y_INICIAL);
		element.appendChild(elem);
		elem.setTextContent(Double.toString(yInicial));

		Iterator<Flota> itFlota = flotas.iterator();
		while (itFlota.hasNext()) {
			elem = doc.createElement(TAG_FLOTAS);
			element.appendChild(elem);
			elem.appendChild(itFlota.next().getElementoXML(doc));
		}
		Iterator<FabricaBonus> itFab = fabricasBonus.iterator();
		while (itFab.hasNext()) {
			elem = doc.createElement(TAG_FABRICAS_BONUS);
			element.appendChild(elem);
			elem.appendChild(itFab.next().getElementoXML(doc));
		}

		return element;

	}

	@Override
	public void fromElementoXML(Element element) {
		flotas = new LinkedList<Flota>();
		fabricasBonus = new LinkedList<FabricaBonus>();
		int maxPuntos_ = 1000;
		NodeList hijos;
		Element elem;
		hijos = element.getChildNodes();
		if (hijos != null && hijos.getLength() > 0) {
			for (int i = 0; i < hijos.getLength(); i++) {
				if (hijos.item(i).getNodeType() != Node.ELEMENT_NODE)
					continue;
				elem = (Element) hijos.item(i);
				if (elem.getTagName().equals(TAG_GANADO))
					ganado = Boolean.parseBoolean(elem.getTextContent());
				else if (elem.getTagName().equals(TAG_PUNTOS))
					puntos = Integer.parseInt(elem.getTextContent());
				else if (elem.getTagName().equals(TAG_PAUSADO))
					pausado = Boolean.parseBoolean(elem.getTextContent());
				else if (elem.getTagName().equals(TAG_MAX_PUNTOS))
					maxPuntos_ = Integer.parseInt(elem.getTextContent());
				else if (elem.getTagName().equals(TAG_RUTA_XML))
					rutaXML = elem.getTextContent();
				else if (elem.getTagName().equals(TAG_TAG_NIVEL))
					tagNivel = elem.getTextContent();
				else if (elem.getTagName().equals(TAG_X_INICIAL))
					xInicial = Double.parseDouble(elem.getTextContent());
				else if (elem.getTagName().equals(TAG_Y_INICIAL))
					yInicial = Double.parseDouble(elem.getTextContent());
				else if (elem.getTagName().equals(TAG_FLOTAS)) {
					NodeList nodes = elem.getChildNodes();
					int j;
					for (j = 0; j < nodes.getLength(); j++)
						if (nodes.item(j).getNodeType() == Node.ELEMENT_NODE)
							break;
					flotas.add((Flota) DiccionarioDeSerializables
							.getInstancia((Element) nodes.item(j)));
				} else if (elem.getTagName().equals(TAG_FABRICAS_BONUS)) {
					NodeList nodes = elem.getChildNodes();
					int j;
					for (j = 0; j < nodes.getLength(); j++)
						if (nodes.item(j).getNodeType() == Node.ELEMENT_NODE)
							break;
					fabricasBonus.add((FabricaBonus) DiccionarioDeSerializables
							.getInstancia((Element) nodes.item(j)));
				}

			}
		}
		maxPuntos = maxPuntos_;

	}

}
