package misc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public abstract class FabricaBonus implements ActionListener, SerializableXML {
	public static final String TAG = "objeto-fabrica-bonus";
	private static final String TAG_SORTEADOR = "sorteador";
	private static final String TAG_INTERVALO_SORTEO = "intervalo-sorteo";
	private static final String TAG_PRODUCIENDO = "produciendo";
	private boolean produciendo;
	private SorteadorBinario sorteadorBinario;
	private int intervaloSorteo; // en mseg
	private Timer timer;

	public FabricaBonus(SorteadorBinario sorteador) {
		produciendo = true;
		intervaloSorteo = 1000;
		sorteadorBinario = sorteador;
		timer = new Timer(intervaloSorteo, this);
		timer.start();
	}

	public FabricaBonus() {

	}

	public void comenzarProduccion() {
		if (produciendo)
			return;
		produciendo = true;
	}

	public void detenerProduccion() {
		produciendo = false;
	}

	public abstract void crearBonus();

	public void actionPerformed(ActionEvent e) {
		if (produciendo == false)
			return;
		if (sorteadorBinario.sortear())
			crearBonus();
	}

	public int getIntervaloSorteo() {
		return intervaloSorteo;
	}

	public void setIntervaloSorteo(int intervaloSorteo) {
		this.intervaloSorteo = intervaloSorteo;
	}

	public void setSorteadorBinario(SorteadorBinario sorteadorBinario) {
		this.sorteadorBinario = sorteadorBinario;
	}

	public boolean estaProduciendo() {
		return produciendo;
	}

	@Override
	public Element getElementoXML(Document doc) {
		Element element = doc.createElement(TAG);
		Element elem = doc.createElement(TAG_SORTEADOR);
		element.appendChild(elem);
		elem.appendChild(sorteadorBinario.getElementoXML(doc));

		elem = doc.createElement(TAG_PRODUCIENDO);
		element.appendChild(elem);
		elem.setTextContent(Boolean.toString(produciendo));

		elem = doc.createElement(TAG_INTERVALO_SORTEO);
		element.appendChild(elem);
		elem.setTextContent(Integer.toString(intervaloSorteo));

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
				
				if (elem.getTagName().equals(TAG_SORTEADOR)){
					NodeList nodes=elem.getChildNodes();
					int j;
					for(j=0;j<nodes.getLength();j++)
						if (nodes.item(j).getNodeType() == Node.ELEMENT_NODE)
							break;
					sorteadorBinario = (SorteadorBinario) DiccionarioDeSerializables
							.getInstancia((Element) nodes.item(j));
				}
					
				else if (elem.getTagName().equals(TAG_PRODUCIENDO))
					produciendo = Boolean.parseBoolean(elem.getTextContent());
				else if (elem.getTagName().equals(TAG_INTERVALO_SORTEO))
					intervaloSorteo = Integer.parseInt(elem.getTextContent());
			}
		}
		timer = new Timer(intervaloSorteo, this);
		timer.start();

	}
}
