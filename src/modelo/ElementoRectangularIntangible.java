package modelo;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import excepciones.NoPudoLeerXMLExeption;

/*
 * Representa todos los elementos del modelo que no pueden ser chocados.
 */
public class ElementoRectangularIntangible extends ElementoRectangular {
	public  static final String TAG = "objeto-elemento-rectangular-intangible";
	
	public ElementoRectangularIntangible(){}
	public ElementoRectangularIntangible(Element element) throws NoPudoLeerXMLExeption{
		super((Element)element.getElementsByTagName(ElementoRectangular.TAG).item(0));
	}
}
