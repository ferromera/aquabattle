package modelo;

import java.util.ArrayList;
import java.util.Iterator;

import misc.Observable;
import misc.Observador;
import titiritero.Posicionable;
import utils.Direccion;


public class ElementoRectangular implements Posicionable , Observable {
	private double posX;
	private double posY;
	private double alto;
	private double ancho;
	private ArrayList<Observador> observadores;
	private Direccion orientacion;
	
	public ElementoRectangular(){
		posX=0.0;
		posY=0.0;
		alto=30.0;
		ancho=30.0;
		observadores=new ArrayList<Observador>();
		orientacion=Direccion.Norte();
	}
	public ElementoRectangular(double x,double y){
		posX=x;
		posY=y;
		alto=30;
		ancho=30;
		observadores=new ArrayList<Observador>();
		orientacion=Direccion.Norte();
	}
	public ElementoRectangular(double x,double y,double alto, double ancho){
		posX=x;
		posY=y;
		this.alto=alto;
		this.ancho=ancho;
		observadores=new ArrayList<Observador>();
		orientacion=Direccion.Norte();
	}
	public double getX(){
		return posX;
	}
	public double getY(){
		return posY;
	}
	public void setX(double x){
		posX=x;
	}
	public void setY(double y){
		posY=y;
	}
	public double getAlto(){
		return alto;
	}
	public double getAncho(){
		return ancho;
	}
	public void setAlto(double alto){
		this.alto=alto;
	}
	public void setAncho(double ancho){
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
	public void avanzarEste(double dx){
		posX+=dx;
	}
	public void avanzarEste(){
		posX++;
	}
	public void avanzarOeste(double dx){
		posX-=dx;
	}
	public void avanzarOeste(){
		posX--;
	}
	public void avanzarNorte(double dy){
		posY-=dy;
	}
	public void avanzarNorte(){
		posY--;
	}
	public void avanzarSur(double dy){
		posY+=dy;
	}
	public void avanzarSur(){
		posY++;
	}
	public void avanzar(){
		avanzar(1);
	}
	public void avanzar(double espacio){
		switch(orientacion.get()){
		case Direccion.NORTE:
			avanzarNorte(espacio);
			break;
		case Direccion.SUR:
			avanzarSur(espacio);
			break;
		case Direccion.ESTE:
			avanzarEste(espacio);
			break;
		case Direccion.OESTE:
			avanzarOeste(espacio);
			break;
		}
	}
	public void retroceder(){
		retroceder(1);
	}
	public void retroceder(double espacio){
		switch(orientacion.get()){
		case Direccion.NORTE:
			avanzarSur(espacio);
			break;
		case Direccion.SUR:
			avanzarNorte(espacio);
			break;
		case Direccion.ESTE:
			avanzarOeste(espacio);
			break;
		case Direccion.OESTE:
			avanzarEste(espacio);
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
	public void orientarNorte(){
		if(!orientacion.esNorte()){
			orientacion.setNorte();
			notificar();
		}
	}
	public void orientarSur(){
		if(!orientacion.esSur()){
			orientacion.setSur();
			notificar();
		}
	}
	public void orientarOeste(){
		if(!orientacion.esOeste()){
			orientacion.setOeste();
			notificar();
		}
	}
	public void orientarEste(){
		if(!orientacion.esEste()){
			orientacion.setEste();
			notificar();
		}
	}
	public Direccion getOrientacion(){
		return orientacion;
	}
	public boolean fueraDeEscenario(){
		
		Escenario escenario= Escenario.getActual();
		 
		if( posX < 0 || posX >= escenario.getAncho() ||
			posY < 0 || posY >= escenario.getAlto() )
			return true;
		 
		return false;
	}
}
