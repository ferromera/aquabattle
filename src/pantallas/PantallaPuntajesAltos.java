package pantallas;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

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
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import controlador.ControladorPantallaPuntajesAltos;

import titiritero.ControladorJuego;
import vista.pantallas.VistaPantallaPuntajesAltos;

public class PantallaPuntajesAltos extends Pantalla {

	private static PantallaPuntajesAltos instancia = null;
	private static final int CANTIDAD_DE_PUNTAJES = 5;
	private static final String RUTA_PUNTAJE = "puntajes.xml";
	private static final String TAG_NOMBRE = "nombre";
	private static final String TAG_PUNTOS = "puntos";
	private static final String TAG_PUNTAJES = "archivo-de-puntajes";
	private static final String TAG_PUNTAJE = "puntaje";
	private static final int PUNTUACION_X = 200;
	private static final int PUNTUACION_Y = 250;
	private static final int SEPARACION_Y = 90;
	
	private ArrayList<Puntuacion> puntajes;

	public PantallaPuntajesAltos() {
		cargar();
	}

	private void cargar() {
		puntajes=new ArrayList<Puntuacion>();
		try {
			Document doc;
			doc = DocumentBuilderFactory.newInstance().newDocumentBuilder()
					.parse(new File(RUTA_PUNTAJE));

			Element element = doc.getDocumentElement();
			NodeList nodes = element.getChildNodes();
			for (int i = 0; i < nodes.getLength(); i++) {
				if (nodes.item(i).getNodeType() != Node.ELEMENT_NODE)
					continue;
				String nombre="";
				int puntos=0;
				NodeList hijos = nodes.item(i).getChildNodes();
				for (int j = 0; j < hijos.getLength(); j++) {
					if (hijos.item(j).getNodeType() != Node.ELEMENT_NODE)
						continue;
					Element elem = (Element) hijos.item(j);
					if (elem.getTagName().equals(TAG_NOMBRE))
						nombre = elem.getTextContent();
					else if (elem.getTagName().equals(TAG_PUNTOS))
						puntos = Integer.parseInt(elem.getTextContent());
				}
				Puntuacion p=new Puntuacion(nombre, puntos);
				puntajes.add(p);
			}
			Collections.sort(puntajes);
			notificar();

		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		}
		for(int i=0;i<CANTIDAD_DE_PUNTAJES;i++){
			puntajes.get(i).setPosicion(PUNTUACION_X, PUNTUACION_Y+(SEPARACION_Y*i));
		}

	}

	public static PantallaPuntajesAltos getInstancia() {
		if (instancia == null)
			instancia = new PantallaPuntajesAltos();
		return instancia;
	}

	@Override
	public void vivir() {

	}

	@Override
	public void pausar() {
	}

	@Override
	public void reanudar() {
		
	}

	@Override
	public void convertirEnActual() {
		ControladorJuego.getInstancia().agregarDibujable(
				VistaPantallaPuntajesAltos.getInstancia());
		ControladorJuego.getInstancia().agregarObjetoVivo(this);
		ControladorJuego.getInstancia().agregarKeyPressObservador(ControladorPantallaPuntajesAltos.getInstancia());
	}

	@Override
	public void dejarDeSerActual() {
		ControladorJuego.getInstancia().removerDibujable(
				VistaPantallaPuntajesAltos.getInstancia());
		ControladorJuego.getInstancia().removerObjetoVivo(this);
		ControladorJuego.getInstancia().removerKeyPressObservador(ControladorPantallaPuntajesAltos.getInstancia());

	}

	public void insertar(Puntuacion puntuacion) {
		puntajes.add(puntuacion);
		Collections.sort(puntajes);
		puntajes.remove(CANTIDAD_DE_PUNTAJES);
		for(int i=0;i<CANTIDAD_DE_PUNTAJES;i++){
			puntajes.get(i).setPosicion(PUNTUACION_X, PUNTUACION_Y+(SEPARACION_Y*i));
		}
		notificar();
		guardar();
	}

	private void guardar() {
		try {

			Document doc = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder().newDocument();

			Element element = doc.createElement(TAG_PUNTAJES);
			doc.appendChild(element);
			for(int i=0;i<CANTIDAD_DE_PUNTAJES;i++){
				Element elem=doc.createElement(TAG_PUNTAJE);
				element.appendChild(elem);
				Element eNombre=doc.createElement(TAG_NOMBRE);
				elem.appendChild(eNombre);
				eNombre.setTextContent(puntajes.get(i).getNombre());
				Element ePuntos=doc.createElement(TAG_PUNTOS);
				elem.appendChild(ePuntos);
				ePuntos.setTextContent(Integer.toString(puntajes.get(i).getPuntos()));
			}

			Transformer transformer = TransformerFactory.newInstance()
					.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.transform(new DOMSource(doc), new StreamResult(
					new PrintStream(RUTA_PUNTAJE)));

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

	public Iterator<Puntuacion> getIteradorPuntuacion() {
		return puntajes.iterator();
	}

	public int getMenorPuntaje() {
		return puntajes.get(CANTIDAD_DE_PUNTAJES-1).getPuntos();
	}

}
