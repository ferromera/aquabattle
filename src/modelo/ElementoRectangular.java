package modelo;

import java.util.ArrayList;
import java.util.Iterator;

import misc.Observable;
import misc.Observador;
import titiritero.Posicionable;
import utils.Direccion;


public class ElementoRectangular implements Posicionable , Observable {
	private int posX;
	private int posY;
	private int alto;
	private int ancho;
	private ArrayList<Observador> observadores;
	private Direccion orientacion;
	
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
	public void avanzar(){
		avanzar(1);
	}
	public void avanzar(int pixels){
		switch(orientacion.get()){
		case Direccion.NORTE:
			avanzarNorte();
			break;
		case Direccion.SUR:
			avanzarSur();
			break;
		case Direccion.ESTE:
			avanzarEste();
			break;
		case Direccion.OESTE:
			avanzarOeste();
			break;
		}
	}
	
	public void setOrientacion(Direccion dir){
		orientacion=dir;
		switch(orientacion.get()){
		case Direccion.NORTE:
			orientarNorte();
			break;
		case Direccion.SUR:
			orientarSur();
			break;
		case Direccion.ESTE:
			orientarEste();
			break;
		case Direccion.OESTE:
			orientarOeste();
			break;
		}
	}
	private void orientarNorte(){
		if(!orientacion.esNorte()){
			orientacion.setNorte();
			notificar();
		}
	}
	private void orientarSur(){
		if(!orientacion.esSur()){
			orientacion.setSur();
			notificar();
		}
	}
	private void orientarOeste(){
		if(!orientacion.esOeste()){
			orientacion.setOeste();
			notificar();
		}
	}
	private void orientarEste(){
		if(!orientacion.esEste()){
			orientacion.setEste();
			notificar();
		}
	}
	public Direccion getOrientacion(){
		return orientacion;
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
