package modelo.armamento;

import modelo.FabricaElementos;
import modelo.Tanque;

public class Ametralladora extends Arma {

	private final int TIEMPO_CARGA=500;
	public Ametralladora(Tanque tanque){
		setTanque(tanque);
		tiempoCarga=TIEMPO_CARGA;
	
	}
	public Bala crearBala(){
		return FabricaElementos.crearBalaAmetralladora();
	}
	public boolean tieneMunicion(){
		return true;
	}
	
}
