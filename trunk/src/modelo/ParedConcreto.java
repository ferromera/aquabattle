package modelo;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import excepciones.NoPudoLeerXMLExeption;

import titiritero.Posicionable;
import misc.ContadorDeInstancias;
import misc.DiccionarioDeSerializables;
import misc.Observable;

public class ParedConcreto extends Pared implements Posicionable, Observable {
	private long id = ContadorDeInstancias.getId();

	public static final String TAG = "objeto-pared-concreto";
	private static final int ALTO = 20;
	private static final int ANCHO = 20;
	private static final String TAG_DESTRUIDA = "destruida";
	private boolean destruida = false;

	public ParedConcreto() {

	}

	public ParedConcreto(double posicionEnX, double posicionEnY) {
		super(posicionEnX, posicionEnY);
		setAlto(ALTO);
		setAncho(ANCHO);

	}


	public void recibirImpacto(int fuerza) {
		// Solo resiste un impacto
		Escenario escenarioActual = Escenario.getActual();
		escenarioActual.borrarSolido(this);
		destruida = true;
		notificar();
	}

	public boolean estaDestruida() {
		return destruida;
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
				if (elem.getTagName().equals(TAG_DESTRUIDA))
					destruida = Boolean.parseBoolean(elem.getTextContent());
			}
		}

	}

}