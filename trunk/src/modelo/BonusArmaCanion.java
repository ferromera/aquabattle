package modelo;

import java.util.Date;

import javax.swing.Timer;

import modelo.armamento.Canion;
import modelo.mejoras.MejoraTanqueAtaque;

import org.w3c.dom.Element;

import excepciones.NoPudoLeerXMLExeption;
import excepciones.NoSePudoPosicionarException;

public class BonusArmaCanion extends Bonus {
	public  static final String TAG = "bonus-arma-canion";
	private static final int TIEMPO_DE_VIDA = 10000;
	private long tiempoActual;
	
	public BonusArmaCanion(PosicionadorAleatorio posicionador)
			throws NoSePudoPosicionarException {
		super(posicionador, TIEMPO_DE_VIDA);

	}
	
	public BonusArmaCanion(double posX, double posY) throws NoSePudoPosicionarException{
		setX(posX);
		setY(posY);

		Timer timer=new Timer(TIEMPO_DE_VIDA,this);
		timer.setRepeats(false);
		timer.start();
		tiempoActual = new Date().getTime();

	}
	public BonusArmaCanion(Element element) throws NoPudoLeerXMLExeption{
		super((Element)element.getElementsByTagName(Bonus.TAG).item(0));
	}
	
	public void aplicarEfecto(Tanque tanque){
		Canion canionAgregar = new Canion(tanque);
	}
	
}
