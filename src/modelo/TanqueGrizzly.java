package modelo;

import org.w3c.dom.Element;

import excepciones.NoPudoLeerXMLExeption;

import modelo.ai.Bot;
import modelo.ai.BotCentro;
import modelo.armamento.Ametralladora;
import modelo.TanqueHeroe;

public class TanqueGrizzly extends TanqueEnemigo {
	public static final String TAG="tanque-grizzly";
	private static final int RESISTENCIA = 100;
	private static final double VELOCIDAD = 30.0;
	private static final double ANCHO = 50.0;
	private static final double ALTO = 50;
	private static final int TIEMPO_ENTRE_DISPAROS = 4000;
	private static final int PUNTOS_OTORGADOS = 20;
	private Bot bot;

	public TanqueGrizzly(double x, double y) {
		destruido=false;
		resistencia = RESISTENCIA;
		setX(x);
		setY(y);
		velocidad = VELOCIDAD;
		setAlto(ALTO);
		setAncho(ANCHO);
		setPuntosOtorgados(PUNTOS_OTORGADOS);
		Ametralladora am = new Ametralladora(this);
		am.setTiempoDeCarga(TIEMPO_ENTRE_DISPAROS);
		agregarArma(am);
		bot = new BotCentro(this, TanqueHeroe.getInstancia());
	}
	public TanqueGrizzly(Element element) throws NoPudoLeerXMLExeption{
		super((Element)element.getElementsByTagName(TanqueEnemigo.TAG).item(0));
	}

	@Override
	public void calcularSiguienteMovimiento() {
		bot.setObjetivo(TanqueHeroe.getInstancia());
		bot.actuar();
	}
	@Override
	protected void tirarArma() {
		// No vale la pena tirar mi arma
	}
	

}
