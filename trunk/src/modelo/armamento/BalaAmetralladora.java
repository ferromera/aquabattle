package modelo.armamento;

import misc.ContadorDeInstancias;
import misc.DiccionarioDeSerializables;
import modelo.ElementoRectangularSolido;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import excepciones.NoPudoLeerXMLExeption;



public class BalaAmetralladora extends Bala {
	private long id=ContadorDeInstancias.getId();
	
	public  static final String TAG = "objeto-bala-ametralladora";
	private static final int FUERZA=20;
	private static final double VELOCIDAD=150.0;
	private static final double ANCHO=10.0;
	private static final double ALTO=10.0;
	
	public BalaAmetralladora(){
		velocidad=VELOCIDAD;
		fuerza=FUERZA;
		setAlto(ALTO);
		setAncho(ANCHO);
	}
	
	public BalaAmetralladora(double x,double y){
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
