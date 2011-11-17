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
		if (tanque.getCentroX() < objetivo.getCentroX())
			atacarEn(Direccion.Este());
		else
			atacarEn(Direccion.Oeste());
	}

	private void moverHaciaYDeObjetivo() {
		if (tanque.getCentroY() < objetivo.getCentroY()) {
			if (tanque.getCentroX() < objetivo.getCentroX())
				moverPor(Direccion.Sur(), Direccion.Este());
			else
				moverPor(Direccion.Sur(), Direccion.Oeste());
		} else {
			if (tanque.getCentroX() < objetivo.getCentroX())
				moverPor(Direccion.Norte(), Direccion.Este());
			else
				moverPor(Direccion.Norte(), Direccion.Oeste());
		}
	}

	private void moverHaciaBordeX() {
		if (tanque.getCentroX() < objetivo.getCentroX()) {
			if (tanque.getCentroY() < objetivo.getCentroY())
				moverPor(Direccion.Sur(), Direccion.Oeste());
			else
				moverPor(Direccion.Norte(), Direccion.Oeste());
		} else {
			if (tanque.getCentroY() < objetivo.getCentroY())
				moverPor(Direccion.Sur(), Direccion.Este());
			else
				moverPor(Direccion.Norte(), Direccion.Este());
		}
	}



	private boolean enBordeX() {
		return ( ((Escenario.getActual().getAncho() - tanque.getCentroX()) < 20)
				|| (tanque.getCentroX() < 20) );
	}



	private boolean enYDeObjetivo() {
		return (tanque.getCentroY() == objetivo.getCentroY());
	}
}
