package vista;

import misc.Observador;
import modelo.TanqueIFV;
import modelo.armamento.Ametralladora;
import modelo.armamento.Canion;
import titiritero.Posicionable;
import titiritero.SuperficieDeDibujo;
import titiritero.vista.Animacion;
import titiritero.vista.Imagen;
import utils.Direccion;

public class VistaTanqueIFV extends VistaTanque implements Observador {
	
	private static final int ORDEN=3;
	
	private Animacion spriteAmetralladora;
	private Animacion spriteCanion;
	

	private final String RUTA_SPRITE = "/sprites/SpriteIFV.png";
	
	private static final int ALTO_SPRITE = 50;
	private static final int ANCHO_SPRITE = 50;

	// FILA DE LOS SPRITES INDIVIDUALES EN EL SPRITE GENERAL
	private static final int FILA_SPRITE_AMETRALLADORA = 0;
	private static final int FILA_SPRITE_CANION = 1;

	// FPS DE CADA SPRITE
	private static final double FPS_AMETRALLADORA = 25.0;
	private static final double FPS_CANION = 25.0;
	


	public VistaTanqueIFV(TanqueIFV tanque) {
		super(tanque);
		orden=ORDEN;
		tanque.adscribir(this);
		Imagen spriteTanque = new Imagen(RUTA_SPRITE, tanque);
		
		Imagen subImagen = spriteTanque.getSubimagen( 0 ,
				FILA_SPRITE_AMETRALLADORA * ALTO_SPRITE,
				spriteTanque.getAncho(),ALTO_SPRITE);
		spriteAmetralladora=new Animacion(subImagen,ANCHO_SPRITE,ALTO_SPRITE);
		spriteAmetralladora.setFps(FPS_AMETRALLADORA);
		
		subImagen = spriteTanque.getSubimagen( 0 ,
				FILA_SPRITE_CANION * ALTO_SPRITE,
				spriteTanque.getAncho(),ALTO_SPRITE);
		spriteCanion=new Animacion(subImagen,ANCHO_SPRITE,ALTO_SPRITE);
		spriteCanion.setFps(FPS_CANION);
		
		spriteActual=spriteCanion;
		actualizar();
	}

	public Posicionable getPosicionable() {
		return tanque;
	}

	public void setPosicionable(Posicionable tanque) {
		this.tanque = (TanqueIFV) tanque;
		spriteAmetralladora.setPosicionable(tanque);
		spriteCanion.setPosicionable(tanque);
	}

	public void setTanque(TanqueIFV tanque) {
		setPosicionable(tanque);
	}

	public TanqueIFV getTanque() {
		return (TanqueIFV) tanque;
	}

	@Override
	public void actualizar() {
		spriteActual.detener();
		if(tanque.estaDestruido()){
			VistaEscenario.getInstancia().borrarVista(this);
		}else{
			if(tanque.getArmaActual().getClass()==Ametralladora.class)
				spriteActual=spriteAmetralladora;
			else if(tanque.getArmaActual().getClass()==Canion.class)
				spriteActual=spriteCanion;	
		}
		if(tanque.enMovimiento())
			spriteActual.reproducir();
		else
			spriteActual.detener();
		actualizarOrientacion();
	}

	private void actualizarOrientacion(){
			switch(tanque.getOrientacion().get()){
			case Direccion.NORTE:
				spriteActual.orientarArriba();
				break;
			case Direccion.SUR:
				spriteActual.orientarAbajo();
				break;
			case Direccion.ESTE:
				spriteActual.orientarDerecha();
				break;
			case Direccion.OESTE:
				spriteActual.orientarIzquierda();
				break;
				
			}
	}

}
