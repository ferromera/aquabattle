package modelo.ai;

import utils.Direccion;
import modelo.ElementoRectangular;
import modelo.Tanque;


public class BotCentro extends Bot {

	public BotCentro(Tanque tanque, ElementoRectangular objetivo) {
		super(tanque, objetivo);
	}

	@Override
	public void actuar() {
		boolean haciaEste = false;
		boolean haciaSur = false;
		boolean enCentroX = false;
		boolean enCentroY = false;

		if (tanque.getCentroX() < objetivo.getCentroX() - 4.0)
			haciaEste = true;
		else if (tanque.getCentroX() > objetivo.getCentroX() + 4.0)
			haciaEste = false;
		else
			enCentroX = true;
		if (tanque.getCentroY() < objetivo.getCentroY() - 4.0)
			haciaSur = true;
		else if (tanque.getCentroY() > objetivo.getCentroY() + 4.0)
			haciaSur = false;
		else
			enCentroY = true;

		if (enCentroX)
			if (haciaSur)
				atacarEn(Direccion.Sur());
			else
				atacarEn(Direccion.Norte());
		else if (enCentroY)
			if (haciaEste)
				atacarEn(Direccion.Este());
			else
				atacarEn(Direccion.Oeste());
		else {
			if (haciaEste)
				if (haciaSur)
					moverPor(Direccion.Este(), Direccion.Sur());
				else
					moverPor(Direccion.Este(), Direccion.Norte());
			else if (haciaSur)
				moverPor(Direccion.Oeste(), Direccion.Sur());
			else
				moverPor(Direccion.Oeste(), Direccion.Norte());
		}

	}

}
