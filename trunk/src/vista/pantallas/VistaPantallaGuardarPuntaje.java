package vista.pantallas;

import java.awt.Color;
import java.awt.Font;

import misc.Observador;
import modelo.ElementoRectangular;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import pantallas.PantallaGuardarPuntaje;

import titiritero.Dibujable;
import titiritero.Posicionable;
import titiritero.SuperficieDeDibujo;
import titiritero.vista.Imagen;
import titiritero.vista.ObjetoDeTexto;
import titiritero.vista.TextoDinamico;
import titiritero.vista.TextoEstatico;
import vista.Vista;
import vista.VistaTanque.TextoResistencia;

public class VistaPantallaGuardarPuntaje extends Vista implements Observador{
	public class TextoNombre implements ObjetoDeTexto {

		@Override
		public String getTexto() {
			return pantalla.getNombre();
		}

	}
	public class TextoPuntaje implements ObjetoDeTexto {

		@Override
		public String getTexto() {
			return new String(TEXTO_DINAMICO_PUNTAJE).concat(Integer.toString(pantalla.getPuntaje()));
		}

	}
	private static final int TEXTO_ESTATICO_X = 230;
	private static final int TEXTO_ESTATICO_Y = 450;
	private static final String TEXTO_ESTATICO = "INGRESE SU NOMBRE Y PRESIONE ENTER";
	private static final int TEXTO_DINAMICO_PUNTAJE_X = 400;
	private static final int TEXTO_DINAMICO_PUNTAJE_Y = 300;
	private static final String TEXTO_DINAMICO_PUNTAJE = "PUNTAJE FINAL: ";
	private static final int TEXTO_DINAMICO_X = 250;
	private static final int TEXTO_DINAMICO_Y = 510;

	private static VistaPantallaGuardarPuntaje instancia=null;
	private PantallaGuardarPuntaje pantalla;
	private Imagen fondo;
	private TextoEstatico textoEstatico;
	private TextoDinamico textoNombre;
	private TextoDinamico textoPuntaje;
	private static String RUTA_FONDO="/sprites/FondoGuardarPuntaje.png";
	
	
	private VistaPantallaGuardarPuntaje(){
		pantalla=PantallaGuardarPuntaje.getInstancia();
		pantalla.adscribir(this);
	
		fondo = new Imagen(RUTA_FONDO, new ElementoRectangular(0,0));
		
		ElementoRectangular cuadradoTexto=new ElementoRectangular(TEXTO_ESTATICO_X,TEXTO_ESTATICO_Y);
		textoEstatico= new TextoEstatico(TEXTO_ESTATICO);
		textoEstatico.setFuente("Arial", 28);
		textoEstatico.setColor(Color.WHITE);
		textoEstatico.setPosicionable(cuadradoTexto);
		
		textoNombre = new TextoDinamico(new TextoNombre(), Color.WHITE,
				new Font("Arial", Font.BOLD, 24));
		textoNombre.setPosicionable(new ElementoRectangular(TEXTO_DINAMICO_X,TEXTO_DINAMICO_Y));
		
		textoPuntaje = new TextoDinamico(new TextoPuntaje(), Color.WHITE,
				new Font("Arial", Font.BOLD, 28));
		textoPuntaje.setPosicionable(new ElementoRectangular(TEXTO_DINAMICO_PUNTAJE_X,TEXTO_DINAMICO_PUNTAJE_Y));
		 
		actualizar();
		
	}
	@Override
	public void dibujar(SuperficieDeDibujo superficie) {
		fondo.dibujar(superficie);
		textoEstatico.dibujar(superficie);
		textoNombre.dibujar(superficie);
		textoPuntaje.dibujar(superficie);
	}

	@Override
	public Posicionable getPosicionable() {
		return pantalla;
	}

	@Override
	public void setPosicionable(Posicionable posicionable) {
		//solo hay una pantallaGuardarPuntaje
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

	public static VistaPantallaGuardarPuntaje getInstancia() {
		if(instancia==null)
			instancia=new VistaPantallaGuardarPuntaje();
		return instancia;
	}
	@Override
	public void actualizar() {
		// TODO Auto-generated method stub
		
	}

}
