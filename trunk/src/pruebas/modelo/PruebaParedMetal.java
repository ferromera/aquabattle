package pruebas.modelo;

public class PruebaParedMetal {

	ParedMetal paredDeMetal;
	
	@Before
	public void setUp(){
		Escenario.nuevaInstancia();
		paredDeMetal= FabricaElementos.crearParedMetal(100.0, 100.0);
		paredDeMetal.setAlto(20.0);
		paredDeMetal.setAncho(20.0);

		
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
		Escenario.agregarObjetoSolido(paredDeMetal);
		while(!paredDeMetal.estaDestruida()){
			paredDeMetal.recibirImpacto(10);
		}
		Assert.assertTrue(Escenario.cantidadActualDeObjetosSolidos() == 0);
	}
	
}
