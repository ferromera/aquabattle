package modelo;



import titiritero.Posicionable;
import misc.Observable;


public class ParedMetal extends Pared implements 
Posicionable, Observable {
	
	private final int ALTO = 20;
	private final int ANCHO = 20;	
	private int impactosRecibidos;
	private boolean destruida=false;
	 
	public ParedMetal(double posicionEnX, double posicionEnY){
		super(posicionEnX,posicionEnY);
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
			destruida=true;
		}
		notificar();
	}
	
	public boolean estaDestruida(){
		return destruida;
	}
	
	public int impactosRecibidos(){
		return impactosRecibidos;
	}
	
}