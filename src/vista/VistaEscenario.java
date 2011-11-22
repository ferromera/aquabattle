package vista;

import java.util.Iterator;
import java.util.PriorityQueue;

import modelo.Escenario;
import titiritero.Posicionable;
import titiritero.SuperficieDeDibujo;

public class VistaEscenario extends Vista{
	
	private Escenario escenario;
	private PriorityQueue<Vista> vistas;
	private static VistaEscenario instancia;
	public static VistaEscenario getInstancia(){
		if(instancia==null)
			instancia=new VistaEscenario();
		return instancia;
	}
	
	public VistaEscenario(){
		vistas=new PriorityQueue<Vista>();
	}

	@Override
	public void dibujar(SuperficieDeDibujo superfice) {
		PriorityQueue<Vista> clon=new PriorityQueue<Vista>();
		Iterator<Vista> it= vistas.iterator();
		while(it.hasNext()){
			clon.add(it.next());
		}
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
	

}
