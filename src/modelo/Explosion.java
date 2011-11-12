package modelo;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.Timer;

public class Explosion extends ElementoRectangularIntangible implements ActionListener {
	private final int DURACION= 2000;
	private boolean destruido=false;
	
	public Explosion(){
		Timer timer= new Timer(DURACION, this);
		timer.start();
	}
	public Explosion(double x,double y){
		setX(x);
		setY(y);
		Timer timer= new Timer(DURACION, this);
		timer.start();
	}
	
	public void actionPerformed(ActionEvent e){
		destruir();
	}
	public void destruir(){
		destruido=true;
		notificar();
	}

}
