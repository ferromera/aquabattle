package pantallas;

import java.io.File;
import java.util.ArrayList;

import misc.FabricaElementos;
import titiritero.ControladorJuego;
import vista.pantallas.VistaBoton;
import vista.pantallas.VistaMenuPrincipal;
import vista.pantallas.VistaPantallaPausa;
import controlador.ControladorMenuPrincipal;
import controlador.ControladorPantallaPausa;

public class PantallaPausa extends Pantalla {

	static PantallaPausa instancia;

	private static final int CANT_BOTONES = 4;
	private static final int X_BOTON_REANUDAR = -5;
	private static final int Y_BOTON_REANUDAR = 494;
	private static final String TEXTO_REANUDAR = "REANUDAR PARTIDA";

	private static final int X_BOTON_GUARDAR = -5;
	private static final int Y_BOTON_GUARDAR = 545;
	private static final String TEXTO_GUARDAR = "GUARDAR";

	private static final int X_BOTON_CARGAR = -5;
	private static final int Y_BOTON_CARGAR = 596;
	private static final String TEXTO_CARGAR = "CARGAR";

	private static final int X_BOTON_SALIR = -5;
	private static final int Y_BOTON_SALIR = 647;
	private static final String TEXTO_SALIR = "SALIR";

	private ArrayList<Boton> botones;
	private int indiceBotonSeleccionado;
	private Boton botonSeleccionado;
	private int cantBotones;

	public PantallaPausa() {
		new VistaPantallaPausa(this);
		botones = new ArrayList<Boton>();
		Boton boton;

		boton = new Boton(X_BOTON_REANUDAR, Y_BOTON_REANUDAR, TEXTO_REANUDAR);
		VistaPantallaPausa.getInstancia().agregarVistaBoton(
				new VistaBoton(boton));
		boton.setAccion(AccionReanudar.getInstancia());
		botones.add(boton);

		boton = new Boton(X_BOTON_GUARDAR, Y_BOTON_GUARDAR, TEXTO_GUARDAR);
		VistaPantallaPausa.getInstancia().agregarVistaBoton(
				new VistaBoton(boton));
		boton.setAccion(AccionGuardar.getInstancia());
		botones.add(boton);

		boton = new Boton(X_BOTON_CARGAR, Y_BOTON_CARGAR, TEXTO_CARGAR);
		VistaPantallaPausa.getInstancia().agregarVistaBoton(
				new VistaBoton(boton));
		boton.setAccion(AccionCargar.getInstancia());
		botones.add(boton);

		boton = new Boton(X_BOTON_SALIR, Y_BOTON_SALIR, TEXTO_SALIR);
		VistaPantallaPausa.getInstancia().agregarVistaBoton(
				new VistaBoton(boton));
		boton.setAccion(AccionSalirPausa.getInstancia());
		botones.add(boton);

		botonSeleccionado = botones.get(0);
		botonSeleccionado.seleccionar();
		indiceBotonSeleccionado = 0;

	}

	public static PantallaPausa getInstancia() {
		if (instancia == null) {
			instancia = new PantallaPausa();
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
		ControladorJuego.getInstancia().agregarDibujable(
				VistaPantallaPausa.getInstancia());
		ControladorJuego.getInstancia().agregarObjetoVivo(this);
		ControladorJuego.getInstancia().agregarKeyPressObservador(
				ControladorPantallaPausa.getInstancia());
		botonSeleccionado.deseleccionar();
		botonSeleccionado = botones.get(0);
		botonSeleccionado.seleccionar();
		indiceBotonSeleccionado = 0;
	}

	@Override
	public void dejarDeSerActual() {
		pausar();
		ControladorJuego.getInstancia().removerDibujable(
				VistaPantallaPausa.getInstancia());
		ControladorJuego.getInstancia().removerObjetoVivo(this);
		ControladorJuego.getInstancia().removerKeyPressObservador(
				ControladorPantallaPausa.getInstancia());

	}

	@Override
	public void reanudar() {

	}
	public void seleccionarSiguienteBoton() {
		if (indiceBotonSeleccionado < CANT_BOTONES - 1) {
			botonSeleccionado.deseleccionar();
			indiceBotonSeleccionado++;
			botonSeleccionado = botones.get(indiceBotonSeleccionado);
			botonSeleccionado.seleccionar();
		}
	}

	public void seleccionarBotonAnterior() {
		if (indiceBotonSeleccionado > 0) {
			botonSeleccionado.deseleccionar();
			indiceBotonSeleccionado--;
			botonSeleccionado = botones.get(indiceBotonSeleccionado);
			botonSeleccionado.seleccionar();
		}

	}

	public void presionarBoton() {
		botonSeleccionado.actuar();

	}

}
