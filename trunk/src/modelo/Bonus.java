package modelo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import excepciones.NoSePudoPosicionarException;
import titiritero.ObjetoVivo;

public abstract class Bonus extends ElementoRectangularIntangible implements
		ObjetoVivo ,ActionListener{

	public Bonus(PosicionadorAleatorio posicionador,int tiempoDeVida) throws NoSePudoPosicionarException{
		posicionador.posicionar(this);
		Timer timer=new Timer(tiempoDeVida,this);
		timer.start();
	}
	public void actionPerformed(ActionEvent e) {
		destruir();
	}

	public void vivir() {
		TanqueHeroe tanque = TanqueHeroe.getInstancia();

		if (this.superpuestoCon(tanque)) {
			aplicarEfecto(tanque);
			destruir();
		}
	}
	protected abstract void aplicarEfecto(Tanque tanque);
	
	protected void destruir(){
		Escenario.getActual().borrarObjeto(this);
		Escenario.getActual().borrarObjetoVivo(this);
	}

}
