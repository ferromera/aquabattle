package modelo;


import org.w3c.dom.Document;
import org.w3c.dom.Element;

import misc.ContadorDeInstancias;
import misc.DiccionarioDeSerializables;
import modelo.armamento.LanzaCohetes;

import excepciones.NoPudoLeerXMLExeption;
import excepciones.NoSePudoPosicionarException;

public class ArmaTiradaLanzaCohetes extends ArmaTirada {
	public  static final String TAG = "objeto-arma-tirada-lanzacohetes";
	
	private long id=ContadorDeInstancias.getId();

	private static final int TIEMPO_DE_VIDA = 10000;

	public ArmaTiradaLanzaCohetes(){
		
	}
	public ArmaTiradaLanzaCohetes(double posX,double posY)
			throws NoSePudoPosicionarException {
		super(posX, posY, TIEMPO_DE_VIDA);

	}

	public void aplicarEfecto(Tanque tanque){
		tanque.agregarArma(new LanzaCohetes(tanque));
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
		super.fromElementoXML((Element)element.getElementsByTagName(ArmaTirada.TAG).item(0));
		
	}
}