package modelo;


public class Base extends ElementoRectangular implements Impactable, Posicionable {
	
	private int impactosRecibidos;

	
	public Base(int posicionX, int posicionY){
		this.posicionEnX = posicionX;
		this.posicionEnY = posicionY;
		this.alto = 20; //poner cuanto va medir
		this.ancho = 20;
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