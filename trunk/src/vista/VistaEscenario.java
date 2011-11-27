package vista;

import java.util.Iterator;
import java.util.PriorityQueue;

import modelo.Escenario;
import titiritero.Posicionable;
import titiritero.SuperficieDeDibujo;
import titiritero.vista.Imagen;

public class VistaEscenario extends Vista{
	
	private Escenario escenario;
	private PriorityQueue<Vista> vistas;
	private static VistaEscenario instancia;
	private static final String RUTA_SUELO="/sprites/suelo.png";
	private Imagen suelo;
	public static VistaEscenario getInstancia(){
		if(instancia==null)
			instancia=new VistaEscenario();
		return instancia;
	}
	
	public VistaEscenario(){
		vistas=new PriorityQueue<Vista>();
		suelo = new Imagen(RUTA_SUELO, Escenario.getActual());
	}

	@Override
	public void dibujar(SuperficieDeDibujo superfice) {
		suelo.dibujar(superfice);
		PriorityQueue<Vista> clon=new PriorityQueue<Vista>(vistas);
		while(clon.peek()!=null){
			clon.poll().dibujar(superfice);
		}
	}

	@Override
	public Posicionable getPosicionable() {
		return escenario;
	}

	@Override
	public void setPosicionable(Posicionable posicionable) {
		escenario=(Escenario)posicionable;
		
	}
	public void agregarVista(Vista vista){
		vistas.add(vista);
	}
	public void borrarVista(Vista vista){
		vistas.remove(vista);
	}
	

}