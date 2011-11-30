package vista;

import java.util.PriorityQueue;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


import misc.ContadorDeInstancias;
import misc.DiccionarioDeSerializables;
import misc.SerializableXML;
import modelo.Escenario;
import titiritero.Posicionable;
import titiritero.SuperficieDeDibujo;
import titiritero.vista.Imagen;

public class VistaEscenario extends Vista implements SerializableXML {
	private long id = ContadorDeInstancias.getId();

	private PriorityQueue<Vista> vistas;
	private static VistaEscenario instancia;
	private static final String RUTA_SUELO = "/sprites/suelo.png";

	public static final String TAG = "objeto-vista-escenario";
	private static final String TAG_ESCENARIO = "escenario";
	private static final String TAG_VISTAS = "vistas";

	private Imagen suelo;

	public static VistaEscenario getInstancia() {
		if (instancia == null)
			instancia = new VistaEscenario();
		return instancia;
	}

	public VistaEscenario() {
		vistas = new PriorityQueue<Vista>();
		suelo = new Imagen(RUTA_SUELO, Escenario.getActual());
	}


	@Override
	public void dibujar(SuperficieDeDibujo superfice) {
		suelo.dibujar(superfice);
		PriorityQueue<Vista> clon = new PriorityQueue<Vista>(vistas);
		while (clon.peek() != null) {
			clon.poll().dibujar(superfice);
		}
	}

	@Override
	public Posicionable getPosicionable() {
		return Escenario.getActual();
	}

	@Override
	public void setPosicionable(Posicionable posicionable) {
		// solo hay un escenario

	}

	public void agregarVista(Vista vista) {
		vistas.add(vista);
	}

	public void borrarVista(Vista vista) {
		vistas.remove(vista);
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

		elem = doc.createElement(TAG_ESCENARIO);
		element.appendChild(elem);
		elem.appendChild(Escenario.getActual().getElementoXML(doc));

		PriorityQueue<Vista> clon = new PriorityQueue<Vista>(vistas);
		while (clon.peek() != null) {
			elem = doc.createElement(TAG_VISTAS);
			element.appendChild(elem);
			elem.appendChild(clon.poll().getElementoXML(doc));
		}

		return element;
	}

	@Override
	public void fromElementoXML(Element element) {
		vistas = new PriorityQueue<Vista>();
		NodeList hijos;
		Element elem;
		hijos = element.getChildNodes();
		if (hijos != null && hijos.getLength() > 0) {
			for (int i = 0; i < hijos.getLength(); i++) {
				if (hijos.item(i).getNodeType() != Node.ELEMENT_NODE)
					continue;
				elem = (Element) hijos.item(i);
				if (elem.getTagName().equals(TAG_ESCENARIO)){
					NodeList nodes=elem.getChildNodes();
					int j;
					for(j=0;j<nodes.getLength();j++)
						if (nodes.item(j).getNodeType() == Node.ELEMENT_NODE)
							break;
					suelo=new Imagen(RUTA_SUELO,(Escenario) DiccionarioDeSerializables.getInstancia((Element)nodes.item(j)));
				}
					
				else if (elem.getTagName().equals(TAG_VISTAS)){
					NodeList nodes=elem.getChildNodes();
					int j;
					for(j=0;j<nodes.getLength();j++)
						if (nodes.item(j).getNodeType() == Node.ELEMENT_NODE)
							break;
					vistas.add((Vista) DiccionarioDeSerializables.getInstancia((Element)nodes.item(j)));
				}
			}
		}

	}

	public static VistaEscenario nuevaInstancia() {
		instancia=new VistaEscenario();
		return instancia;
		
	}

}
