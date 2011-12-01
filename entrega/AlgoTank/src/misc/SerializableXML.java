package misc;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public interface SerializableXML {

	public Element getElementoXML(Document doc);
	public void fromElementoXML(Element element);
}
