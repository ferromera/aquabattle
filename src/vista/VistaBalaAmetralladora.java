package vista;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import excepciones.NoPudoLeerXMLExeption;

import titiritero.ControladorJuego;
import titiritero.Dibujable;
import titiritero.Posicionable;
import titiritero.SuperficieDeDibujo;
import titiritero.vista.Animacion;
import titiritero.vista.Imagen;
import utils.Direccion;
import misc.ContadorDeInstancias;
import misc.DiccionarioDeSerializables;
import misc.Observador;
import modelo.ArmaTiradaLanzaCohetes;
import modelo.armamento.BalaAmetralladora;

public class VistaBalaAmetralladora extends Vista implements Observador {
	private long id = ContadorDeInstancias.getId();

	private BalaAmetralladora bala;
	private Imagen sprite;
	private static final int ORDEN = 3;

	public static final String TAG = "objeto-vista-bala-ametralladora";

	private static final String RUTA_SPRITE = "/sprites/SpriteBalaAmetralladora.png";

	private static final String TAG_BALA = "bala";

	public VistaBalaAmetralladora() {

	}

	public VistaBalaAmetralladora(BalaAmetralladora bala) {
		this.bala = bala;
		bala.adscribir(this);
		sprite = new Imagen(RUTA_SPRITE, bala);
		orden = ORDEN;
		actualizar();

	}


	public void dibujar(SuperficieDeDibujo sup) {
		sprite.dibujar(sup);
	}

	public Posicionable getPosicionable() {
		return bala;
	}

	public void setPosicionable(Posicionable bala) {
		this.bala = (BalaAmetralladora) bala;
		sprite.setPosicionable(bala);
	}

	public void setBala(BalaAmetralladora bala) {
		setPosicionable(bala);
	}

	public BalaAmetralladora getBala() {
		return bala;
	}

	@Override
	public void actualizar() {
		if (bala.estaDestruida()) {
			VistaEscenario.getInstancia().borrarVista(this);
		} else
			actualizarOrientacion();
	}

	private void actualizarOrientacion() {
		switch (bala.getOrientacion().get()) {
		case Direccion.NORTE:
			sprite.orientarArriba();
			break;
		case Direccion.SUR:
			sprite.orientarAbajo();
			break;
		case Direccion.ESTE:
			sprite.orientarDerecha();
			break;
		case Direccion.OESTE:
			sprite.orientarIzquierda();
			break;

		}
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

		elem = doc.createElement(TAG_BALA);
		element.appendChild(elem);
		elem.appendChild(bala.getElementoXML(doc));

		return element;
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
				if (elem.getTagName().equals(TAG_BALA)){
					NodeList nodes=elem.getChildNodes();
					int j;
					for(j=0;j<nodes.getLength();j++)
						if (nodes.item(j).getNodeType() == Node.ELEMENT_NODE)
							break;
					bala= (BalaAmetralladora) DiccionarioDeSerializables.getInstancia((Element)nodes.item(j));
				}
					
			}
		}
		bala.adscribir(this);
		sprite = new Imagen(RUTA_SPRITE, bala);
		orden = ORDEN;
		actualizar();

	}
}
