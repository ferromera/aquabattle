package modelo;

public class MejoraTanqueAtaque extends MejoraTanque {

	private final double PORCENTAJE_VELOCIDAD = 0.2;
	private final double PORCENTAJE_DISPARO = 0.3;

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
