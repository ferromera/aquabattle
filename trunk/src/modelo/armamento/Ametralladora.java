package modelo.armamento;

import org.w3c.dom.Element;

import excepciones.NoPudoLeerXMLExeption;

import misc.FabricaElementos;
import modelo.Pared;
import modelo.Tanque;

public class Ametralladora extends Arma {
	public  static final String TAG = "ametralladora";
	private static final int TIEMPO_CARGA=1500;
	public Ametralladora(Tanque tanque){
		setTanque(tanque);
		tiempoCarga=TIEMPO_CARGA;
	}
	public Ametralladora(Element element) throws NoPudoLeerXMLExeption{
		super((Element)element.getElementsByTagName(Arma.TAG).item(0));
	
	}
	protected Bala crearBala(){
		return FabricaElementos.crearBalaAmetralladora();
	}
	public boolean tieneMunicion(){
		return true;
	}
	
}
