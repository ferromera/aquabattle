package modelo;

import java.util.ArrayList;
import java.util.Iterator;

import excepciones.NoExisteArmaSeleccionadaException;

import titiritero.ObjetoVivo;

public abstract class Tanque extends ElementoRectangularSolido implements
		ObjetoVivo {
	private ArrayList<Arma> armas;
	private Arma armaActual;
	private Iterator<Arma> itArmaActual;
	private int vida;
	private boolean moviendose;

	public Tanque() {

		moviendose = false;
		orientarNorte();
		vida = 100;
		armas = new ArrayList<Arma>();
		itArmaActual = armas.iterator();
		this.armaActual = null;
	}

	public abstract void vivir();

	public void disparar() {
		armaActual.disparar();
	}

	public void moverNorte() {
		orientarNorte();
		moviendose = true;
	}

	public void moverSur() {
		orientarNorte();
		moviendose = true;
	}

	public void moverOeste() {
		orientarNorte();
		moviendose = true;
	}

	public void moverEste() {
		orientarNorte();
		moviendose = true;
	}

	public void detener() {
		moviendose = false;
	}

	protected void setVida(int vida) {
		this.vida = vida;
	}

	public int getVida() {
		return vida;
	}

	public boolean enMovimiento() {
		return moviendose;
	}

	public void siguienteArma() {
		if (itArmaActual.hasNext())
			armaActual = itArmaActual.next();
		else {
			itArmaActual = armas.iterator();
			if (itArmaActual.hasNext())
				armaActual = itArmaActual.next();
			else
				armaActual = null;

		}
	}

	public void agregarArma(Arma arma) {
		armas.remove(arma);
		armas.add(arma);
		try {
			seleccionarArma(arma);
		} catch (NoExisteArmaSeleccionadaException e) {
			e.printStackTrace();
		}

	}

	public void seleccionarArma(Arma arma)
			throws NoExisteArmaSeleccionadaException {

		itArmaActual = armas.iterator();
		while (itArmaActual.hasNext()) {
			armaActual = itArmaActual.next();
			if (armaActual.equals(arma))
				return;
		}
		throw new NoExisteArmaSeleccionadaException();
	}

	public void quitarArma() {
		armas.remove(armaActual);
		itArmaActual=armas.iterator();
		siguienteArma();
	}

}
