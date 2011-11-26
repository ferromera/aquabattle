package modelo;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.Timer;

public class Explosion extends ElementoRectangularIntangible implements ActionListener {
	private static final int DURACION= 500;
	private static final double ALTO= 50;
	private static final double ANCHO= 50;
	private boolean destruida=false;
	
	public Explosion(){
		setAlto(ALTO);
		setAncho(ANCHO);
		Timer timer= new Timer(DURACION, this);
		timer.setRepeats(false);
		timer.start();
	}
	public Explosion(double x,double y){
		setX(x);
		setY(y);
		setAlto(ALTO);
		setAncho(ANCHO);
		Timer timer= new Timer(DURACION, this);
		timer.setRepeats(false);
		timer.start();
	}
	
	public void actionPerformed(ActionEvent e){
		destruir();
	}
	public void destruir(){
		destruida=true;
		notificar();
	}
	public boolean estaDestruida() {
		return destruida;
	}

}
