package misc;

import excepciones.NoSePudoPosicionarException;

public class FabricaBonusVida extends FabricaBonus {
	
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
}
