package pantallas;

import java.util.ArrayList;
import java.util.Iterator;

import controlador.ControladorMenuPrincipal;

import titiritero.ControladorJuego;
import vista.pantallas.VistaMenuPrincipal;

import misc.FabricaElementos;

public class MenuPrincipal extends Pantalla {

	boolean pausado;
	static MenuPrincipal instancia;
	
	private static final int X_BOTON_JUEGO_NUEVO = -5;
	private static final int Y_BOTON_JUEGO_NUEVO = 545;
	private static final int X_BOTON_MEJORES_PUNTAJES = -5;
	private static final int Y_BOTON_MEJORES_PUNTAJES = 596;
	private static final int X_BOTON_SALIR = -5;
	private static final int Y_BOTON_SALIR = 647;
	private static final String TEXTO_JUEGO_NUEVO="JUEGO NUEVO";
	private static final String TEXTO_MEJORES_PUNTAJES="MEJORES PUNTAJES";
	private static final String TEXTO_SALIR="SALIR";

	private ArrayList<Boton> botones;
	private int indiceBotonSeleccionado;
	private Boton botonSeleccionado;
	private int cantBotones;

	public MenuPrincipal(){
		botones = new ArrayList<Boton>();
		new VistaMenuPrincipal(this);
		
		botones.add(FabricaElementos.crearBoton(X_BOTON_JUEGO_NUEVO,Y_BOTON_JUEGO_NUEVO,TEXTO_JUEGO_NUEVO));
		botones.add(FabricaElementos.crearBoton(X_BOTON_MEJORES_PUNTAJES,Y_BOTON_MEJORES_PUNTAJES,TEXTO_MEJORES_PUNTAJES));
		botones.add(FabricaElementos.crearBoton(X_BOTON_SALIR,Y_BOTON_SALIR,TEXTO_SALIR));
		cantBotones=3;
		
		botonSeleccionado=botones.get(0);
		botonSeleccionado.seleccionar();
		indiceBotonSeleccionado=0;
		
		
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
	public void seleccionarSiguienteBoton() {
		if(indiceBotonSeleccionado < cantBotones-1){
			botonSeleccionado.deseleccionar();
			indiceBotonSeleccionado++;
			botonSeleccionado=botones.get(indiceBotonSeleccionado);
			botonSeleccionado.seleccionar();
		}
	}
	public void seleccionarBotonAnterior() {
		if(indiceBotonSeleccionado > 0){
			botonSeleccionado.deseleccionar();
			indiceBotonSeleccionado--;
			botonSeleccionado=botones.get(indiceBotonSeleccionado);
			botonSeleccionado.seleccionar();
		}
		
	}

}
