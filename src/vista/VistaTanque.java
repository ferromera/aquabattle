package vista;

import java.awt.Color;
import java.awt.Font;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import misc.DiccionarioDeSerializables;
import misc.SerializableXML;
import modelo.ElementoRectangular;
import modelo.Tanque;
import titiritero.SuperficieDeDibujo;
import titiritero.vista.Animacion;
import titiritero.vista.ObjetoDeTexto;
import titiritero.vista.TextoDinamico;

public abstract class VistaTanque extends Vista implements SerializableXML {
	public class TextoResistencia implements ObjetoDeTexto {

		@Override
		public String getTexto() {
			return Integer.toString(tanque.getResistencia());
		}

	}

	private static final String TAG_TANQUE = "tanque";

	public static final String TAG = "objeto-vista-tanque";

	protected Animacion spriteActual;
	protected Tanque tanque;
	private TextoDinamico resistencia;

	public VistaTanque() {

	}

	public VistaTanque(Tanque tanque) {
		this.tanque = tanque;
		resistencia = new TextoDinamico(new TextoResistencia(), Color.BLACK,
				new Font("Arial", Font.BOLD, 16));
	}

	public void dibujar(SuperficieDeDibujo sup) {
		spriteActual.dibujar(sup);
		resistencia.setPosicionable(new ElementoRectangular(tanque.getX() + 10,
				tanque.getY() + tanque.getAlto() + 15));
		resistencia.dibujar(sup);
	}

	@Override
	public Element getElementoXML(Document doc) {
		Element element = doc.createElement(TAG);

		Element elem = doc.createElement(TAG_TANQUE);
		element.appendChild(elem);
		elem.appendChild(tanque.getElementoXML(doc));

		return element;
	}

	@Override
	public void fromElementoXML(Element element) {
		NodeList hijos;
		Element elem;
		hijos = element.getChildNodes();
		if (hijos != null && hijos.getLength() > 0) {
			for (int i = 0; i < hijos.getLength(); i++) {
				if (hijos.item(i).getNodeType() != Node.ELEMENT_NODE)
					continue;
				elem = (Element) hijos.item(i);
				if (elem.getTagName().equals(TAG_TANQUE)){
					NodeList nodes=elem.getChildNodes();
					int j;
					for(j=0;j<nodes.getLength();j++)
						if (nodes.item(j).getNodeType() == Node.ELEMENT_NODE)
							break;
					tanque=(Tanque) DiccionarioDeSerializables.getInstancia((Element)nodes.item(j));
				}
				
			}
		}

		resistencia = new TextoDinamico(new TextoResistencia(), Color.BLACK,
				new Font("Arial", Font.BOLD, 16));

	}

}
