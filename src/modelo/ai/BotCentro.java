package modelo.ai;

import utils.Direccion;
import excepciones.NoExisteElementoColisionadoException;
import modelo.ElementoRectangular;
import modelo.ElementoRectangularSolido;
import modelo.Tanque;
import modelo.TanqueEnemigo;

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

	private void moverPor(Direccion primaria, Direccion secundaria) {
		ElementoRectangularSolido colisionPrimaria = null;
		tanque.avanzarEnDireccion(primaria);
		if (tanque.estaColisionado()) {
			// Bloqueado por primaria
			try {
				colisionPrimaria = tanque.getColisionado();
			} catch (NoExisteElementoColisionadoException e1) {
				e1.printStackTrace();
			}
			tanque.retrocederEnDireccion(primaria);
			tanque.avanzarEnDireccion(secundaria);
			if (tanque.estaColisionado()) {
				// Bloqueado por primaria y secundaria
				tanque.retrocederEnDireccion(secundaria);
				if (colisionPrimaria instanceof TanqueEnemigo) {
					// Enemigo en direccion primaria
					tanque.mover(secundaria);
					tanque.disparar();
				} else {
					// No hay enemigo en primaria
					tanque.mover(primaria);
					tanque.disparar();
				}
			} else {
				// Bloqueado primaria, secundaria libre
				tanque.retrocederEnDireccion(secundaria);
				tanque.mover(secundaria);
			}
		} else {
			// primaria libre
			tanque.retrocederEnDireccion(primaria);
			tanque.mover(primaria);
		}
	}

	private void atacarEn(Direccion dir) {
		ElementoRectangularSolido visto = null;
		switch (dir.get()) {
		case Direccion.NORTE:
			visto = tanque.getSolidoVistoNorte();
			break;
		case Direccion.SUR:
			visto = tanque.getSolidoVistoSur();
			break;
		case Direccion.ESTE:
			visto = tanque.getSolidoVistoEste();
			break;
		case Direccion.OESTE:
			visto = tanque.getSolidoVistoOeste();
			break;
		}
		tanque.mover(dir);
		if (!(visto instanceof TanqueEnemigo))
			tanque.disparar();
	}

}
