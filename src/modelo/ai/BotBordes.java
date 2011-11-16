package modelo.ai;


import excepciones.NoExisteElementoColisionadoException;
import utils.Direccion;
import modelo.ElementoRectangular;
import modelo.ElementoRectangularSolido;
import modelo.Tanque;
import modelo.TanqueEnemigo;

public class BotBordes extends Bot {
	private boolean disparar = false;

	public BotBordes(Tanque tanque, ElementoRectangular objetivo) {
		super(tanque, objetivo);
	}

	@Override
	void decidirDisparo() {
		if(disparar)
			tanque.disparar();

	}

	@Override
	void calcularOrientacion() {
		double centroObjetivo = objetivo.getCentroX();
		boolean centroDerecha = true;
		boolean enCentro = false;
		disparar = false;

		if (tanque.getCentroX() < centroObjetivo - 4.0)
			centroDerecha = true;
		else if (tanque.getCentroX() > centroObjetivo + 4.0)
			centroDerecha = false;
		else
			enCentro = true;

		if (!enCentro) {
			if (centroDerecha)
				moverPorIzquierdaCentro();
			else
				moverPorDerechaCentro();
		} else
			moverEnCentro();

	}

	private void moverPorIzquierdaCentro() {
		ElementoRectangularSolido colisionCentro=null;
		tanque.avanzarEste();
		if (tanque.estaColisionado()) {
			// Bloqueado por centro
			try {
				colisionCentro = tanque.getColisionado();
			} catch (NoExisteElementoColisionadoException e1) {
				e1.printStackTrace();
			}
			tanque.avanzarOeste();
			tanque.avanzarSur();
			if (tanque.estaColisionado()) {
				// Bloqueado por centro y sur
				tanque.avanzarNorte();
				if (colisionCentro instanceof TanqueEnemigo) {
					// Enemigo al centro
					tanque.mover(Direccion.Sur());
					disparar = true;
				} else {
					// No hay enemigo al centro
					tanque.mover(Direccion.Este());
					disparar = true;
				}
			} else {
				// Bloqueado centro, sur libre
				tanque.avanzarNorte();
				tanque.mover(Direccion.Sur());
			}
		} else {
			// Centro libre
			tanque.avanzarOeste();
			tanque.mover(Direccion.Este());
		}
	}

	private void moverPorDerechaCentro() {
		ElementoRectangularSolido colisionCentro = null;
		tanque.avanzarOeste();
		if (tanque.estaColisionado()) {
			// Bloqueado por centro
			try {
				colisionCentro = tanque.getColisionado();
			} catch (NoExisteElementoColisionadoException e) {
				e.printStackTrace();
			}
			tanque.avanzarEste();
			tanque.avanzarSur();
			if (tanque.estaColisionado()) {
				// Bloqueado por centro y sur
				tanque.avanzarNorte();
				if (colisionCentro instanceof TanqueEnemigo) {
					// Enemigo al centro
					tanque.mover(Direccion.Sur());
					disparar = true;
				} else {
					// No hay enemigo al centro
					tanque.mover(Direccion.Oeste());
					disparar = true;
				}
			} else {
				// Bloqueado centro, sur libre
				tanque.avanzarNorte();
				tanque.mover(Direccion.Sur());
			}
		} else {
			// Centro libre
			tanque.avanzarEste();
			tanque.mover(Direccion.Oeste());
		}
	}

	private void moverEnCentro() {
		ElementoRectangularSolido visto = tanque.getSolidoVistoSur();
		tanque.mover(Direccion.Sur());
		if (!(visto instanceof TanqueEnemigo))
			disparar = true;
	}
}
