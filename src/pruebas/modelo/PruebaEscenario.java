package pruebas.modelo;

public class PruebaEscenario {

	ParedConcreto paredDeConcreto;
	ParedMetal paredDeMetal;
	
	@Before
	public void setUp(){
		Escenario.nuevaInstancia();
		paredDeConcreto= FabricaElementos.crearParedConcreto(100.0, 100.0);
		paredDeConcreto.setAlto(20.0);
		paredDeConcreto.setAncho(20.0);
		paredDeMetal= FabricaElementos.crearParedMetal(100.0, 200.0);
		paredDeMetal.setAlto(20.0);
		paredDeMetal.setAncho(20.0);
		
	}
	

	
	@Test
	public void probarAgregarObjetoSolido(){
		Escenario.agregarObjetoSolido(paredDeConcreto);
		Assert.assertTrue(Escenario.cantidadActualDeObjetosSolidos() == 1);
	}
	
	@Test
	public void probarBorrarObjetoSolido(){
		Escenario.agregarObjetoSolido(paredDeConcreto);
		Escenario.agregarObjetoSolido(paredDeMetal);
		Escenario.borrarObjetoSolido(paredDeConcreto);
		Assert.assertTrue(Escenario.cantidadActualDeObjetosSolidos() == 1);
	}
	
	
}
