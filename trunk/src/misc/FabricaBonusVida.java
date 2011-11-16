package misc;

public class FabricaBonusVida extends FabricaBonus {
	
	public FabricaBonusVida(SorteadorBinario sorteador){
		super(sorteador);
	}
	
	public void crearBonus(){
		FabricaElementos.crearBonusVida();
	}
}
