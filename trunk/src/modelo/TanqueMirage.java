package modelo;

import org.w3c.dom.Element;

import excepciones.NoExisteBaseException;
import excepciones.NoPudoLeerXMLExeption;
import excepciones.NoSePudoPosicionarException;
import misc.FabricaElementos;
import modelo.ai.Bot;
import modelo.ai.BotCentro;
import modelo.armamento.Ametralladora;
import modelo.armamento.Canion;
import modelo.armamento.LanzaCohetes;

public class TanqueMirage extends TanqueEnemigo {
	public static final String TAG="tanque-mirage";
	private static final int PUNTOS_OTORGADOS = 50;
	private static final int TIEMPO_ENTRE_DISPAROS = 2000;
	private final int RESISTENCIA = 100;
	private final double VELOCIDAD = 50.0;
	private final double ANCHO = 50.0;
	private final double ALTO = 50;
	
	private Bot bot;
	
	public TanqueMirage(double x, double y) {
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
		LanzaCohetes la = new LanzaCohetes(this);
		la.setTiempoDeCarga(TIEMPO_ENTRE_DISPAROS);
		agregarArma(la);
		bot=null;
		try {
			bot = new BotCentro(this, Escenario.getActual().getBase());
		} catch (NoExisteBaseException e) {
			e.printStackTrace();
		}
	}
	public TanqueMirage(Element element) throws NoPudoLeerXMLExeption{
		super((Element)element.getElementsByTagName(TanqueEnemigo.TAG).item(0));
	}
	@Override
	public void calcularSiguienteMovimiento() {
		bot.actuar();
	}
	@Override
	protected void tirarArma() {
		try {
			FabricaElementos.crearArmaTiradaLanzaCohetes(getX(), getY());
		} catch (NoSePudoPosicionarException e) {
			e.printStackTrace();
		}
	}

}
