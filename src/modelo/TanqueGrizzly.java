package modelo;

import modelo.ai.Bot;
import modelo.ai.BotBordes;
import modelo.armamento.Ametralladora;
import modelo.TanqueHeroe;

public class TanqueGrizzly extends TanqueEnemigo {
	
	private final int RESISTENCIA = 100;
	private final double VELOCIDAD = 100.0;
	private final double ANCHO = 50.0;
	private final double ALTO = 50;
	
	private Bot bot;

	public TanqueGrizzly(double x, double y) {
		destruido=false;
		resistencia = RESISTENCIA;
		setX(x);
		setY(y);
		velocidad = VELOCIDAD;
		setAlto(ALTO);
		setAncho(ANCHO);
		agregarArma(new Ametralladora(this));
		bot = new BotBordes(this, TanqueHeroe.getInstancia());
	}

	@Override
	public void calcularSiguienteMovimiento() {
		bot.actuar();
	}

}
