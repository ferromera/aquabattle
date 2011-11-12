package modelo;

public class Cohete extends Bala {

	private final double VELOCIDAD = 300.0;

	public Cohete() {
		velocidad = VELOCIDAD;

	}

	public void impactar(ElementoRectangularSolido solido) {
		if (solido != null)
			solido.recibirImpacto(solido.getResistencia() / 2);
		destruir();
	}

}
