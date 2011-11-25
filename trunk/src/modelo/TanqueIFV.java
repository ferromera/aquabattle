package modelo;

import org.w3c.dom.Element;

import excepciones.NoExisteBaseException;
import excepciones.NoPudoLeerXMLExeption;
import modelo.ai.Bot;
import modelo.ai.BotBordes;
import modelo.armamento.Canion;

public class TanqueIFV extends TanqueEnemigo {
	public static final String TAG="tanque-ifv";
	private static final int RESISTENCIA = 100;
	private static final double VELOCIDAD = 100.0;
	private static final double ANCHO = 50.0;
	private static final double ALTO = 50;
	
	private Bot bot;
	
	public TanqueIFV(double x, double y) throws NoExisteBaseException {
		destruido=false;
		resistencia = RESISTENCIA;
		setX(x);
		setY(y);
		velocidad = VELOCIDAD;
		setAlto(ALTO);
		setAncho(ANCHO);
		agregarArma(new Canion(this));
		bot = new BotBordes(this, Escenario.getActual().getBase());
	}
	public TanqueIFV(Element element) throws NoPudoLeerXMLExeption{
		super((Element)element.getElementsByTagName(TanqueEnemigo.TAG).item(0));
	}

	@Override
	public void calcularSiguienteMovimiento() {
		bot.actuar();
	}

}
