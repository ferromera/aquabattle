package modelo.armamento;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import excepciones.NoPudoLeerXMLExeption;

import misc.ContadorDeInstancias;
import misc.DiccionarioDeSerializables;
import modelo.ElementoRectangularSolido;

public class Cohete extends Bala {
	private long id=ContadorDeInstancias.getId();
	
	public  static final String TAG = "objeto-cohete";
	private final double VELOCIDAD = 300.0;
	private final double ANCHO = 50.0;
	private final double ALTO = 50.0;

	public Cohete() {
		velocidad = VELOCIDAD;
		setAlto(ALTO);
		setAncho(ANCHO);
	}
	
	public Cohete(double x, double y) {
		velocidad = VELOCIDAD;
		setAlto(ALTO);
		setAncho(ANCHO);
		setX(x);
		setY(y);
	}

	public void impactar(ElementoRectangularSolido solido) {
		if (solido != null)
			solido.recibirImpacto(solido.getResistencia() / 2);
		destruir();
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
