package pruebas.modelo;

import misc.FabricaElementos;
import modelo.Escenario;
import modelo.ParedConcreto;
import modelo.ParedMetal;
import modelo.TanqueHeroe;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;

public class PruebaEscenario {

	ParedConcreto paredDeConcreto;
	ParedMetal paredDeMetal;
	TanqueHeroe tanque;
	
	@Before
	public void setUp(){
		Escenario.nuevaInstancia();	
	}
	

	
	@Test
	public void probarAgregarObjetoSolido(){
		paredDeConcreto= FabricaElementos.crearParedConcreto(100.0, 100.0);
		paredDeMetal= FabricaElementos.crearParedMetal(200.0, 200.0);
		Assert.assertTrue(Escenario.getActual().cantidadActualDeObjetosSolidos() == 2);
	}
	
	@Test
	public void probarBorrarObjetoSolido(){
		paredDeConcreto= FabricaElementos.crearParedConcreto(100.0, 100.0);
		paredDeMetal= FabricaElementos.crearParedMetal(200.0, 200.0);
		Escenario.getActual().borrarSolido(paredDeConcreto);
		Assert.assertTrue(Escenario.getActual().cantidadActualDeObjetosSolidos() == 1);
	}
	
	@Test
	public void probarAgregarObjetoVivo(){
		tanque= FabricaElementos.crearTanqueHeroe();
		Assert.assertTrue(Escenario.getActual().cantidadActualDeObjetosVivos() == 1);
	}
	
	@Test
	public void probarBorrarObjetoVivo(){
		tanque= FabricaElementos.crearTanqueHeroe();
		Escenario.getActual().borrarObjetoVivo(tanque);
		Assert.assertTrue(Escenario.getActual().cantidadActualDeObjetosVivos() == 0);
	}
	
}
