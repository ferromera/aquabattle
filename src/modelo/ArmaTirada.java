package modelo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.Timer;

import excepciones.NoSePudoPosicionarException;

import titiritero.ObjetoVivo;

public abstract class ArmaTirada extends ElementoRectangularIntangible implements
ObjetoVivo ,ActionListener{
	
	private long tiempoActual;
	
	public ArmaTirada(double posX, double posY,int tiempoDeVida) throws NoSePudoPosicionarException{
		setX(posX);
		setY(posY);
		Timer timer=new Timer(tiempoDeVida,this);
		timer.setRepeats(false);
		timer.start();
		tiempoActual = new Date().getTime();
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
