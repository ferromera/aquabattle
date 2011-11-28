package modelo;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import excepciones.NoPudoLeerXMLExeption;

import misc.ContadorDeInstancias;
import misc.DiccionarioDeSerializables;
import modelo.ai.Bot;
import modelo.ai.BotCentro;
import modelo.armamento.Ametralladora;
import modelo.armamento.Arma;
import modelo.mejoras.MejoraTanque;
import modelo.TanqueHeroe;

public class TanqueGrizzly extends TanqueEnemigo {
	private long id=ContadorDeInstancias.getId();
	
	public static final String TAG="objeto-tanque-grizzly";
	private static final String TAG_BOT = "bot";
	
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
		NodeList hijos;
		Element elem;
		hijos = element.getChildNodes();
		if(hijos!=null && hijos.getLength()>0){
			for(int i=0;i<hijos.getLength();i++){
				elem = (Element) hijos.item(i);
				if(elem.getTagName().equals(TAG_BOT))
					bot=(Bot)DiccionarioDeSerializables.getInstancia((Element)elem.getFirstChild());
			}
		}
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
