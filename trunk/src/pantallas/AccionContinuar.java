package pantallas;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import modelo.Escenario;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import vista.VistaEscenario;

public class AccionContinuar implements AccionBoton {
	private static AccionContinuar instancia=null;
	private AccionContinuar(){}
	public static AccionContinuar getInstancia(){
		if(instancia==null)
			instancia=new AccionContinuar();
		return instancia;
	}
	@Override
	public void actuar() {
		try {

			Document doc = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder().parse(new File(PantallaJuego.RUTA_GUARDADO));
			Element element = doc.getDocumentElement();
			PantallaJuego.getInstancia().fromElementoXML(element);
			PantallaActual.getInstancia().cambiarA(PantallaJuego.getInstancia());

		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		
	}
}
