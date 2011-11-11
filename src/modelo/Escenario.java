package modelo;

import java.util.*;

import titiritero.ObjetoVivo;
import titiritero.Posicionable;
import misc.Observable;
import misc.Observador;

public class Escenario implements ObjetoVivo,Posicionable, Observable {
	
	private int alto;
	private int ancho;
	private static Escenario escenarioActual = null;
	private ArrayList<ObjetoVivo> objetosVivos = new ArrayList<ObjetoVivo>();
	private ArrayList<ElementoRectangularSolido> objetosSolidos = new ArrayList<ElementoRectangularSolido>();
	private ArrayList<Observador> observadores;
	
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
		if (escenarioActual == null){
			escenarioActual = new Escenario(50,50); //Ver medidas
		}
			return escenarioActual;
	}
	
	public void agregarObjetoVivo(ObjetoVivo objetoAgregar){
		objetosVivos.add(objetoAgregar);
	}
	
	public void agregarObjetoSolido(ElementoRectangularSolido objetoAgregar){
		objetosSolidos.add(objetoAgregar);
	}
	
	public void borrarObjetoVivo(ObjetoVivo objetoBorrar){
		objetosVivos.remove(objetoBorrar);
	}
	
	public void borrarSolido(ElementoRectangularSolido objetoBorrar){
		objetosSolidos.remove(objetoBorrar);
	}
	
	public void vivir(){
		Iterator<ObjetoVivo> iterador = objetosVivos.iterator();
			while (iterador.hasNext()){
				iterador.next().vivir();
			}
	}
	
	public void adscribir(Observador observador){
		if(!observadores.contains(observador))
		observadores.add(observador);
	}
	public void quitar(Observador observador){
		observadores.remove(observador);
	}
	public void notificar(){
		Iterator<Observador> it= observadores.iterator();
		while(it.hasNext()){
			it.next().actualizar();
		}
		
	}

}
