package vista.pantallas;

import java.util.ArrayList;
import java.util.Iterator;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import misc.ContadorDeInstancias;
import misc.DiccionarioDeSerializables;
import misc.Observador;
import misc.SerializableXML;
import modelo.ElementoRectangular;


import pantallas.PantallaJuego;
import titiritero.Dibujable;
import titiritero.Posicionable;
import titiritero.SuperficieDeDibujo;
import titiritero.vista.ObjetoDeTexto;
import titiritero.vista.TextoDinamico;
import titiritero.vista.TextoEstatico;

import vista.Vista;
import vista.VistaEscenario;

public class VistaPantallaJuego extends Vista implements Dibujable,SerializableXML,Observador {
	private long id=ContadorDeInstancias.getId();
	
	class TextoPuntos implements ObjetoDeTexto{
		@Override
		public String getTexto() {
			return Integer.toString(pantalla.getPuntos());
		}
	}
	
	private static VistaPantallaJuego instancia=null;
	private PantallaJuego pantalla;
	private VistaEscenario vistaEscenario;
	private ArrayList<VistaVida> vistaVidas;
	private static final String TEXTO_PUNTOS="PUNTOS";
	private static final int TEXTO_PUNTOS_X= 963;
	private static final int TEXTO_PUNTOS_Y= 300;
	private static final int PUNTOS_X= 965;
	private static final int PUNTOS_Y= 330;
	public static final String TAG = "objeto-vista-pantalla-juego";
	private static final String TAG_VISTA_ESCENARIO = "vista-escenario";
	private static final String TAG_VISTA_VIDA = "vista-vida";
	private TextoEstatico textoPuntos;
	private TextoDinamico puntos;
	
	public VistaPantallaJuego(PantallaJuego pantallaJuego) {
		pantalla = pantallaJuego;
		pantalla.adscribir(this);
		vistaEscenario=VistaEscenario.nuevaInstancia();
		vistaVidas=new ArrayList<VistaVida>();
		ElementoRectangular cuadradoTexto = new ElementoRectangular(TEXTO_PUNTOS_X, TEXTO_PUNTOS_Y);
		textoPuntos= new TextoEstatico(TEXTO_PUNTOS);
		textoPuntos.setFuente("Arial", 11);
		textoPuntos.setPosicionable(cuadradoTexto);
		TextoPuntos objetoTexto=new TextoPuntos();
		puntos=new TextoDinamico(objetoTexto);
		puntos.setPosicionable(new ElementoRectangular(PUNTOS_X, PUNTOS_Y));
		instancia=this;
		
	}
	public void reiniciarEscenario(){
		vistaEscenario=VistaEscenario.nuevaInstancia();
	}
	public VistaPantallaJuego() {
		pantalla = PantallaJuego.getInstancia();
		vistaEscenario=VistaEscenario.getInstancia();
		vistaVidas=new ArrayList<VistaVida>();
		ElementoRectangular cuadradoTexto = new ElementoRectangular(TEXTO_PUNTOS_X, TEXTO_PUNTOS_Y);
		textoPuntos= new TextoEstatico(TEXTO_PUNTOS);
		textoPuntos.setFuente("Arial", 11);
		textoPuntos.setPosicionable(cuadradoTexto);
		TextoPuntos objetoTexto=new TextoPuntos();
		puntos=new TextoDinamico(objetoTexto);
		puntos.setPosicionable(new ElementoRectangular(PUNTOS_X, PUNTOS_Y));
		instancia=this;
		
	}
	public VistaPantallaJuego(Element element) {
		
	}
	public void agregarVistaVida(VistaVida vista){
		vistaVidas.add(vista);
	}
	public void borrarVistaVida(VistaVida vista){
		vistaVidas.remove(vista);
	}
	public void dibujar(SuperficieDeDibujo sup) {
		VistaEscenario.getInstancia().dibujar(sup);
		dibujarVidas(sup);
		dibujarPuntos(sup);
	}

	private void dibujarPuntos(SuperficieDeDibujo sup) {
		textoPuntos.dibujar(sup);
		puntos.dibujar(sup);
		
	}

	private void dibujarVidas(SuperficieDeDibujo sup) {
		ArrayList<VistaVida> clon= new ArrayList<VistaVida> (vistaVidas);
		Iterator<VistaVida> it= clon.iterator();
		while(it.hasNext()){
			it.next().dibujar(sup);
		}
	}

	public Posicionable getPosicionable() {
		return pantalla;
	}

	public void setPosicionable(Posicionable pantalla) {
		this.pantalla = (PantallaJuego) pantalla;
	}


	public static VistaPantallaJuego getInstancia() {
		if(instancia==null)
			instancia=new VistaPantallaJuego();
		return instancia;
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
		elem = doc.createElement(TAG_VISTA_ESCENARIO);
		element.appendChild(elem);
		elem.appendChild(vistaEscenario.getElementoXML(doc));
		
		Iterator<VistaVida> it = vistaVidas.iterator();
		while (it.hasNext()) {
		elem = doc.createElement(TAG_VISTA_VIDA);
		element.appendChild(elem);
		elem.appendChild(it.next().getElementoXML(doc));
		}
		
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
				if (elem.getTagName().equals(TAG_VISTA_ESCENARIO)){
					NodeList nodes=elem.getChildNodes();
					int j;
					for(j=0;j<nodes.getLength();j++)
						if (nodes.item(j).getNodeType() == Node.ELEMENT_NODE)
							break;
					vistaEscenario=(VistaEscenario) DiccionarioDeSerializables.getInstancia((Element)nodes.item(j));
				}
				else if (elem.getTagName().equals(TAG_VISTA_VIDA)){
					NodeList nodes=elem.getChildNodes();
					int j;
					for(j=0;j<nodes.getLength();j++)
						if (nodes.item(j).getNodeType() == Node.ELEMENT_NODE)
							break;
					vistaVidas.add((VistaVida) DiccionarioDeSerializables.getInstancia((Element)nodes.item(j)));
				}
					
			}
		}
		
	}
	@Override
	public void actualizar() {
		// TODO Auto-generated method stub
		
	}
}
