package modelo.ai;



import utils.Direccion;
import modelo.ElementoRectangular;
import modelo.Escenario;

import modelo.Tanque;


public class BotBordes extends Bot {

	public BotBordes(Tanque tanque, ElementoRectangular objetivo) {
		super(tanque, objetivo);
	}

	@Override
	public void actuar(){
		if (!enYDeObjetivo()) {
			if (!enBordeX())
				moverHaciaBordeX();
			else
				moverHaciaYDeObjetivo();
		} else {
			atacarHaciaXDeObjetivo();
		}
	}

	private void atacarHaciaXDeObjetivo() {
		if (tanque.getCentroX() < getObjetivo().getCentroX())
			atacarEn(Direccion.Este());
		else
			atacarEn(Direccion.Oeste());
	}

	private void moverHaciaYDeObjetivo() {
		if (tanque.getCentroY() < getObjetivo().getCentroY()) {
			if (tanque.getCentroX() < getObjetivo().getCentroX())
				moverPor(Direccion.Sur(), Direccion.Este());
			else
				moverPor(Direccion.Sur(), Direccion.Oeste());
		} else {
			if (tanque.getCentroX() < getObjetivo().getCentroX())
				moverPor(Direccion.Norte(), Direccion.Este());
			else
				moverPor(Direccion.Norte(), Direccion.Oeste());
		}
	}

	private void moverHaciaBordeX() {
		if (tanque.getCentroX() < getObjetivo().getCentroX()) {
			if (tanque.getCentroY() < getObjetivo().getCentroY())
				moverPor(Direccion.Oeste(),Direccion.Sur());
			else
				moverPor(Direccion.Oeste(),Direccion.Norte());
		} else {
			if (tanque.getCentroY() < getObjetivo().getCentroY())
				moverPor(Direccion.Este(),Direccion.Sur());
			else
				moverPor(Direccion.Este(),Direccion.Norte());
		}
	}



	private boolean enBordeX() {
		return ( ((Escenario.getActual().getAncho() - tanque.getCentroX()) < 20)
				|| (tanque.getCentroX() < 20) );
	}



	private boolean enYDeObjetivo() {
		return (tanque.getCentroY() < getObjetivo().getCentroY()+5
				&& tanque.getCentroY() > getObjetivo().getCentroY()-5 );
	}
}
