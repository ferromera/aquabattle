package pantallas;

import java.io.FileNotFoundException;
import java.io.PrintStream;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class AccionGuardar implements AccionBoton {
	private static AccionGuardar instancia=null;
	private AccionGuardar(){}
	public static AccionGuardar getInstancia(){
		if(instancia==null)
			instancia=new AccionGuardar();
		return instancia;
	}
	@Override
	public void actuar() {
		try {
			Document doc = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder().newDocument();

			Element element = PantallaJuego.getInstancia().getElementoXML(doc);
			doc.appendChild(element);

			Transformer transformer = TransformerFactory.newInstance()
					.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.transform(new DOMSource(doc), new StreamResult(
					new PrintStream(PantallaJuego.RUTA_GUARDADO)));

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}
}
