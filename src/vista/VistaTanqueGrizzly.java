package vista;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import misc.ContadorDeInstancias;
import misc.DiccionarioDeSerializables;
import misc.Observador;
import modelo.TanqueGrizzly;
import titiritero.Posicionable;
import titiritero.vista.Animacion;
import titiritero.vista.Imagen;
import utils.Direccion;

public class VistaTanqueGrizzly extends VistaTanque implements Observador {
	private long id=ContadorDeInstancias.getId();
	
	private static final int ORDEN=3;
	

	private static final String RUTA_SPRITE = "/sprites/SpriteGrizzly.png";
	
	private static final int ALTO_SPRITE = 50;
	private static final int ANCHO_SPRITE = 50;


	// FPS DE CADA SPRITE
	private static final double FPS = 25.0;

	public static final String TAG = "objeto-vista-tanque-grizzly";
	
	public VistaTanqueGrizzly(){
		
	}
	public VistaTanqueGrizzly(TanqueGrizzly tanque) {
		super(tanque);

		orden=ORDEN;
		tanque.adscribir(this);
		
		spriteActual=new Animacion(new Imagen(RUTA_SPRITE, tanque),ANCHO_SPRITE,ALTO_SPRITE);
		spriteActual.setFps(FPS);
		
		actualizar();
	}


	public Posicionable getPosicionable() {
		return tanque;
	}

	public void setPosicionable(Posicionable tanque) {
		this.tanque = (TanqueGrizzly) tanque;
		spriteActual.setPosicionable(tanque);
	}

	public void setTanque(TanqueGrizzly tanque) {
		setPosicionable(tanque);
	}

	public TanqueGrizzly getTanque() {
		return (TanqueGrizzly) tanque;
	}

	@Override
	public void actualizar() {
		spriteActual.detener();
		if(tanque.estaDestruido()){
			VistaEscenario.getInstancia().borrarVista(this);
		}
		if(tanque.enMovimiento())
			spriteActual.reproducir();
		else
			spriteActual.detener();
	
		actualizarOrientacion();
	}

	private void actualizarOrientacion(){
			switch(tanque.getOrientacion().get()){
			case Direccion.NORTE:
				spriteActual.orientarArriba();
				break;
			case Direccion.SUR:
				spriteActual.orientarAbajo();
				break;
			case Direccion.ESTE:
				spriteActual.orientarDerecha();
				break;
			case Direccion.OESTE:
				spriteActual.orientarIzquierda();
				break;
				
			}
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
		super.fromElementoXML((Element)element.getElementsByTagName(VistaTanque.TAG).item(0));
		orden=ORDEN;
		tanque.adscribir(this);
		
		spriteActual=new Animacion(new Imagen(RUTA_SPRITE, tanque),ANCHO_SPRITE,ALTO_SPRITE);
		spriteActual.setFps(FPS);
		
		actualizar();
		
	}
	
}
