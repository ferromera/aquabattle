package modelo.ai;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import excepciones.NoPudoLeerXMLExeption;

import utils.Direccion;
import misc.ContadorDeInstancias;
import misc.DiccionarioDeSerializables;
import modelo.ElementoRectangular;
import modelo.Tanque;


public class BotCentro extends Bot {
	public static final String TAG = "objeto-bot-centro";
	private long id=ContadorDeInstancias.getId();

	public BotCentro(){
		
	}
	
	public BotCentro(Tanque tanque, ElementoRectangular objetivo) {
		super(tanque, objetivo);
	}


	@Override
	public void actuar() {
		boolean haciaEste = false;
		boolean haciaSur = false;
		boolean enCentroX = false;
		boolean enCentroY = false;

		if (tanque.getCentroX() < getObjetivo().getCentroX() - 4.0)
			haciaEste = true;
		else if (tanque.getCentroX() > getObjetivo().getCentroX() + 4.0)
			haciaEste = false;
		else
			enCentroX = true;
		if (tanque.getCentroY() < getObjetivo().getCentroY() - 4.0)
			haciaSur = true;
		else if (tanque.getCentroY() > getObjetivo().getCentroY() + 4.0)
			haciaSur = false;
		else
			enCentroY = true;

		if (enCentroX)
			if (haciaSur)
				atacarEn(Direccion.Sur());
			else
				atacarEn(Direccion.Norte());
		else if (enCentroY)
			if (haciaEste)
				atacarEn(Direccion.Este());
			else
				atacarEn(Direccion.Oeste());
		else {
			if (haciaEste)
				if (haciaSur)
					moverPor(Direccion.Este(), Direccion.Sur());
				else
					moverPor(Direccion.Este(), Direccion.Norte());
			else if (haciaSur)
				moverPor(Direccion.Oeste(), Direccion.Sur());
			else
				moverPor(Direccion.Oeste(), Direccion.Norte());
		}

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
		super.fromElementoXML((Element)element.getElementsByTagName(Bot.TAG).item(0));
		
	}

}
