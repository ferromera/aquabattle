package pantallas;

import java.io.File;
import java.util.ArrayList;

import controlador.ControladorMenuPrincipal;

import titiritero.ControladorJuego;
import vista.pantallas.VistaMenuPrincipal;

import misc.FabricaElementos;

public class MenuPrincipal extends Pantalla {

	static MenuPrincipal instancia;
	
	private static final int X_BOTON_CONTINUAR = -5;
	private static final int Y_BOTON_CONTINUAR = 494;
	private static final String TEXTO_CONTINUAR = "CONTINUAR";
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
		Boton boton;
		if(new File(PantallaJuego.RUTA_GUARDADO).exists()){
			boton=FabricaElementos.crearBoton(X_BOTON_CONTINUAR,Y_BOTON_CONTINUAR,TEXTO_CONTINUAR);
			boton.setAccion(AccionContinuar.getInstancia());
			botones.add(boton);
			cantBotones=4;
		}
		else{
			cantBotones=3;
		}
		
		boton=FabricaElementos.crearBoton(X_BOTON_JUEGO_NUEVO,Y_BOTON_JUEGO_NUEVO,TEXTO_JUEGO_NUEVO);
		boton.setAccion(AccionJuegoNuevo.getInstancia());
		botones.add(boton);
		boton=FabricaElementos.crearBoton(X_BOTON_MEJORES_PUNTAJES,Y_BOTON_MEJORES_PUNTAJES,TEXTO_MEJORES_PUNTAJES);
		boton.setAccion(AccionPuntajes.getInstancia());
		botones.add(boton);
		boton=FabricaElementos.crearBoton(X_BOTON_SALIR,Y_BOTON_SALIR,TEXTO_SALIR);
		boton.setAccion(AccionSalir.getInstancia());
		botones.add(boton);
		
		botonSeleccionado=botones.get(0);
		botonSeleccionado.seleccionar();
		indiceBotonSeleccionado=0;

		
		
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
	public void pausar() {
	}

	@Override
	public void convertirEnActual() {
		ControladorJuego.getInstancia().agregarDibujable(VistaMenuPrincipal.getInstancia());
		ControladorJuego.getInstancia().agregarObjetoVivo(this);
		ControladorJuego.getInstancia().agregarKeyPressObservador(ControladorMenuPrincipal.getInstancia());
		chequearPartidaGuardada();
		botonSeleccionado.deseleccionar();
		botonSeleccionado=botones.get(0);
		botonSeleccionado.seleccionar();
		indiceBotonSeleccionado=0;
		
		reanudar();
		
	}

	private void chequearPartidaGuardada() {
		if(cantBotones==3 && new File(PantallaJuego.RUTA_GUARDADO).exists()){
			Boton boton=FabricaElementos.crearBoton(X_BOTON_CONTINUAR,Y_BOTON_CONTINUAR,TEXTO_CONTINUAR);
			boton.setAccion(AccionContinuar.getInstancia());
			botones.add(0, boton);
			cantBotones=4;
		}
		
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
	public void presionarBoton() {
		botonSeleccionado.actuar();
		
	}

}
