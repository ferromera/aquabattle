import java.awt.Color;

import titiritero.vista.KeyPressedController;

import controlador.ControladorTanqueHeroe;
import excepciones.NoExisteArmaSeleccionadaException;
import excepciones.NoSePudoPosicionarException;
import excepciones.YaExisteBaseException;

import titiritero.ControladorJuego;



import titiritero.vista.Panel;
import titiritero.vista.Ventana;
import titiritero.vista.Animacion;
import utils.Direccion;
import vista.VistaEscenario;
import vista.VistaTanqueHeroe;

import misc.FabricaElementos;
import modelo.Base;
import modelo.BonusVida;
import modelo.Escenario;
import modelo.ParedConcreto;
import modelo.ParedMetal;
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
		
		ParedConcreto pared = FabricaElementos.crearParedConcreto(0.0,100.0);
		ParedMetal pared2 = FabricaElementos.crearParedMetal(0.0,200.0);
		
		try{
		Base base = FabricaElementos.crearBase(100.0,100.0);
		}catch (YaExisteBaseException e){
			e.printStackTrace();
		}
		
		try{
		BonusVida bonus = FabricaElementos.crearBonusVida();
		} catch (NoSePudoPosicionarException e){
			e.printStackTrace();
		}
		
		
		
		TanqueHeroe tanque=FabricaElementos.crearTanqueHeroe();
		tanque.setX(0.0);
		tanque.setY(300.0);
		
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
