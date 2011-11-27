package modelo;


import modelo.armamento.Canion;

import excepciones.NoSePudoPosicionarException;

public class ArmaTiradaCanion extends ArmaTirada {

	private static final int TIEMPO_DE_VIDA = 10000;
	
	public ArmaTiradaCanion(double posX, double posY)
			throws NoSePudoPosicionarException {
		super(posX, posY, TIEMPO_DE_VIDA);
	}
	

	
	public void aplicarEfecto(Tanque tanque){
		tanque.agregarArma(new Canion(tanque));
	}
	
}
