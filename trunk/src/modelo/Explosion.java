package modelo;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Date;

import javax.swing.Timer;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import excepciones.NoPudoLeerXMLExeption;

import titiritero.ObjetoVivo;

import misc.ContadorDeInstancias;

public class Explosion extends ElementoRectangularIntangible implements ActionListener,ObjetoVivo {
	public  static final String TAG = "objeto-explosion";
	
	private long id=ContadorDeInstancias.getId();
	
	private static final int DURACION= 500;
	private static final double ALTO= 50;
	private static final double ANCHO= 50;

	private static final String TAG_DESTRUIDA = "destruida";
	private static final String TAG_PAUSADO = "pausado";
	private static final String TAG_RESTANTE = "restante";
	private static final String TAG_TIEMPO_INICO = "tiempo-inicio";
	
	private boolean destruida=false;
	private boolean pausado;
	private int restante;
	private Timer timer;
	private long tiempoInicio;
	
	public Explosion(){
		setAlto(ALTO);
		setAncho(ANCHO);
		restante=DURACION;
		tiempoInicio=new Date().getTime();
		Timer timer= new Timer(DURACION, this);
		timer.setRepeats(false);
		timer.start();
		pausado=false;
		destruida=false;
	}
	public Explosion(double x,double y){
		setX(x);
		setY(y);
		setAlto(ALTO);
		setAncho(ANCHO);
		Timer timer= new Timer(DURACION, this);
		timer.setRepeats(false);
		timer.start();
	}
	
	public Explosion(Element element) throws NoPudoLeerXMLExeption {
		super((Element)element.getElementsByTagName(ElementoRectangularIntangible.TAG).item(0));
		NodeList hijos;
		Element elem;
		hijos = element.getChildNodes();
		if(hijos!=null && hijos.getLength()>0){
			for(int i=0;i<hijos.getLength();i++){
				elem = (Element) hijos.item(i);
				if(elem.getTagName().equals(TAG_DESTRUIDA))
					destruida=Boolean.parseBoolean(elem.getTextContent());
				else if(elem.getTagName().equals(TAG_PAUSADO))
					pausado=Boolean.parseBoolean(elem.getTextContent());
				else if(elem.getTagName().equals(TAG_RESTANTE))
					restante=Integer.parseInt(elem.getTextContent());
				else if(elem.getTagName().equals(TAG_TIEMPO_INICO))
					tiempoInicio=Long.parseLong(elem.getTextContent());
			}
		}
	}
	public void actionPerformed(ActionEvent e){
		destruir();
	}
	public void destruir(){
		Escenario.getActual().borrarObjeto(this);
		Escenario.getActual().borrarObjetoVivo(this);
		destruida=true;
		notificar();
	}
	public boolean estaDestruida() {
		return destruida;
	}
	public void pausar(){
		if(pausado)
			return;
		pausado=true;
		restante-=(int)(new Date().getTime() - tiempoInicio);
		timer.stop();
	}
	public void reanudar(){
		if(!pausado)
			return;
		pausado=false;
		tiempoInicio= new Date().getTime();
		timer = new Timer(restante,this);
		timer.setRepeats(false);
		timer.start();
	}
	@Override
	public void vivir() {
		
		
	}

}
