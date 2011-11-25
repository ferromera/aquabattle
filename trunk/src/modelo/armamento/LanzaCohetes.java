package modelo.armamento;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import excepciones.NoPudoLeerXMLExeption;

import misc.FabricaElementos;
import modelo.Tanque;

public class LanzaCohetes extends Arma{
	public  static final String TAG = "lanzacohetes";
	private static final int TIEMPO_CARGA=500;
	private static final int MUNICION_INICIAL=10;
	private static final String TAG_MUNICION = "municion";
	private int municion;
	public LanzaCohetes(Tanque tanque){
		setTanque(tanque);
		tiempoCarga=TIEMPO_CARGA;
		municion=MUNICION_INICIAL;
	}
	public LanzaCohetes(Element element) throws NoPudoLeerXMLExeption{
		super((Element)element.getElementsByTagName(Arma.TAG).item(0));
		NodeList nodo;
		Element elem;
		nodo = element.getElementsByTagName(TAG_MUNICION);
		if(nodo!=null && nodo.getLength()>0){
			if(nodo.getLength()>1)
				throw new NoPudoLeerXMLExeption("No puede haber mas de un tag: "+TAG_MUNICION+" en el nodo "+element.getTagName());
			elem = (Element) nodo.item(0);
			municion=Integer.parseInt(elem.getNodeValue());
		}
	}
	protected Bala crearBala(){
		municion--;
		return FabricaElementos.crearCohete();
	}
	public boolean tieneMunicion(){
		return municion!=0;
	}
}
