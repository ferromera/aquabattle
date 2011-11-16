package modelo.mejoras;

import modelo.Tanque;

public class MejoraTanqueAtaque extends MejoraTanque {

	private final double PORCENTAJE_VELOCIDAD;
	private final double PORCENTAJE_DISPARO;

	public MejoraTanqueAtaque(double porcentajeVelocidad,double porcentajeDisparo){
		PORCENTAJE_VELOCIDAD=porcentajeVelocidad;
		PORCENTAJE_DISPARO=porcentajeDisparo;
	}
	
	public void mejorar(Object o) {
		Tanque tanque = (Tanque) o;
		tanque.mejorarVelocidad(PORCENTAJE_VELOCIDAD);
		tanque.mejorarVelocidadDisparo(PORCENTAJE_DISPARO);

	}

	public void deshacer(Object o) {
		Tanque tanque = (Tanque) o;
		tanque.empeorarVelocidad(1 - 1/(1+PORCENTAJE_VELOCIDAD));
		tanque.empeorarVelocidadDisparo(1 - 1/(1+PORCENTAJE_DISPARO));
	}
}
