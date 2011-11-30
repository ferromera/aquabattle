package pantallas;

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

import modelo.Escenario;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import titiritero.ControladorJuego;
import vista.VistaEscenario;
import vista.pantallas.VistaPantallaGuardarPuntaje;
import vista.pantallas.VistaPantallaJuego;
import controlador.ControladorPantallaGuardarPuntaje;
import controlador.ControladorPantallaJuego;

public class PantallaGuardarPuntaje extends Pantalla {

	private static final String RUTA_PUNTAJE = "puntajes.xml";
	public static final String TAG_PUNTAJE = "puntaje";
	public static final String TAG_NOMBRE = "nombre";
	public static final String TAG_PUNTOS = "puntos";
	private static final String TAG_PUNTAJES = "puntajes";
	private int puntaje;
	private String nombre;
	
	private static PantallaGuardarPuntaje instancia=null;
	
	public static PantallaGuardarPuntaje getInstancia(){
		if(instancia==null)
			instancia=new PantallaGuardarPuntaje();
		return instancia;
	}
	
	public PantallaGuardarPuntaje(){
		puntaje=PantallaJuego.getInstancia().getPuntos();
		nombre=new String();
	}
	
	public void agregarLetra(char letra){
		System.out.println("agregando letra "+letra);
		nombre=nombre.concat(new Character(letra).toString());
	}
	public void borrarUltimaLetra(){
		if(!nombre.isEmpty())
		nombre=nombre.substring(0, nombre.length()-1);
	}
	
	@Override
	public void vivir() {
		
		
	}

	@Override
	public void pausar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reanudar() {
		// TODO Auto-generated method stub
		
	}

	

	@Override
	public void convertirEnActual() {
		ControladorJuego.getInstancia().agregarDibujable(
				VistaPantallaGuardarPuntaje.getInstancia());
		ControladorJuego.getInstancia().agregarObjetoVivo(this);
		ControladorJuego.getInstancia().agregarKeyPressObservador(
				ControladorPantallaGuardarPuntaje.getInstancia());
		
		reiniciar();
	}

	@Override
	public void dejarDeSerActual() {
		ControladorJuego.getInstancia().removerDibujable(
				VistaPantallaGuardarPuntaje.getInstancia());
		ControladorJuego.getInstancia().removerObjetoVivo(this);
		ControladorJuego.getInstancia().removerKeyPressObservador(
				ControladorPantallaGuardarPuntaje.getInstancia());
		
		
	}
	private void reiniciar() {
		puntaje=PantallaJuego.getInstancia().getPuntos();
		nombre=new String();
	}

	public void guardar() {
		
		try {
			Document doc;
			if(new File(RUTA_PUNTAJE).exists()){
				doc = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder().parse(new File(RUTA_PUNTAJE));
				
			}else{
				doc = DocumentBuilderFactory.newInstance()
						.newDocumentBuilder().newDocument();
				Element root=doc.createElement(TAG_PUNTAJES);
				doc.appendChild(root);
			}
			
			Element element=doc.getDocumentElement();
			element.appendChild(getElementoPuntaje(doc));
			
			Transformer transformer = TransformerFactory.newInstance()
					.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.transform(new DOMSource(doc), new StreamResult(
					new PrintStream(RUTA_PUNTAJE)));

		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
		
	}

	private Element getElementoPuntaje(Document doc) {
		Element element= doc.createElement(TAG_PUNTAJE);
		Element elem= doc.createElement(TAG_NOMBRE);
		element.appendChild(elem);
		elem.setTextContent(nombre);
		elem=doc.createElement(TAG_PUNTOS);
		element.appendChild(elem);
		elem.setTextContent(Integer.toString(puntaje));
		return element;
	}

	public String getNombre() {
		return nombre;
	}

	public int getPuntaje() {
		return puntaje;
	}

}
