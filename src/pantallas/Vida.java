package pantallas;

import java.util.ArrayList;
import java.util.Iterator;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import excepciones.NoPudoLeerXMLExeption;

import titiritero.Posicionable;
import misc.DiccionarioDeSerializables;
import misc.Observable;
import misc.Observador;


public class Vida implements Observable,Posicionable {
	
	private static final int ANCHO=15;
	private static final int ALTO=15;
	private static final String TAG_X = "x";
	private static final String TAG_Y = "y";
	private static final String TAG_BORRADA = "borrada";
	private static final String TAG_OBSERVADORES = "observadores";
	
	private int x,y;
	private boolean borrada;
	private ArrayList<Observador> observadores=new ArrayList<Observador>();
	
	public Vida(int x,int y){
		this.x=x;
		this.y=y;
		borrada=false;
	}
	public int getAncho(){
		return ANCHO;
	}
	public int getAlto(){
		return ALTO;
	}
	@Override
	public double getX() {
		return x;
	}
	@Override
	public double getY() {
		return y;
	}
	public void setX(int x){
		this.x=x;
	}
	public void setY(int y){
		this.y=y;
	}
	public boolean estaBorrada(){
		return borrada;
	}
	public void borrar(){
		borrada=true;
		notificar();
	}


	public void adscribir(Observador observador){
		if(!observadores.contains(observador))
		observadores.add(observador);
	}
	public void quitar(Observador observador){
		observadores.remove(observador);
	}
	public void notificar(){
		Iterator<Observador> it= observadores.iterator();
		while(it.hasNext()){
			it.next().actualizar();
		}
	}
	
	
	public Vida(Element element) throws NoPudoLeerXMLExeption {
		NodeList hijos;
		Element elem;
		hijos = element.getChildNodes();
		if(hijos!=null && hijos.getLength()>0){
			for(int i=0;i<hijos.getLength();i++){
				elem = (Element) hijos.item(i);
				if(elem.getTagName().equals(TAG_X))
					x=Integer.parseInt(elem.getTextContent());
				else if(elem.getTagName().equals(TAG_Y))
					y=Integer.parseInt(elem.getTextContent());
				else if(elem.getTagName().equals(TAG_BORRADA))
					borrada=Boolean.parseBoolean(elem.getTextContent());
				else if(elem.getTagName().equals(TAG_OBSERVADORES))
					observadores.add((Observador) DiccionarioDeSerializables.getInstancia((Element)elem.getFirstChild()));
			}
		}
	}

}
