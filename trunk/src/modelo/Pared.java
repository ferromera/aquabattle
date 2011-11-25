package modelo;



import org.w3c.dom.Element;

import excepciones.NoPudoLeerXMLExeption;

import titiritero.Posicionable;
import misc.Observable;


public abstract class Pared extends ElementoRectangularSolido implements 
Posicionable, Observable {
	public  static final String TAG = "pared";
	
	public Pared(){}
	public Pared(Element element) throws NoPudoLeerXMLExeption{
		super((Element)element.getElementsByTagName(ElementoRectangularSolido.TAG).item(0));
	}
	public Pared(double posicionEnX, double posicionEnY){
		setX(posicionEnX);
		setY(posicionEnY);
	}

	
	public abstract void recibirImpacto(int fuerza);
	
	public int getResistencia(){
		return 1;
	}
	
}