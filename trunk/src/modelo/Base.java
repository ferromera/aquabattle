package modelo;

import titiritero.Posicionable;

public class Base extends ElementoRectangularSolido implements Impactable, Posicionable {
	
	private int impactosRecibidos;
	private final int ALTO = 50;
	private final int ANCHO = 50;
	
	public Base(double posicionX, double posicionY){
		setX(posicionX);
		setY(posicionY);
		setAlto(ALTO); //poner cuanto va medir
		setAncho(ANCHO);
		this.impactosRecibidos = 0;
	}
	
	
	public void recibirImpacto(int fuerza){
		this.impactosRecibidos ++;
		if (impactosRecibidos > 1){
			Escenario escenarioActual = Escenario.getActual();
			escenarioActual.borrarSolido(this);
			//Igual se tendria que terminar el juego...
					
		}
	}

}