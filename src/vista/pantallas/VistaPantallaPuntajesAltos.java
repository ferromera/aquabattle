package vista.pantallas;

import java.util.ArrayList;

import pantallas.PantallaGuardarPuntaje;
import pantallas.PantallaPuntajesAltos;
import misc.Observador;
import titiritero.Posicionable;
import titiritero.SuperficieDeDibujo;
import titiritero.vista.Imagen;
import vista.Vista;

public class VistaPantallaPuntajesAltos extends Vista implements Observador{
	

	private PantallaPuntajesAltos pantalla;
	private static VistaPantallaPuntajesAltos instancia = null;
	private Imagen fondo;
	private static String RUTA_FONDO="/sprites/FondoPuntajesAltos.png";
	
	private VistaPantallaPuntajesAltos (){
		fondo = new Imagen(RUTA_FONDO, pantalla);
		
	}
	
	public VistaPantallaPuntajesAltos (PantallaPuntajesAltos pantalla){
		this.pantalla = pantalla;
		fondo = new Imagen(RUTA_FONDO, pantalla);
		instancia = this;
	}
	
	
	@Override
	public void dibujar(SuperficieDeDibujo superficie) {
		fondo.dibujar(superficie);
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
	public void actualizar() {
		// TODO Auto-generated method stub
		
	}

}
