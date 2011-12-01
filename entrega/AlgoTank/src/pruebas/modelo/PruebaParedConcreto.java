package pruebas.modelo;


import misc.FabricaElementos;
import modelo.Escenario;
import modelo.ParedConcreto;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;

import excepciones.NoSePudoPosicionarException;

public class PruebaParedConcreto {
	ParedConcreto paredDeConcreto;
	
	@Before
	public void setUp(){
		Escenario.nuevaInstancia();
		try {
			paredDeConcreto= FabricaElementos.crearParedConcreto(100.0, 100.0);
		} catch (NoSePudoPosicionarException e) {
			e.printStackTrace();
		}
	}
	

	
	@Test
	public void probarDestruirParedConcreto(){
		int contadorDisparos = 0;
		while(!paredDeConcreto.estaDestruida()){
			paredDeConcreto.recibirImpacto(10);
			contadorDisparos ++;
		}
		Assert.assertTrue(contadorDisparos == 1);
	}
	
	@Test
	public void probarDestuirDeEscenario(){
		while(!paredDeConcreto.estaDestruida()){
			paredDeConcreto.recibirImpacto(10);
		}
		Assert.assertTrue(Escenario.getActual().cantidadActualDeObjetosSolidos() == 0);
	}
	
}
