package modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import modelo.armamento.Arma;
import modelo.mejoras.Mejora;
import modelo.mejoras.MejoraTanque;
import modelo.mejoras.Mejorable;

import excepciones.NoExisteArmaSeleccionadaException;

import titiritero.ObjetoVivo;

public abstract class Tanque extends ElementoRectangularSolido implements
		ObjetoVivo, Mejorable {
	private ArrayList<Arma> armas;
	private Arma armaActual;
	private Iterator<Arma> itArmaActual;
	private ArrayList<MejoraTanque> mejoras;
	protected int resistencia;
	protected double velocidad;
	private boolean moviendose;
	private boolean mejorado;
	private long ultimoTiempo;

	public Tanque() {

		moviendose = false;
		orientarNorte();
		resistencia = 100;
		velocidad = 100.0;
		armas = new ArrayList<Arma>();
		itArmaActual = armas.iterator();
		this.armaActual = null;
		ultimoTiempo = new Date().getTime();
	}

	public void vivir() {
		if (enMovimiento()) {
			long tiempoActual = new Date().getTime();
			int intervaloTiempo = (int) (tiempoActual - ultimoTiempo);
			ultimoTiempo = tiempoActual;
			double movimientoRestante = (velocidad * (double) intervaloTiempo / 1000.0);

			while (movimientoRestante > 1.0) {
				movimientoRestante--;
				avanzar();
				if (estaColisionado()) {
					retroceder();
					calcularSiguienteMovimiento();
					return;
				}
			}
			avanzar(movimientoRestante);
			if (estaColisionado()) {
				retroceder(movimientoRestante);
			}
		}
		calcularSiguienteMovimiento();
	}
	/*
	 * Debe definir la logica que determine el siguiente
	 * movimiento.
	 */
	public abstract void calcularSiguienteMovimiento();

	public void disparar() {
		armaActual.disparar();
	}

	public void moverNorte() {
		orientarNorte();
		moviendose = true;
	}

	public void moverSur() {
		orientarSur();
		moviendose = true;
	}

	public void moverOeste() {
		orientarOeste();
		moviendose = true;
	}

	public void moverEste() {
		orientarEste();
		moviendose = true;
	}

	public void detener() {
		moviendose = false;
	}

	protected void setResistencia(int resistencia) {
		this.resistencia = resistencia;
	}

	public int getResistencia() {
		return resistencia;
	}

	public void recibirImpacto(int fuerza) {
		resistencia -= fuerza;
		if (resistencia < 0) {
			resistencia = 0;
			destruir();
		}
	}

	protected abstract void destruir();

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
		itArmaActual = armas.iterator();
		siguienteArma();
	}

	public Arma getArmaActual() {
		return armaActual;
	}

	public void mejorarVelocidad(double porcentaje) {
		velocidad *= 1 + porcentaje;

	}

	public void mejorarVelocidadDisparo(double porcentaje) {
		if (armaActual == null)
			return;
		armaActual.mejorarTiempoCarga(porcentaje);

	}

	public void empeorarVelocidad(double porcentaje) {
		velocidad *= 1 - porcentaje;

	}

	public void empeorarVelocidadDisparo(double porcentaje) {
		if (armaActual == null)
			return;
		armaActual.empeorarTiempoCarga(porcentaje);

	}

	public void mejorarVida(double porcentaje) {
		resistencia *= 1 + porcentaje;
	}

	public void agregarMejora(Mejora mejora) {
		MejoraTanque mejoraTanque = (MejoraTanque) mejora;
		mejoraTanque.mejorar(this);
		mejoras.add(mejoraTanque);
		if (!mejorado) {
			mejorado = true;
			notificar();
		}
	}

	public void quitarMejora(Mejora mejora) {
		MejoraTanque mejoraTanque = (MejoraTanque) mejora;
		mejoras.remove(mejoraTanque);
		mejoraTanque.deshacer(this);
		if (mejoras.isEmpty()) {
			mejorado = false;
			notificar();
		}
	}
}
