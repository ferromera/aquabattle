package pantallas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

import titiritero.ControladorJuego;
import vista.pantallas.VistaPantallaJuego;

import excepciones.NoPudoLeerXMLExeption;
import excepciones.NoSePudoPosicionarException;

import misc.FabricaBonus;
import misc.FabricaBonusAtaque;
import misc.FabricaElementos;
import misc.Nivel;
import misc.SorteadorBernoulli;
import modelo.Escenario;

public class PantallaJuego extends Pantalla {
	private static final String RUTA_NIVEL_1 = "./bin/niveles/nivel1.xml";
	private static final int VIDAS = 5;
	private static final int X_VIDA = 975;
	private static final int Y_VIDA = 500;
	private static final int SEPARACION_VIDA = 30;
	private static PantallaJuego instancia;
	private Escenario escenario;
	private FabricaBonus fabricaBonus;
	private ArrayList<Nivel> niveles;
	private ArrayList<Vida> vidas;
	private int nivelActual;
	private int puntos;
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
		nivelActual=0;
		try {
			niveles.get(nivelActual).cargar();
		} catch (NoPudoLeerXMLExeption e) {
			System.err.println("No se pudo cargar nivel "+nivelActual+1 +" ." );
			e.printStackTrace();
		}
		puntos=0;
		vidas=new ArrayList<Vida>();
		new VistaPantallaJuego(this);
		for(int i=0 ;i<VIDAS;i++)
			vidas.add(FabricaElementos.crearVida(X_VIDA,Y_VIDA+SEPARACION_VIDA*i));
		perdido=false;		
		fabricaBonus=new FabricaBonusAtaque(new SorteadorBernoulli());
		fabricaBonus.detenerProduccion();
	}
	
	

	@Override
	public void vivir() {
		if(perdido)
			return;
		if(niveles.get(nivelActual).estaGanado()){
			siguienteNivel();
			return;
		}
	//	if(!fabricaBonus.estaProduciendo())
		//	fabricaBonus.comenzarProduccion();
		escenario.vivir();
		
		
	}
	public void perderVida() {
		vidas.get(0).borrar();
		vidas.remove(0);
		if(vidas.size()==0){
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
	public void perder() {
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
	public int getPuntos(){
		return puntos;
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
	@Override
	public void dejarDeSerActual() {
		ControladorJuego.getInstancia().removerDibujable(VistaPantallaJuego.getInstancia());
		ControladorJuego.getInstancia().removerObjetoVivo(this);
		
	}
	public void convertirEnActual(){
		ControladorJuego.getInstancia().agregarDibujable(VistaPantallaJuego.getInstancia());
		ControladorJuego.getInstancia().agregarObjetoVivo(this);
	}

}
