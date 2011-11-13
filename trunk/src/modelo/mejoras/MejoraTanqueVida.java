package modelo.mejoras;

import modelo.Tanque;

public class MejoraTanqueVida extends MejoraTanque {
	private final double PORCENTAJE_VIDA;
	
	public MejoraTanqueVida(double porcentaje) {
		PORCENTAJE_VIDA=porcentaje;
	}
	
	public void mejorar(Object o){
		Tanque tanque = (Tanque) o;
		tanque.mejorarVida(PORCENTAJE_VIDA);
	}
	public void deshacer(Object o){
		// Esta mejora no se deshace.
	}
}
