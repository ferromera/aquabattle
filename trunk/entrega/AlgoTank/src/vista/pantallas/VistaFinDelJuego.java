package vista.pantallas;


import org.w3c.dom.Document;
import org.w3c.dom.Element;

import misc.Observador;
import pantallas.FinDelJuego;
import titiritero.Dibujable;
import titiritero.Posicionable;
import titiritero.SuperficieDeDibujo;
import titiritero.vista.Imagen;
import vista.Vista;

public class VistaFinDelJuego extends Vista implements Dibujable, Observador{

	private static VistaFinDelJuego instancia=null;

	private FinDelJuego finDelJuego;


	private Imagen sprite;
	private static final String RUTA_SPRITE_FONDO= "/sprites/FondoJuegoTerminado.png";


	public VistaFinDelJuego(){
		this.finDelJuego = FinDelJuego.getInstancia();
		sprite = new Imagen(RUTA_SPRITE_FONDO, finDelJuego);
	}
	
	public static VistaFinDelJuego getInstancia() {
		if(instancia == null)
			instancia = new VistaFinDelJuego();
		return instancia;
	}
	
	
	public void dibujar(SuperficieDeDibujo sup) {
		sprite.dibujar(sup);		
	}
	
	public Posicionable getPosicionable() {
		return finDelJuego;
	}

	public void setPosicionable(Posicionable finJuego) {
		this.finDelJuego = (FinDelJuego) finJuego;
	}

	@Override
	public void actualizar() {
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
