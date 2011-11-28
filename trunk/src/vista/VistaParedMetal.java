package vista;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import excepciones.NoPudoLeerXMLExeption;

import misc.ContadorDeInstancias;
import misc.DiccionarioDeSerializables;
import misc.FabricaElementos;
import misc.Observador;
import modelo.ParedConcreto;
import modelo.ParedMetal;
import titiritero.Posicionable;
import titiritero.SuperficieDeDibujo;
import titiritero.vista.Imagen;

public class VistaParedMetal  extends Vista implements Observador{
	public static final String TAG = "objeto-vista-pared-metal";

	private static final String TAG_PARED = "pared";

	private static final int ORDEN = 1;

	private long id=ContadorDeInstancias.getId();
	
	private ParedMetal pared;
	private Imagen sprite;
	
	private final String RUTA_SPRITE_ParedMetalNormal = "/sprites/SpriteParedMetalNormal1.png"; 
	private final String RUTA_SPRITE_ParedMetalConDisparo= "/sprites/SpriteParedMetalConDisparo.png"; 
	private final String RUTA_SPRITE_ParedMetalDestruida = "/sprites/SpriteParedMetalDestruida.png";
	
	public VistaParedMetal(ParedMetal paredMetal){
		this.pared = paredMetal;
		paredMetal.adscribir(this);
		sprite = new Imagen(RUTA_SPRITE_ParedMetalNormal, paredMetal);
		orden=ORDEN;
		actualizar();
		
	}
	
	
	public VistaParedMetal(Element element) throws NoPudoLeerXMLExeption {
		NodeList hijos;
		Element elem;
		hijos = element.getChildNodes();
		if (hijos != null && hijos.getLength() > 0) {
			for (int i = 0; i < hijos.getLength(); i++) {
				elem = (Element) hijos.item(i);
				if (elem.getTagName().equals(TAG_PARED))
					pared = (ParedMetal) DiccionarioDeSerializables
							.getInstancia((Element) elem.getFirstChild());
			}
		}
		pared.adscribir(this);
		sprite = new Imagen(RUTA_SPRITE_ParedMetalNormal, pared);
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
		this.pared = (ParedMetal) paredPos;
		sprite.setPosicionable(paredPos);
	}
	
	public void setParedConcreto(ParedMetal pared) {
		setPosicionable(pared);
	}

	public ParedMetal getParedMetal() {
		return pared;
	}
	
	
	@Override
	public void actualizar() {
		if (pared.impactosRecibidos() == 1 ){
			Imagen nuevaImagen = new Imagen(RUTA_SPRITE_ParedMetalConDisparo, pared);
			sprite = nuevaImagen;

		}else if (pared.impactosRecibidos() > 1){
			Imagen nuevaImagen = new Imagen(RUTA_SPRITE_ParedMetalDestruida, pared);
			sprite = nuevaImagen;
			//Borrarla del escenario
		}
	};
	
	
	
	
}
