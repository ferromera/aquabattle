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

public class VistaBase implements Dibujable,Observador {
	private Base base;
	private Imagen sprite;

	private final String RUTA_SPRITE = "/sprites/SpriteBase.png"; 

	private Animacion spriteActual;
	private Animacion spriteNormal;
	private Animacion spriteDestruido;
	
	private static final int ALTO_SPRITE = 50;
	private static final int ANCHO_SPRITE = 50;
	

	private static final int FILA_SPRITE_NORMAL = 0;
	private static final double FPS_NORMAL = 25.0;

	private static final int FILA_SPRITE_DESTRUIDO = 1;
	private static final double FPS_DESTRUIDO = 4.0;
	
	
	
	
	public VistaBase(){
		try{
		this.base = Escenario.getActual().getBase();
		}catch (NoExisteBaseException e){
			e.printStackTrace();
		}
		base.adscribir(this);
		sprite = new Imagen(RUTA_SPRITE, base);
		
		Imagen subImagen = sprite.getSubimagen( 0 , FILA_SPRITE_NORMAL * ALTO_SPRITE, sprite.getAncho(),ALTO_SPRITE);
		spriteNormal=new Animacion(subImagen,ANCHO_SPRITE,ALTO_SPRITE);
		spriteNormal.setFps(FPS_NORMAL);
		
		
		subImagen = sprite.getSubimagen(0,FILA_SPRITE_DESTRUIDO * ALTO_SPRITE, sprite.getAncho(), ALTO_SPRITE);
		spriteDestruido=new Animacion(subImagen,ANCHO_SPRITE,ALTO_SPRITE);
		spriteDestruido.setFps(FPS_DESTRUIDO);
				
		spriteActual = spriteNormal;
				
		
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
		spriteActual.detener();
			if (base.estaDestruida()){
				spriteActual = spriteDestruido;
				spriteActual.reproducirUnaVez();
				//Aca se deberia terminar el juego
			}

	}
	
}
