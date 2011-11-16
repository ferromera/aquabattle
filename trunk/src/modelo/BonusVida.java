package modelo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import excepciones.NoSePudoPosicionarException;

import modelo.mejoras.MejoraTanqueVida;

import titiritero.ObjetoVivo;
import titiritero.Posicionable;

public class BonusVida extends ElementoRectangularIntangible implements
ObjetoVivo, Posicionable, ActionListener {

	public BonusVida(){
		try{
		PosicionadorAleatorioStd posicionAleatoria = new PosicionadorAleatorioStd(this);
		posicionAleatoria.posicionar();
		}catch (NoSePudoPosicionarException e){
		}
	}
	
	public void vivir(){
		TanqueHeroe tanque = TanqueHeroe.getInstancia();
		
		if(this.superpuestoCon(tanque)){
			MejoraTanqueVida mejoraVida = new MejoraTanqueVida(40.0);
			tanque.agregarMejora(mejoraVida);
			Escenario.getActual().borrarObjeto(this);
			}
		}
	
	public void actionPerformed(ActionEvent e){
		Escenario.getActual().borrarObjeto(this);
	}
		


}
