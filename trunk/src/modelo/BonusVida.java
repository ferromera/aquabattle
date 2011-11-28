package modelo;


import org.w3c.dom.Element;

import excepciones.NoPudoLeerXMLExeption;
import excepciones.NoSePudoPosicionarException;

import misc.ContadorDeInstancias;
import modelo.mejoras.MejoraTanqueVida;


public class BonusVida extends Bonus {
	private long id=ContadorDeInstancias.getId();
	
	public  static final String TAG = "objeto-bonus-vida";
	private static final int TIEMPO_DE_VIDA = 10000;

	public BonusVida(PosicionadorAleatorio posicionador)
			throws NoSePudoPosicionarException {
		super(posicionador, TIEMPO_DE_VIDA);

	}
	public BonusVida(Element element) throws NoPudoLeerXMLExeption{
		super((Element)element.getElementsByTagName(Bonus.TAG).item(0));
	}
	
	public void aplicarEfecto(Tanque tanque){
		new MejoraTanqueVida(0.4,tanque);
	}
		


}
