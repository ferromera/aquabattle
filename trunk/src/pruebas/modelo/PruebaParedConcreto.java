package pruebas.modelo;

public class PruebaParedConcreto {

	ParedConcreto paredDeConcreto;
	
	@Before
	public void setUp(){
		Escenario.nuevaInstancia();
		paredDeConcreto= FabricaElementos.crearParedConcreto(100.0, 100.0);
		paredDeConcreto.setAlto(20.0);
		paredDeConcreto.setAncho(20.0);

		
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
		Escenario.agregarObjetoSolido(paredDeConcreto);
		while(!paredDeConcreto.estaDestruida()){
			paredDeConcreto.recibirImpacto(10);
		}
		Assert.assertTrue(Escenario.cantidadActualDeObjetosSolidos() == 0);
	}
	
}
