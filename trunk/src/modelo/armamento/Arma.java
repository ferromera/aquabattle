package modelo.armamento;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import excepciones.NoPudoLeerXMLExeption;

import misc.DiccionarioDeSerializables;
import modelo.Tanque;

import utils.Direccion;



public abstract class Arma implements ActionListener  {
	public  static final String TAG = "arma";
	private static final String TAG_TANQUE = "tanque";
	private static final String TAG_CARGADA = "cargada";
	private static final String TAG_TIEMPO_CARGA = "tiempo-carga";
	private static final String TAG_CANTIDAD_BALAS = "cantidad-balas";
	private Tanque tanque;
	private boolean cargada=true;
	protected int tiempoCarga;
	private Timer timer;
	
	
	public Arma(){
		
	}
	public Arma(Element element) throws NoPudoLeerXMLExeption{
		NodeList nodo;
		Element elem;
		nodo = element.getElementsByTagName(TAG_TANQUE);
		if(nodo!=null && nodo.getLength()>0){
			if(nodo.getLength()>1)
				throw new NoPudoLeerXMLExeption("No puede haber mas de un tag: "+TAG_TANQUE+" en el nodo "+element.getTagName());
			elem = (Element) nodo.item(0);
			tanque=DiccionarioDeSerializables.getInstanciaTanque(elem);
		}
		nodo = element.getElementsByTagName(TAG_CARGADA);
		if(nodo!=null && nodo.getLength()>0){
			if(nodo.getLength()>1)
				throw new NoPudoLeerXMLExeption("No puede haber mas de un tag: "+TAG_CARGADA+" en el nodo "+element.getTagName());
			elem = (Element) nodo.item(0);
			cargada=Boolean.parseBoolean(elem.getTextContent());
		}
		nodo = element.getElementsByTagName(TAG_TIEMPO_CARGA);
		if(nodo!=null && nodo.getLength()>0){
			if(nodo.getLength()>1)
				throw new NoPudoLeerXMLExeption("No puede haber mas de un tag: "+TAG_TIEMPO_CARGA+" en el nodo "+element.getTagName());
			elem = (Element) nodo.item(0);
			tiempoCarga=Integer.parseInt(elem.getTextContent());
		}
	}
	public boolean equals(Arma a){
		return (getClass().equals(a.getClass()));
	}
	
	public void mejorarTiempoCarga(double porcentaje){
		tiempoCarga/=(1+porcentaje);
	}
	public void empeorarTiempoCarga(double porcentaje){
		tiempoCarga/=1-porcentaje;
	}
	
	public void disparar(){
		
		 if(!cargada)	 
			return;
		
		descargar();
		Bala bala= crearBala();
		posicionarBala(bala);
		
		
	}
	private void posicionarBala(Bala bala){
		double xBala= tanque.getX();
		double yBala= tanque.getY();
		switch(tanque.getOrientacion().get()){
		case Direccion.NORTE:
				yBala-= bala.getAlto() + 1;
				//centrada en X
				xBala+= (tanque.getAncho()-bala.getAncho())/2;
				bala.setOrientacion(Direccion.Norte());
				break;
		case Direccion.SUR:
				yBala+= tanque.getAlto() + 1;
				//centrada en X
				xBala+= (tanque.getAncho()-bala.getAncho())/2; 
				bala.setOrientacion(Direccion.Sur());
				break;
		case Direccion.ESTE:
				xBala+= tanque.getAncho() + 1;
				//centrada en Y
				yBala+= (tanque.getAlto()-bala.getAlto())/2; 
				bala.setOrientacion(Direccion.Este());
				break;
		case Direccion.OESTE:
				xBala-= bala.getAncho() + 1;
				//centrada en Y
				yBala+= (tanque.getAlto()-bala.getAlto())/2;
				bala.setOrientacion(Direccion.Oeste());
				break;
		}
		bala.setX(xBala);
		bala.setY(yBala);
	}
	/*
	 * Debe crear la instancia de bala correspondiente
	 * y actualizar la municion del arma.
	 */
	protected abstract Bala crearBala();
	
	public abstract boolean tieneMunicion();
	
	private void destruir(){
		if(tanque!=null)
			tanque.quitarArma();
		tanque=null;
	}	
	private void descargar(){
		cargada=false;
		timer = new Timer(tiempoCarga, this);
		timer.start();
	}
	public void actionPerformed(ActionEvent e){
		cargada=true;
		 if(!tieneMunicion()){
				destruir();
		 }
		 timer.stop();
		
	}
	public void setTanque(Tanque tanque){
		this.tanque=tanque;
		tanque.agregarArma(this);
	}
	public Tanque getTanque(){
		return tanque;
	}
	
	
}
