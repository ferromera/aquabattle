package vista.pantallas;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import excepciones.NoPudoLeerXMLExeption;

import pantallas.Vida;
import misc.ContadorDeInstancias;
import misc.DiccionarioDeSerializables;
import misc.Observador;
import modelo.armamento.Cohete;
import titiritero.Posicionable;
import titiritero.SuperficieDeDibujo;
import titiritero.vista.Imagen;
import vista.Vista;

public class VistaVida extends Vista implements Observador {
	private long id=ContadorDeInstancias.getId();
	
	private Vida vida;
	

	private Imagen sprite;
	

	private static final String RUTA_SPRITE = "/sprites/SpriteVida.png";
	
	private static final int ALTO_SPRITE = 15;
	private static final int ANCHO_SPRITE = 15;

	public static final String TAG = "objeto-vista-vida";

	private static final String TAG_VIDA = "vida";
	
	
	public VistaVida(Vida vida){
		this.vida = vida;
		vida.adscribir(this);
		

		sprite = new Imagen(RUTA_SPRITE, vida);

		actualizar();
		
	}
	
	public VistaVida(Element element) throws NoPudoLeerXMLExeption {
		NodeList hijos;
		Element elem;
		hijos = element.getChildNodes();
		if (hijos != null && hijos.getLength() > 0) {
			for (int i = 0; i < hijos.getLength(); i++) {
				elem = (Element) hijos.item(i);
				if (elem.getTagName().equals(TAG_VIDA))
					vida = (Vida) DiccionarioDeSerializables
							.getInstancia((Element) elem.getFirstChild());
			}
		}
		
		
		vida.adscribir(this);
		
		sprite = new Imagen(RUTA_SPRITE, vida);

		actualizar();
	}

	public void dibujar(SuperficieDeDibujo sup) {
		sprite.dibujar(sup);
	}

	public Posicionable getPosicionable() {
		return this.vida;
	}

	public void setPosicionable(Posicionable vida) {
		this.vida = (Vida) vida;
		sprite.setPosicionable(vida);

	}

	public void setVida(Vida vida) {
		setPosicionable(vida);
	}

	public Vida getVida() {
		return this.vida;
	}

	@Override
	public void actualizar() {
		if (this.vida.estaBorrada()) {
			VistaPantallaJuego.getInstancia().borrarVistaVida(this);
		}

	}
	

}
