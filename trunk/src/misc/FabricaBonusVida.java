package misc;

import org.w3c.dom.Element;

import excepciones.NoPudoLeerXMLExeption;
import excepciones.NoSePudoPosicionarException;

public class FabricaBonusVida extends FabricaBonus {
	public static final String TAG = "objeto-fabrica-bonus-vida";
	private long id=ContadorDeInstancias.getId();
	
	public FabricaBonusVida(SorteadorBinario sorteador){
		super(sorteador);
	}
	
	public FabricaBonusVida(Element element) throws NoPudoLeerXMLExeption {
		super((Element)element.getElementsByTagName(FabricaBonus.TAG).item(0));
	}

	public void crearBonus() {
		try {
			FabricaElementos.crearBonusVida();
		} catch (NoSePudoPosicionarException e) {
			System.err.println("No se pudo posicionar Bonus de Vida");
			e.printStackTrace();
		}
	}
}
