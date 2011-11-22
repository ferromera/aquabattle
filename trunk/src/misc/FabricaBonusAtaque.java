package misc;

import excepciones.NoSePudoPosicionarException;

public class FabricaBonusAtaque extends FabricaBonus {
	
	
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
}
