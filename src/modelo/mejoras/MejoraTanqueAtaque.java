package modelo.mejoras;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.Timer;

import modelo.Tanque;
import modelo.TanqueHeroe;

public class MejoraTanqueAtaque extends MejoraTanque implements ActionListener{

	private final double PORCENTAJE_VELOCIDAD;
	private final double PORCENTAJE_DISPARO;
	private int restante;
	private final int DURACION=10000;
	private Timer timer=null;
	private Tanque tanque=null;
	private long tiempoInicio;

	public MejoraTanqueAtaque(double porcentajeVelocidad,double porcentajeDisparo,Tanque tanque){
		PORCENTAJE_VELOCIDAD=porcentajeVelocidad;
		PORCENTAJE_DISPARO=porcentajeDisparo;
		this.tanque=tanque;
		tanque.agregarMejora(this);
		restante=DURACION;
		
	}
	
	public void mejorar() {

		tanque.mejorarVelocidad(PORCENTAJE_VELOCIDAD);
		tanque.mejorarVelocidadDisparo(PORCENTAJE_DISPARO);
		tiempoInicio= new Date().getTime();
		timer = new Timer(DURACION,this);
		timer.setRepeats(false);
		timer.start();

	}

	public void deshacer() {
		tanque.empeorarVelocidad(1 - 1/(1+PORCENTAJE_VELOCIDAD));
		tanque.empeorarVelocidadDisparo(1 - 1/(1+PORCENTAJE_DISPARO));
	}
	
	public void pausar(){
		restante-=(int)(new Date().getTime() - tiempoInicio);
		timer.stop();
	}
	public void reanudar(){
		tiempoInicio= new Date().getTime();
		timer = new Timer(restante,this);
		timer.setRepeats(false);
		timer.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		tanque.quitarMejora(this);
		
	}

}
