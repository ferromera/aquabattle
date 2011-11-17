package modelo;

import excepciones.NoExisteBaseException;
import titiritero.Posicionable;

public class Base extends ElementoRectangularSolido implements Impactable, Posicionable {
	
	private int impactosRecibidos;
	private final int ALTO = 50;
	private final int ANCHO = 50;
	
	public Base(double posicionX, double posicionY){
		setX(posicionX);
		setY(posicionY);
		setAlto(ALTO);
		setAncho(ANCHO);
		this.impactosRecibidos = 0;
	}
	
	
	public void recibirImpacto(int fuerza){
		this.impactosRecibidos ++;
		if (impactosRecibidos > 1){
			try{
			Escenario.getActual().borrarBase();
			}catch (NoExisteBaseException e){
				e.printStackTrace();
			}
		}
	}
	
	public int getResistencia(){
		return 1;
	}

}