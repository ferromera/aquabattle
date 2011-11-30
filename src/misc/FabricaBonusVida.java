package misc;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import excepciones.NoPudoLeerXMLExeption;
import excepciones.NoSePudoPosicionarException;

public class FabricaBonusVida extends FabricaBonus {
	public static final String TAG = "objeto-fabrica-bonus-vida";
	private long id=ContadorDeInstancias.getId();
	
	public FabricaBonusVida(){
		
	}
	public FabricaBonusVida(SorteadorBinario sorteador){
		super(sorteador);
	}
	
	

	public void crearBonus() {
		try {
			FabricaElementos.crearBonusVida();
		} catch (NoSePudoPosicionarException e) {
			System.err.println("No se pudo posicionar Bonus de Vida");
			e.printStackTrace();
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
		super.fromElementoXML((Element)element.getElementsByTagName(FabricaBonus.TAG).item(0));
		
	}
}
