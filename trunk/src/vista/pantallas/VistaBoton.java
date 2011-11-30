package vista.pantallas;



import java.awt.Color;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import misc.Observador;
import modelo.ElementoRectangular;


import pantallas.Boton;
import titiritero.Posicionable;
import titiritero.SuperficieDeDibujo;
import titiritero.vista.Imagen;
import titiritero.vista.ObjetoDeTexto;
import titiritero.vista.TextoEstatico;
import vista.Vista;

public class VistaBoton extends Vista implements Observador {
	
	private Boton boton;
	

	private Imagen sprite;
	private Imagen spriteSeleccionado;
	private Imagen spriteNoSeleccionado;
	private TextoEstatico textoBoton;
	

	private static final String RUTA_SPRITE_BOTON = "/sprites/Boton.png";
	private static final String RUTA_SPRITE_BOTONSELECCIONADO = "/sprites/BotonSeleccionado.png";
	

	
	
	public VistaBoton(Boton boton){
		this.boton = boton;
		boton.adscribir(this);
	
		spriteNoSeleccionado = new Imagen(RUTA_SPRITE_BOTON, boton);
		spriteSeleccionado= new Imagen(RUTA_SPRITE_BOTONSELECCIONADO,boton);
		
		ElementoRectangular cuadradoTexto=new ElementoRectangular(boton.getX()+100,boton.getY()+30);
		textoBoton= new TextoEstatico(boton.getTexto());
		textoBoton.setFuente("Arial", 28);
		textoBoton.setColor(Color.BLACK);
		textoBoton.setPosicionable(cuadradoTexto);

		actualizar();
		
	}
	


	public void dibujar(SuperficieDeDibujo sup) {
		sprite.dibujar(sup);
		textoBoton.dibujar(sup);
		

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
			sprite =spriteSeleccionado;
		}else{
			sprite=spriteNoSeleccionado;
		}

	}



	@Override
	public Element getElementoXML(Document doc) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void fromElementoXML(Element element) {
		// TODO Auto-generated method stub
		
	}
	

}