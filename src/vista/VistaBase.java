package vista;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import excepciones.NoExisteBaseException;
import excepciones.NoPudoLeerXMLExeption;
import misc.ContadorDeInstancias;
import misc.DiccionarioDeSerializables;
import misc.Observador;
import modelo.Base;
import modelo.Escenario;
import modelo.armamento.BalaCanion;
import titiritero.Dibujable;
import titiritero.Posicionable;
import titiritero.SuperficieDeDibujo;
import titiritero.vista.Animacion;
import titiritero.vista.Imagen;

public class VistaBase extends Vista implements Observador {
	public static final String TAG = "objeto-vista-base";

	private static final int ORDEN = 3;

	private static final String TAG_BASE = "base";

	private long id=ContadorDeInstancias.getId();
	
	private Base base;
	private Imagen sprite;

	private final String RUTA_SPRITE_BaseNormal = "/sprites/SpriteBaseNormal.png"; 
	private final String RUTA_SPRITE_BaseConDisparo = "/sprites/SpriteBaseConDisparo.png"; 
	private final String RUTA_SPRITE_BaseDestruida = "/sprites/SpriteBaseDestruida.png"; 

	
	
	
	
	public VistaBase(){
		try{
		this.base = Escenario.getActual().getBase();
		}catch (NoExisteBaseException e){
			e.printStackTrace();
		}
		base.adscribir(this);
		sprite = new Imagen(RUTA_SPRITE_BaseNormal, base);
		
		actualizar();
	}
	
	public VistaBase(Element element) throws NoPudoLeerXMLExeption {
		NodeList hijos;
		Element elem;
		hijos = element.getChildNodes();
		if (hijos != null && hijos.getLength() > 0) {
			for (int i = 0; i < hijos.getLength(); i++) {
				elem = (Element) hijos.item(i);
				if (elem.getTagName().equals(TAG_BASE))
					base = (Base) DiccionarioDeSerializables
							.getInstancia((Element) elem.getFirstChild());
			}
		}
		base.adscribir(this);
		sprite = new Imagen(RUTA_SPRITE_BaseNormal, base);
		
		actualizar();
	}

	public void dibujar(SuperficieDeDibujo sup) {
		sprite.dibujar(sup);
	}
	
	public Posicionable getPosicionable() {
		return base;
	}
	
	public void setPosicionable(Posicionable basePos) {
		this.base = (Base) basePos;
		sprite.setPosicionable(basePos);
	}
	
	public void setBase(Base base) {
		setPosicionable(base);
	}

	public Base getBase() {
		return base;
	}
	
	@Override
	public void actualizar() {
			if (base.impactosRecibidos() == 1){
				Imagen nuevaImagen = new Imagen(RUTA_SPRITE_BaseConDisparo, base);
				sprite = nuevaImagen;
			}else if (base.impactosRecibidos() > 1){
				Imagen nuevaImagen = new Imagen(RUTA_SPRITE_BaseDestruida, base);
				sprite = nuevaImagen;
			}
		}

}
	
