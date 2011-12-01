package modelo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.Timer;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import excepciones.NoSePudoPosicionarException;

import titiritero.ObjetoVivo;

public abstract class ArmaTirada extends ElementoRectangularIntangible
		implements ObjetoVivo, ActionListener {

	public static final String TAG = "objeto-arma-tirada";
	private static final String TAG_BORRADO = "borrado";
	private static final String TAG_PAUSADO = "pausado";
	private static final String TAG_RESTANTE = "restante";
	private static final String TAG_TIEMPO_INICO = "tiempo-inicio";

	private boolean borrado;
	private boolean pausado;
	private int restante;
	private Timer timer;
	private long tiempoInicio;

	public ArmaTirada() {

	}

	public ArmaTirada(double posX, double posY, int tiempoDeVida)
			throws NoSePudoPosicionarException {
		this.setX(posX);
		this.setY(posY);
		restante = tiempoDeVida;
		timer = new Timer(tiempoDeVida, this);
		timer.setRepeats(false);
		timer.start();
		tiempoInicio = new Date().getTime();
		borrado = false;
		pausado = false;
	}

	

	public void actionPerformed(ActionEvent e) {
		destruir();
	}

	public void vivir() {
		if (borrado || pausado)
			return;
		TanqueHeroe tanque = TanqueHeroe.getInstancia();

		if (this.superpuestoCon(tanque)) {
			aplicarEfecto(tanque);
			destruir();
		}
	}

	protected abstract void aplicarEfecto(Tanque tanque);

	protected void destruir() {
		borrado = true;
		Escenario.getActual().borrarObjeto(this);
		Escenario.getActual().borrarObjetoVivo(this);
		notificar();
	}

	public void pausar() {
		if (pausado)
			return;
		pausado = true;
		restante -= (int) (new Date().getTime() - tiempoInicio);
		timer.stop();
	}

	public void reanudar() {
		if (!pausado)
			return;
		pausado = false;
		tiempoInicio = new Date().getTime();
		timer = new Timer(restante, this);
		timer.setRepeats(false);
		timer.start();
	}

	public boolean estaBorrado() {
		return borrado;
	}

	@Override
	public Element getElementoXML(Document doc) {
		Element element = doc.createElement(TAG);
		element.appendChild(super.getElementoXML(doc));
		Element elem = doc.createElement(TAG_BORRADO);
		element.appendChild(elem);
		elem.setTextContent(Boolean.toString(borrado));

		elem = doc.createElement(TAG_PAUSADO);
		element.appendChild(elem);
		elem.setTextContent(Boolean.toString(pausado));

		elem = doc.createElement(TAG_RESTANTE);
		element.appendChild(elem);
		elem.setTextContent(Integer.toString(restante));

		elem = doc.createElement(TAG_TIEMPO_INICO);
		element.appendChild(elem);
		elem.setTextContent(Long.toString(tiempoInicio));
		return element;
	}

	@Override
	public void fromElementoXML(Element element) {
		super.fromElementoXML((Element) element.getElementsByTagName(
				ElementoRectangularIntangible.TAG).item(0));
		NodeList hijos;
		Element elem;
		hijos = element.getChildNodes();
		if (hijos != null && hijos.getLength() > 0) {
			for (int i = 0; i < hijos.getLength(); i++) {
				if (hijos.item(i).getNodeType() != Node.ELEMENT_NODE)
					continue;
				elem = (Element) hijos.item(i);
				if (elem.getTagName().equals(TAG_BORRADO))
					borrado = Boolean.parseBoolean(elem.getTextContent());
				else if (elem.getTagName().equals(TAG_PAUSADO))
					pausado = Boolean.parseBoolean(elem.getTextContent());
				else if (elem.getTagName().equals(TAG_RESTANTE))
					restante = Integer.parseInt(elem.getTextContent());
				else if (elem.getTagName().equals(TAG_TIEMPO_INICO))
					tiempoInicio = Long.parseLong(elem.getTextContent());
			}
		}

	}

}
