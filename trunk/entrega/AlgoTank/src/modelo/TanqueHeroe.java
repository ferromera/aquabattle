package modelo;


import org.w3c.dom.Document;
import org.w3c.dom.Element;

import pantallas.PantallaJuego;


import misc.ContadorDeInstancias;
import misc.DiccionarioDeSerializables;
import modelo.armamento.Ametralladora;

public class TanqueHeroe extends Tanque {
	private long id=ContadorDeInstancias.getId();
	
	public static final String TAG="objeto-tanque-heroe";
	
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
	
	@Override
	protected void destruir() {
		Escenario.getActual().borrarObjetoVivo(this);
		Escenario.getActual().borrarSolido(this);
		Escenario.getActual().borrarObjeto(this);
		destruido=true;
		PantallaJuego.getInstancia().perderVida();
		notificar();
		
	}
	
	public void calcularSiguienteMovimiento(){
		//No hace nada ya que el proximo movimiento
		//es controlado por el usuario.
	}
	@Override
	public Element getElementoXML(Document doc) {
		Element element = doc.createElement(TAG);
		Element elem= doc.createElement(ContadorDeInstancias.TAG_ID);
		element.appendChild(elem);
		elem.setTextContent(Long.toString(id));
		if(DiccionarioDeSerializables.fueSerializado(id))
			return element;
		DiccionarioDeSerializables.marcarSerializado(id);
		element.appendChild(super.getElementoXML(doc));
		
		return element;
	}

	@Override
	public void fromElementoXML(Element element) {
		super.fromElementoXML((Element)element.getElementsByTagName(Tanque.TAG).item(0));
		instancia=this;
		notificar();
	}
	



}
