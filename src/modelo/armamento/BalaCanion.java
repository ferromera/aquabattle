package modelo.armamento;

import misc.ContadorDeInstancias;
import misc.DiccionarioDeSerializables;
import modelo.ElementoRectangularSolido;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import excepciones.NoPudoLeerXMLExeption;

public class BalaCanion extends Bala {
	private long id=ContadorDeInstancias.getId();
	
	public  static final String TAG = "objeto-bala-canion";
	private final int FUERZA=30;
	private final double VELOCIDAD=200.0;
	private final double ANCHO=30.0;
	private final double ALTO=30.0;
	
	public BalaCanion(){
		velocidad=VELOCIDAD;
		fuerza=FUERZA;
		setAlto(ALTO);
		setAncho(ANCHO);
	}
	
	public BalaCanion(double x, double y){
		velocidad=VELOCIDAD;
		fuerza=FUERZA;
		setAlto(ALTO);
		setAncho(ANCHO);
		setX(x);
		setY(y);
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
		super.fromElementoXML((Element)element.getElementsByTagName(Bala.TAG).item(0));
		
	}
	
}
