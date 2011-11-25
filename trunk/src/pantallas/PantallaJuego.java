package pantallas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

import excepciones.NoSePudoPosicionarException;

import misc.FabricaBonus;
import misc.FabricaElementos;
import misc.Nivel;
import modelo.Escenario;

public class PantallaJuego extends Pantalla {
	private static final String RUTA_NIVEL_1 = "./bin/niveles/nivel1.xml";
	private static final int VIDAS = 3;
	private static PantallaJuego instancia;
	private Escenario escenario;
	private FabricaBonus fabricaBonus;
	private ArrayList<Nivel> niveles;
	private int nivelActual;
	private int puntos;
	private int vidas;
	private boolean perdido;
	
	public static PantallaJuego getInstancia(){
		if(instancia==null)
			instancia=new PantallaJuego();
		return instancia;
	}
	private PantallaJuego(){
		escenario=Escenario.nuevaInstancia();
		niveles=new ArrayList<Nivel>();
		niveles.add(new Nivel(1,RUTA_NIVEL_1,1000));
		//TODO: agregar mas niveles.
		nivelActual=1;
		puntos=0;
		vidas=VIDAS;
		perdido=false;
		
	}
	

	@Override
	public void vivir() {
		if(perdido)
			return;
		if(niveles.get(nivelActual).estaGanado()){
			siguienteNivel();
			return;
		}
		if(!fabricaBonus.estaProduciendo())
			fabricaBonus.comenzarProduccion();
		escenario.vivir();
		
		
	}
	public void perderVida() {

		if(--vidas==0){
			perder();
			return;
		}
		double xInicial = niveles.get(nivelActual).getXInicial();
		double yInicial = niveles.get(nivelActual).getYInicial();
		try {
			
			FabricaElementos.crearNuevoTanqueHeroe(xInicial, yInicial);
			
		} catch (NoSePudoPosicionarException e) {
			System.err.println("No se pudo posicionar tanque heroe en posicion inicial: ( "+xInicial+" ; "+yInicial+ ").");
			e.printStackTrace();
		}
		
	}
	private void perder() {
		pausar();
		perdido=true;
		Timer timer=new Timer(2000,new ActionListener(){
			public void actionPerformed(ActionEvent e){
				cambiarA(FinDelJuego.getInstancia());
			}
		});
		timer.setRepeats(false);
		timer.start();
		return;
		
	}
	public void sumarPuntos(int puntosGanados){
		puntos+=puntosGanados;
		
	}
	public void pausar() {
		escenario.pausar();
		fabricaBonus.detenerProduccion();

	}
	private void siguienteNivel() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void cambiarA(Pantalla pantalla) {
		// TODO Auto-generated method stub
		
	}

}
