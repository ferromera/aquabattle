package modelo;


import modelo.armamento.LanzaCohetes;

import excepciones.NoSePudoPosicionarException;

public class ArmaTiradaLanzaCohetes extends ArmaTirada {

	private static final int TIEMPO_DE_VIDA = 10000;

	
	public ArmaTiradaLanzaCohetes(double posX,double posY)
			throws NoSePudoPosicionarException {
		super(posX, posY, TIEMPO_DE_VIDA);

	}


	
	public void aplicarEfecto(Tanque tanque){
		LanzaCohetes armaAgregar = new LanzaCohetes(tanque);
	}
}