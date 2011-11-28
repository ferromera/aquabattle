package modelo;


import org.w3c.dom.Element;

import pantallas.Pantalla;

import misc.ContadorDeInstancias;
import modelo.armamento.Canion;

import excepciones.NoPudoLeerXMLExeption;
import excepciones.NoSePudoPosicionarException;

public class ArmaTiradaCanion extends ArmaTirada {
	public  static final String TAG = "objeto-arma-tirada-canion";
	
	private long id=ContadorDeInstancias.getId();

	private static final int TIEMPO_DE_VIDA = 10000;
	
	public ArmaTiradaCanion(double posX, double posY)
			throws NoSePudoPosicionarException {
		super(posX, posY, TIEMPO_DE_VIDA);
	}
	

	
	public ArmaTiradaCanion(Element element) throws NoPudoLeerXMLExeption {
		super((Element)element.getElementsByTagName(ArmaTirada.TAG).item(0));
		
	}



	public void aplicarEfecto(Tanque tanque){
		tanque.agregarArma(new Canion(tanque));
	}
	
}
