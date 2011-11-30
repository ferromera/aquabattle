package modelo;


import org.w3c.dom.Document;
import org.w3c.dom.Element;

import excepciones.NoSePudoPosicionarException;

import misc.ContadorDeInstancias;
import misc.DiccionarioDeSerializables;
import modelo.mejoras.MejoraTanqueVida;


public class BonusVida extends Bonus {
	private long id=ContadorDeInstancias.getId();
	
	public  static final String TAG = "objeto-bonus-vida";
	private static final int TIEMPO_DE_VIDA = 10000;
	
	public BonusVida(){
		
	}
	public BonusVida(PosicionadorAleatorio posicionador)
			throws NoSePudoPosicionarException {
		super(posicionador, TIEMPO_DE_VIDA);

	}
	
	
	public void aplicarEfecto(Tanque tanque){
		new MejoraTanqueVida(0.4,tanque);
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
