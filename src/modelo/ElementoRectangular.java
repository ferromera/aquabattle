package modelo;

import java.util.ArrayList;
import java.util.Iterator;

import misc.Observable;
import misc.Observador;
import titiritero.Posicionable;


public class ElementoRectangular implements Posicionable , Observable {
	private int posX;
	private int posY;
	private int alto;
	private int ancho;
	private ArrayList<Observador> observadores;
	
	public ElementoRectangular(){
		posX=0;
		posY=0;
		alto=30;
		ancho=30;
		observadores=new ArrayList<Observador>();
	}
	public ElementoRectangular(int x,int y){
		posX=x;
		posY=y;
		alto=30;
		ancho=30;
		observadores=new ArrayList<Observador>();
	}
	public ElementoRectangular(int x,int y,int alto, int ancho){
		posX=x;
		posY=y;
		this.alto=alto;
		this.ancho=ancho;
		observadores=new ArrayList<Observador>();
	}
	public int getX(){
		return posX;
	}
	public int getY(){
		return posY;
	}
	public void setX(int x){
		posX=x;
	}
	public void setY(int y){
		posY=y;
	}
	public int getAlto(){
		return alto;
	}
	public int getAncho(){
		return ancho;
	}
	public void setAlto(int alto){
		this.alto=alto;
	}
	public void setAncho(int ancho){
		this.ancho=ancho;
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
	public boolean superpuestoCon(ElementoRectangular rect){
		if( posX <= rect.posX + rect.ancho &&
			posY <= rect.posY + rect.alto &&
			posX + ancho >= rect.posX &&
			posY + alto >= rect.posY )
			return true;
		return false;
	}
	public void avanzarEste(int pixels){
		posX+=pixels;
	}
	public void avanzarEste(){
		posX++;
	}
	public void avanzarOeste(int pixels){
		posX-=pixels;
	}
	public void avanzarOeste(){
		posX--;
	}
	public void avanzarNorte(int pixels){
		posY-=pixels;
	}
	public void avanzarNorte(){
		posY--;
	}
	public void avanzarSur(int pixels){
		posY+=pixels;
	}
	public void avanzarSur(){
		posY++;
	}
	public boolean fueraDeEscenario(){
		/* FALTA ESCENARIO
		escenario= Escenario.getActual();
		 
		if( posX < 0 || posX >= escenario.getAncho() ||
			posY < 0 || posY >= escenario.getAlto() )
			return true;
		 */
		return false;
	}
}
