package modelo;

import java.util.Date;

import excepciones.NoExisteElementoColisionadoException;
import modelo.armamento.Ametralladora;

public class TanqueHeroe extends Tanque {

	private final int RESISTENCIA = 100;
	private final double POS_X = 300.0;
	private final double POS_Y = 400.0;
	private final double VELOCIDAD = 100.0;
	private final double ANCHO = 50.0;
	private final double ALTO = 50;
	private long ultimoTiempo;
	private boolean destruido;

	private static TanqueHeroe instancia = null;

	static TanqueHeroe getInstancia() {
		if (instancia == null)
			instancia = new TanqueHeroe();
		return instancia;
	}

	public TanqueHeroe() {
		destruido=false;
		resistencia = RESISTENCIA;
		setX(POS_X);
		setY(POS_Y);
		velocidad = VELOCIDAD;
		setAlto(ALTO);
		setAncho(ANCHO);
		agregarArma(new Ametralladora(this));
		ultimoTiempo = new Date().getTime();
	}

	public void destruir() {
		Escenario.getActual().borrarObjetoVivo(this);
		Escenario.getActual().borrarSolido(this);
		notificar();
		
	}

	public void vivir() {
		if (enMovimiento()) {
			long tiempoActual = new Date().getTime();
			int intervaloTiempo = (int) (tiempoActual - ultimoTiempo);
			ultimoTiempo = tiempoActual;
			double movimientoRestante = (velocidad * (double) intervaloTiempo / 1000.0);

			while (movimientoRestante > 1.0) {
				movimientoRestante--;
				avanzar();
				if (estaColisionado()) {
					retroceder();
					return;
				}
			}
			avanzar(movimientoRestante);
			if (estaColisionado()) {
				retroceder(movimientoRestante);
			}
		}
	}

	public boolean estaDestruido() {
		return destruido;
	}



}
