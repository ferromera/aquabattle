package misc;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import excepciones.ProbabilidadInvalidaException;

public class SorteadorBernoulli extends SorteadorBinario implements SerializableXML{
	public static final String TAG = "objeto-sorteador-bernoulli";

	private static final String TAG_PROBABILIDAD_EXITO = "probabilidad-exito";

	private long id=ContadorDeInstancias.getId();
	
	private double probabilidadExito;

	public double getProbabilidadExito() {
		return probabilidadExito;
	}

	public void setProbabilidadExito(double probabilidadExito) throws ProbabilidadInvalidaException {
		if(probabilidadExito > 1.0)
			throw new ProbabilidadInvalidaException();
		this.probabilidadExito = probabilidadExito;
	}
	public SorteadorBernoulli(){
		probabilidadExito=0.5;
	}
	public SorteadorBernoulli(double probabilidadExito) throws ProbabilidadInvalidaException{
		setProbabilidadExito(probabilidadExito);
	}
	public SorteadorBernoulli(Element element) {
		NodeList hijos;
		Element elem;
		hijos = element.getChildNodes();
		if(hijos!=null && hijos.getLength()>0){
			for(int i=0;i<hijos.getLength();i++){
				if(hijos.item(i).getNodeType()!=Node.ELEMENT_NODE)continue;elem = (Element) hijos.item(i);
				if(elem.getTagName().equals(TAG_PROBABILIDAD_EXITO))
					probabilidadExito=Double.parseDouble(elem.getTextContent());
			}
		}
	}

	public boolean sortear(){
		return (Math.random() < probabilidadExito);
	}
	@Override
	public Element getElementoXML(Document doc) {
		Element element = doc.createElement(TAG);
		Element elem= doc.createElement(ContadorDeInstancias.TAG_ID);
		element.appendChild(elem);
		elem.setTextContent(Long.toString(id));
		if(DiccionarioDeSerializables.fueSerializado(id))
			return element;
		DiccionarioDeSerializables.marcarSerializado(id);
		elem=doc.createElement(TAG_PROBABILIDAD_EXITO);
		element.appendChild(elem);
		elem.setTextContent(Double.toString(probabilidadExito));
		
		return element;
	}

	@Override
	public void fromElementoXML(Element element) {
		NodeList hijos;
		Element elem;
		hijos = element.getChildNodes();
		if(hijos!=null && hijos.getLength()>0){
			for(int i=0;i<hijos.getLength();i++){
				if(hijos.item(i).getNodeType()!=Node.ELEMENT_NODE)continue;elem = (Element) hijos.item(i);
				if(elem.getTagName().equals(TAG_PROBABILIDAD_EXITO))
					probabilidadExito=Double.parseDouble(elem.getTextContent());
			}
		}
		
	}
}
