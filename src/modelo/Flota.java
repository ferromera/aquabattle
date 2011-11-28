package modelo;

import java.util.ArrayList;
import java.util.Iterator;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import excepciones.NoPudoLeerXMLExeption;
import excepciones.NoSePudoPosicionarException;

import misc.ContadorDeInstancias;
import misc.DiccionarioDeSerializables;
import misc.FabricaBonus;
import misc.FabricaElementos;

public class Flota {
	public static final String TAG = "objeto-flota";

	private static final String TAG_TANQUES = "tanques";

	private long id=ContadorDeInstancias.getId();
	
	private ArrayList<TanqueEnemigo> tanques;
	
	
	public Flota(){
		tanques=new ArrayList<TanqueEnemigo>();
	}
	
	public Flota(Element element) throws NoPudoLeerXMLExeption {
		tanques=new ArrayList<TanqueEnemigo>();
		NodeList hijos;
		Element elem;
		hijos = element.getChildNodes();
		if(hijos!=null && hijos.getLength()>0){
			for(int i=0;i<hijos.getLength();i++){
				elem = (Element) hijos.item(i);
				if(elem.getTagName().equals(TAG_TANQUES))
					tanques.add((TanqueEnemigo) DiccionarioDeSerializables.getInstancia((Element)elem.getFirstChild()));
			}
		}
	}

	public void agregar(){
		Iterator<TanqueEnemigo> it= tanques.iterator();
		while(it.hasNext()){
			try {
				FabricaElementos.insertarTanqueEnemigo(it.next());
			} catch (NoSePudoPosicionarException e) {
				System.err.println("No se pudo posicionar tanque de la flota");
			}
		}
	}
	
	public void agregarTanque(TanqueEnemigo tanque){
		tanques.add(tanque);
	}
	public void borrarTanque(TanqueEnemigo tanque){
		tanques.remove(tanque);
	}
	public boolean estaDestruida(){
		Iterator<TanqueEnemigo> it= tanques.iterator();
		while(it.hasNext()){
			if(!it.next().estaDestruido())
				return false;
		}
		return true;
	}

}
