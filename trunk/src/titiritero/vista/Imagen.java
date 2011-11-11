package titiritero.vista;

import java.awt.Graphics;
import java.awt.RenderingHints;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.lang.Math;

import javax.imageio.ImageIO;

import titiritero.Dibujable;
import titiritero.Posicionable;
import titiritero.SuperficieDeDibujo;

/*
 * Esta clase representa una imagen JPG abstrayendo al usuario de los detalles de Java2D
 * Simplemente requiere de una referencia al nombre del archivo JPG
 */
public class Imagen implements Dibujable{
	
	public Imagen(String nombreArchivoImagen){
		setNombreArchivoImagen(nombreArchivoImagen);
	}
	private double anguloRotacion;
	public void dibujar(SuperficieDeDibujo superficeDeDibujo) {
		Graphics grafico = (Graphics)superficeDeDibujo.getBuffer();
		AffineTransform at = AffineTransform.getRotateInstance(anguloRotacion, imagen.getWidth()/2, imagen.getHeight()/2);
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_INTERPOLATION,
                      RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        AffineTransformOp atop = new AffineTransformOp(at,rh);
        BufferedImage imagenRotada = atop.filter(imagen, null);
		grafico.drawImage(imagenRotada, this.posicionable.getX(), this.posicionable.getY(), null);
	}
	    
	public String getNombreArchivoImagen() {
		return nombreArchivoImagen;
	}

	/**
	 * Estable la imagen con la que se dibujará el objeto.
	 * @param nombreArchivoImagen es el nombre del archivo que contiene l a imagen. Se espera que dicho archivo sea .jpg y esté ubicado en....
	 */
	public void setNombreArchivoImagen(String nombreArchivoImagen) {
		this.nombreArchivoImagen = nombreArchivoImagen;
		try{
			URL u = this.getClass().getResource(this.nombreArchivoImagen);
			this.imagen = ImageIO.read(u);
		}catch(Exception ex){

		}			
	}
	public void rotar(double radianes){
		anguloRotacion=radianes;
		
	}
	public void orientarDerecha(){
		anguloRotacion=Math.PI/2;
		
	}
	public void orientarIzquierda(){
		anguloRotacion=-Math.PI/2;
		
	}
	public void orientarAbajo(){
		anguloRotacion=Math.PI;
		
	}
	public void orientarArriba(){
		anguloRotacion=0;
		
	}
	public Posicionable getPosicionable() {
		return posicionable;
	}

	public void setPosicionable(Posicionable posicionable) {
		this.posicionable = posicionable;
	}
	
	private String nombreArchivoImagen;
    private BufferedImage imagen;
    private Posicionable posicionable;

}
