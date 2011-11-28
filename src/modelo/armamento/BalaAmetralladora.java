package modelo.armamento;

import misc.ContadorDeInstancias;
import modelo.ElementoRectangularSolido;

import org.w3c.dom.Element;

import excepciones.NoPudoLeerXMLExeption;



public class BalaAmetralladora extends Bala {
	private long id=ContadorDeInstancias.getId();
	
	public  static final String TAG = "objeto-bala-ametralladora";
	private static final int FUERZA=20;
	private static final double VELOCIDAD=150.0;
	private static final double ANCHO=10.0;
	private static final double ALTO=10.0;
	
	public BalaAmetralladora(){
		velocidad=VELOCIDAD;
		fuerza=FUERZA;
		setAlto(ALTO);
		setAncho(ANCHO);
	}
	public BalaAmetralladora(Element element) throws NoPudoLeerXMLExeption{
		super((Element)element.getElementsByTagName(Bala.TAG).item(0));
		
	}
	public BalaAmetralladora(double x,double y){
		velocidad=VELOCIDAD;
		fuerza=FUERZA;
		setAlto(ALTO);
		setAncho(ANCHO);
		setX(x);
		setY(y);
	}
	

}
