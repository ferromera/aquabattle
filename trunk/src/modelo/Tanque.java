package modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import misc.ContadorDeInstancias;
import misc.DiccionarioDeSerializables;
import misc.Observador;
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
	public static final String TAG = "objeto-tanque";
	private static final String TAG_RESISTENCIA = "resistencia";
	private static final String TAG_VELOCIDAD = "velocidad";
	private static final String TAG_MOVIENDOSE = "moviendose";
	private static final String TAG_DESTRUIDO = "destruido";
	private static final String TAG_PAUSADO = "pausado";
	private static final String TAG_DISPARO_MEJORADO = "disparo-mejorado";
	private static final String TAG_MEJORA_DISPARO = "mejora-disparo";
	private static final String TAG_ARMAS = "armas";
	private static final String TAG_ARMA_ACTUAL = "arma-actual";
	private static final String TAG_MEJORAS = "mejoras";
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
		pausado = true;
		mejoras = new ArrayList<MejoraTanque>();
		disparoMejorado = false;
		mejoraDisparo = 0;
	}

	public void vivir() {
		if (destruido || pausado)
			return;
		long tiempoActual = new Date().getTime();
		if (enMovimiento()) {
			int intervaloTiempo = (int) (tiempoActual - ultimoTiempo);
			ultimoTiempo = tiempoActual;
			double movimientoRestante = (velocidad * (double) intervaloTiempo / 1000.0);

			while (movimientoRestante > 1.0) {
				movimientoRestante--;
				avanzar();
				if (estaColisionado() || fueraDeEscenario()) {
					retroceder();
					calcularSiguienteMovimiento();
					return;
				}
			}
			avanzar(movimientoRestante);
			if (estaColisionado() || fueraDeEscenario()) {
				retroceder(movimientoRestante);
			}
		} else {
			ultimoTiempo = tiempoActual;
		}
		calcularSiguienteMovimiento();
	}

	/*
	 * Debe definir la logica que determine el siguiente movimiento.
	 */
	public abstract void calcularSiguienteMovimiento();

	public void disparar() {
		if (pausado)
			return;
		armaActual.disparar();
	}

	public void mover(Direccion dir) {
		if (pausado)
			return;
		setOrientacion(dir);
		if (!moviendose) {
			moviendose = true;
			notificar();
		}
		moviendose = true;

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
		destruido = true;
		notificar();

	}

	public boolean enMovimiento() {
		return moviendose;
	}

	public void siguienteArma() {
		if (pausado)
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
		if (disparoMejorado)
			arma.mejorarTiempoCarga(mejoraDisparo);

	}

	public void seleccionarArma(Arma arma)
			throws NoExisteArmaSeleccionadaException {

		itArmaActual = armas.iterator();
		while (itArmaActual.hasNext()) {
			armaActual = itArmaActual.next();
			if (armaActual.equals(arma)) {
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
		if (disparoMejorado)
			return;
		disparoMejorado = true;
		mejoraDisparo = porcentaje;
		Iterator<Arma> it = armas.iterator();
		while (it.hasNext()) {
			it.next().mejorarTiempoCarga(porcentaje);
		}
		notificar();
	}

	public void empeorarVelocidad(double porcentaje) {
		velocidad *= 1 - porcentaje;

	}

	public void empeorarVelocidadDisparo(double porcentaje) {
		if (!disparoMejorado)
			return;
		disparoMejorado = false;
		mejoraDisparo = 0;
		Iterator<Arma> it = armas.iterator();
		while (it.hasNext()) {
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

	public void setVelocidad(double vel) {
		velocidad = vel;
	}

	public boolean tieneDisparoMejorado() {
		return disparoMejorado;
	}

	public void pausar() {
		if (pausado)
			return;
		pausado = true;
		Iterator<MejoraTanque> it = mejoras.iterator();
		while (it.hasNext()) {
			it.next().pausar();
		}
	}

	public void reanudar() {
		if (!pausado)
			return;
		pausado = false;
		System.out.println("reanudar()");
		ultimoTiempo = new Date().getTime();
		Iterator<MejoraTanque> it = mejoras.iterator();
		while (it.hasNext()) {
			it.next().reanudar();
		}

	}

	@Override
	public Element getElementoXML(Document doc) {
		Element element = doc.createElement(TAG);
		element.appendChild(super.getElementoXML(doc));

		Element elem = doc.createElement(TAG_RESISTENCIA);
		element.appendChild(elem);
		elem.setTextContent(Integer.toString(resistencia));

		elem = doc.createElement(TAG_VELOCIDAD);
		element.appendChild(elem);
		elem.setTextContent(Double.toString(velocidad));

		elem = doc.createElement(TAG_MOVIENDOSE);
		element.appendChild(elem);
		elem.setTextContent(Boolean.toString(moviendose));

		elem = doc.createElement(TAG_DESTRUIDO);
		element.appendChild(elem);
		elem.setTextContent(Boolean.toString(destruido));

		elem = doc.createElement(TAG_PAUSADO);
		element.appendChild(elem);
		elem.setTextContent(Boolean.toString(pausado));

		elem = doc.createElement(TAG_DISPARO_MEJORADO);
		element.appendChild(elem);
		elem.setTextContent(Boolean.toString(disparoMejorado));

		elem = doc.createElement(TAG_MEJORA_DISPARO);
		element.appendChild(elem);
		elem.setTextContent(Double.toString(mejoraDisparo));

		Iterator<Arma> itArma = armas.iterator();
		while (itArma.hasNext()) {
			elem = doc.createElement(TAG_ARMAS);
			element.appendChild(elem);
			elem.appendChild(itArma.next().getElementoXML(doc));
		}

		elem = doc.createElement(TAG_ARMA_ACTUAL);
		element.appendChild(elem);
		elem.appendChild(armaActual.getElementoXML(doc));

		Iterator<MejoraTanque> itMejora = mejoras.iterator();
		while (itMejora.hasNext()) {
			elem = doc.createElement(TAG_MEJORAS);
			element.appendChild(elem);
			elem.appendChild(itMejora.next().getElementoXML(doc));
		}

		return element;
	}

	@Override
	public void fromElementoXML(Element element) {
		super.fromElementoXML((Element) element.getElementsByTagName(
				ElementoRectangularSolido.TAG).item(0));
		armas = new ArrayList<Arma>();

		armaActual = null;
		ultimoTiempo = new Date().getTime();

		NodeList hijos;
		Element elem;
		hijos = element.getChildNodes();
		if (hijos != null && hijos.getLength() > 0) {
			for (int i = 0; i < hijos.getLength(); i++) {
				if (hijos.item(i).getNodeType() != Node.ELEMENT_NODE)
					continue;
				elem = (Element) hijos.item(i);
				if (elem.getTagName().equals(TAG_RESISTENCIA))
					resistencia = Integer.parseInt(elem.getTextContent());
				else if (elem.getTagName().equals(TAG_VELOCIDAD))
					velocidad = Double.parseDouble(elem.getTextContent());
				else if (elem.getTagName().equals(TAG_MOVIENDOSE))
					moviendose = Boolean.parseBoolean(elem.getTextContent());
				else if (elem.getTagName().equals(TAG_DESTRUIDO))
					destruido = Boolean.parseBoolean(elem.getTextContent());
				else if (elem.getTagName().equals(TAG_PAUSADO))
					pausado = Boolean.parseBoolean(elem.getTextContent());
				else if (elem.getTagName().equals(TAG_DISPARO_MEJORADO))
					disparoMejorado = Boolean.parseBoolean(elem
							.getTextContent());
				else if (elem.getTagName().equals(TAG_MEJORA_DISPARO))
					mejoraDisparo = Double.parseDouble(elem.getTextContent());
				else if (elem.getTagName().equals(TAG_ARMAS)){
					NodeList nodes=elem.getChildNodes();
					int j;
					for(j=0;j<nodes.getLength();j++)
						if (nodes.item(j).getNodeType() == Node.ELEMENT_NODE)
							break;
					armas.add((Arma) DiccionarioDeSerializables.getInstancia((Element)nodes.item(j)));
				}
				else if (elem.getTagName().equals(TAG_ARMA_ACTUAL)){
					NodeList nodes=elem.getChildNodes();
					int j;
					for(j=0;j<nodes.getLength();j++)
						if (nodes.item(j).getNodeType() == Node.ELEMENT_NODE)
							break;
					armaActual=(Arma) DiccionarioDeSerializables.getInstancia((Element)nodes.item(j));
				}
				else if (elem.getTagName().equals(TAG_MEJORAS)){
					NodeList nodes=elem.getChildNodes();
					int j;
					for(j=0;j<nodes.getLength();j++)
						if (nodes.item(j).getNodeType() == Node.ELEMENT_NODE)
							break;
					mejoras.add((MejoraTanque) DiccionarioDeSerializables.getInstancia((Element)nodes.item(j)));
				}
			}
		}
		itArmaActual = armas.iterator();
		while (itArmaActual.hasNext()) {
			if (itArmaActual.next() == armaActual)
				break;
		}

	}

}
