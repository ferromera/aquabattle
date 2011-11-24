import java.awt.Color;

import titiritero.vista.KeyPressedController;

import controlador.ControladorTanqueHeroe;
import excepciones.NoExisteArmaSeleccionadaException;

import titiritero.ControladorJuego;



import titiritero.vista.Panel;
import titiritero.vista.Ventana;
import titiritero.vista.Animacion;
import utils.Direccion;
import vista.VistaEscenario;
import vista.VistaTanqueHeroe;

import misc.FabricaElementos;
import modelo.Escenario;
import modelo.TanqueHeroe;
import modelo.armamento.Canion;
import modelo.armamento.LanzaCohetes;


public class Pruebas {

	
	public static void main(String args[]){
		ControladorJuego controladorJuego= ControladorJuego.getInstancia();
		KeyPressedController keyController= new KeyPressedController(controladorJuego);
		Ventana ventana = new Ventana(960,720,controladorJuego);
		ventana.addKeyListener(keyController);
		Panel panel = new Panel(960,720,ventana);
		controladorJuego.setSuperficieDeDibujo(panel);
		controladorJuego.setIntervaloSimulacion(1000/50);
		//Agrego dibujables
		Escenario escenario= Escenario.getActual();
		controladorJuego.agregarObjetoVivo(escenario);
		controladorJuego.agregarDibujable(VistaEscenario.getInstancia());
		//Agrego objetos vivos
		controladorJuego.agregarObjetoVivo(escenario);
		
		TanqueHeroe tanque=FabricaElementos.crearTanqueHeroe();
		tanque.setX(0.0);
		tanque.setY(100.0);
		
		LanzaCohetes arma=new LanzaCohetes(tanque);
		tanque.agregarArma(arma);
		Canion canion=new Canion(tanque);
		tanque.agregarArma(canion);
		try {
			tanque.seleccionarArma(arma);
		} catch (NoExisteArmaSeleccionadaException e) {
			e.printStackTrace();
		}
		tanque.mover(Direccion.Este());
		
		controladorJuego.comenzarJuego();
		
		/*ControladorJuego controladorJuego= ControladorJuego.getInstancia();
		Ventana ventana = new Ventana(960,720,controladorJuego);
		Panel panel = new Panel(960,720,ventana);
		
		FabricaElementos.crearTanqueHeroe();
		FabricaElementos.crearBalaAmetralladora();
		FabricaElementos.crearBalaCanion();
		FabricaElementos.crearCohete();
		VistaEscenario.getInstancia().dibujar(panel);*/
		
	}
}
