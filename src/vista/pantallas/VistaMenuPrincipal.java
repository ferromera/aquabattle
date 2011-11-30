package vista.pantallas;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import misc.Observador;
import modelo.ElementoRectangular;

import pantallas.MenuPrincipal;
import titiritero.Dibujable;
import titiritero.Posicionable;
import titiritero.SuperficieDeDibujo;
import titiritero.vista.Imagen;
import titiritero.vista.TextoEstatico;
import vista.Vista;
import vista.pantallas.VistaPantallaJuego.TextoPuntos;

public class VistaMenuPrincipal extends Vista implements Dibujable, Observador{

	private static VistaMenuPrincipal instancia=null;

	private MenuPrincipal menu;
	
	ArrayList<VistaBoton> vistaBotones;

	private Imagen sprite;

	
	private static final String RUTA_SPRITE_FONDO= "/sprites/FondoMenu.png";

	/*public void agregarTextos(SuperficieDeDibujo sup){
		String TEXTO;
		int TEXTO1_X= 10;
		int TEXTO1_Y= 575;
		int indice;
		
		for(indice = 0; indice < 4; indice++){
			if(indice==0){
				TEXTO = "CONTINUAR";
			}
			else if(indice==1){
				TEXTO = "NUEVO JUEGO";
			}
			else if(indice==2){
				TEXTO = "PUNTAJES ALTOS";
			}
			else{ //(indice==3){
				TEXTO = "SALIR";
			}

		ElementoRectangular cuadradoTexto = new ElementoRectangular(TEXTO1_X, TEXTO1_Y + (51*indice));
		TextoEstatico textoContinuar = new TextoEstatico(TEXTO);
		textoContinuar.setFuente("Arial", 25);
		Color unColor = new Color(255,255,255);
		textoContinuar.setColor(unColor);
		textoContinuar.setPosicionable(cuadradoTexto);
		textoContinuar.dibujar(sup);
		}
		

		
		
	}*/
	private VistaMenuPrincipal (){
		sprite = new Imagen(RUTA_SPRITE_FONDO, menu);
		vistaBotones = new ArrayList<VistaBoton>();
		
	}
	
	public VistaMenuPrincipal (MenuPrincipal menu){
		this.menu = menu;
		sprite = new Imagen(RUTA_SPRITE_FONDO, menu);
		vistaBotones = new ArrayList<VistaBoton>();
		instancia = this;
	}
	
	public void agregarVistaBoton(VistaBoton vista){
		vistaBotones.add(vista);
	}
	
	public void borrarVistaBoton(VistaBoton vista){
		vistaBotones.remove(vista);
	}
	
	private void dibujarBotones(SuperficieDeDibujo sup) {
		ArrayList<VistaBoton> clon= new ArrayList<VistaBoton> (vistaBotones);
		Iterator<VistaBoton> it= clon.iterator();
		while(it.hasNext()){
			it.next().dibujar(sup);
		}
	}
	
	public static VistaMenuPrincipal getInstancia() {
		if(instancia == null)
			instancia = new VistaMenuPrincipal();
		return instancia;
	}
	
	
	public void dibujar(SuperficieDeDibujo sup) {
		sprite.dibujar(sup);
		dibujarBotones(sup);
		
	}
	
	public Posicionable getPosicionable() {
		return menu;
	}

	public void setPosicionable(Posicionable menu) {
		this.menu = (MenuPrincipal) menu;
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
