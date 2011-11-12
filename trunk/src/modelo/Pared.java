package modelo;

import java.util.*;

import titiritero.ObjetoVivo;
import titiritero.Posicionable;
import misc.Observable;
import misc.Observador;

public abstract class Pared extends ElementoRectangularSolido implements ObjetoVivo,Posicionable, Observable {
	
	int impactosRecibidos;
	
	
	public Pared(int posicionEnX, int posicionEnY){
		this.posX = posicionEnX;
		this.posY = posicionEnY;
		this.alto = 20;      //Poner la medida que se va a usar
		this.ancho = 20; 
	}
	
	public abstract void vivir();
	
	public abstract void recibirImpacto(int fuerza);
	
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
	
	
}