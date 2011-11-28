package pantallas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import controlador.ControladorPantallaJuego;

import titiritero.ControladorJuego;
import vista.pantallas.VistaPantallaJuego;

import excepciones.NoPudoLeerXMLExeption;
import excepciones.NoSePudoPosicionarException;

import misc.DiccionarioDeSerializables;
import misc.FabricaBonus;
import misc.FabricaBonusAtaque;
import misc.FabricaElementos;
import misc.Nivel;
import misc.SorteadorBernoulli;
import modelo.Escenario;
import modelo.TanqueEnemigo;

public class PantallaJuego extends Pantalla {
	private static final String RUTA_NIVEL_1 = "./bin/niveles/nivel1.xml";
	private static final int VIDAS = 5;
	private static final int X_VIDA = 975;
	private static final int Y_VIDA = 500;
	private static final int SEPARACION_VIDA = 30;
	private static PantallaJuego instancia;
	private ArrayList<Nivel> niveles;
	private ArrayList<Vida> vidas;
	private int nivelActual;
	private int puntos;
	private boolean pausado=false;
	
	public static PantallaJuego getInstancia(){
		if(instancia==null)
			instancia=new PantallaJuego();
		return instancia;
	}
	private PantallaJuego(){
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
		pausado=false;
	}
	
	

	@Override
	public void vivir() {
		if(pausado)
			return;
		if(niveles.get(nivelActual).estaGanado()){
			siguienteNivel();
			return;
		}
		niveles.get(nivelActual).vivir();	
		
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
		pausado=true;
		niveles.get(nivelActual).pausar();
	}
	public void reanudar(){
		pausado=false;
		niveles.get(nivelActual).reanudar();
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
		pausar();
		ControladorJuego.getInstancia().removerDibujable(VistaPantallaJuego.getInstancia());
		ControladorJuego.getInstancia().removerObjetoVivo(this);
		ControladorJuego.getInstancia().removerKeyPressObservador(ControladorPantallaJuego.getInstancia());
		
	}
	public void convertirEnActual(){
		ControladorJuego.getInstancia().agregarDibujable(VistaPantallaJuego.getInstancia());
		ControladorJuego.getInstancia().agregarObjetoVivo(this);
		ControladorJuego.getInstancia().agregarKeyPressObservador(ControladorPantallaJuego.getInstancia());
		reanudar();
	}
	
	
	
	
	public static final String TAG = "objeto-pantalla-juego";
	private static final String TAG_PUNTOS = "puntos";
	private static final String TAG_PAUSADO = "pausado";
	private static final String TAG_NIVEL_ACTUAL = "nivel-actual";
	private static final String TAG_VIDAS = "vidas";
	private static final String TAG_NIVELES = "niveles";
	private static final String TAG_ESCENARIO = "escenario";
	
	// Constructor deserializador
	private PantallaJuego(Element element) throws NoPudoLeerXMLExeption{
		super((Element)element.getElementsByTagName(Pantalla.TAG).item(0));
		new VistaPantallaJuego(this);
		niveles=new ArrayList<Nivel>();
		vidas=new ArrayList<Vida>();
		NodeList hijos;
		Element elem;
		hijos = element.getChildNodes();
		if(hijos!=null && hijos.getLength()>0){
			for(int i=0;i<hijos.getLength();i++){
				elem = (Element) hijos.item(i);
				if(elem.getTagName().equals(TAG_NIVEL_ACTUAL))
					nivelActual=Integer.parseInt(elem.getTextContent());
				else if(elem.getTagName().equals(TAG_PUNTOS))
					puntos=Integer.parseInt(elem.getTextContent());
				else if(elem.getTagName().equals(TAG_PAUSADO))
					pausado=Boolean.parseBoolean(elem.getTextContent());
				else if(elem.getTagName().equals(TAG_VIDAS))
					vidas.add(new Vida((Element)elem.getFirstChild()));
				else if(elem.getTagName().equals(TAG_NIVELES))
					niveles.add((Nivel) DiccionarioDeSerializables.getInstancia((Element)elem.getFirstChild()));
				else if(elem.getTagName().equals(TAG_ESCENARIO))
					DiccionarioDeSerializables.getInstancia((Element)elem.getFirstChild());
			}
		}
		
	}

}
