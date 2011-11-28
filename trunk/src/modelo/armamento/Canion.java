package modelo.armamento;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import excepciones.NoPudoLeerXMLExeption;

import misc.ContadorDeInstancias;
import misc.DiccionarioDeSerializables;
import misc.FabricaElementos;
import modelo.Tanque;

public class Canion extends Arma {
	private long id=ContadorDeInstancias.getId();
	
	public  static final String TAG = "objeto-canion";
	private static final String TAG_MUNICION = "municion";
	
	private static final int TIEMPO_CARGA=1000;
	private static final int MUNICION_INICIAL=20;
	
	private int municion;
	
	public Canion(Tanque tanque){
		setTanque(tanque);
		tiempoCarga=TIEMPO_CARGA;
		municion=MUNICION_INICIAL;
	}
	public Canion(Element element) throws NoPudoLeerXMLExeption{
		super((Element)element.getElementsByTagName(Arma.TAG).item(0));
		
		NodeList hijos;
		Element elem;
		hijos = element.getChildNodes();
		if(hijos!=null && hijos.getLength()>0){
			for(int i=0;i<hijos.getLength();i++){
				elem = (Element) hijos.item(i);
				if(elem.getTagName().equals(TAG_MUNICION))
					municion=Integer.parseInt(elem.getTextContent());
			}
		}
		
	}
	protected Bala crearBala(){
		municion--;
		return FabricaElementos.crearBalaCanion();
	}
	public boolean tieneMunicion(){
		return municion!=0;
	}
	
}
