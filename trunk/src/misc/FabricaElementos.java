package misc;

import modelo.Escenario;
import modelo.armamento.BalaAmetralladora;
import modelo.armamento.BalaCanion;
import modelo.armamento.Cohete;

public class FabricaElementos {

	
	public static BalaAmetralladora crearBalaAmetralladora(){
		BalaAmetralladora bala = new BalaAmetralladora();
		Escenario.getActual().agregarObjetoSolido(bala);
		Escenario.getActual().agregarObjetoVivo(bala);
		return bala;
	}
	public static BalaCanion crearBalaCanion(){
		BalaCanion bala = new BalaCanion();
		Escenario.getActual().agregarObjetoSolido(bala);
		Escenario.getActual().agregarObjetoVivo(bala);
		return bala;
	}
	public static Cohete crearCohete(){
		Cohete cohete = new Cohete();
		Escenario.getActual().agregarObjetoSolido(cohete);
		Escenario.getActual().agregarObjetoVivo(cohete);
		return cohete;
	}
}
