package modelo;

import java.util.ArrayList;
import java.util.Iterator;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import excepciones.NoPudoLeerXMLExeption;

import misc.DiccionarioDeSerializables;
import misc.Observable;
import misc.Observador;
import titiritero.Posicionable;
import utils.Direccion;


public abstract class ElementoRectangular implements Posicionable , Observable {
	private static final String TAG_POS_Y = "posicion-y";
	private static final String TAG_POS_X = "posicion-x";
	private static final String TAG_ALTO = "alto";
	private static final String TAG_ANCHO = "ancho";
	public  static final String TAG = "elemento-rectangular";
	private static final String TAG_DIRECCION = "direccion";
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
	public ElementoRectangular(Element element) throws NoPudoLeerXMLExeption{
		//Valores por default
		posX=0.0;
		posY=0.0;
		alto=30.0;
		ancho=30.0;
		observadores=new ArrayList<Observador>();
		orientacion=Direccion.Norte();
		
		NodeList nodoX = element.getElementsByTagName(TAG_POS_X);
		if(nodoX!=null && nodoX.getLength()>0){
			if(nodoX.getLength()>1)
				throw new NoPudoLeerXMLExeption("No puede haber mas de un tag: "+TAG_POS_X+" en el nodo "+element.getTagName());
			Element elemX = (Element) nodoX.item(0);
			posX=Double.parseDouble(elemX.getNodeValue());
		}
		NodeList nodoY = element.getElementsByTagName(TAG_POS_Y);
		if(nodoY!=null && nodoY.getLength()>0){
			if(nodoY.getLength()>1)
				throw new NoPudoLeerXMLExeption("No puede haber mas de un tag: "+TAG_POS_Y+" en el nodo "+element.getTagName());
			Element elemY = (Element) nodoY.item(0);
			posY=Double.parseDouble(elemY.getNodeValue());
		}
		NodeList nodoAlto = element.getElementsByTagName(TAG_ALTO);
		if(nodoAlto!=null && nodoAlto.getLength()>0){
			if(nodoAlto.getLength()>1)
				throw new NoPudoLeerXMLExeption("No puede haber mas de un tag: "+TAG_ALTO+" en el nodo "+element.getTagName());
			Element elemAlto = (Element) nodoAlto.item(0);
			alto=Double.parseDouble(elemAlto.getNodeValue());
		}
		NodeList nodoAncho = element.getElementsByTagName(TAG_ANCHO);
		if(nodoAncho!=null && nodoAncho.getLength()>0){
			if(nodoAncho.getLength()>1)
				throw new NoPudoLeerXMLExeption("No puede haber mas de un tag: "+TAG_ANCHO+" en el nodo "+element.getTagName());
			Element elemAncho = (Element) nodoAncho.item(0);
			ancho=Double.parseDouble(elemAncho.getNodeValue());
		}
		NodeList nodoDir = element.getElementsByTagName(TAG_DIRECCION);
		if(nodoDir!=null && nodoDir.getLength()>0){
			if(nodoDir.getLength()>1)
				throw new NoPudoLeerXMLExeption("No puede haber mas de un tag: "+TAG_DIRECCION+" en el nodo "+element.getTagName());
			Element elemDir = (Element) nodoDir.item(0);
			orientacion=DiccionarioDeSerializables.getInstanciaDireccion(elemDir);
		}
		//TODO: Falta Observables
		
		
		
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
		if(rect==this)
			return false;
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
	public void avanzarEnDireccion(Direccion dir){
		avanzarEnDireccion(dir, 1);
	}
	public void avanzarEnDireccion(Direccion dir,double espacio){
		switch(dir.get()){
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
	public void retrocederEnDireccion(Direccion dir){
		retrocederEnDireccion(dir,1);
	}
	public void retrocederEnDireccion(Direccion dir,double espacio){
		switch(dir.get()){
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
		switch(dir.get()){
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

	public boolean estaSuperpuesto() {

		Escenario escenario = Escenario.getActual();
		Iterator<ElementoRectangular> it = escenario.getObjetos();
		ElementoRectangular elem;
		while (it.hasNext()) {
			elem = it.next();
			if (superpuestoCon(elem)) {
				return true;
			}
		}
		return false;
	}
	public double getCentroX(){
		return posX + ancho/2.0;
	}
	public double getCentroY(){
		return posY + alto/2.0;
	}
	
	
	
}
