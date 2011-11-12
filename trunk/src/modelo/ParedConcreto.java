package modelo;


import titiritero.ObjetoVivo;
import titiritero.Posicionable;
import misc.Observable;
import misc.Observador;

public abstract class ParedConcreto extends Pared implements 
ObjetoVivo,Posicionable, Observable {
	
	private final int ALTO = 20;
	private final int ANCHO = 20;	
	
	public ParedConcreto(int posicionEnX, int posicionEnY){
		setX(posicionEnX);
		setY(posicionEnY);
		setAlto(ALTO);      //Poner la medida que se va a usar
		setAncho(ANCHO); 
		this.impactosRecibidos = 0;
	}
	
	
	public void recibirImpacto(int fuerza){
		this.impactosRecibidos ++;
		if(impactosRecibidos > 0){
			Escenario escenarioActual = Escenario.getActual();
			escenarioActual.borrarSolido(this);
		}
	}
	
}