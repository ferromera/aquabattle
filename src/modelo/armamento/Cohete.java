package modelo.armamento;

import modelo.ElementoRectangularSolido;

public class Cohete extends Bala {

	private final double VELOCIDAD = 300.0;
	private final double ANCHO = 50.0;
	private final double ALTO = 50.0;

	public Cohete() {
		velocidad = VELOCIDAD;
		setAlto(ALTO);
		setAncho(ANCHO);
	}
	public Cohete(double x, double y) {
		velocidad = VELOCIDAD;
		setAlto(ALTO);
		setAncho(ANCHO);
		setX(x);
		setY(y);
	}

	public void impactar(ElementoRectangularSolido solido) {
		if (solido != null)
			solido.recibirImpacto(solido.getResistencia() / 2);
		destruir();
	}

}
