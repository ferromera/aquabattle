package vista.pantallas;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;

import misc.Observador;

import pantallas.MenuPrincipal;
import titiritero.Dibujable;
import titiritero.Posicionable;
import titiritero.SuperficieDeDibujo;
import titiritero.vista.Imagen;
import vista.Vista;
import vista.VistaEscenario;

public class VistaMenuPrincipal extends Vista implements Dibujable, Observador{

	private static VistaMenuPrincipal instancia=null;

	private MenuPrincipal menu;
	
	ArrayList<VistaBoton> vistaBotones = new ArrayList<VistaBoton>();

	private Imagen sprite;
	private static final String RUTA_SPRITE_FONDO= "/sprites/FondoMenu.png";

	public VistaMenuPrincipal (){
		sprite = new Imagen(RUTA_SPRITE_FONDO, menu);
	}
	
	public VistaMenuPrincipal (MenuPrincipal menu){
		this.menu = menu;
		sprite = new Imagen(RUTA_SPRITE_FONDO, menu);
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
	
	
}
