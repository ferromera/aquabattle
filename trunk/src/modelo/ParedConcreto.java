package modelo;



import titiritero.Posicionable;
import misc.Observable;


public class ParedConcreto extends Pared implements 
Posicionable, Observable {
	
	private final int ALTO = 20;
	private final int ANCHO = 20;	
	 
	public ParedConcreto(double posicionEnX, double posicionEnY){
		super(posicionEnX,posicionEnY);
		setAlto(ALTO);      //Poner la medida que se va a usar
		setAncho(ANCHO); 
		
	}
	
	
	public void recibirImpacto(int fuerza){
		//Solo resiste un impacto
		Escenario escenarioActual = Escenario.getActual();
		escenarioActual.borrarSolido(this);	
	}
	
}