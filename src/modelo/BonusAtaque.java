package modelo;


import modelo.mejoras.MejoraTanqueAtaque;

import excepciones.NoSePudoPosicionarException;


public class BonusAtaque extends Bonus {

	private static final int TIEMPO_DE_VIDA = 10000;
	private static final double PORCENTAJE_VELOCIDAD= 0.2;
	private static final double PORCENTAJE_DISPARO= 0.3;

	public BonusAtaque(PosicionadorAleatorio posicionador)
			throws NoSePudoPosicionarException {
		super(posicionador, TIEMPO_DE_VIDA);

	}
	
	public void aplicarEfecto(Tanque tanque){
		MejoraTanqueAtaque mejoraAtaque = new MejoraTanqueAtaque(PORCENTAJE_VELOCIDAD,PORCENTAJE_DISPARO);
		tanque.agregarMejora(mejoraAtaque);
	}
	
	

}
