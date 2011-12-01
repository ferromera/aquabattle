package modelo;



import org.w3c.dom.Document;
import org.w3c.dom.Element;

import titiritero.Posicionable;
import misc.Observable;


public abstract class Pared extends ElementoRectangularSolido implements 
Posicionable, Observable {
	public  static final String TAG = "objeto-pared";
	
	public Pared(){}
	
	public Pared(double posicionEnX, double posicionEnY){
		setX(posicionEnX);
		setY(posicionEnY);
	}

	
	public abstract void recibirImpacto(int fuerza);
	
	public int getResistencia(){
		return 1;
	}
	@Override
	public Element getElementoXML(Document doc) {
		Element element = doc.createElement(TAG);
		element.appendChild(super.getElementoXML(doc));
		return element;
	}

	@Override
	public void fromElementoXML(Element element) {
		super.fromElementoXML((Element)element.getElementsByTagName(ElementoRectangularSolido.TAG).item(0));
		
	}
	
}