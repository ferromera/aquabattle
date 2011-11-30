package vista.pantallas;

import java.util.ArrayList;
import java.util.Iterator;

import misc.Observador;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import pantallas.PantallaPausa;

import titiritero.Posicionable;
import titiritero.SuperficieDeDibujo;
import titiritero.vista.Imagen;
import vista.Vista;

public class VistaPantallaPausa extends Vista implements Observador{

	private static VistaPantallaPausa instancia=null;

	private PantallaPausa pantalla;
	
	ArrayList<VistaBoton> vistaBotones;

	private Imagen sprite;

	
	private static final String RUTA_SPRITE_FONDO= "/sprites/PantallaJuegoPausado.png";

	public VistaPantallaPausa (PantallaPausa pausa){
		pantalla=pausa;
		sprite = new Imagen(RUTA_SPRITE_FONDO, pantalla);
		vistaBotones = new ArrayList<VistaBoton>();
		instancia=this;
	}
	private VistaPantallaPausa (){
		pantalla=PantallaPausa.getInstancia();
		sprite = new Imagen(RUTA_SPRITE_FONDO, pantalla);
		vistaBotones = new ArrayList<VistaBoton>();
	}
	
	public void agregarVistaBoton(VistaBoton vista){
		vistaBotones.add(vista);
	}
	
	public void borrarVistaBoton(VistaBoton vista){
		vistaBotones.remove(vista);
	}
	
	private void dibujarBotones(SuperficieDeDibujo sup) {

		Iterator<VistaBoton> it= vistaBotones.iterator();
		while(it.hasNext()){
			it.next().dibujar(sup);
		}
	}
	
	public static VistaPantallaPausa getInstancia() {
		if(instancia == null)
			instancia = new VistaPantallaPausa();
		return instancia;
	}
	
	
	public void dibujar(SuperficieDeDibujo sup) {
		sprite.dibujar(sup);
		dibujarBotones(sup);
		
	}
	
	public Posicionable getPosicionable() {
		return pantalla;
	}

	public void setPosicionable(Posicionable pantalla) {
		this.pantalla = (PantallaPausa) pantalla;
	}
	
	@Override
	public void actualizar() {
		ArrayList<VistaBoton> clon= new ArrayList<VistaBoton> (vistaBotones);
		Iterator<VistaBoton> it= clon.iterator();
		while(it.hasNext()){
			it.next().actualizar();
		}
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

}
