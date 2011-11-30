package vista.pantallas;


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
		sprite = new Imagen(RUTA_SPRITE_FONDO, finDelJuego);
	}
	
	public VistaFinDelJuego(FinDelJuego finalJuego){
		this.finDelJuego = finalJuego;
		sprite = new Imagen(RUTA_SPRITE_FONDO, finalJuego);
		instancia = this;
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
	
}