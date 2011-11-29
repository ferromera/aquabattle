package pantallas;

import java.util.ArrayList;

import controlador.ControladorMenuPrincipal;
import controlador.ControladorPantallaJuego;

import titiritero.ControladorJuego;
import vista.pantallas.VistaMenuPrincipal;
import vista.pantallas.VistaPantallaJuego;

import misc.FabricaElementos;
import misc.Nivel;

public class MenuPrincipal extends Pantalla {

	boolean pausado;
	static MenuPrincipal instancia;
	
	private static final int BOTONES = 4;
	private static final int X_BOTON = -5;
	private static final int Y_BOTON = 545;
	private static final int SEPARACION_BOTONES= 11;
	private ArrayList<Boton> botones;
	
	public MenuPrincipal(){

		botones = new ArrayList<Boton>();
		for(int i=0 ;i<BOTONES;i++)
			botones.add(FabricaElementos.crearBoton(X_BOTON,Y_BOTON+SEPARACION_BOTONES*i));		
		pausado = false;
	}
	
	public static MenuPrincipal getInstance(){
		if(instancia == null){
			instancia = new MenuPrincipal();
		}
		
		return instancia;
	}
	
	@Override
	public void vivir() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cambiarA(Pantalla pantalla) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pausar() {
		pausado=true;
	}

	@Override
	public void convertirEnActual() {
		ControladorJuego.getInstancia().agregarDibujable(VistaMenuPrincipal.getInstancia());
		ControladorJuego.getInstancia().agregarObjetoVivo(this);
		ControladorJuego.getInstancia().agregarKeyPressObservador(ControladorMenuPrincipal.getInstancia());
		reanudar();
		
	}

	@Override
	public void dejarDeSerActual() {
		pausar();
		ControladorJuego.getInstancia().removerDibujable(VistaMenuPrincipal.getInstancia());
		ControladorJuego.getInstancia().removerObjetoVivo(this);
		ControladorJuego.getInstancia().removerKeyPressObservador(ControladorMenuPrincipal.getInstancia());
		
		
	}

	@Override
	public void reanudar() {
		pausado=false;
		
	}

}
