package modelo;


import excepciones.NoSePudoPosicionarException;

import modelo.mejoras.MejoraTanqueVida;


public class BonusVida extends Bonus {

	private static final int TIEMPO_DE_VIDA = 10000;

	public BonusVida(PosicionadorAleatorio posicionador)
			throws NoSePudoPosicionarException {
		super(posicionador, TIEMPO_DE_VIDA);

	}
	
	public void aplicarEfecto(Tanque tanque){
		MejoraTanqueVida mejoraVida = new MejoraTanqueVida(0.4);
		tanque.agregarMejora(mejoraVida);
	}
		


}
