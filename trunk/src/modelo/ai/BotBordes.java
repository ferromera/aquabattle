package modelo.ai;



import org.w3c.dom.Document;
import org.w3c.dom.Element;

import utils.Direccion;
import misc.ContadorDeInstancias;
import misc.DiccionarioDeSerializables;
import modelo.ElementoRectangular;
import modelo.Escenario;

import modelo.Tanque;


public class BotBordes extends Bot {
	public static final String TAG = "objeto-bot-bordes";
	private long id=ContadorDeInstancias.getId();
	
	public BotBordes(){
		
	}
	public BotBordes(Tanque tanque, ElementoRectangular objetivo) {
		super(tanque, objetivo);
	}

	

	@Override
	public void actuar(){
		if (!enYDeObjetivo()) {
			if (!enBordeX())
				moverHaciaBordeX();
			else
				moverHaciaYDeObjetivo();
		} else {
			atacarHaciaXDeObjetivo();
		}
	}

	private void atacarHaciaXDeObjetivo() {
		if (tanque.getCentroX() < getObjetivo().getCentroX())
			atacarEn(Direccion.Este());
		else
			atacarEn(Direccion.Oeste());
	}

	private void moverHaciaYDeObjetivo() {
		if (tanque.getCentroY() < getObjetivo().getCentroY()) {
			if (tanque.getCentroX() < getObjetivo().getCentroX())
				moverPor(Direccion.Sur(), Direccion.Este());
			else
				moverPor(Direccion.Sur(), Direccion.Oeste());
		} else {
			if (tanque.getCentroX() < getObjetivo().getCentroX())
				moverPor(Direccion.Norte(), Direccion.Este());
			else
				moverPor(Direccion.Norte(), Direccion.Oeste());
		}
	}

	private void moverHaciaBordeX() {
		if (tanque.getCentroX() < getObjetivo().getCentroX()) {
			if (tanque.getCentroY() < getObjetivo().getCentroY())
				moverPor(Direccion.Oeste(),Direccion.Sur());
			else
				moverPor(Direccion.Oeste(),Direccion.Norte());
		} else {
			if (tanque.getCentroY() < getObjetivo().getCentroY())
				moverPor(Direccion.Este(),Direccion.Sur());
			else
				moverPor(Direccion.Este(),Direccion.Norte());
		}
	}

	private boolean enBordeX() {
		return ( ((Escenario.getActual().getAncho() - tanque.getCentroX()) < 20)
				|| (tanque.getCentroX() < 20) );
	}

	private boolean enYDeObjetivo() {
		return (tanque.getCentroY() < getObjetivo().getCentroY()+5
				&& tanque.getCentroY() > getObjetivo().getCentroY()-5 );
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
