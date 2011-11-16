package misc;

public class FabricaBonusAtaque extends FabricaBonus {
	
	public FabricaBonusAtaque(SorteadorBinario sorteador){
		super(sorteador);
	}
	
	public void crearBonus(){
		FabricaElementos.crearBonusAtaque();
	}
}
