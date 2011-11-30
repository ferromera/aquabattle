package modelo.armamento;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import misc.DiccionarioDeSerializables;
import misc.SerializableXML;
import modelo.Tanque;

import utils.Direccion;

public abstract class Arma implements ActionListener, SerializableXML {
	public static final String TAG = "objeto-arma";
	private static final String TAG_TANQUE = "tanque";
	private static final String TAG_CARGADA = "cargada";
	private static final String TAG_TIEMPO_CARGA = "tiempo-carga";

	private Tanque tanque;
	private boolean cargada = true;
	protected int tiempoCarga;
	private Timer timer;

	public Arma() {

	}


	public boolean equals(Arma a) {
		return (getClass().equals(a.getClass()));
	}

	public void mejorarTiempoCarga(double porcentaje) {
		tiempoCarga /= (1 + porcentaje);
	}

	public void empeorarTiempoCarga(double porcentaje) {
		tiempoCarga /= 1 - porcentaje;
	}

	public void disparar() {
		if (!cargada)
			return;

		descargar();
		Bala bala = crearBala();
		posicionarBala(bala);

	}

	private void posicionarBala(Bala bala) {
		double xBala = tanque.getX();
		double yBala = tanque.getY();
		switch (tanque.getOrientacion().get()) {
		case Direccion.NORTE:
			yBala -= bala.getAlto() + 1;
			// centrada en X
			xBala += (tanque.getAncho() - bala.getAncho()) / 2;
			bala.setOrientacion(Direccion.Norte());
			break;
		case Direccion.SUR:
			yBala += tanque.getAlto() + 1;
			// centrada en X
			xBala += (tanque.getAncho() - bala.getAncho()) / 2;
			bala.setOrientacion(Direccion.Sur());
			break;
		case Direccion.ESTE:
			xBala += tanque.getAncho() + 1;
			// centrada en Y
			yBala += (tanque.getAlto() - bala.getAlto()) / 2;
			bala.setOrientacion(Direccion.Este());
			break;
		case Direccion.OESTE:
			xBala -= bala.getAncho() + 1;
			// centrada en Y
			yBala += (tanque.getAlto() - bala.getAlto()) / 2;
			bala.setOrientacion(Direccion.Oeste());
			break;
		}
		bala.setX(xBala);
		bala.setY(yBala);
		bala.reanudar();
	}

	/*
	 * Debe crear la instancia de bala correspondiente y actualizar la municion
	 * del arma.
	 */
	protected abstract Bala crearBala();

	public abstract boolean tieneMunicion();

	private void destruir() {
		if (tanque != null)
			tanque.quitarArma();
		tanque = null;
	}

	private void descargar() {
		cargada = false;
		if (timer != null)
			timer.stop();
		timer = new Timer(tiempoCarga, this);
		timer.start();
	}

	public void actionPerformed(ActionEvent e) {
		cargada = true;
		if (!tieneMunicion()) {
			destruir();
		}
		timer.stop();

	}

	public void setTanque(Tanque tanque) {
		this.tanque = tanque;
		tanque.agregarArma(this);
	}

	public Tanque getTanque() {
		return tanque;
	}

	public void setTiempoDeCarga(int tiempoEntreDisparos) {
		tiempoCarga = tiempoEntreDisparos;

	}

	@Override
	public Element getElementoXML(Document doc) {
		Element element = doc.createElement(TAG);
		Element elem = doc.createElement(TAG_TANQUE);
		element.appendChild(elem);
		elem.appendChild(tanque.getElementoXML(doc));

		elem = doc.createElement(TAG_CARGADA);
		element.appendChild(elem);
		elem.setTextContent(Boolean.toString(cargada));

		elem = doc.createElement(TAG_TIEMPO_CARGA);
		element.appendChild(elem);
		elem.setTextContent(Integer.toString(tiempoCarga));

		return element;
	}

	public void fromElementoXML(Element element) {
		NodeList hijos;
		Element elem;
		hijos = element.getChildNodes();
		if (hijos != null && hijos.getLength() > 0) {
			for (int i = 0; i < hijos.getLength(); i++) {
				if (hijos.item(i).getNodeType() != Node.ELEMENT_NODE)
					continue;
				elem = (Element) hijos.item(i);
				if (elem.getTagName().equals(TAG_TANQUE)){
					NodeList nodes=elem.getChildNodes();
					int j;
					for(j=0;j<nodes.getLength();j++)
						if (nodes.item(j).getNodeType() == Node.ELEMENT_NODE)
							break;
					tanque= (Tanque) DiccionarioDeSerializables.getInstancia((Element)nodes.item(j));
				}
				else if (elem.getTagName().equals(TAG_CARGADA))
					cargada = Boolean.parseBoolean(elem.getTextContent());
				else if (elem.getTagName().equals(TAG_TIEMPO_CARGA))
					tiempoCarga = Integer.parseInt(elem.getTextContent());
			}
		}
		descargar();

	}

}
