package modelo;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import misc.ContadorDeInstancias;
import misc.DiccionarioDeSerializables;
import misc.SerializableXML;
import excepciones.NoSePudoPosicionarException;

public class PosicionadorAleatorioStd  implements PosicionadorAleatorio,SerializableXML{
	public static final String TAG = "objeto-posicionador-aleatorio-std";
	private long id=ContadorDeInstancias.getId();

	
	public PosicionadorAleatorioStd(Element element) {
		
	}
	public PosicionadorAleatorioStd() {
		
	}
	public void posicionar(ElementoRectangular elemento) throws NoSePudoPosicionarException{
		double x;
		double y;
		boolean posicionInvalida=true;
		int iteraciones=0;
		while(posicionInvalida){
				
			if(iteraciones > 1000)
				throw new NoSePudoPosicionarException("PosicionadorAleatorioStd fallo al posicionar elemento.");
			x= generarX();
			y= generarY();
			elemento.setX(x);
			elemento.setY(y);
			posicionInvalida=elemento.estaSuperpuesto();
		}
	}
	private double generarX(){
		return Math.random()*(Escenario.getActual().getAncho());
	}
	private double generarY(){
		return Math.random()*(Escenario.getActual().getAlto());
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
		return element;
	}

	@Override
	public void fromElementoXML(Element element) {
		
	}
}
