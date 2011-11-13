package modelo;



import titiritero.Posicionable;
import misc.Observable;
import misc.Observador;

public abstract class Pared extends ElementoRectangularSolido implements 
Posicionable, Observable {

	public Pared(double posicionEnX, double posicionEnY){
		setX(posicionEnX);
		setY(posicionEnY);
	}

	
	public abstract void recibirImpacto(int fuerza);
	
	public int getResistencia(){
		return 1;
	}
	
}