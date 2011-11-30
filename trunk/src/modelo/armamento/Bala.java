package modelo.armamento;

import java.util.Date;

import javax.rmi.CORBA.Tie;
import javax.swing.Timer;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import misc.ContadorDeInstancias;
import misc.DiccionarioDeSerializables;
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
	public static final String TAG = "objeto-bala";
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


	public void vivir() {
		if (destruida || pausado)
			return;

		long tiempoActual = new Date().getTime();
		int intervaloTiempo = (int) (tiempoActual - ultimoTiempo);
		ultimoTiempo = tiempoActual;
		double movimientoRestante = (velocidad * (double) intervaloTiempo / 1000.0);

		while (movimientoRestante > 1.0) {
			movimientoRestante--;
			avanzar();
			if (fueraDeEscenario()) {
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
		if (fueraDeEscenario()) {
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
			y += getAlto() - (explosion.getAlto()) / 2;
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
			// centrada en X
			x -= explosion.getAncho() / 2;
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
		if (pausado)
			return;
		pausado = true;

	}

	public void reanudar() {
		if (!pausado)
			return;
		pausado = false;
		ultimoTiempo = new Date().getTime();

	}

	public Element getElementoXML(Document doc) {
		Element element = doc.createElement(TAG);
		element.appendChild(super.getElementoXML(doc));
		Element elem = doc.createElement(TAG_DESTRUIDA);
		element.appendChild(elem);
		elem.setTextContent(Boolean.toString(destruida));

		elem = doc.createElement(TAG_PAUSADO);
		element.appendChild(elem);
		elem.setTextContent(Boolean.toString(pausado));

		elem = doc.createElement(TAG_FUERZA);
		element.appendChild(elem);
		elem.setTextContent(Integer.toString(fuerza));

		elem = doc.createElement(TAG_VELOCIDAD);
		element.appendChild(elem);
		elem.setTextContent(Double.toString(velocidad));

		return element;

	}

	public void fromElementoXML(Element element) {
		super.fromElementoXML((Element) element.getElementsByTagName(
				ElementoRectangularSolido.TAG).item(0));
		NodeList hijos;
		Element elem;
		hijos = element.getChildNodes();
		if (hijos != null && hijos.getLength() > 0) {
			for (int i = 0; i < hijos.getLength(); i++) {
				if (hijos.item(i).getNodeType() != Node.ELEMENT_NODE)
					continue;
				elem = (Element) hijos.item(i);
				if (elem.getTagName().equals(TAG_DESTRUIDA))
					destruida = Boolean.parseBoolean(elem.getTextContent());
				else if (elem.getTagName().equals(TAG_PAUSADO))
					pausado = Boolean.parseBoolean(elem.getTextContent());
				else if (elem.getTagName().equals(TAG_FUERZA))
					fuerza = Integer.parseInt(elem.getTextContent());
				else if (elem.getTagName().equals(TAG_VELOCIDAD))
					velocidad = Double.parseDouble(elem.getTextContent());
			}
		}
	}

}
