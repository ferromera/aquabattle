package modelo;

import org.w3c.dom.Element;

import excepciones.NoExisteBaseException;
import excepciones.NoPudoLeerXMLExeption;
import modelo.ai.Bot;
import modelo.ai.BotCentro;
import modelo.armamento.Canion;

public class TanqueMirage extends TanqueEnemigo {
	public static final String TAG="tanque-mirage";
	private final int RESISTENCIA = 100;
	private final double VELOCIDAD = 100.0;
	private final double ANCHO = 50.0;
	private final double ALTO = 50;
	
	private Bot bot;
	
	public TanqueMirage(double x, double y) throws NoExisteBaseException {
		destruido=false;
		resistencia = RESISTENCIA;
		setX(x);
		setY(y);
		velocidad = VELOCIDAD;
		setAlto(ALTO);
		setAncho(ANCHO);
		agregarArma(new Canion(this));
		bot = new BotCentro(this, Escenario.getActual().getBase());
	}
	public TanqueMirage(Element element) throws NoPudoLeerXMLExeption{
		super((Element)element.getElementsByTagName(TanqueEnemigo.TAG).item(0));
	}
	@Override
	public void calcularSiguienteMovimiento() {
		bot.actuar();
	}

}
