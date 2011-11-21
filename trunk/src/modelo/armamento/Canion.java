package modelo.armamento;

import misc.FabricaElementos;
import modelo.Tanque;

public class Canion extends Arma {

	private final int TIEMPO_CARGA=1000;
	private final int MUNICION_INICIAL=20;
	private int municion;
	public Canion(Tanque tanque){
		setTanque(tanque);
		tiempoCarga=TIEMPO_CARGA;
		municion=MUNICION_INICIAL;
	}
	protected Bala crearBala(){
		municion--;
		return FabricaElementos.crearBalaCanion();
	}
	public boolean tieneMunicion(){
		return municion!=0;
	}
	
}
