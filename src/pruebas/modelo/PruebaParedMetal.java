package pruebas.modelo;



import misc.FabricaElementos;
import modelo.Escenario;

import modelo.ParedMetal;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;

import excepciones.NoSePudoPosicionarException;

public class PruebaParedMetal {
	ParedMetal paredDeMetal;

	
	@Before
	public void setUp(){
		Escenario.nuevaInstancia();
		try {
			paredDeMetal = FabricaElementos.crearParedMetal(100.0, 100.0);
		} catch (NoSePudoPosicionarException e) {
			e.printStackTrace();
		}		
	}
	

	
	@Test
	public void probarDestruirParedMetal(){
		int contadorDisparos = 0;
		while(!paredDeMetal.estaDestruida()){
			paredDeMetal.recibirImpacto(10);
			contadorDisparos ++;
		}
		Assert.assertTrue(contadorDisparos == 2);
	}
	
	@Test
	public void probarDestuirDeEscenario(){
		while(!paredDeMetal.estaDestruida()){
			paredDeMetal.recibirImpacto(10);
		}
		Assert.assertTrue(Escenario.getActual().cantidadActualDeObjetosSolidos() == 0);
	}
	
}
