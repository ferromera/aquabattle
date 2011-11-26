package pantallas;


import java.util.ArrayList;
import java.util.Iterator;

import misc.Observable;
import misc.Observador;
import titiritero.ObjetoVivo;
import titiritero.Posicionable;


public abstract class Pantalla implements Posicionable,ObjetoVivo,Observable {
	private ArrayList<Observador> observadores;
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
	
	
	public abstract void cambiarA(Pantalla pantalla);
	@Override
	public double getX() {
		return 0;
	}

	@Override
	public double getY() {
		return 0;
	}
	public abstract void convertirEnActual();
	public abstract void dejarDeSerActual();

}
