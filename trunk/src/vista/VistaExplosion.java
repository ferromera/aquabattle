package vista;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import excepciones.NoPudoLeerXMLExeption;

import misc.ContadorDeInstancias;
import misc.DiccionarioDeSerializables;
import misc.Observador;
import modelo.Explosion;
import modelo.armamento.BalaAmetralladora;
import modelo.armamento.Cohete;
import titiritero.Posicionable;
import titiritero.SuperficieDeDibujo;
import titiritero.vista.Imagen;
import utils.Direccion;

public class VistaExplosion extends Vista implements Observador {
	private long id=ContadorDeInstancias.getId();
	
	private Explosion explosion;
	private Imagen sprite;
	private static final int ORDEN=4;

	public static final String TAG = "objeto-vista-explosion";

	private static final String TAG_EXPLOSION = "explosion";
	
	
	private final String RUTA_SPRITE = "/sprites/explosion.png"; 
	
	public VistaExplosion(Explosion explosion) {
		this.explosion = explosion;
		explosion.adscribir(this);
		sprite = new Imagen(RUTA_SPRITE, explosion);
		orden=ORDEN;
		actualizar();
		
	}

	public VistaExplosion(Element element) throws NoPudoLeerXMLExeption {
		NodeList hijos;
		Element elem;
		hijos = element.getChildNodes();
		if (hijos != null && hijos.getLength() > 0) {
			for (int i = 0; i < hijos.getLength(); i++) {
				elem = (Element) hijos.item(i);
				if (elem.getTagName().equals(TAG_EXPLOSION))
					explosion = (Explosion) DiccionarioDeSerializables
							.getInstancia((Element) elem.getFirstChild());
			}
		}
		explosion.adscribir(this);
		sprite = new Imagen(RUTA_SPRITE, explosion);
		orden=ORDEN;
		actualizar();
	}

	public void dibujar(SuperficieDeDibujo sup) {
		sprite.dibujar(sup);
	}

	public Posicionable getPosicionable() {
		return explosion;
	}

	public void setPosicionable(Posicionable explosion) {
		this.explosion = (Explosion) explosion;
		sprite.setPosicionable(explosion);
	}

	public void setExplosion(Explosion explosion) {
		setPosicionable(explosion);
	}

	public Explosion getExplosion() {
		return explosion;
	}

	@Override
	public void actualizar() {
		if(explosion.estaDestruida()){
			VistaEscenario.getInstancia().borrarVista(this);
		}
	}




}
