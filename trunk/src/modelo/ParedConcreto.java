package modelo;



import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import excepciones.NoPudoLeerXMLExeption;

import titiritero.Posicionable;
import misc.Observable;


public class ParedConcreto extends Pared implements 
Posicionable, Observable {
	public  static final String TAG = "pared-concreto";
	private static final int ALTO = 20;
	private static final int ANCHO = 20;
	private static final String TAG_DESTRUIDA = "destruida";	
	private boolean destruida=false;
	
	 
	public ParedConcreto(double posicionEnX, double posicionEnY){
		super(posicionEnX,posicionEnY);
		setAlto(ALTO);   
		setAncho(ANCHO); 
		
	}
	public ParedConcreto(Element element) throws NoPudoLeerXMLExeption{
		super((Element)element.getElementsByTagName(Pared.TAG).item(0));
		destruida=false;
		NodeList nodoDestruida = element.getElementsByTagName(TAG_DESTRUIDA);
		if(nodoDestruida!=null && nodoDestruida.getLength()>0){
			if(nodoDestruida.getLength()>1)
				throw new NoPudoLeerXMLExeption("No puede haber mas de un tag: "+TAG_DESTRUIDA+" en el nodo "+element.getTagName());
			Element elemDestruida = (Element) nodoDestruida.item(0);
			destruida=Boolean.parseBoolean(elemDestruida.getTextContent());
		}
		
	}
	
	
	public void recibirImpacto(int fuerza){
		//Solo resiste un impacto
		Escenario escenarioActual = Escenario.getActual();
		escenarioActual.borrarSolido(this);	
		destruida=true;
		notificar();
	}
	public boolean estaDestruida(){
		return destruida;
	}
	
}