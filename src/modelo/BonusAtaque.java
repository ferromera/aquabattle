package modelo;


import org.w3c.dom.Document;
import org.w3c.dom.Element;

import misc.ContadorDeInstancias;
import misc.DiccionarioDeSerializables;
import modelo.mejoras.MejoraTanqueAtaque;

import excepciones.NoPudoLeerXMLExeption;
import excepciones.NoSePudoPosicionarException;


public class BonusAtaque extends Bonus {
	private long id=ContadorDeInstancias.getId();
	
	public  static final String TAG = "objeto-bonus-ataque";
	private static final int TIEMPO_DE_VIDA = 10000;
	private static final double PORCENTAJE_VELOCIDAD= 0.2;
	private static final double PORCENTAJE_DISPARO= 0.3;
	
	public BonusAtaque(){
		
	}
	public BonusAtaque(PosicionadorAleatorio posicionador)
			throws NoSePudoPosicionarException {
		super(posicionador, TIEMPO_DE_VIDA);

	}
	
	
	public void aplicarEfecto(Tanque tanque){
		new MejoraTanqueAtaque(PORCENTAJE_VELOCIDAD,PORCENTAJE_DISPARO,tanque);
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
		super.fromElementoXML((Element)element.getElementsByTagName(Bonus.TAG).item(0));
		
	}
	
	

}
