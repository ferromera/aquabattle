package modelo;

import java.util.ArrayList;
import java.util.Iterator;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import titiritero.ObjetoVivo;

import excepciones.NoPudoLeerXMLExeption;
import excepciones.NoSePudoPosicionarException;

import misc.ContadorDeInstancias;
import misc.DiccionarioDeSerializables;
import misc.FabricaBonus;
import misc.FabricaElementos;
import misc.SerializableXML;

public class Flota implements SerializableXML {
	public static final String TAG = "objeto-flota";

	private static final String TAG_TANQUES = "tanques";

	private long id=ContadorDeInstancias.getId();
	
	private ArrayList<TanqueEnemigo> tanques;
	
	
	public Flota(){
		tanques=new ArrayList<TanqueEnemigo>();
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
	@Override
	public Element getElementoXML(Document doc) {
		Element element=doc.createElement(TAG);
		Element elem;
		elem=doc.createElement(ContadorDeInstancias.TAG_ID);
		element.appendChild(elem);
		elem.setTextContent(Long.toString(id));
		if(DiccionarioDeSerializables.fueSerializado(id))
			return element;
		DiccionarioDeSerializables.marcarSerializado(id);
		
		Iterator<TanqueEnemigo> it = tanques.iterator();
		while(it.hasNext()){
			elem=doc.createElement(TAG_TANQUES);
			element.appendChild(elem);
			elem.appendChild(it.next().getElementoXML(doc));
		}
		return element;
	}
	@Override
	public void fromElementoXML(Element element) {
		tanques=new ArrayList<TanqueEnemigo>();
		NodeList hijos;
		Element elem;
		hijos = element.getChildNodes();
		if(hijos!=null && hijos.getLength()>0){
			for(int i=0;i<hijos.getLength();i++){
				if(hijos.item(i).getNodeType()!=Node.ELEMENT_NODE)continue;elem = (Element) hijos.item(i);
				if(elem.getTagName().equals(TAG_TANQUES)){
					NodeList nodes=elem.getChildNodes();
					int j;
					for(j=0;j<nodes.getLength();j++)
						if (nodes.item(j).getNodeType() == Node.ELEMENT_NODE)
							break;
					tanques.add((TanqueEnemigo) DiccionarioDeSerializables.getInstancia((Element)nodes.item(j)));
				}
					
			}
		}
		
	}

	

}
