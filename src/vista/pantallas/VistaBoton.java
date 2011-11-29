package vista.pantallas;


import java.util.ArrayList;

import misc.Observador;


import pantallas.Boton;
import titiritero.Posicionable;
import titiritero.SuperficieDeDibujo;
import titiritero.vista.Imagen;
import vista.Vista;

public class VistaBoton extends Vista implements Observador {
	
	private Boton boton;
	

	private Imagen sprite;
	

	private static final String RUTA_SPRITE_BOTON = "/sprites/Boton.png";
	private static final String RUTA_SPRITE_BOTONSELECCIONADO = "/sprites/BotonSeleccionado.png";
	

	
	
	public VistaBoton(Boton boton){
		this.boton = boton;
		boton.adscribir(this);
		

		sprite = new Imagen(RUTA_SPRITE_BOTON, boton);

		actualizar();
		
	}
	


	public void dibujar(SuperficieDeDibujo sup) {
		sprite.dibujar(sup);
		System.out.println("Boton Dibujado");
	}

	public Posicionable getPosicionable() {
		return this.boton;
	}

	public void setPosicionable(Posicionable boton) {
		this.boton = (Boton) boton;
		sprite.setPosicionable(boton);

	}

	public void setBoton(Boton boton) {
		setPosicionable(boton);
	}

	public Boton getBoton() {
		return this.boton;
	}

	@Override
	public void actualizar() {
		if (boton.estaSeleccionado()) {
			sprite = new Imagen(RUTA_SPRITE_BOTONSELECCIONADO, boton);
		}

	}
	

}