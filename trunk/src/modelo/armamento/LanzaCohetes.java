package modelo.armamento;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import excepciones.NoPudoLeerXMLExeption;

import misc.ContadorDeInstancias;
import misc.DiccionarioDeSerializables;
import misc.FabricaElementos;
import modelo.Tanque;

public class LanzaCohetes extends Arma {
	private long id = ContadorDeInstancias.getId();

	public static final String TAG = "objeto-lanzacohetes";
	private static final int TIEMPO_CARGA = 500;
	private static final int MUNICION_INICIAL = 10;
	private static final String TAG_MUNICION = "municion";

	private int municion;

	public LanzaCohetes() {

	}

	public LanzaCohetes(Tanque tanque) {
		setTanque(tanque);
		tiempoCarga = TIEMPO_CARGA;
		municion = MUNICION_INICIAL;
	}

	protected Bala crearBala() {
		municion--;
		return FabricaElementos.crearCohete();
	}

	public boolean tieneMunicion() {
		return municion != 0;
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

		elem = doc.createElement(TAG_MUNICION);
		element.appendChild(elem);
		elem.setTextContent(Integer.toString(municion));

		return element;
	}

	@Override
	public void fromElementoXML(Element element) {
		super.fromElementoXML((Element) element.getElementsByTagName(Arma.TAG)
				.item(0));
		NodeList hijos;
		Element elem;
		hijos = element.getChildNodes();
		if (hijos != null && hijos.getLength() > 0) {
			for (int i = 0; i < hijos.getLength(); i++) {
				if (hijos.item(i).getNodeType() != Node.ELEMENT_NODE)
					continue;
				elem = (Element) hijos.item(i);
				if (elem.getTagName().equals(TAG_MUNICION))
					municion = Integer.parseInt(elem.getTextContent());
			}
		}

	}
}
