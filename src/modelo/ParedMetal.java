package modelo;



import titiritero.Posicionable;
import misc.Observable;
import misc.Observador;

public abstract class ParedMetal extends Pared implements 
Posicionable, Observable {
	
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
		//Solo resiste dos impacto
		if(impactosRecibidos > 1){
			Escenario escenarioActual = Escenario.getActual();
			escenarioActual.borrarSolido(this);
		}
	}
	
}