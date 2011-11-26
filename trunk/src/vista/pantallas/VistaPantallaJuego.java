package vista.pantallas;

import java.util.ArrayList;
import java.util.Iterator;

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

public class VistaPantallaJuego extends Vista implements Dibujable {
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
	private TextoEstatico textoPuntos;
	private TextoDinamico puntos;
	
	public VistaPantallaJuego(PantallaJuego pantallaJuego) {
		pantalla = pantallaJuego;
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
	public VistaPantallaJuego() {
		pantalla = PantallaJuego.getInstancia();
		vistaEscenario=VistaEscenario.getInstancia();
		vistaVidas=new ArrayList<VistaVida>();
		
	}
	public void agregarVistaVida(VistaVida vista){
		vistaVidas.add(vista);
	}
	public void borrarVistaVida(VistaVida vista){
		vistaVidas.remove(vista);
	}
	public void dibujar(SuperficieDeDibujo sup) {
		vistaEscenario.dibujar(sup);
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
}
