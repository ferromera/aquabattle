package misc;

import modelo.TanqueEnemigo;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import pantallas.Pantalla;

import excepciones.NoPudoLeerXMLExeption;
import excepciones.NoSePudoPosicionarException;

public class FabricaBonusAtaque extends FabricaBonus {
	public static final String TAG = "objeto-fabrica-bonus-ataque";
	private long id=ContadorDeInstancias.getId();
	
	public FabricaBonusAtaque(SorteadorBinario sorteador){
		super(sorteador);
	}
	
	public FabricaBonusAtaque(Element element) throws NoPudoLeerXMLExeption {
		super((Element)element.getElementsByTagName(FabricaBonus.TAG).item(0));
		
	}

	public void crearBonus() {
		try {
			FabricaElementos.crearBonusAtaque();
		} catch (NoSePudoPosicionarException e) {
			System.err.println("No se pudo posicionar Bonus de Ataque");
			e.printStackTrace();
		}
	}
}
