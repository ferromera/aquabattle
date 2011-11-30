package modelo;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import titiritero.ObjetoVivo;

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
	private long id = ContadorDeInstancias.getId();

	public static final String TAG = "objeto-tanque-grizzly";
	private static final String TAG_BOT = "bot";

	private static final int RESISTENCIA = 100;
	private static final double VELOCIDAD = 30.0;
	private static final double ANCHO = 50.0;
	private static final double ALTO = 50;
	private static final int TIEMPO_ENTRE_DISPAROS = 4000;
	private static final int PUNTOS_OTORGADOS = 20;

	private Bot bot;

	public TanqueGrizzly() {

	}

	public TanqueGrizzly(double x, double y) {
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
		bot = new BotCentro(this, TanqueHeroe.getInstancia());
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
