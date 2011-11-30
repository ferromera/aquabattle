package modelo;

import java.util.Iterator;

import misc.ContadorDeInstancias;
import misc.DiccionarioDeSerializables;
import misc.Observador;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import utils.Direccion;

import excepciones.NoExisteElementoColisionadoException;
import excepciones.NoPudoLeerXMLExeption;
import excepciones.NoSePudoPosicionarException;

/*
 * Representa todos los elementos del modelo que pueden chocar
 * entre ellos.
 */
public abstract class ElementoRectangularSolido extends ElementoRectangular
		implements Impactable {
	private static final String TAG_COLISIONADO = "elemento-colisionado";
	public static final String TAG = "objeto-elemento-rectangular-solido";

	public abstract void recibirImpacto(int fuerza);

	public abstract int getResistencia();

	private ElementoRectangularSolido elemColisionado;

	public ElementoRectangularSolido() {
		elemColisionado = null;
	}


	public ElementoRectangularSolido getColisionado()
			throws NoExisteElementoColisionadoException {
		if (!estaColisionado())
			throw new NoExisteElementoColisionadoException();
		return elemColisionado;
	}

	public boolean estaColisionado() {
		Escenario escenario = Escenario.getActual();
		Iterator<ElementoRectangularSolido> it = escenario.getSolidos();
		while (it.hasNext()) {
			ElementoRectangularSolido solido = it.next();
			if (superpuestoCon(solido)) {
				elemColisionado = solido;
				return true;
			}
		}
		return false;
	}

	public ElementoRectangularSolido getSolidoVistoSur() {
		Iterator<ElementoRectangularSolido> it = Escenario.getActual()
				.getSolidos();
		boolean primerVisto = true;
		ElementoRectangularSolido solido = null, solidoVisto = null;
		while (it.hasNext()) {
			solido = it.next();
			if (estaViendoSur(solido)) {
				if (primerVisto) {
					solidoVisto = solido;
					primerVisto = false;
				}
				if (solidoVisto != null && solidoVisto.getY() > solido.getY())
					solidoVisto = solido;
			}
		}
		return solidoVisto;
	}

	public boolean estaViendoSur(ElementoRectangularSolido solido) {
		if (getY() >= solido.getY())
			return false;
		if (getX() > solido.getX() + solido.getAncho())
			return false;
		if (getX() + getAncho() < solido.getX())
			return false;
		return true;

	}

	public ElementoRectangularSolido getSolidoVistoNorte() {
		Iterator<ElementoRectangularSolido> it = Escenario.getActual()
				.getSolidos();
		boolean primerVisto = true;
		ElementoRectangularSolido solido = null, solidoVisto = null;
		while (it.hasNext()) {
			solido = it.next();
			if (estaViendoNorte(solido)) {
				if (primerVisto) {
					solidoVisto = solido;
					primerVisto = false;
				}
				if (solidoVisto != null && solidoVisto.getY() < solido.getY())
					solidoVisto = solido;
			}
		}
		return solidoVisto;
	}

	public boolean estaViendoNorte(ElementoRectangularSolido solido) {
		if (getY() <= solido.getY())
			return false;
		if (getX() > solido.getX() + solido.getAncho())
			return false;
		if (getX() + getAncho() < solido.getX())
			return false;
		return true;

	}

	public ElementoRectangularSolido getSolidoVistoEste() {
		Iterator<ElementoRectangularSolido> it = Escenario.getActual()
				.getSolidos();
		boolean primerVisto = true;
		ElementoRectangularSolido solido = null, solidoVisto = null;
		while (it.hasNext()) {
			solido = it.next();
			if (estaViendoEste(solido)) {
				if (primerVisto) {
					solidoVisto = solido;
					primerVisto = false;
				} else if (solidoVisto != null
						&& solidoVisto.getX() > solido.getX())
					solidoVisto = solido;
			}
		}
		return solidoVisto;
	}

	public boolean estaViendoEste(ElementoRectangularSolido solido) {
		if (getX() >= solido.getX())
			return false;
		if (getY() > solido.getY() + solido.getAlto())
			return false;
		if (getY() + getAlto() < solido.getY())
			return false;
		return true;

	}

	public ElementoRectangularSolido getSolidoVistoOeste() {
		Iterator<ElementoRectangularSolido> it = Escenario.getActual()
				.getSolidos();
		boolean primerVisto = true;
		ElementoRectangularSolido solido = null, solidoVisto = null;
		while (it.hasNext()) {
			solido = it.next();
			if (estaViendoOeste(solido)) {
				if (primerVisto) {
					solidoVisto = solido;
					primerVisto = false;
				}
				if (solidoVisto != null && solidoVisto.getX() < solido.getX())
					solidoVisto = solido;
			}
		}
		return solidoVisto;
	}

	public boolean estaViendoOeste(ElementoRectangularSolido solido) {
		if (getX() <= solido.getX())
			return false;
		if (getY() > solido.getY() + solido.getAlto())
			return false;
		if (getY() + getAlto() < solido.getY())
			return false;
		return true;
	}

	/*
	 * posiciona el elemento en la posicion mas cercana en la cual no este
	 * superpuesto con otro solido
	 */
	public void posicionar() throws NoSePudoPosicionarException {
		int iteracion = 1;
		int maxIteracion = 500;
		double xInicial = getX();
		double yInicial = getY();
		if (!estaColisionado() && !fueraDeEscenario())
			return;
		while (iteracion < maxIteracion) {
			setX(xInicial + iteracion);
			setY(yInicial + iteracion);
			if (!estaColisionado() && !fueraDeEscenario())
				return;
			// me muevo hacia la izquierda
			while (getX() > xInicial - iteracion) {
				setX(getX() - 1);
				if (!estaColisionado() && !fueraDeEscenario())
					return;
			}
			// luego hacia abajo
			while (getY() < yInicial + iteracion) {
				setY(getY() + 1);
				if (!estaColisionado() && !fueraDeEscenario())
					return;
			}
			// hacia la derecha
			while (getX() < xInicial + iteracion) {
				setX(getX() + 1);
				if (!estaColisionado() && !fueraDeEscenario())
					return;
			}
			// y hacia arriba
			while (getY() > yInicial - iteracion + 1) {
				setY(getY() - 1);
				if (!estaColisionado() && !fueraDeEscenario())
					return;
			}
			iteracion++;
		}
		throw new NoSePudoPosicionarException(
				"No se pudo posicionar solido en ( " + xInicial + " ; "
						+ yInicial + " ).");

	}

	@Override
	public Element getElementoXML(Document doc) {
		Element element = doc.createElement(TAG);
		element.appendChild(super.getElementoXML(doc));
		if (elemColisionado != null) {
			Element elem = doc.createElement(TAG_COLISIONADO);
			element.appendChild(elem);
			elem.appendChild(elemColisionado.getElementoXML(doc));
		}

		return element;
	}

	@Override
	public void fromElementoXML(Element element) {
		super.fromElementoXML((Element) element.getElementsByTagName(
				ElementoRectangular.TAG).item(0));
		elemColisionado = null;
		NodeList hijos;
		Element elem;
		hijos = element.getChildNodes();
		if (hijos != null && hijos.getLength() > 0) {
			for (int i = 0; i < hijos.getLength(); i++) {
				if(hijos.item(i).getNodeType()!=Node.ELEMENT_NODE)continue;elem = (Element) hijos.item(i);
				if (elem.getTagName().equals(TAG_COLISIONADO)){
					NodeList nodes=elem.getChildNodes();
					int j;
					for(j=0;j<nodes.getLength();j++)
						if (nodes.item(j).getNodeType() == Node.ELEMENT_NODE)
							break;
					elemColisionado=(ElementoRectangularSolido) DiccionarioDeSerializables.getInstancia((Element)nodes.item(j));
				}
			}
		}

	}
}
