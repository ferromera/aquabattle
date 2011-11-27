package modelo;

import org.w3c.dom.Element;

import excepciones.NoExisteBaseException;
import excepciones.NoPudoLeerXMLExeption;
import excepciones.NoSePudoPosicionarException;
import misc.FabricaElementos;
import modelo.ai.Bot;
import modelo.ai.BotBordes;
import modelo.armamento.Ametralladora;
import modelo.armamento.Canion;

public class TanqueIFV extends TanqueEnemigo {
	public static final String TAG="tanque-ifv";
	private static final int RESISTENCIA = 100;
	private static final double VELOCIDAD = 80.0;
	private static final double ANCHO = 50.0;
	private static final double ALTO = 50;
	private static final int PUNTOS_OTORGADOS = 30;
	private static final int TIEMPO_ENTRE_DISPAROS = 3000;
	
	private Bot bot;
	
	public TanqueIFV(double x, double y)  {
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
		Canion ca = new Canion(this);
		ca.setTiempoDeCarga(TIEMPO_ENTRE_DISPAROS);
		agregarArma(ca);
		try {
			bot = new BotBordes(this, Escenario.getActual().getBase());
		} catch (NoExisteBaseException e) {
			e.printStackTrace();
		}
	}
	public TanqueIFV(Element element) throws NoPudoLeerXMLExeption{
		super((Element)element.getElementsByTagName(TanqueEnemigo.TAG).item(0));
	}

	@Override
	public void calcularSiguienteMovimiento() {
		bot.actuar();
	}
	@Override
	protected void tirarArma() {
		try {
			FabricaElementos.crearArmaTiradaCanion(getX(), getY());
		} catch (NoSePudoPosicionarException e) {
			e.printStackTrace();
		}
	}

}
