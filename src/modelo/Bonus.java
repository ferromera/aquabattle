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
	public  static final String TAG = "base";
	private static final String TAG_TIEMPO_RESTANTE = "tiempo-restante";
	private boolean borrado;
	private Timer timer;
	private int restante;
	private long tiempoInicial;
	private int tiempoVida;
	private boolean pausado;
	

	public Bonus(PosicionadorAleatorio posicionador,int tiempoDeVida) throws NoSePudoPosicionarException{
		posicionador.posicionar(this);
		restante=tiempoDeVida;
		tiempoVida=tiempoDeVida;
		timer=new Timer(restante,this);
		timer.setRepeats(false);
		timer.start();
		tiempoInicial = new Date().getTime();
		borrado=false;
		pausado=false;
	}
	public Bonus(Element element) throws NoPudoLeerXMLExeption{
		super((Element)element.getElementsByTagName(ElementoRectangularIntangible.TAG).item(0));
		NodeList nodo = element.getElementsByTagName(TAG_TIEMPO_RESTANTE);
		if(nodo!=null && nodo.getLength()>0){
			if(nodo.getLength()>1)
				throw new NoPudoLeerXMLExeption("No puede haber mas de un tag: "+TAG_TIEMPO_RESTANTE+" en el nodo "+element.getTagName());
			Element elem = (Element) nodo.item(0);
			int tiempoRestante=Integer.parseInt(elem.getTextContent());
			Timer timer =new Timer(tiempoRestante,this);
			timer.setRepeats(false);
		}
	}
	public void actionPerformed(ActionEvent e) {
		destruir();
	}

	public void vivir() {
		if(borrado)
			return;
		TanqueHeroe tanque = TanqueHeroe.getInstancia();
		if(pausado){
			timer=new Timer(restante,this);
			timer.setRepeats(false);
			timer.start();
		}
			
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
	@Override
	public void pausar() {
		pausado=true;
		restante= tiempoVida-(int)(new Date().getTime()-tiempoInicial);
		timer.stop();
	}

}
