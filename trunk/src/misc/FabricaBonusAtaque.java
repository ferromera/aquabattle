package misc;

import modelo.TanqueEnemigo;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import pantallas.Pantalla;

import excepciones.NoPudoLeerXMLExeption;
import excepciones.NoSePudoPosicionarException;

public class FabricaBonusAtaque extends FabricaBonus {
	public static final String TAG = "objeto-fabrica-bonus-ataque";
	private long id=ContadorDeInstancias.getId();
	
	public FabricaBonusAtaque(){
		
	}
	public FabricaBonusAtaque(SorteadorBinario sorteador){
		super(sorteador);
	}
	
	

	public void crearBonus() {
		try {
			FabricaElementos.crearBonusAtaque();
		} catch (NoSePudoPosicionarException e) {
			System.err.println("No se pudo posicionar Bonus de Ataque");
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
