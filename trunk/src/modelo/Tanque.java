package modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import modelo.armamento.Arma;
import modelo.mejoras.Mejora;
import modelo.mejoras.MejoraTanque;
import modelo.mejoras.Mejorable;

import excepciones.NoExisteArmaSeleccionadaException;
import excepciones.NoPudoLeerXMLExeption;

import titiritero.ObjetoVivo;
import utils.Direccion;

public abstract class Tanque extends ElementoRectangularSolido implements
		ObjetoVivo, Mejorable {
	public static final String TAG = "tanque";
	private static final String TAG_RESISTENCIA = "resistencia";
	private static final String TAG_VELOCIDAD = "velocidad";
	private static final String TAG_MOVIENDOSE = "moviendose";
	private static final String TAG_MEJORADO = "mejorado";
	private static final String TAG_DESTRUIDO = "destruido";
	private static final String TAG_PAUSADO = "pausado";
	private ArrayList<Arma> armas;
	private Arma armaActual;
	private Iterator<Arma> itArmaActual;
	private ArrayList<MejoraTanque> mejoras;
	protected int resistencia;
	protected double velocidad;
	private boolean moviendose;
	private long ultimoTiempo;
	protected boolean destruido;
	private boolean pausado;
	private boolean disparoMejorado;
	private double mejoraDisparo;

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
		mejoras=new ArrayList<MejoraTanque>();
		disparoMejorado=false;
		mejoraDisparo=0;
	}
	
	public Tanque(Element element) throws NoPudoLeerXMLExeption{
		super((Element)element.getElementsByTagName(ElementoRectangularSolido.TAG).item(0));
		//Por default
		moviendose = false;
		orientarNorte();
		resistencia = 100;
		velocidad = 100.0;
		armas = new ArrayList<Arma>();
		itArmaActual = armas.iterator();
		this.armaActual = null;
		ultimoTiempo = new Date().getTime();
		pausado=true;
		NodeList nodo;
		Element elem;
		nodo = element.getElementsByTagName(TAG_RESISTENCIA);
		if(nodo!=null && nodo.getLength()>0){
			if(nodo.getLength()>1)
				throw new NoPudoLeerXMLExeption("No puede haber mas de un tag: "+TAG_RESISTENCIA+" en el nodo "+element.getTagName());
			elem = (Element) nodo.item(0);
			resistencia=Integer.parseInt(elem.getTextContent());
		}
		nodo = element.getElementsByTagName(TAG_VELOCIDAD);
		if(nodo!=null && nodo.getLength()>0){
			if(nodo.getLength()>1)
				throw new NoPudoLeerXMLExeption("No puede haber mas de un tag: "+TAG_VELOCIDAD+" en el nodo "+element.getTagName());
			elem = (Element) nodo.item(0);
			velocidad=Double.parseDouble(elem.getTextContent());
		}
		nodo = element.getElementsByTagName(TAG_MOVIENDOSE);
		if(nodo!=null && nodo.getLength()>0){
			if(nodo.getLength()>1)
				throw new NoPudoLeerXMLExeption("No puede haber mas de un tag: "+TAG_MOVIENDOSE+" en el nodo "+element.getTagName());
			elem = (Element) nodo.item(0);
			moviendose=Boolean.parseBoolean(elem.getTextContent());
		}
		
		nodo = element.getElementsByTagName(TAG_DESTRUIDO);
		if(nodo!=null && nodo.getLength()>0){
			if(nodo.getLength()>1)
				throw new NoPudoLeerXMLExeption("No puede haber mas de un tag: "+TAG_DESTRUIDO+" en el nodo "+element.getTagName());
			elem = (Element) nodo.item(0);
			destruido=Boolean.parseBoolean(elem.getTextContent());
		}
		nodo = element.getElementsByTagName(TAG_PAUSADO);
		if(nodo!=null && nodo.getLength()>0){
			if(nodo.getLength()>1)
				throw new NoPudoLeerXMLExeption("No puede haber mas de un tag: "+TAG_PAUSADO+" en el nodo "+element.getTagName());
			elem = (Element) nodo.item(0);
			pausado=Boolean.parseBoolean(elem.getTextContent());
		}
		
	}

	public void vivir() {
		System.out.println(resistencia + " " + mejoraDisparo);
		if(destruido)
			return;
		long tiempoActual = new Date().getTime();
		if(pausado){
			ultimoTiempo = tiempoActual;
			pausado=false;
			reanudarMejoras();
		}
		if (enMovimiento()) {
			int intervaloTiempo = (int) (tiempoActual - ultimoTiempo);
			ultimoTiempo = tiempoActual;
			double movimientoRestante = (velocidad * (double) intervaloTiempo / 1000.0);

			while (movimientoRestante > 1.0) {
				movimientoRestante--;
				avanzar();
				if (estaColisionado()||fueraDeEscenario()) {
					retroceder();
					calcularSiguienteMovimiento();
					return;
				}
			}
			avanzar(movimientoRestante);
			if (estaColisionado()||fueraDeEscenario()) {
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
		if(pausado)
			return;
		armaActual.disparar();
	}


	public void mover(Direccion dir){
		if(pausado)
			return;
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
		if (resistencia <= 0) {
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
		if(pausado)
			return;
		if (itArmaActual.hasNext())
			armaActual = itArmaActual.next();
		else {
			itArmaActual = armas.iterator();
			if (itArmaActual.hasNext())
				armaActual = itArmaActual.next();
			else
				armaActual = null;
		}
		notificar();
	}

	public void agregarArma(Arma arma) {
		armas.remove(arma);
		armas.add(arma);
		try {
			seleccionarArma(arma);
		} catch (NoExisteArmaSeleccionadaException e) {
			e.printStackTrace();
		}
		if(disparoMejorado)
			arma.mejorarTiempoCarga(mejoraDisparo);

	}

	public void seleccionarArma(Arma arma)
			throws NoExisteArmaSeleccionadaException {

		itArmaActual = armas.iterator();
		while (itArmaActual.hasNext()) {
			armaActual = itArmaActual.next();
			if (armaActual.equals(arma)){
				notificar();
				return;
			}
				
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
		if(disparoMejorado)
			return;
		disparoMejorado=true;
		mejoraDisparo=porcentaje;
		Iterator<Arma> it = armas.iterator();
		while(it.hasNext()){
			it.next().mejorarTiempoCarga(porcentaje);
		}
		notificar();
	}

	public void empeorarVelocidad(double porcentaje) {
		velocidad *= 1 - porcentaje;

	}

	public void empeorarVelocidadDisparo(double porcentaje) {
		if(!disparoMejorado)
			return;
		disparoMejorado=false;
		mejoraDisparo=0;
		Iterator<Arma> it = armas.iterator();
		while(it.hasNext()){
			it.next().empeorarTiempoCarga(porcentaje);
		}
		notificar();
	}

	public void mejorarVida(double porcentaje) {
		resistencia *= 1 + porcentaje;
	}

	public void agregarMejora(Mejora mejora) {
		MejoraTanque mejoraTanque = (MejoraTanque) mejora;
		mejoraTanque.mejorar();
		mejoras.add(mejoraTanque);
	}

	public void quitarMejora(Mejora mejora) {
		MejoraTanque mejoraTanque = (MejoraTanque) mejora;
		mejoras.remove(mejoraTanque);
		mejoraTanque.deshacer();
		
	}
	public void setVelocidad(double vel){
		velocidad=vel;
	}
	public boolean tieneDisparoMejorado(){
		return disparoMejorado;
	}
	public void pausar(){
		pausado=true;
		Iterator<MejoraTanque> it = mejoras.iterator();
		while(it.hasNext()){
			it.next().pausar();
		}
	}
	private void reanudarMejoras() {
		Iterator<MejoraTanque> it = mejoras.iterator();
		while(it.hasNext()){
			it.next().reanudar();
		}
		
	}
	
}
