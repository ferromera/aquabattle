package modelo;

import org.w3c.dom.Element;

import excepciones.NoPudoLeerXMLExeption;

public abstract class TanqueEnemigo extends Tanque {
	public static final String TAG="tanque-enemigo";
	public TanqueEnemigo(){}
	public TanqueEnemigo(Element element) throws NoPudoLeerXMLExeption{
		super((Element)element.getElementsByTagName(Tanque.TAG).item(0));
	}
}
