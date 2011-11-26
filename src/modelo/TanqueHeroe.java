package modelo;


import org.w3c.dom.Element;

import pantallas.PantallaJuego;

import excepciones.NoPudoLeerXMLExeption;

import modelo.armamento.Ametralladora;

public class TanqueHeroe extends Tanque {
	public static final String TAG="tanque-heroe";
	private static final int RESISTENCIA = 100;
	private static final double POS_X = 300.0;
	private static final double POS_Y = 400.0;
	private static final double VELOCIDAD = 100.0;
	private static final double ANCHO = 50.0;
	private static final double ALTO = 50;
	

	private static TanqueHeroe instancia = null;

	public static TanqueHeroe getInstancia() {
		if (instancia == null)
			instancia = new TanqueHeroe();
		return instancia;
	}
	public static TanqueHeroe nuevaInstancia() {
		instancia = new TanqueHeroe();
		return instancia;
	}
	public static TanqueHeroe nuevaInstancia(Element element) throws NoPudoLeerXMLExeption {
		instancia = new TanqueHeroe(element);
		return instancia;
	}

	public TanqueHeroe() {
		destruido=false;
		resistencia = RESISTENCIA;
		setX(POS_X);
		setY(POS_Y);
		velocidad = VELOCIDAD;
		setAlto(ALTO);
		setAncho(ANCHO);
		agregarArma(new Ametralladora(this));
		
	}
	public TanqueHeroe(Element element) throws NoPudoLeerXMLExeption {
		super((Element)element.getElementsByTagName(Tanque.TAG).item(0));
	}
	@Override
	protected void destruir() {
		Escenario.getActual().borrarObjetoVivo(this);
		Escenario.getActual().borrarSolido(this);
		destruido=true;
		PantallaJuego.getInstancia().perderVida();
		notificar();
		
	}
	
	public void calcularSiguienteMovimiento(){
		//No hace nada ya que el proximo movimiento
		//es controlado por el usuario.
	}

	



}
