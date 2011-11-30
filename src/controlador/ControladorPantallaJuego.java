package controlador;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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
import org.xml.sax.SAXException;

import pantallas.MenuPrincipal;
import pantallas.PantallaActual;
import pantallas.PantallaJuego;

import misc.Observador;
import modelo.Escenario;
import modelo.TanqueHeroe;

import titiritero.KeyPressedObservador;
import vista.VistaEscenario;

public class ControladorPantallaJuego implements KeyPressedObservador {
	private String archivo = "partida_guardada.xml";
	private ControladorTanqueHeroe controlTanque;
	private static ControladorPantallaJuego instancia = null;
	boolean pausado = false;

	public static ControladorPantallaJuego getInstancia() {
		if (instancia == null)
			instancia = new ControladorPantallaJuego();
		return instancia;
	}

	private ControladorPantallaJuego() {
		controlTanque = new ControladorTanqueHeroe(TanqueHeroe.getInstancia());
	}

	private void persistir() {
		try {

			Document doc = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder().newDocument();

			Element element = PantallaJuego.getInstancia().getElementoXML(doc);
			doc.appendChild(element);

			Transformer transformer = TransformerFactory.newInstance()
					.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.transform(new DOMSource(doc), new StreamResult(
					new PrintStream(archivo)));

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

	@Override
	public void keyPressed(KeyEvent event) {
		System.out.println("pressed");
		controlTanque.keyPressed(event);
		int key = event.getKeyCode();
		if (key == KeyEvent.VK_ENTER) {
			if (!pausado) {
				pausado = true;
				PantallaJuego.getInstancia().pausar();
			} else {
				pausado = false;
				PantallaJuego.getInstancia().reanudar();
			}

		}
		if (key == KeyEvent.VK_S)
			persistir();
		if (key == KeyEvent.VK_L)
			cargar();
	}

	private void cargar() {
		try {

			Document doc = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder().parse(new File(archivo));
			Element element = doc.getDocumentElement();
			PantallaActual.getInstancia().cambiarA(new MenuPrincipal());
			Escenario.nuevaInstancia();
			VistaEscenario.nuevaInstancia();

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

	@Override
	public void keyReleased(KeyEvent event) {
		controlTanque.keyReleased(event);

	}

	public void actualizar() {
		controlTanque = new ControladorTanqueHeroe(TanqueHeroe.getInstancia());
	}

}
