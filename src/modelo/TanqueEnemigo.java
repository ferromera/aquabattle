package modelo;

import misc.ContadorDeInstancias;
import misc.DiccionarioDeSerializables;
import modelo.armamento.Arma;
import modelo.mejoras.MejoraTanque;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import pantallas.PantallaJuego;

import excepciones.NoPudoLeerXMLExeption;

public abstract class TanqueEnemigo extends Tanque {
	public static final String TAG = "objeto-tanque-enemigo";
	private static final String TAG_PUNTOS_OTORGADOS = "puntos-otorgados";
	private int puntosOtorgados = 20;

	public TanqueEnemigo() {
	}

	

	protected void destruir() {
		Escenario.getActual().borrarObjetoVivo(this);
		Escenario.getActual().borrarSolido(this);
		Escenario.getActual().borrarObjeto(this);
		PantallaJuego.getInstancia().sumarPuntos(puntosOtorgados);
		tirarArma();
		destruido = true;
		notificar();
	}

	protected abstract void tirarArma();

	protected void setPuntosOtorgados(int puntos) {
		puntosOtorgados = puntos;
	}

	@Override
	public Element getElementoXML(Document doc) {
		Element element = doc.createElement(TAG);
		element.appendChild(super.getElementoXML(doc));
		Element elem = doc.createElement(TAG_PUNTOS_OTORGADOS);
		element.appendChild(elem);
		elem.setTextContent(Integer.toString(puntosOtorgados));

		return element;
	}

	@Override
	public void fromElementoXML(Element element) {
		super.fromElementoXML((Element) element
				.getElementsByTagName(Tanque.TAG).item(0));
		NodeList hijos;
		Element elem;
		hijos = element.getChildNodes();
		if (hijos != null && hijos.getLength() > 0) {
			for (int i = 0; i < hijos.getLength(); i++) {
				if (hijos.item(i).getNodeType() != Node.ELEMENT_NODE)
					continue;
				elem = (Element) hijos.item(i);
				if (elem.getTagName().equals(TAG_PUNTOS_OTORGADOS))
					puntosOtorgados = Integer.parseInt(elem.getTextContent());
			}
		}

	}
}
