package pruebas.modelo;

import java.util.Date;

import misc.FabricaElementos;
import modelo.Escenario;
import modelo.ParedConcreto;
import modelo.ParedMetal;
import modelo.TanqueHeroe;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;

public class PruebaTanqueHeroe {
	TanqueHeroe heroe;
	ParedConcreto paredConcreto;
	ParedMetal paredMetal;
	
	@Before
	public void setUp(){
		Escenario.nuevaInstancia();
		heroe = FabricaElementos.crearTanqueHeroe();
		heroe.setX(300.0);
		heroe.setY(200.0);
		heroe.setAncho(50.0);
		heroe.setAlto(50.0);
		heroe.setVelocidad(100.0);
		paredConcreto= FabricaElementos.crearParedConcreto(300.0, 100.0);
		paredConcreto.setAlto(50.0);
		paredConcreto.setAncho(50.0);
		paredMetal= FabricaElementos.crearParedMetal(500.0, 200.0);
		paredMetal.setAlto(50.0);
		paredMetal.setAncho(50.0);
		
		
	}
	
	@Test
	public void probarVivirNorte(){
		heroe.moverNorte();
		
		try {
			Thread.sleep(30);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}// paso 30 mseg
		heroe.vivir();
		
		double x= heroe.getX();
		double y= heroe.getY();
	
		Assert.assertTrue(x==300.0);
		Assert.assertTrue(y>=196.9 && y<=197.1);
	}
	@Test
	public void probarVivirSur(){
		heroe.moverSur();

		long tiempo= new Date().getTime();
		try {
			Thread.sleep(35);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}// paso 35 mseg
		heroe.vivir();
		tiempo= new Date().getTime() - tiempo;
		
		double x= heroe.getX();
		double y= heroe.getY();

		Assert.assertTrue(x==300.0);
		Assert.assertTrue(y>=203.4 && y<= 203.6);
	}
	@Test
	public void probarVivirEste(){
		heroe.moverEste();

		try {
			Thread.sleep(51);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}// paso 51 mseg
		heroe.vivir();
		double x= heroe.getX();
		double y= heroe.getY();
		
		Assert.assertTrue(x>=305.0 && x<=305.2);
		Assert.assertTrue(y==200.0);
	}
	@Test
	public void probarVivirOeste(){
		heroe.moverOeste();
		//Inicializa el tiempo
		heroe.vivir();		
		try {
			Thread.sleep(30);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}// paso 30 mseg
		heroe.vivir();
		double x= heroe.getX();
		double y= heroe.getY();
		Assert.assertTrue(x>=296.9 && x<=297.1);
		Assert.assertTrue(y==200.0);
	}
	@Test
	public void probarDetener(){
		heroe.detener();
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}// paso 100 mseg
		heroe.vivir();
		double x= heroe.getX();
		double y= heroe.getY();
		Assert.assertTrue(x==300.0);
		Assert.assertTrue(y==200.0);
	}
	@Test
	public void romperConcreto(){
		heroe.detener();
		heroe.orientarNorte();
		heroe.disparar();

		try {
			Thread.sleep(259);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}// paso 258 mseg
		Escenario.getActual().vivir();
		//Todavia no impacto en la pared
		Assert.assertTrue(!paredConcreto.estaDestruida());
		try {
			Thread.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}// paso 2 mseg
		Escenario.getActual().vivir();
		Assert.assertTrue(paredConcreto.estaDestruida());
	
	}
	@Test
	public void romperMetal(){
		heroe.detener();
		heroe.orientarEste();
		heroe.disparar();
	
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}// paso 1 seg
		Escenario.getActual().vivir();
		//Todavia impacto pero no rompio
		Assert.assertTrue(!paredMetal.estaDestruida());
		heroe.disparar();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}// paso 1 seg
		Escenario.getActual().vivir();
		Assert.assertTrue(paredMetal.estaDestruida());
	
	}
	
}
