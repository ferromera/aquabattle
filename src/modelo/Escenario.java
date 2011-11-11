package modelo;

import java.util.ArrayList;

public class Escenario implements Posicionable, Observable {
	
	private int alto;
	private int ancho;
	private static Escenario escenarioActual = null;
	ArrayList objetosVivos = new ArrayList();
	ArrayList objetosSolidos = new ArrayList();
	
	public Escenario(int alto, int ancho){
		this.alto = alto;
		this.ancho = ancho;
		escenarioActual = this;
	}
	
	public int getAlto() {
		return alto;
	}

	public int getAncho() {
		return ancho;
	}
	public int getX() {
		return 0;
	}

	public int getY() {
		return 0;
	}
	
	
	public static Escenario getActual(){
		if (escenarioActual = null){
			escenarioActual = new Escenario(50,50); //Ver medidas
		}
			return escenarioActual;
	}
	
	public agregarObjetoVivo(ElementoRectangular objetoAgregar){
		objetosVivos.add(objetoAgregar);
	}
	
	public agregarObjetoSolido(ElementoRectangularSolido objetoAgregar){
		objetosSolidos.add(objetoAgregar);
	}
	
	public borrar(ElementoRectangular objetoBorrar){
		objetosVivos.remove(objetoAgregar);
	}
	
	public borrarSolido(ElementoRectangularSolido objetoBorrar){
		objetosSolidos.remove(objetoAgregar);
	}

}
