package modelo;



import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import excepciones.NoPudoLeerXMLExeption;

import titiritero.Posicionable;
import misc.Observable;


public class ParedMetal extends Pared implements 
Posicionable, Observable {
	public  static final String TAG = "pared-metal";
	private static final String TAG_DESTRUIDA = "destruida";
	private static final String TAG_IMPACTOS = "impactos";
	private static final int ALTO = 20;
	private static final int ANCHO = 20;	
	private int impactosRecibidos;
	private boolean destruida=false;
	 
	public ParedMetal(double posicionEnX, double posicionEnY){
		super(posicionEnX,posicionEnY);
		setAlto(ALTO);      //Poner la medida que se va a usar
		setAncho(ANCHO); 
		this.impactosRecibidos = 0;
	}
	public ParedMetal(Element element) throws NoPudoLeerXMLExeption{
		super((Element)element.getElementsByTagName(Pared.TAG).item(0));
		destruida=false;
		NodeList nodo;
		Element elem;
		nodo = element.getElementsByTagName(TAG_DESTRUIDA);
		if(nodo!=null && nodo.getLength()>0){
			if(nodo.getLength()>1)
				throw new NoPudoLeerXMLExeption("No puede haber mas de un tag: "+TAG_DESTRUIDA+" en el nodo "+element.getTagName());
			elem = (Element) nodo.item(0);
			destruida=Boolean.parseBoolean(elem.getTextContent());
		}
		nodo = element.getElementsByTagName(TAG_IMPACTOS);
		if(nodo!=null && nodo.getLength()>0){
			if(nodo.getLength()>1)
				throw new NoPudoLeerXMLExeption("No puede haber mas de un tag: "+TAG_IMPACTOS+" en el nodo "+element.getTagName());
			elem = (Element) nodo.item(0);
			impactosRecibidos=Integer.parseInt(elem.getTextContent());
		}
	}
	
	
	public void recibirImpacto(int fuerza){
		this.impactosRecibidos ++;
		//Solo resiste dos impacto
		if(impactosRecibidos > 1){
			Escenario escenarioActual = Escenario.getActual();
			escenarioActual.borrarSolido(this);
			destruida=true;
		}
		notificar();
	}
	
	public boolean estaDestruida(){
		return destruida;
	}
	
	public int impactosRecibidos(){
		return impactosRecibidos;
	}
	
}