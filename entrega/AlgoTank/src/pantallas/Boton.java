package pantallas;

import java.util.ArrayList;
import java.util.Iterator;

import misc.Observable;
import misc.Observador;
import titiritero.Posicionable;

public class Boton implements Observable,Posicionable {
	
	private static final int ANCHO=700;
	private static final int ALTO=40;
	
	private int x,y;
	private boolean seleccionado;
	private ArrayList<Observador> observadores=new ArrayList<Observador>();
	private String texto;
	private AccionBoton accion;
	
	public Boton(int x,int y,String texto){
		this.x=x;
		this.y=y;
		this.texto=texto;
		seleccionado=false;
	}
	public void setAccion(AccionBoton accion){
		this.accion=accion;
	}
	public void actuar(){
		if(accion!=null)
			accion.actuar();
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
	public boolean estaSeleccionado(){
		return seleccionado;
	}

	public void seleccionar(){
		seleccionado = true;
		notificar();
	}
	public void deseleccionar(){
		seleccionado = false;
		notificar();
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
	public String getTexto() {
		return texto;
	}
}
	
