package vista;

import misc.FabricaElementos;
import misc.Observador;
import modelo.ParedConcreto;
import modelo.ParedMetal;
import titiritero.Posicionable;
import titiritero.SuperficieDeDibujo;
import titiritero.vista.Imagen;

public class VistaParedMetal  extends Vista implements Observador{
	private ParedMetal pared;
	private Imagen sprite;
	
	private final String RUTA_SPRITE_ParedMetalNormal = "/sprites/SpriteParedMetalNormal1.png"; 
	private final String RUTA_SPRITE_ParedMetalConDisparo= "/sprites/SpriteParedMetalConDisparo.png"; 
	private final String RUTA_SPRITE_ParedMetalDestruida = "/sprites/SpriteParedMetalDestruida.png";
	
	public VistaParedMetal(ParedMetal paredMetal){
		this.pared = paredMetal;
		paredMetal.adscribir(this);
		sprite = new Imagen(RUTA_SPRITE_ParedMetalNormal, paredMetal);
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
