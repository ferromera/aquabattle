package modelo.armamento;

import misc.FabricaElementos;
import modelo.Tanque;

public class LanzaCohetes extends Arma{

	private final int TIEMPO_CARGA=2000;
	private final int MUNICION_INICIAL=5;
	private int municion;
	public LanzaCohetes(Tanque tanque){
		setTanque(tanque);
		tiempoCarga=TIEMPO_CARGA;
		municion=MUNICION_INICIAL;
	}
	public Bala crearBala(){
		municion--;
		return FabricaElementos.crearCohete();
	}
	public boolean tieneMunicion(){
		return municion!=0;
	}
}
