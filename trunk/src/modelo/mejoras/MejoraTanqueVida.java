package modelo.mejoras;

import modelo.Tanque;

public class MejoraTanqueVida extends MejoraTanque {
	private final double PORCENTAJE_VIDA;
	private Tanque tanque;
	
	public MejoraTanqueVida(double porcentaje ,Tanque tanque) {
		PORCENTAJE_VIDA=porcentaje;
		this.tanque=tanque;
		tanque.agregarMejora(this);
	}
	
	public void mejorar(){
		tanque.mejorarVida(PORCENTAJE_VIDA);
		tanque.quitarMejora(this);
	}
	public void deshacer(){
		// Esta mejora no se deshace.
	}

	@Override
	public void pausar() {
		
	}

	@Override
	public void reanudar() {
		
	}
}
