package vista;


import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import excepciones.NoPudoLeerXMLExeption;

import misc.ContadorDeInstancias;
import misc.DiccionarioDeSerializables;
import misc.Observador;
import modelo.Explosion;
import modelo.ParedConcreto;
import titiritero.Posicionable;
import titiritero.SuperficieDeDibujo;
import titiritero.vista.Imagen;

public class VistaParedConcreto extends Vista implements Observador{
	public static final String TAG = "objeto-vista-pared-concreto";

	private static final int ORDEN = 1;

	private static final String TAG_PARED = "pared";

	private long id=ContadorDeInstancias.getId();
	
	private ParedConcreto pared;
	private Imagen sprite;
	
	private final String RUTA_SPRITE_ParedConcretoNormal = "/sprites/SpriteParedConcretoNormal.png"; 
	private final String RUTA_SPRITE_ParedConcretoDestruida = "/sprites/SpriteParedConcretoDestruida.png";
	
	public VistaParedConcreto(ParedConcreto paredConcreto){
		this.pared = paredConcreto;
		paredConcreto.adscribir(this);
		sprite = new Imagen(RUTA_SPRITE_ParedConcretoNormal, paredConcreto);
		orden=ORDEN;
		actualizar();
		
	}
	
	
	public VistaParedConcreto(Element element) throws NoPudoLeerXMLExeption {
		NodeList hijos;
		Element elem;
		hijos = element.getChildNodes();
		if (hijos != null && hijos.getLength() > 0) {
			for (int i = 0; i < hijos.getLength(); i++) {
				elem = (Element) hijos.item(i);
				if (elem.getTagName().equals(TAG_PARED))
					pared = (ParedConcreto) DiccionarioDeSerializables
							.getInstancia((Element) elem.getFirstChild());
			}
		}
		pared.adscribir(this);
		sprite = new Imagen(RUTA_SPRITE_ParedConcretoNormal, pared);
		orden=ORDEN;
		actualizar();
	}


	public void dibujar(SuperficieDeDibujo sup) {
		sprite.dibujar(sup);
	}
	
	public Posicionable getPosicionable() {
		return this.pared;
	}
	
	public void setPosicionable(Posicionable paredPos) {
		this.pared = (ParedConcreto) paredPos;
		sprite.setPosicionable(paredPos);
	}
	
	public void setParedConcreto(ParedConcreto pared) {
		setPosicionable(pared);
	}

	public ParedConcreto getParedConcreto() {
		return pared;
	}
	
	
	@Override
	public void actualizar() {
		if (pared.estaDestruida()){
			Imagen nuevaImagen = new Imagen(RUTA_SPRITE_ParedConcretoDestruida, pared);
			sprite = nuevaImagen;
			//Borrarla del escenario
		}
	};
	
	
	
	
}
