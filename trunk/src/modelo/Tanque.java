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
import utils.Direccion;

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
	protected boolean destruido;
	private boolean pausado;

	public Tanque() {

		moviendose = false;
		orientarNorte();
		resistencia = 100;
		velocidad = 100.0;
		armas = new ArrayList<Arma>();
		itArmaActual = armas.iterator();
		this.armaActual = null;
		ultimoTiempo = new Date().getTime();
		pausado=true;
	}

	public void vivir() {
		if(destruido)
			return;
		long tiempoActual = new Date().getTime();
		if(pausado){
			ultimoTiempo = tiempoActual;
			pausado=false;
		}
		if (enMovimiento()) {
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
		else{
			ultimoTiempo = tiempoActual;
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


	public void mover(Direccion dir){
		setOrientacion(dir);
		if(!moviendose){
			moviendose=true;
			notificar();
		}moviendose=true;
		
	}
	public void detener() {
		moviendose = false;
		notificar();
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
	
	public boolean estaDestruido() {
		return destruido;
	}
	protected void destruir() {
		Escenario.getActual().borrarObjetoVivo(this);
		Escenario.getActual().borrarSolido(this);
		destruido=true;
		notificar();
		
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
	public void setVelocidad(double vel){
		velocidad=vel;
	}
	public boolean estaMejorado(){
		return mejorado;
	}
	public void pausar(){
		pausado=true;
	}
	
}
