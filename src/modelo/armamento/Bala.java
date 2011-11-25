package modelo.armamento;

import java.util.Date;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import misc.FabricaElementos;
import modelo.Bonus;
import modelo.ElementoRectangularSolido;
import modelo.Escenario;
import modelo.Explosion;

import excepciones.NoExisteElementoColisionadoException;
import excepciones.NoPudoLeerXMLExeption;
import titiritero.ObjetoVivo;
import utils.Direccion;

public abstract class Bala extends ElementoRectangularSolido implements
		ObjetoVivo {
	public static final String TAG = "bala";
	private static final String TAG_DESTRUIDA = "destruida";
	private static final String TAG_PAUSADO = "pausado";
	private static final String TAG_FUERZA = "fuerza";
	private static final String TAG_VELOCIDAD = "velocidad";
	private boolean destruida;
	private boolean pausado;
	private long ultimoTiempo;
	protected int fuerza;
	protected double velocidad;

	public Bala() {
		setX(0.0);
		setY(0.0);
		destruida = false;
		fuerza = 10;
		velocidad = 150.0;
		setOrientacion(Direccion.Norte());
		ultimoTiempo = new Date().getTime();
		pausado = true;

	}

	public Bala(Element element) throws NoPudoLeerXMLExeption {
		super((Element) element.getElementsByTagName(
				ElementoRectangularSolido.TAG).item(0));
		destruida = false;
		fuerza = 10;
		velocidad = 150.0;
		ultimoTiempo = new Date().getTime();
		pausado = true;
		NodeList nodo;
		Element elem;
		nodo = element.getElementsByTagName(TAG_DESTRUIDA);
		if (nodo != null && nodo.getLength() > 0) {
			if (nodo.getLength() > 1)
				throw new NoPudoLeerXMLExeption(
						"No puede haber mas de un tag: " + TAG_DESTRUIDA
								+ " en el nodo " + element.getTagName());
			elem = (Element) nodo.item(0);
			destruida = Boolean.parseBoolean(elem.getNodeValue());
		}
		nodo = element.getElementsByTagName(TAG_PAUSADO);
		if (nodo != null && nodo.getLength() > 0) {
			if (nodo.getLength() > 1)
				throw new NoPudoLeerXMLExeption(
						"No puede haber mas de un tag: " + TAG_PAUSADO
								+ " en el nodo " + element.getTagName());
			elem = (Element) nodo.item(0);
			pausado = Boolean.parseBoolean(elem.getNodeValue());
		}
		nodo = element.getElementsByTagName(TAG_FUERZA);
		if (nodo != null && nodo.getLength() > 0) {
			if (nodo.getLength() > 1)
				throw new NoPudoLeerXMLExeption(
						"No puede haber mas de un tag: " + TAG_FUERZA
								+ " en el nodo " + element.getTagName());
			elem = (Element) nodo.item(0);

			fuerza = Integer.parseInt(elem.getNodeValue());
		}
		nodo = element.getElementsByTagName(TAG_VELOCIDAD);
		if (nodo != null && nodo.getLength() > 0) {
			if (nodo.getLength() > 1)
				throw new NoPudoLeerXMLExeption(
						"No puede haber mas de un tag: " + TAG_VELOCIDAD
								+ " en el nodo " + element.getTagName());
			elem = (Element) nodo.item(0);
			velocidad = Double.parseDouble(elem.getNodeValue());
		}

	}

	public void vivir() {
		if (destruida)
			return;

		long tiempoActual = new Date().getTime();
		if (pausado) {
			ultimoTiempo = tiempoActual;
			pausado = false;
			return;
		}
		int intervaloTiempo = (int) (tiempoActual - ultimoTiempo);
		ultimoTiempo = tiempoActual;
		double movimientoRestante = (velocidad * (double) intervaloTiempo / 1000.0);

		while (movimientoRestante > 1.0) {
			movimientoRestante--;
			avanzar();
			if (fueraDeEscenario()){
				destruir();
				return;
			}
			if (estaColisionado()) {
				try {
					impactar(getColisionado());
				} catch (NoExisteElementoColisionadoException e) {
					e.printStackTrace();
				}
				return;
			}
		}
		avanzar(movimientoRestante);
		if (fueraDeEscenario()){
			destruir();
			return;
		}
		if (estaColisionado()) {
			try {
				impactar(getColisionado());
			} catch (NoExisteElementoColisionadoException e) {
				e.printStackTrace();
			}
		}
	}

	protected void impactar(ElementoRectangularSolido solido) {
		if (solido != null)
			solido.recibirImpacto(fuerza);
		destruir();
	}

	public void recibirImpacto(int fuerza) {
		destruir();
	}

	protected void destruir() {
		posicionarExplosion(FabricaElementos.crearExplosion());
		Escenario.getActual().borrarSolido(this);
		Escenario.getActual().borrarObjetoVivo(this);
		destruida = true;
		notificar();
	}

	private void posicionarExplosion(Explosion explosion) {
		double x = getX();
		double y = getY();
		switch (getOrientacion().get()) {
		case Direccion.NORTE:
			// centrada en Y
			y -= explosion.getAlto() / 2;
			// centrada en X
			x += (getAncho() - explosion.getAncho()) / 2;
			break;
		case Direccion.SUR:
			// centrada en Y
			y += (getAlto() - explosion.getAlto()) / 2;
			// centrada en X
			x += (getAncho() - explosion.getAncho()) / 2;
			break;
		case Direccion.ESTE:
			// centrada en X
			x += (getAncho() - explosion.getAncho()) / 2;
			// centrada en Y
			y += (getAlto() - explosion.getAlto()) / 2;
			break;
		case Direccion.OESTE:
			//centrada en X
			x-= explosion.getAncho()/2;
			// centrada en Y
			y += (getAlto() - explosion.getAlto()) / 2;
			break;
		}
		explosion.setX(x);
		explosion.setY(y);
	}

	public boolean estaDestruida() {
		return destruida;
	}

	public int getResistencia() {
		return 1;
	}

	public void pausar() {
		pausado = true;
	}

}
