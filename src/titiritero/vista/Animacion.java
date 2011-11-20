package titiritero.vista;

import java.awt.Graphics;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.Timer;

import titiritero.Dibujable;
import titiritero.Posicionable;
import titiritero.SuperficieDeDibujo;

public class Animacion implements Dibujable,ActionListener {
	private ArrayList<Imagen> imagenes;
	private int frame=0;
	private int alto,ancho;
	private double fps;
	private Timer timer;
	private Posicionable posicionable;
	private boolean unaVez=false;
	
	public Animacion(String nombreArchivoSprite,Posicionable posicionable,int alto, int ancho){
		imagenes=new ArrayList<Imagen>();
		Imagen imagen=new Imagen(nombreArchivoSprite,posicionable);
		this.alto=alto;
		this.ancho=ancho;
		timer = new Timer((int)(1000.0/50.0),this);
		setFps(50.0);
		int x=0,y=0;
		int anchoImagen=imagen.getAncho();
		int altoImagen=imagen.getAlto();
		
		for(y=0;y<altoImagen;y+=alto)
			for(x=0;x<anchoImagen;x+=ancho)
				imagenes.add(imagen.getSubimagen(x, y, ancho, alto));
			
	}
	public Animacion(Imagen imagen,int alto, int ancho){
		imagenes=new ArrayList<Imagen>();
		this.alto=alto;
		this.ancho=ancho;
		timer = new Timer(100,this);
		int x=0,y=0;
		int anchoImagen=imagen.getAncho();
		int altoImagen=imagen.getAlto();
		
		for(y=0;y<altoImagen;y+=alto)
			for(x=0;x<anchoImagen;x+=ancho)
				imagenes.add(imagen.getSubimagen(x, y, ancho, alto));
				
	}

	public double getFps() {
		return fps;
	}

	public void setFps(double fps) {
		this.fps = fps;
		if(timer.isRunning()){
			timer.stop();
			timer = new Timer((int)(1000.0/fps),this);
			timer.start();
		}else 
			timer = new Timer((int)(1000.0/fps),this);
	}
	public void reproducir(){
		timer.start();
		unaVez=false;
	}
	public void reproducirUnaVez(){
		frame=0;
		timer.start();
		unaVez=true;
		
	}
	public void detener(){
		timer.stop();
	}
	public void actionPerformed(ActionEvent e){
		frame++;
		if(frame==imagenes.size()){
			if(unaVez){
				detener();
				frame--;
			}
			else
				frame=0;
			
		}
			
	}
	public void dibujar(SuperficieDeDibujo superficeDeDibujo) {
		imagenes.get(frame).dibujar(superficeDeDibujo);
	}

	@Override
	public Posicionable getPosicionable() {
		
		return posicionable;
	}

	@Override
	public void setPosicionable(Posicionable posicionable) {
		this.posicionable=posicionable;
		
	}
	
	public void rotar(double radianes){
		Iterator<Imagen> it = imagenes.iterator();
		while(it.hasNext())
			it.next().rotar(radianes);
		
	}
	public void orientarDerecha(){
		Iterator<Imagen> it = imagenes.iterator();
		while(it.hasNext())
			it.next().orientarDerecha();
		
	}
	public void orientarIzquierda(){
		Iterator<Imagen> it = imagenes.iterator();
		while(it.hasNext())
			it.next().orientarIzquierda();
		
	}
	public void orientarAbajo(){
		Iterator<Imagen> it = imagenes.iterator();
		while(it.hasNext())
			it.next().orientarAbajo();
		
	}
	public void orientarArriba(){
		Iterator<Imagen> it = imagenes.iterator();
		while(it.hasNext())
			it.next().orientarArriba();
		
	}
	

}
