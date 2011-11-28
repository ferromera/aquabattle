package modelo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.Timer;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import excepciones.NoPudoLeerXMLExeption;
import excepciones.NoSePudoPosicionarException;
import titiritero.ObjetoVivo;

public abstract class Bonus extends ElementoRectangularIntangible implements
		ObjetoVivo ,ActionListener{
	public  static final String TAG = "objeto-bonus";
	private static final String TAG_RESTANTE = "restante";
	private static final String TAG_BORRADO = "borrado";
	private static final String TAG_PAUSADO = "pausado";
	private static final String TAG_TIEMPO_INICO = "tiempo-inicio";
	private boolean borrado;
	private Timer timer;
	private int restante;
	private boolean pausado;
	private long tiempoInicio;
	

	public Bonus(PosicionadorAleatorio posicionador,int tiempoDeVida) throws NoSePudoPosicionarException{
		posicionador.posicionar(this);
		restante=tiempoDeVida;
		timer=new Timer(restante,this);
		timer.setRepeats(false);
		timer.start();
		tiempoInicio = new Date().getTime();
		borrado=false;
		pausado=false;
	}
	public Bonus(Element element) throws NoPudoLeerXMLExeption{
		super((Element)element.getElementsByTagName(ElementoRectangularIntangible.TAG).item(0));
		NodeList hijos;
		Element elem;
		hijos = element.getChildNodes();
		if(hijos!=null && hijos.getLength()>0){
			for(int i=0;i<hijos.getLength();i++){
				elem = (Element) hijos.item(i);
				if(elem.getTagName().equals(TAG_BORRADO))
					borrado=Boolean.parseBoolean(elem.getTextContent());
				else if(elem.getTagName().equals(TAG_PAUSADO))
					pausado=Boolean.parseBoolean(elem.getTextContent());
				else if(elem.getTagName().equals(TAG_RESTANTE))
					restante=Integer.parseInt(elem.getTextContent());
				else if(elem.getTagName().equals(TAG_TIEMPO_INICO))
					tiempoInicio=Long.parseLong(elem.getTextContent());
			}
		}
		
	}
	public void actionPerformed(ActionEvent e) {
		destruir();
	}

	public void vivir() {
		if(borrado||pausado)
			return;
		TanqueHeroe tanque = TanqueHeroe.getInstancia();
			
		if (this.superpuestoCon(tanque)) {
			aplicarEfecto(tanque);
			destruir();
		}
	}
	protected abstract void aplicarEfecto(Tanque tanque);
	
	protected void destruir(){
		borrado=true;
		Escenario.getActual().borrarObjeto(this);
		Escenario.getActual().borrarObjetoVivo(this);
		notificar();
	}
	public boolean estaBorrado() {
		return borrado;
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

}
