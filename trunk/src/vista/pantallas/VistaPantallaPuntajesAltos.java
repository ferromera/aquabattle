package vista.pantallas;

import java.util.ArrayList;
import java.util.Iterator;

import org.w3c.dom.Document;
import org.w3c.dom.Element;


import pantallas.PantallaPuntajesAltos;
import pantallas.Puntuacion;
import misc.Observador;
import titiritero.Posicionable;
import titiritero.SuperficieDeDibujo;
import titiritero.vista.Imagen;
import vista.Vista;

public class VistaPantallaPuntajesAltos extends Vista implements Observador {
	
	private static VistaPantallaPuntajesAltos instancia = null;
	
	private PantallaPuntajesAltos pantalla;
	private Imagen fondo;
	private static String RUTA_FONDO="/sprites/FondoPuntajesAltos.png";
	private ArrayList<VistaPuntuacion> puntajes;
	
	private VistaPantallaPuntajesAltos (){
		pantalla=PantallaPuntajesAltos.getInstancia();
		pantalla.adscribir(this);
		fondo = new Imagen(RUTA_FONDO, pantalla);
		puntajes=new ArrayList<VistaPuntuacion>();
		actualizar();
		
	}
	
	@Override
	public void dibujar(SuperficieDeDibujo superficie) {
		fondo.dibujar(superficie);
		Iterator<VistaPuntuacion> it=puntajes.iterator();
		while(it.hasNext()){
			it.next().dibujar(superficie);
		}
	}

	@Override
	public Posicionable getPosicionable() {
		return pantalla;
	}

	@Override
	public void setPosicionable(Posicionable posicionable) {
	}
	
	public static VistaPantallaPuntajesAltos getInstancia() {
		if(instancia==null)
			instancia=new VistaPantallaPuntajesAltos();
		return instancia;
	}

	@Override
	public Element getElementoXML(Document doc) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void fromElementoXML(Element element) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actualizar() {
		puntajes=new ArrayList<VistaPuntuacion>();
		Iterator<Puntuacion> it=pantalla.getIteradorPuntuacion();
		while(it.hasNext()){
			puntajes.add(new VistaPuntuacion(it.next()));
		}
		
	}

}
