package modelo;



import titiritero.Posicionable;
import misc.Observable;
import misc.Observador;

public abstract class ParedConcreto extends Pared implements 
Posicionable, Observable {
	
	private final int ALTO = 20;
	private final int ANCHO = 20;	
	 
	public ParedConcreto(double posicionEnX, double posicionEnY){
		setX(posicionEnX);
		setY(posicionEnY);
		setAlto(ALTO);      //Poner la medida que se va a usar
		setAncho(ANCHO); 
		this.impactosRecibidos = 0;
	}
	
	
	public void recibirImpacto(int fuerza){
		this.impactosRecibidos ++;
		//Solo resiste un impacto
		if(impactosRecibidos > 0){
			Escenario escenarioActual = Escenario.getActual();
			escenarioActual.borrarSolido(this);
		}
	}
	
}