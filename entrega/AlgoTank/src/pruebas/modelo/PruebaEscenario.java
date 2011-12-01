package pruebas.modelo;

import misc.FabricaElementos;
import modelo.Escenario;
import modelo.ParedConcreto;
import modelo.ParedMetal;
import modelo.TanqueHeroe;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;

import excepciones.NoSePudoPosicionarException;

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
		try {
			paredDeConcreto= FabricaElementos.crearParedConcreto(100.0, 100.0);
			paredDeMetal= FabricaElementos.crearParedMetal(200.0, 200.0);
			Assert.assertTrue(Escenario.getActual().cantidadActualDeObjetosSolidos() == 2);
		} catch (NoSePudoPosicionarException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void probarBorrarObjetoSolido(){
		try {
			paredDeConcreto= FabricaElementos.crearParedConcreto(100.0, 100.0);
			paredDeMetal= FabricaElementos.crearParedMetal(200.0, 200.0);
			Escenario.getActual().borrarSolido(paredDeConcreto);
			Assert.assertTrue(Escenario.getActual().cantidadActualDeObjetosSolidos() == 1);
		} catch (NoSePudoPosicionarException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void probarAgregarObjetoVivo(){
		try {
			tanque= FabricaElementos.crearTanqueHeroe();
			Assert.assertTrue(Escenario.getActual().cantidadActualDeObjetosVivos() == 1);
			
		} catch (NoSePudoPosicionarException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void probarBorrarObjetoVivo(){
		try {
			tanque= FabricaElementos.crearTanqueHeroe();
			Escenario.getActual().borrarObjetoVivo(tanque);
			Assert.assertTrue(Escenario.getActual().cantidadActualDeObjetosVivos() == 0);
			
		} catch (NoSePudoPosicionarException e) {
			e.printStackTrace();
		}
	}
	
}
