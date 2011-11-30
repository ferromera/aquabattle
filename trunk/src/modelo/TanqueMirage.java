package modelo;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import titiritero.ObjetoVivo;

import excepciones.NoExisteBaseException;
import excepciones.NoPudoLeerXMLExeption;
import excepciones.NoSePudoPosicionarException;
import misc.ContadorDeInstancias;
import misc.DiccionarioDeSerializables;
import misc.FabricaElementos;
import modelo.ai.Bot;
import modelo.ai.BotCentro;
import modelo.armamento.Ametralladora;
import modelo.armamento.Canion;
import modelo.armamento.LanzaCohetes;

public class TanqueMirage extends TanqueEnemigo {
	private long id = ContadorDeInstancias.getId();

	public static final String TAG = "objeto-tanque-mirage";
	private static final int PUNTOS_OTORGADOS = 50;
	private static final int TIEMPO_ENTRE_DISPAROS = 2000;

	private static final String TAG_BOT = "bot";
	private final int RESISTENCIA = 100;
	private final double VELOCIDAD = 50.0;
	private final double ANCHO = 50.0;
	private final double ALTO = 50;

	private Bot bot;

	public TanqueMirage() {

	}

	public TanqueMirage(double x, double y) {
		destruido = false;
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
		bot = null;
		try {
			bot = new BotCentro(this, Escenario.getActual().getBase());
		} catch (NoExisteBaseException e) {
			e.printStackTrace();
		}
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

	@Override
	public Element getElementoXML(Document doc) {
		Element element = doc.createElement(TAG);
		Element elem = doc.createElement(ContadorDeInstancias.TAG_ID);
		element.appendChild(elem);
		elem.setTextContent(Long.toString(id));
		if (DiccionarioDeSerializables.fueSerializado(id))
			return element;
		DiccionarioDeSerializables.marcarSerializado(id);
		element.appendChild(super.getElementoXML(doc));

		elem = doc.createElement(TAG_BOT);
		element.appendChild(elem);
		elem.appendChild(bot.getElementoXML(doc));

		return element;
	}

	@Override
	public void fromElementoXML(Element element) {
		super.fromElementoXML((Element) element.getElementsByTagName(
				TanqueEnemigo.TAG).item(0));
		NodeList hijos;
		Element elem;
		hijos = element.getChildNodes();
		if (hijos != null && hijos.getLength() > 0) {
			for (int i = 0; i < hijos.getLength(); i++) {
				if (hijos.item(i).getNodeType() != Node.ELEMENT_NODE)
					continue;
				elem = (Element) hijos.item(i);
				if (elem.getTagName().equals(TAG_BOT)){
					NodeList nodes=elem.getChildNodes();
					int j;
					for(j=0;j<nodes.getLength();j++)
						if (nodes.item(j).getNodeType() == Node.ELEMENT_NODE)
							break;
					bot=(Bot) DiccionarioDeSerializables.getInstancia((Element)nodes.item(j));
				}
					
			}
		}

	}

}
