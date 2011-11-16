package modelo.ai;

import modelo.ElementoRectangular;
import modelo.Tanque;

public abstract class Bot {

	protected Tanque tanque;
	protected ElementoRectangular objetivo;
	
	public Bot(Tanque tanque, ElementoRectangular objetivo) {
		this.tanque = tanque;
		this.objetivo = objetivo;
	}

	public abstract void actuar() ;

	

}
