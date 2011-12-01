package modelo.armamento;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import misc.ContadorDeInstancias;
import misc.DiccionarioDeSerializables;
import misc.FabricaElementos;
import modelo.Tanque;

public class Ametralladora extends Arma {
	private long id=ContadorDeInstancias.getId();
	
	public  static final String TAG = "objeto-ametralladora";
	private static final int TIEMPO_CARGA=1500;
	
	public Ametralladora(){
		
	}
	
	public Ametralladora(Tanque tanque){
		setTanque(tanque);
		tiempoCarga=TIEMPO_CARGA;
	}
	
	protected Bala crearBala(){
		return FabricaElementos.crearBalaAmetralladora();
	}
	public boolean tieneMunicion(){
		return true;
	}
	@Override
	public Element getElementoXML(Document doc) {
		Element element = doc.createElement(TAG);
		Element elem= doc.createElement(ContadorDeInstancias.TAG_ID);
		element.appendChild(elem);
		elem.setTextContent(Long.toString(id));
		if(DiccionarioDeSerializables.fueSerializado(id))
			return element;
		DiccionarioDeSerializables.marcarSerializado(id);
		element.appendChild(super.getElementoXML(doc));
		return element;
	}

	@Override
	public void fromElementoXML(Element element) {
		super.fromElementoXML((Element)element.getElementsByTagName(Arma.TAG).item(0));
		
	}
	
	
}
