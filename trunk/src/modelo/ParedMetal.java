package modelo;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import pantallas.Pantalla;
import pantallas.Vida;

import excepciones.NoPudoLeerXMLExeption;

import titiritero.Posicionable;
import vista.pantallas.VistaPantallaJuego;
import misc.ContadorDeInstancias;
import misc.DiccionarioDeSerializables;
import misc.Nivel;
import misc.Observable;

public class ParedMetal extends Pared implements Posicionable, Observable {
	private long id = ContadorDeInstancias.getId();

	public static final String TAG = "objeto-pared-metal";
	private static final String TAG_DESTRUIDA = "destruida";
	private static final String TAG_IMPACTOS = "impactos";
	private static final int ALTO = 20;
	private static final int ANCHO = 20;
	private int impactosRecibidos;
	private boolean destruida = false;

	public ParedMetal() {

	}

	public ParedMetal(double posicionEnX, double posicionEnY) {
		super(posicionEnX, posicionEnY);
		setAlto(ALTO); // Poner la medida que se va a usar
		setAncho(ANCHO);
		this.impactosRecibidos = 0;
	}

	

	public void recibirImpacto(int fuerza) {
		this.impactosRecibidos++;
		// Solo resiste dos impacto
		if (impactosRecibidos > 1) {
			Escenario escenarioActual = Escenario.getActual();
			escenarioActual.borrarSolido(this);
			destruida = true;
		}
		notificar();
	}

	public boolean estaDestruida() {
		return destruida;
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

		elem = doc.createElement(TAG_IMPACTOS);
		element.appendChild(elem);
		elem.setTextContent(Integer.toString(impactosRecibidos));

		elem = doc.createElement(TAG_DESTRUIDA);
		element.appendChild(elem);
		elem.setTextContent(Boolean.toString(destruida));

		return element;
	}

	@Override
	public void fromElementoXML(Element element) {
		super.fromElementoXML((Element) element.getElementsByTagName(Pared.TAG)
				.item(0));

		NodeList hijos;
		Element elem;
		hijos = element.getChildNodes();
		if (hijos != null && hijos.getLength() > 0) {
			for (int i = 0; i < hijos.getLength(); i++) {
				if (hijos.item(i).getNodeType() != Node.ELEMENT_NODE)
					continue;
				elem = (Element) hijos.item(i);
				if (elem.getTagName().equals(TAG_IMPACTOS))
					impactosRecibidos = Integer.parseInt(elem.getTextContent());
				else if (elem.getTagName().equals(TAG_DESTRUIDA))
					destruida = Boolean.parseBoolean(elem.getTextContent());
			}
		}notificar();

	}

}