package modelo;


import org.w3c.dom.Element;

import misc.ContadorDeInstancias;
import modelo.armamento.LanzaCohetes;

import excepciones.NoPudoLeerXMLExeption;
import excepciones.NoSePudoPosicionarException;

public class ArmaTiradaLanzaCohetes extends ArmaTirada {
	public  static final String TAG = "objeto-arma-tirada-lanzacohetes";
	
	private long id=ContadorDeInstancias.getId();

	private static final int TIEMPO_DE_VIDA = 10000;

	
	public ArmaTiradaLanzaCohetes(double posX,double posY)
			throws NoSePudoPosicionarException {
		super(posX, posY, TIEMPO_DE_VIDA);

	}


	
	public ArmaTiradaLanzaCohetes(Element element) throws NoPudoLeerXMLExeption {
		super((Element)element.getElementsByTagName(ArmaTirada.TAG).item(0));
		
	}



	public void aplicarEfecto(Tanque tanque){
		tanque.agregarArma(new LanzaCohetes(tanque));
	}
}