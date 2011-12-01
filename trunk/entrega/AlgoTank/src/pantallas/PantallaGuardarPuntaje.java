package pantallas;

import titiritero.ControladorJuego;
import vista.pantallas.VistaPantallaGuardarPuntaje;
import controlador.ControladorPantallaGuardarPuntaje;


public class PantallaGuardarPuntaje extends Pantalla {
	private int puntaje;
	private String nombre;
	
	private static PantallaGuardarPuntaje instancia=null;
	
	public static PantallaGuardarPuntaje getInstancia(){
		if(instancia==null)
			instancia=new PantallaGuardarPuntaje();
		return instancia;
	}
	
	public PantallaGuardarPuntaje(){
		puntaje=PantallaJuego.getInstancia().getPuntos();
		nombre=new String();
	}
	
	public void agregarLetra(char letra){
		nombre=nombre.concat(new Character(letra).toString());
	}
	public void borrarUltimaLetra(){
		if(!nombre.isEmpty())
		nombre=nombre.substring(0, nombre.length()-1);
	}
	
	@Override
	public void vivir() {	
	}

	@Override
	public void pausar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reanudar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void convertirEnActual() {
		ControladorJuego.getInstancia().agregarDibujable(
				VistaPantallaGuardarPuntaje.getInstancia());
		ControladorJuego.getInstancia().agregarObjetoVivo(this);
		ControladorJuego.getInstancia().agregarKeyPressObservador(
				ControladorPantallaGuardarPuntaje.getInstancia());
		
		reiniciar();
	}

	@Override
	public void dejarDeSerActual() {
		ControladorJuego.getInstancia().removerDibujable(
				VistaPantallaGuardarPuntaje.getInstancia());
		ControladorJuego.getInstancia().removerObjetoVivo(this);
		ControladorJuego.getInstancia().removerKeyPressObservador(
				ControladorPantallaGuardarPuntaje.getInstancia());
		
		
	}
	private void reiniciar() {
		puntaje=PantallaJuego.getInstancia().getPuntos();
		nombre=new String();
	}

	public void guardar() {
		PantallaPuntajesAltos.getInstancia().insertar(new Puntuacion(nombre,puntaje));
		
	}

	public String getNombre() {
		return nombre;
	}

	public int getPuntaje() {
		return puntaje;
	}

}
