package modelo;

import java.util.Iterator;

import misc.DiccionarioDeSerializables;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

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
	public static final String TAG = "elemento-rectangular-solido";

	public abstract void recibirImpacto(int fuerza);

	public abstract int getResistencia();

	private ElementoRectangularSolido elemColisionado;

	public ElementoRectangularSolido() {
		elemColisionado = null;
	}

	public ElementoRectangularSolido(Element element)
			throws NoPudoLeerXMLExeption {
		super((Element) element.getElementsByTagName(ElementoRectangular.TAG)
				.item(0));
		elemColisionado = null;
		NodeList nodoColisionado = element
				.getElementsByTagName(TAG_COLISIONADO);
		if (nodoColisionado != null && nodoColisionado.getLength() > 0) {
			if (nodoColisionado.getLength() > 1)
				throw new NoPudoLeerXMLExeption(
						"No puede haber mas de un tag: " + TAG_COLISIONADO
								+ " en el nodo " + element.getTagName());
			Element elemDOMColisionado = (Element) nodoColisionado.item(0);
			elemColisionado = DiccionarioDeSerializables
					.getInstanciaColisionado(elemDOMColisionado);
		}
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
		ElementoRectangularSolido solido = null, solidoVisto = null;
		while (it.hasNext()) {
			solido = it.next();
			if (estaViendoSur(solido))
				if (solidoVisto != null && solidoVisto.getY() > solido.getY())
					solidoVisto = solido;
		}
		return solido;
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
		ElementoRectangularSolido solido = null, solidoVisto = null;
		while (it.hasNext()) {
			solido = it.next();
			if (estaViendoNorte(solido))
				if (solidoVisto != null && solidoVisto.getY() < solido.getY())
					solidoVisto = solido;
		}
		return solido;
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
		ElementoRectangularSolido solido = null, solidoVisto = null;
		while (it.hasNext()) {
			solido = it.next();
			if (estaViendoEste(solido))
				if (solidoVisto != null && solidoVisto.getX() > solido.getX())
					solidoVisto = solido;
		}
		return solido;
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
		ElementoRectangularSolido solido = null, solidoVisto = null;
		while (it.hasNext()) {
			solido = it.next();
			if (estaViendoOeste(solido))
				if (solidoVisto != null && solidoVisto.getX() < solido.getX())
					solidoVisto = solido;
		}
		return solido;
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
		int maxIteracion=500;
		double xInicial=getX();
		double yInicial=getY();
		if(!estaColisionado()&&!fueraDeEscenario())
			return;
		while (iteracion< maxIteracion) {
			setX(xInicial+iteracion);
			setY(yInicial+iteracion);
			if(!estaColisionado()&&!fueraDeEscenario())
				return;
			//me muevo hacia la izquierda
			while(getX()>xInicial-iteracion){
				setX(getX()-1);
				if(!estaColisionado()&&!fueraDeEscenario())
					return;
			}
			//luego hacia abajo
			while(getY()<yInicial+iteracion){
				setY(getY()+1);
				if(!estaColisionado()&&!fueraDeEscenario())
					return;
			}
			//hacia la derecha
			while(getX() < xInicial+iteracion){
				setX(getX()+1);
				if(!estaColisionado()&&!fueraDeEscenario())
					return;
			}
			//y hacia arriba
			while(getY() > yInicial-iteracion+1){
				setY(getY()-1);
				if(!estaColisionado()&&!fueraDeEscenario())
					return;
			}
			iteracion++;
		}
		throw new NoSePudoPosicionarException("No se pudo posicionar solido en ( "+xInicial+" ; "+yInicial+" ).");

	}
}
