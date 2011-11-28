package modelo.mejoras;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.Timer;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import excepciones.NoPudoLeerXMLExeption;

import misc.ContadorDeInstancias;
import misc.DiccionarioDeSerializables;
import misc.FabricaElementos;
import misc.Nivel;
import modelo.Tanque;
import modelo.TanqueHeroe;

public class MejoraTanqueAtaque extends MejoraTanque implements ActionListener{
	public static final String TAG = "objeto-mejora-tanque-ataque";

	private long id=ContadorDeInstancias.getId();

	private final double PORCENTAJE_VELOCIDAD;
	private final double PORCENTAJE_DISPARO;
	private int restante;
	private static final int DURACION=10000;

	private static final String TAG_PORCENTAJE_DISPARO = "porcentaje-disparo";
	private static final String TAG_PORCENTAJE_VELOCIDAD = "porcentaje-velocidad";
	private static final String TAG_RESTANTE = "restante";
	private static final String TAG_TANQUE = "tanque";
	
	private Timer timer=null;
	private Tanque tanque=null;
	private long tiempoInicio;

	public MejoraTanqueAtaque(double porcentajeVelocidad,double porcentajeDisparo,Tanque tanque){
		PORCENTAJE_VELOCIDAD=porcentajeVelocidad;
		PORCENTAJE_DISPARO=porcentajeDisparo;
		this.tanque=tanque;
		tanque.agregarMejora(this);
		restante=DURACION;
		
	}
	
	public MejoraTanqueAtaque(Element element) throws NoPudoLeerXMLExeption {
		double porcentajeDisparo=0,porcentajeVelocidad=0;
		NodeList hijos;
		Element elem;
		hijos = element.getChildNodes();
		if(hijos!=null && hijos.getLength()>0){
			for(int i=0;i<hijos.getLength();i++){
				elem = (Element) hijos.item(i);
				if(elem.getTagName().equals(TAG_PORCENTAJE_DISPARO))
					porcentajeDisparo=Double.parseDouble(elem.getTextContent());
				else if(elem.getTagName().equals(TAG_PORCENTAJE_VELOCIDAD))
					porcentajeVelocidad=Double.parseDouble(elem.getTextContent());
				else if(elem.getTagName().equals(TAG_RESTANTE))
					restante=Integer.parseInt(elem.getTextContent());
				else if(elem.getTagName().equals(TAG_TANQUE))
					tanque=(Tanque)DiccionarioDeSerializables.getInstancia((Element)elem.getFirstChild());
			}
		}
		PORCENTAJE_DISPARO=porcentajeDisparo;
		PORCENTAJE_VELOCIDAD=porcentajeVelocidad;
	}

	public void mejorar() {

		tanque.mejorarVelocidad(PORCENTAJE_VELOCIDAD);
		tanque.mejorarVelocidadDisparo(PORCENTAJE_DISPARO);
		tiempoInicio= new Date().getTime();
		timer = new Timer(DURACION,this);
		timer.setRepeats(false);
		timer.start();

	}

	public void deshacer() {
		tanque.empeorarVelocidad(1 - 1/(1+PORCENTAJE_VELOCIDAD));
		tanque.empeorarVelocidadDisparo(1 - 1/(1+PORCENTAJE_DISPARO));
	}
	
	public void pausar(){
		restante-=(int)(new Date().getTime() - tiempoInicio);
		timer.stop();
	}
	public void reanudar(){
		tiempoInicio= new Date().getTime();
		timer = new Timer(restante,this);
		timer.setRepeats(false);
		timer.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		tanque.quitarMejora(this);
		
	}

}
