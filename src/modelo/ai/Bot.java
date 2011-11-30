package modelo.ai;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import utils.Direccion;
import excepciones.NoExisteElementoColisionadoException;
import misc.DiccionarioDeSerializables;
import misc.SerializableXML;
import modelo.ElementoRectangular;
import modelo.ElementoRectangularSolido;
import modelo.Tanque;
import modelo.TanqueEnemigo;

public abstract class Bot implements SerializableXML {
	public static final String TAG = "objeto-bot";
	private static final String TAG_TANQUE = "tanque";
	private static final String TAG_OBJETIVO = "objetivo";
	protected Tanque tanque;
	private ElementoRectangular objetivo;

	public Bot() {

	}

	public Bot(Tanque tanque, ElementoRectangular objetivo) {
		this.tanque = tanque;
		this.setObjetivo(objetivo);
	}

	public abstract void actuar();

	protected void moverPor(Direccion primaria, Direccion secundaria) {
		ElementoRectangularSolido colisionPrimaria = null;
		boolean finDeEscenarioPorPrimaria = false;

		tanque.avanzarEnDireccion(primaria);
		if (tanque.estaColisionado() || tanque.fueraDeEscenario()) {
			// Bloqueado por primaria
			try {
				if (tanque.fueraDeEscenario())
					finDeEscenarioPorPrimaria = true;
				else
					colisionPrimaria = tanque.getColisionado();
			} catch (NoExisteElementoColisionadoException e1) {
				e1.printStackTrace();
			}
			tanque.retrocederEnDireccion(primaria);
			tanque.avanzarEnDireccion(secundaria);
			if (tanque.estaColisionado() || tanque.fueraDeEscenario()) {
				// Bloqueado por primaria y secundaria
				tanque.retrocederEnDireccion(secundaria);
				if (finDeEscenarioPorPrimaria) {
					tanque.mover(secundaria);
					tanque.disparar();
				} else if (colisionPrimaria instanceof TanqueEnemigo) {
					// Enemigo en direccion primaria
					tanque.mover(secundaria);
					tanque.disparar();
				} else {
					// No hay enemigo en primaria
					tanque.mover(primaria);
					tanque.disparar();
				}
			} else {
				// Bloqueado primaria, secundaria libre
				tanque.retrocederEnDireccion(secundaria);
				tanque.mover(secundaria);
			}
		} else {
			// primaria libre
			tanque.retrocederEnDireccion(primaria);
			tanque.mover(primaria);
		}
	}

	protected void atacarEn(Direccion dir) {
		ElementoRectangularSolido visto = null;
		switch (dir.get()) {
		case Direccion.NORTE:
			visto = tanque.getSolidoVistoNorte();
			break;
		case Direccion.SUR:
			visto = tanque.getSolidoVistoSur();
			break;
		case Direccion.ESTE:
			visto = tanque.getSolidoVistoEste();
			break;
		case Direccion.OESTE:
			visto = tanque.getSolidoVistoOeste();
			break;
		}
		tanque.mover(dir);
		if (!(visto instanceof TanqueEnemigo))
			tanque.disparar();
	}

	public ElementoRectangular getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(ElementoRectangular objetivo) {
		this.objetivo = objetivo;
	}

	@Override
	public void fromElementoXML(Element element) {
		NodeList hijos;
		Element elem;
		hijos = element.getChildNodes();
		if (hijos != null && hijos.getLength() > 0) {
			for (int i = 0; i < hijos.getLength(); i++) {
				if (hijos.item(i).getNodeType() != Node.ELEMENT_NODE)
					continue;
				elem = (Element) hijos.item(i);
				if (elem.getTagName().equals(TAG_OBJETIVO)){
					NodeList nodes=elem.getChildNodes();
					int j;
					for(j=0;j<nodes.getLength();j++)
						if (nodes.item(j).getNodeType() == Node.ELEMENT_NODE)
							break;
					objetivo=(ElementoRectangular)DiccionarioDeSerializables.getInstancia((Element)nodes.item(j));
				}
					
				else if (elem.getTagName().equals(TAG_TANQUE)){
					NodeList nodes=elem.getChildNodes();
					int j;
					for(j=0;j<nodes.getLength();j++)
						if (nodes.item(j).getNodeType() == Node.ELEMENT_NODE)
							break;
					tanque=(Tanque) DiccionarioDeSerializables.getInstancia((Element)nodes.item(j));
				}
			}
		}
	}

	@Override
	public Element getElementoXML(Document doc) {
		Element element = doc.createElement(TAG);
		Element elem = doc.createElement(TAG_OBJETIVO);
		element.appendChild(elem);
		elem.appendChild(objetivo.getElementoXML(doc));

		elem = doc.createElement(TAG_TANQUE);
		element.appendChild(elem);
		elem.appendChild(tanque.getElementoXML(doc));

		return element;
	}

}
