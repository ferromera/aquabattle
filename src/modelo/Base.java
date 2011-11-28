package modelo;

import misc.ContadorDeInstancias;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import pantallas.PantallaJuego;

import excepciones.NoExisteBaseException;
import excepciones.NoPudoLeerXMLExeption;
import titiritero.Posicionable;

public class Base extends ElementoRectangularSolido implements Impactable, Posicionable {
	private long id=ContadorDeInstancias.getId();
	
	public  static final String TAG = "objeto-base";

	private static final String TAG_IMPACTOS_RECIBIDOS = "impactos-recibidos";
	private int impactosRecibidos;
	private static final int ALTO = 50;
	private static final int ANCHO = 50;
	
	public Base(double posicionX, double posicionY){
		setX(posicionX);
		setY(posicionY);
		setAlto(ALTO);
		setAncho(ANCHO);
		this.impactosRecibidos = 0;
	}
	public Base(Element element) throws NoPudoLeerXMLExeption{
		super((Element)element.getElementsByTagName(ElementoRectangularSolido.TAG).item(0));
		NodeList hijos;
		Element elem;
		hijos = element.getChildNodes();
		if(hijos!=null && hijos.getLength()>0){
			for(int i=0;i<hijos.getLength();i++){
				elem = (Element) hijos.item(i);
				if(elem.getTagName().equals(TAG_IMPACTOS_RECIBIDOS))
					impactosRecibidos=Integer.parseInt(elem.getTextContent());
			}
		}
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
