package modelo;

import org.w3c.dom.Document;
import org.w3c.dom.Element;


/*
 * Representa todos los elementos del modelo que no pueden ser chocados.
 */
public class ElementoRectangularIntangible extends ElementoRectangular {
	public  static final String TAG = "objeto-elemento-rectangular-intangible";
	
	public ElementoRectangularIntangible(){}
	
	@Override
	public Element getElementoXML(Document doc) {
		Element element = doc.createElement(TAG);
		element.appendChild(super.getElementoXML(doc));
		return element;
	}

	@Override
	public void fromElementoXML(Element element) {
		super.fromElementoXML((Element)element.getElementsByTagName(ElementoRectangular.TAG).item(0));
		
	}
}
