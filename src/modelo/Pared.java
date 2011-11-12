package modelo;



import titiritero.Posicionable;
import misc.Observable;
import misc.Observador;

public abstract class Pared extends ElementoRectangularSolido implements 
Posicionable, Observable {
	
	int impactosRecibidos;
	
	private final int ALTO = 20;
	private final int ANCHO = 20;
	
	public Pared(double posicionEnX, double posicionEnY){
		setX(posicionEnX);
		setY(posicionEnY);
		setAlto(ALTO);      //Poner la medida que se va a usar
		setAncho(ANCHO); 
		this.impactosRecibidos = 0;
	}

	
	public abstract void recibirImpacto(int fuerza);
	

	
}