package vista;

import excepciones.NoExisteBaseException;
import misc.Observador;
import modelo.Base;
import modelo.Escenario;
import titiritero.Dibujable;
import titiritero.Posicionable;
import titiritero.SuperficieDeDibujo;
import titiritero.vista.Animacion;
import titiritero.vista.Imagen;

public class VistaBase extends Vista implements Observador {
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
	
	public void dibujar(SuperficieDeDibujo sup) {
		sprite.dibujar(sup);
		System.out.println("Dibujada Base");
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

				//Aca se deberia terminar el juego
				
			}
		}

}
	
