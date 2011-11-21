package vista;

import excepciones.NoExisteBaseException;
import misc.Observador;
import modelo.Base;
import modelo.Escenario;
import titiritero.Dibujable;
import titiritero.Posicionable;
import titiritero.SuperficieDeDibujo;
import titiritero.vista.Imagen;

public class VistaBase implements Dibujable,Observador {
	private Base base;
	private Imagen sprite;

	private final String RUTA_SPRITE = "/sprites"; //Aca tengo que ver donde esta la imagen
	
	
	public VistaBase(){
		try{
		this.base = Escenario.getActual().getBase();
		}catch (NoExisteBaseException e){
			e.printStackTrace();
		}
		
		sprite = new Imagen(RUTA_SPRITE, base);
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
			//Aca si esta destruida deberia terminar el juego

	}
	
}
