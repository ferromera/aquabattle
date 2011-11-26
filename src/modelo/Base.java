package modelo;

import org.w3c.dom.Element;

import pantallas.PantallaJuego;

import excepciones.NoExisteBaseException;
import excepciones.NoPudoLeerXMLExeption;
import titiritero.Posicionable;

public class Base extends ElementoRectangularSolido implements Impactable, Posicionable {
	public  static final String TAG = "base";
	private int impactosRecibidos;
	private final int ALTO = 50;
	private final int ANCHO = 50;
	
	public Base(double posicionX, double posicionY){
		setX(posicionX);
		setY(posicionY);
		setAlto(ALTO);
		setAncho(ANCHO);
		this.impactosRecibidos = 0;
	}
	public Base(Element element) throws NoPudoLeerXMLExeption{
		super((Element)element.getElementsByTagName(ElementoRectangularSolido.TAG).item(0));

	}
	
	
	public void recibirImpacto(int fuerza){
		this.impactosRecibidos ++;
		if (impactosRecibidos > 1){
			try{
			Escenario.getActual().borrarBase();
			PantallaJuego.getInstancia().perder();
			}catch (NoExisteBaseException e){
				e.printStackTrace();
			}
		}
		notificar();
	}
	
	public int getResistencia(){
		return 1;
	}
	
	public int impactosRecibidos(){
		return impactosRecibidos;
		}
	
	
}
