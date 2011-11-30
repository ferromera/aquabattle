package modelo;

import misc.ContadorDeInstancias;
import misc.DiccionarioDeSerializables;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import pantallas.PantallaJuego;

import excepciones.NoExisteBaseException;
import excepciones.NoPudoLeerXMLExeption;
import titiritero.Posicionable;

public class Base extends ElementoRectangularSolido implements Impactable,
		Posicionable {
	private long id = ContadorDeInstancias.getId();

	public static final String TAG = "objeto-base";

	private static final String TAG_IMPACTOS_RECIBIDOS = "impactos-recibidos";
	private int impactosRecibidos;
	private static final int ALTO = 50;
	private static final int ANCHO = 50;

	public Base() {

	}

	public Base(double posicionX, double posicionY) {
		setX(posicionX);
		setY(posicionY);
		setAlto(ALTO);
		setAncho(ANCHO);
		this.impactosRecibidos = 0;
	}

	

	public void recibirImpacto(int fuerza) {
		this.impactosRecibidos++;
		if (impactosRecibidos > 1) {
			try {
				Escenario.getActual().borrarBase();
				PantallaJuego.getInstancia().perder();
			} catch (NoExisteBaseException e) {
				e.printStackTrace();
			}
		}
		notificar();
	}

	public int getResistencia() {
		return 1;
	}

	public int impactosRecibidos() {
		return impactosRecibidos;
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
		element.appendChild(super.getElementoXML(doc));
		elem = doc.createElement(TAG_IMPACTOS_RECIBIDOS);
		element.appendChild(elem);
		elem.setTextContent(Integer.toString(impactosRecibidos));
		return element;
	}

	@Override
	public void fromElementoXML(Element element) {
		super.fromElementoXML((Element) element.getElementsByTagName(
				ElementoRectangularSolido.TAG).item(0));
		NodeList hijos;
		Element elem;
		hijos = element.getChildNodes();
		if (hijos != null && hijos.getLength() > 0) {
			for (int i = 0; i < hijos.getLength(); i++) {
				if (hijos.item(i).getNodeType() != Node.ELEMENT_NODE)
					continue;
				elem = (Element) hijos.item(i);
				if (elem.getTagName().equals(TAG_IMPACTOS_RECIBIDOS))
					impactosRecibidos = Integer.parseInt(elem.getTextContent());
			}
		}
		notificar();

	}
}
