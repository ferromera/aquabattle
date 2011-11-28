package modelo;



import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import excepciones.NoPudoLeerXMLExeption;

import titiritero.Posicionable;
import misc.ContadorDeInstancias;
import misc.Observable;


public class ParedConcreto extends Pared implements 
Posicionable, Observable {
	private long id=ContadorDeInstancias.getId();
	
	public  static final String TAG = "objeto-pared-concreto";
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
		NodeList hijos;
		Element elem;
		hijos = element.getChildNodes();
		if(hijos!=null && hijos.getLength()>0){
			for(int i=0;i<hijos.getLength();i++){
				elem = (Element) hijos.item(i);
				if(elem.getTagName().equals(TAG_DESTRUIDA))
					destruida=Boolean.parseBoolean(elem.getTextContent());
			}
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