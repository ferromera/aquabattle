package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import titiritero.ControladorJuego;
import titiritero.Dibujable;
import titiritero.Posicionable;
import titiritero.SuperficieDeDibujo;
import titiritero.vista.Animacion;
import titiritero.vista.Imagen;
import utils.Direccion;
import misc.Observador;
import modelo.TanqueHeroe;
import modelo.armamento.Ametralladora;
import modelo.armamento.BalaAmetralladora;
import modelo.armamento.Canion;

public class VistaBalaAmetralladora implements Dibujable,Observador,ActionListener {
	private BalaAmetralladora bala;
	private Imagen spriteActual;
	private Imagen spriteNormal;
	private Imagen spriteLanzamiento;
	
	private static final int ALTO_SPRITE = 10;
	private static final int ANCHO_SPRITE = 10;
	
	private static final int SPRITE_BALA_NORMAL_X=0;
	private static final int SPRITE_BALA_NORMAL_Y=0;
	private static final int SPRITE_BALA_LANZAMIENTO_X=0;
	private static final int SPRITE_BALA_LANZAMIENTO_Y=10;
	
	
	private final String RUTA_SPRITE = "/sprites/SpriteBalaAmetralladora.png"; 
	
	public VistaBalaAmetralladora(BalaAmetralladora bala) {
		this.bala = bala;
		bala.adscribir(this);
		Imagen spriteBala = new Imagen(RUTA_SPRITE, bala);
		
		spriteNormal = spriteBala.getSubimagen( SPRITE_BALA_NORMAL_X ,
				SPRITE_BALA_NORMAL_Y,
				ANCHO_SPRITE,ALTO_SPRITE);
		spriteLanzamiento = spriteBala.getSubimagen( SPRITE_BALA_LANZAMIENTO_X ,
				SPRITE_BALA_LANZAMIENTO_Y,
				ANCHO_SPRITE,ALTO_SPRITE);
		
		spriteActual=spriteNormal;
		actualizar();
		
	}
	public void actionPerformed(ActionEvent e){
		spriteActual=spriteNormal;
		actualizar();
	}
	public void dibujar(SuperficieDeDibujo sup) {
		spriteActual.dibujar(sup);
	}

	public Posicionable getPosicionable() {
		return bala;
	}

	public void setPosicionable(Posicionable bala) {
		this.bala = (BalaAmetralladora) bala;
		spriteNormal.setPosicionable(bala);
		spriteLanzamiento.setPosicionable(bala);
	}

	public void setBala(BalaAmetralladora bala) {
		setPosicionable(bala);
	}

	public BalaAmetralladora getBala() {
		return bala;
	}

	@Override
	public void actualizar() {
		if(bala.estaDestruida()){
			//TODO: 
			//borrarVista
			ControladorJuego.getInstancia().removerDibujable(this);
		}
		else
			actualizarOrientacion();
	}

	private void actualizarOrientacion(){
			switch(bala.getOrientacion().get()){
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
