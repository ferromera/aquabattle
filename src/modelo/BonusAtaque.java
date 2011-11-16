package modelo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modelo.mejoras.MejoraTanqueAtaque;

import excepciones.NoSePudoPosicionarException;

import titiritero.ObjetoVivo;
import titiritero.Posicionable;

public class BonusAtaque extends ElementoRectangularIntangible implements
ObjetoVivo, Posicionable, ActionListener {
	
	public BonusAtaque(){
		try{
		PosicionadorAleatorioStd posicionAleatoria = new PosicionadorAleatorioStd(this);
		posicionAleatoria.posicionar();
		}catch (NoSePudoPosicionarException e){
		}
	}
	
	
	public void vivir(){
		TanqueHeroe tanque = TanqueHeroe.getInstancia();
		
		if(this.superpuestoCon(tanque)){
			MejoraTanqueAtaque mejoraAtaque = new MejoraTanqueAtaque();
			tanque.agregarMejora(mejoraAtaque);
			Escenario.getActual().borrarObjeto(this);
			}
		}
	
	public void actionPerformed(ActionEvent e){
		Escenario.getActual().borrarObjeto(this);
	}
	
	

}
