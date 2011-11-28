package modelo.armamento;

import misc.ContadorDeInstancias;
import modelo.ElementoRectangularSolido;

import org.w3c.dom.Element;

import excepciones.NoPudoLeerXMLExeption;

public class BalaCanion extends Bala {
	private long id=ContadorDeInstancias.getId();
	
	public  static final String TAG = "objeto-bala-canion";
	private final int FUERZA=30;
	private final double VELOCIDAD=200.0;
	private final double ANCHO=30.0;
	private final double ALTO=30.0;
	
	public BalaCanion(){
		velocidad=VELOCIDAD;
		fuerza=FUERZA;
		setAlto(ALTO);
		setAncho(ANCHO);
	}
	public BalaCanion(Element element) throws NoPudoLeerXMLExeption{
		super((Element)element.getElementsByTagName(Bala.TAG).item(0));
	}
	public BalaCanion(double x, double y){
		velocidad=VELOCIDAD;
		fuerza=FUERZA;
		setAlto(ALTO);
		setAncho(ANCHO);
		setX(x);
		setY(y);
	}
	
}
