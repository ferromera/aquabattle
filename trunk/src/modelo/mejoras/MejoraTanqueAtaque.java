package modelo.mejoras;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.Timer;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import misc.ContadorDeInstancias;
import misc.DiccionarioDeSerializables;

import modelo.Tanque;


public class MejoraTanqueAtaque extends MejoraTanque implements ActionListener {
	public static final String TAG = "objeto-mejora-tanque-ataque";

	private long id = ContadorDeInstancias.getId();

	private double PORCENTAJE_VELOCIDAD;
	private double PORCENTAJE_DISPARO;
	private int restante;
	private static final int DURACION = 10000;

	private static final String TAG_PORCENTAJE_DISPARO = "porcentaje-disparo";
	private static final String TAG_PORCENTAJE_VELOCIDAD = "porcentaje-velocidad";
	private static final String TAG_RESTANTE = "restante";
	private static final String TAG_TANQUE = "tanque";

	private Timer timer = null;
	private Tanque tanque = null;
	private long tiempoInicio;

	public MejoraTanqueAtaque() {

	}

	public MejoraTanqueAtaque(double porcentajeVelocidad,
			double porcentajeDisparo, Tanque tanque) {
		PORCENTAJE_VELOCIDAD = porcentajeVelocidad;
		PORCENTAJE_DISPARO = porcentajeDisparo;
		this.tanque = tanque;
		tanque.agregarMejora(this);
		restante = DURACION;

	}

	public void mejorar() {

		tanque.mejorarVelocidad(PORCENTAJE_VELOCIDAD);
		tanque.mejorarVelocidadDisparo(PORCENTAJE_DISPARO);
		tiempoInicio = new Date().getTime();
		timer = new Timer(DURACION, this);
		timer.setRepeats(false);
		timer.start();

	}

	public void deshacer() {
		tanque.empeorarVelocidad(1 - 1 / (1 + PORCENTAJE_VELOCIDAD));
		tanque.empeorarVelocidadDisparo(1 - 1 / (1 + PORCENTAJE_DISPARO));
	}

	public void pausar() {
		restante -= (int) (new Date().getTime() - tiempoInicio);
		timer.stop();
	}

	public void reanudar() {
		tiempoInicio = new Date().getTime();
		timer = new Timer(restante, this);
		timer.setRepeats(false);
		timer.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		tanque.quitarMejora(this);

	}

	@Override
	public Element getElementoXML(Document doc) {
		Element element = doc.createElement(TAG);
		Element elem = doc.createElement(ContadorDeInstancias.TAG_ID);
		element.appendChild(elem);
		elem.setTextContent(Long.toString(id));
		if (DiccionarioDeSerializables.fueSerializado(id))
			return element;
		DiccionarioDeSerializables.marcarSerializado(id);

		elem = doc.createElement(TAG_PORCENTAJE_DISPARO);
		element.appendChild(elem);
		elem.setTextContent(Double.toString(PORCENTAJE_DISPARO));

		elem = doc.createElement(TAG_PORCENTAJE_VELOCIDAD);
		element.appendChild(elem);
		elem.setTextContent(Double.toString(PORCENTAJE_VELOCIDAD));

		elem = doc.createElement(TAG_RESTANTE);
		element.appendChild(elem);
		elem.setTextContent(Integer.toString(restante));

		elem = doc.createElement(TAG_TANQUE);
		element.appendChild(elem);
		elem.appendChild(tanque.getElementoXML(doc));

		return element;
	}

	@Override
	public void fromElementoXML(Element element) {
		double porcentajeDisparo = 0, porcentajeVelocidad = 0;
		NodeList hijos;
		Element elem;
		hijos = element.getChildNodes();
		if (hijos != null && hijos.getLength() > 0) {
			for (int i = 0; i < hijos.getLength(); i++) {
				if (hijos.item(i).getNodeType() != Node.ELEMENT_NODE)
					continue;
				elem = (Element) hijos.item(i);
				if (elem.getTagName().equals(TAG_PORCENTAJE_DISPARO))
					porcentajeDisparo = Double.parseDouble(elem
							.getTextContent());
				else if (elem.getTagName().equals(TAG_PORCENTAJE_VELOCIDAD))
					porcentajeVelocidad = Double.parseDouble(elem
							.getTextContent());
				else if (elem.getTagName().equals(TAG_RESTANTE))
					restante = Integer.parseInt(elem.getTextContent());
				else if (elem.getTagName().equals(TAG_TANQUE)){
					NodeList nodes=elem.getChildNodes();
					int j;
					for(j=0;j<nodes.getLength();j++)
						if (nodes.item(j).getNodeType() == Node.ELEMENT_NODE)
							break;
					tanque=(Tanque)DiccionarioDeSerializables.getInstancia((Element)nodes.item(j));
				}
			}
		}
		PORCENTAJE_DISPARO = porcentajeDisparo;
		PORCENTAJE_VELOCIDAD = porcentajeVelocidad;

	}

}
