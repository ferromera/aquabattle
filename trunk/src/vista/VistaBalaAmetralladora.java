package vista;

import org.w3c.dom.Element;
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
	private long id=ContadorDeInstancias.getId();
	
	private BalaAmetralladora bala;
	private Imagen sprite;
	private static final int ORDEN=3;

	public static final String TAG = "objeto-vista-bala-ametralladora";
	
	
	private static final String RUTA_SPRITE = "/sprites/SpriteBalaAmetralladora.png";

	private static final String TAG_BALA = "bala"; 
	
	public VistaBalaAmetralladora(BalaAmetralladora bala) {
		this.bala = bala;
		bala.adscribir(this);
		sprite = new Imagen(RUTA_SPRITE, bala);
		orden=ORDEN;
		actualizar();
		
	}

	public VistaBalaAmetralladora(Element element) throws NoPudoLeerXMLExeption {
		NodeList hijos;
		Element elem;
		hijos = element.getChildNodes();
		if (hijos != null && hijos.getLength() > 0) {
			for (int i = 0; i < hijos.getLength(); i++) {
				elem = (Element) hijos.item(i);
				if (elem.getTagName().equals(TAG_BALA))
					bala = (BalaAmetralladora) DiccionarioDeSerializables
							.getInstancia((Element) elem.getFirstChild());
			}
		}
		bala.adscribir(this);
		sprite = new Imagen(RUTA_SPRITE, bala);
		orden=ORDEN;
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
		if(bala.estaDestruida()){
			VistaEscenario.getInstancia().borrarVista(this);
		}
		else
			actualizarOrientacion();
	}

	private void actualizarOrientacion(){
			switch(bala.getOrientacion().get()){
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
	
}
