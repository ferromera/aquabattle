package pantallas;


import java.util.ArrayList;
import java.util.Iterator;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import excepciones.NoPudoLeerXMLExeption;

import misc.DiccionarioDeSerializables;
import misc.FabricaElementos;
import misc.Nivel;
import misc.Observable;
import misc.Observador;
import modelo.TanqueEnemigo;
import titiritero.ObjetoVivo;
import titiritero.Posicionable;


public abstract class Pantalla implements Posicionable,ObjetoVivo,Observable {
	public static final String TAG = "objeto-pantalla";
	private static final String TAG_OBSERVADORES = "observadores";
	private ArrayList<Observador> observadores;
	
	public Pantalla(){
		observadores= new ArrayList<Observador>();
	}
	
	public Pantalla(Element element) throws NoPudoLeerXMLExeption {
		observadores= new ArrayList<Observador>();
		
		NodeList hijos;
		Element elem;
		hijos = element.getChildNodes();
		if(hijos!=null && hijos.getLength()>0){
			for(int i=0;i<hijos.getLength();i++){
				elem = (Element) hijos.item(i);
				if(elem.getTagName().equals(TAG_OBSERVADORES))
					observadores.add((Observador) DiccionarioDeSerializables.getInstancia((Element)elem.getFirstChild()));
			}
		}
		
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
	
	
	public abstract void cambiarA(Pantalla pantalla);
	@Override
	public double getX() {
		return 0;
	}

	@Override
	public double getY() {
		return 0;
	}
	public abstract void convertirEnActual();
	public abstract void dejarDeSerActual();

}
