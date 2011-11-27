package pantallas;

import java.util.ArrayList;
import java.util.Iterator;

import titiritero.Posicionable;
import misc.Observable;
import misc.Observador;


public class Vida implements Observable,Posicionable {
	private int x,y;
	private static final int ANCHO=15;
	private static final int ALTO=15;
	private boolean borrada;
	
	public Vida(int x,int y){
		this.x=x;
		this.y=y;
		borrada=false;
	}
	public int getAncho(){
		return ANCHO;
	}
	public int getAlto(){
		return ALTO;
	}
	@Override
	public double getX() {
		return x;
	}
	@Override
	public double getY() {
		return y;
	}
	public void setX(int x){
		this.x=x;
	}
	public void setY(int y){
		this.y=y;
	}
	public boolean estaBorrada(){
		return borrada;
	}
	public void borrar(){
		borrada=true;
		notificar();
	}

	private ArrayList<Observador> observadores=new ArrayList<Observador>();
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
