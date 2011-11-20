package modelo.armamento;

import misc.FabricaElementos;
import modelo.Tanque;

public class Ametralladora extends Arma {

	private final int TIEMPO_CARGA=1500;
	public Ametralladora(Tanque tanque){
		setTanque(tanque);
		tiempoCarga=TIEMPO_CARGA;
	
	}
	protected Bala crearBala(){
		return FabricaElementos.crearBalaAmetralladora();
	}
	public boolean tieneMunicion(){
		return true;
	}
	
}
