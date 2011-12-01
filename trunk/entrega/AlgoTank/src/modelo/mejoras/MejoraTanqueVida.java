package modelo.mejoras;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import misc.ContadorDeInstancias;
import misc.DiccionarioDeSerializables;
import modelo.Tanque;

public class MejoraTanqueVida extends MejoraTanque {
	public static final String TAG = "objeto-mejora-tanque-vida";
	private static final String TAG_PORCENTAJE_VIDA = "porcentaje-vida";
	private static final String TAG_TANQUE = "tanque";

	private long id = ContadorDeInstancias.getId();

	private double PORCENTAJE_VIDA;
	private Tanque tanque;

	public MejoraTanqueVida() {

	}

	public MejoraTanqueVida(double porcentaje, Tanque tanque) {
		PORCENTAJE_VIDA = porcentaje;
		this.tanque = tanque;
		tanque.agregarMejora(this);
	}

	public void mejorar() {
		tanque.mejorarVida(PORCENTAJE_VIDA);
		tanque.quitarMejora(this);
	}

	public void deshacer() {
		// Esta mejora no se deshace.
	}

	@Override
	public void pausar() {

	}

	@Override
	public void reanudar() {

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

		elem = doc.createElement(TAG_PORCENTAJE_VIDA);
		element.appendChild(elem);
		elem.setTextContent(Double.toString(PORCENTAJE_VIDA));

		elem = doc.createElement(TAG_TANQUE);
		element.appendChild(elem);
		elem.appendChild(tanque.getElementoXML(doc));

		return element;
	}

	@Override
	public void fromElementoXML(Element element) {
		double porcentajeVida = 0;
		NodeList hijos;
		Element elem;
		hijos = element.getChildNodes();
		if (hijos != null && hijos.getLength() > 0) {
			for (int i = 0; i < hijos.getLength(); i++) {
				if (hijos.item(i).getNodeType() != Node.ELEMENT_NODE)
					continue;
				elem = (Element) hijos.item(i);
				if (elem.getTagName().equals(TAG_PORCENTAJE_VIDA))
					porcentajeVida = Double.parseDouble(elem.getTextContent());
				else if (elem.getTagName().equals(TAG_TANQUE)){
					NodeList nodes=elem.getChildNodes();
					int j;
					for(j=0;j<nodes.getLength();j++)
						if (nodes.item(j).getNodeType() == Node.ELEMENT_NODE)
							break;
					tanque=(Tanque) DiccionarioDeSerializables.getInstancia((Element)nodes.item(j));
				}
					
			}
		}
		PORCENTAJE_VIDA = porcentajeVida;

	}
}
