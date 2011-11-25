
import java.awt.Color;


import titiritero.vista.KeyPressedController;

import controlador.ControladorTanqueHeroe;
import excepciones.NoExisteArmaSeleccionadaException;
import excepciones.NoPudoLeerXMLExeption;
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
import misc.Nivel;
import modelo.Base;
import modelo.BonusAtaque;
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
		Ventana ventana = new Ventana(960+16,720+38,controladorJuego);
		ventana.addKeyListener(keyController);
		Panel panel = new Panel(960,720,ventana);
		controladorJuego.setSuperficieDeDibujo(panel);
		controladorJuego.setIntervaloSimulacion(1000/50);
		//Agrego dibujables
		Escenario escenario= Escenario.getActual();
		controladorJuego.agregarDibujable(VistaEscenario.getInstancia());
		//Agrego objetos vivos
		controladorJuego.agregarObjetoVivo(escenario);
		
		Nivel nivel1=new Nivel(1,"./bin/niveles/nivel1.xml",1000);
		try {
			nivel1.cargar();
		} catch (NoPudoLeerXMLExeption e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		controladorJuego.comenzarJuego();
		
		/*
		ControladorJuego controladorJuego= ControladorJuego.getInstancia();
		KeyPressedController keyController= new KeyPressedController(controladorJuego);
		Ventana ventana = new Ventana(960+16,720+38,controladorJuego);
		ventana.addKeyListener(keyController);
		Panel panel = new Panel(960,720,ventana);
		controladorJuego.setSuperficieDeDibujo(panel);
		controladorJuego.setIntervaloSimulacion(1000/50);
		//Agrego dibujables
		Escenario escenario= Escenario.getActual();
		controladorJuego.agregarDibujable(VistaEscenario.getInstancia());
		//Agrego objetos vivos
		controladorJuego.agregarObjetoVivo(escenario);
		
		try {
			ParedConcreto pared = FabricaElementos.crearParedConcreto(0.0,100.0);
			ParedMetal pared2 = FabricaElementos.crearParedMetal(0.0,200.0);
			ParedMetal pared3 = FabricaElementos.crearParedMetal(0.0,250.0);
			ParedMetal pared4 = FabricaElementos.crearParedMetal(0.0,300.0);
			Base base = FabricaElementos.crearBase(100.0,100.0);
			BonusAtaque bonus = FabricaElementos.crearBonusAtaque();
			TanqueHeroe tanque=FabricaElementos.crearTanqueHeroe();
			tanque.setX(0.0);
			tanque.setY(400.0);
			
			LanzaCohetes arma=new LanzaCohetes(tanque);
			tanque.agregarArma(arma);
			Canion canion=new Canion(tanque);
			tanque.agregarArma(canion);
			tanque.seleccionarArma(arma);
			tanque.mover(Direccion.Este());
			controladorJuego.comenzarJuego();
			
		} catch (NoSePudoPosicionarException e1) {
			e1.printStackTrace();
		} catch (YaExisteBaseException e){
			e.printStackTrace();
		} catch (NoExisteArmaSeleccionadaException e) {
			e.printStackTrace();
		}
		
		
		
		
		
		
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
