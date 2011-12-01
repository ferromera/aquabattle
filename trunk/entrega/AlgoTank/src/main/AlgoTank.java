package main;

import pantallas.PantallaActual;
import titiritero.ControladorJuego;
import titiritero.vista.KeyPressedController;
import titiritero.vista.Panel;
import titiritero.vista.Ventana;

public class AlgoTank {

	public static void main(String args[]) {
		ControladorJuego controladorJuego = ControladorJuego.getInstancia();
		KeyPressedController keyController = new KeyPressedController(
				controladorJuego);
		Ventana ventana = new Ventana(1024, 768, controladorJuego);

		ventana.addKeyListener(keyController);

		Panel panel = new Panel(1024, 768, ventana);

		controladorJuego.setSuperficieDeDibujo(panel);
		controladorJuego.setIntervaloSimulacion(1000 / 50);

		PantallaActual.getInstancia();

		controladorJuego.comenzarJuego();
	}
}