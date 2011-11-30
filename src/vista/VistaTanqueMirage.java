package vista;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import excepciones.NoPudoLeerXMLExeption;

import misc.ContadorDeInstancias;
import misc.DiccionarioDeSerializables;
import misc.Observador;
import modelo.TanqueIFV;
import modelo.TanqueMirage;
import modelo.armamento.Ametralladora;
import modelo.armamento.Canion;
import modelo.armamento.LanzaCohetes;
import titiritero.Posicionable;
import titiritero.SuperficieDeDibujo;
import titiritero.vista.Animacion;
import titiritero.vista.Imagen;
import utils.Direccion;

public class VistaTanqueMirage extends VistaTanque implements Observador {
	private long id=ContadorDeInstancias.getId();
	
	private static final int ORDEN=3;
	
	private Animacion spriteAmetralladora;
	private Animacion spriteLanzaCohetes;
	

	private final String RUTA_SPRITE = "/sprites/SpriteMirage.png";
	
	private static final int ALTO_SPRITE = 50;
	private static final int ANCHO_SPRITE = 50;

	// FILA DE LOS SPRITES INDIVIDUALES EN EL SPRITE GENERAL
	private static final int FILA_SPRITE_AMETRALLADORA = 0;
	private static final int FILA_SPRITE_LANZACOHETES = 1;

	// FPS DE CADA SPRITE
	private static final double FPS_AMETRALLADORA = 25.0;
	private static final double FPS_LANZACOHETES = 25.0;

	public static final String TAG = "objeto-vista-tanque-mirage";
	

	public VistaTanqueMirage(){
		
	}
	public VistaTanqueMirage(TanqueMirage tanque) {
		super(tanque);
		orden=ORDEN;
		tanque.adscribir(this);
		Imagen spriteTanque = new Imagen(RUTA_SPRITE, tanque);
		
		Imagen subImagen = spriteTanque.getSubimagen( 0 ,
				FILA_SPRITE_AMETRALLADORA * ALTO_SPRITE,
				spriteTanque.getAncho(),ALTO_SPRITE);
		spriteAmetralladora=new Animacion(subImagen,ANCHO_SPRITE,ALTO_SPRITE);
		spriteAmetralladora.setFps(FPS_AMETRALLADORA);
		
		subImagen = spriteTanque.getSubimagen( 0 ,
				FILA_SPRITE_LANZACOHETES * ALTO_SPRITE,
				spriteTanque.getAncho(),ALTO_SPRITE);
		spriteLanzaCohetes=new Animacion(subImagen,ANCHO_SPRITE,ALTO_SPRITE);
		spriteLanzaCohetes.setFps(FPS_LANZACOHETES);
		
		spriteActual=spriteLanzaCohetes;
		actualizar();
	}


	public Posicionable getPosicionable() {
		return tanque;
	}

	public void setPosicionable(Posicionable tanque) {
		this.tanque = (TanqueMirage) tanque;
		spriteAmetralladora.setPosicionable(tanque);
		spriteLanzaCohetes.setPosicionable(tanque);
	}

	public void setTanque(TanqueMirage tanque) {
		setPosicionable(tanque);
	}

	public TanqueMirage getTanque() {
		return (TanqueMirage) tanque;
	}

	@Override
	public void actualizar() {
		spriteActual.detener();
		if(tanque.estaDestruido()){
			VistaEscenario.getInstancia().borrarVista(this);
		}else{
			if(tanque.getArmaActual().getClass()==Ametralladora.class)
				spriteActual=spriteAmetralladora;
			else if(tanque.getArmaActual().getClass()==LanzaCohetes.class)
				spriteActual=spriteLanzaCohetes;	
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
		Imagen spriteTanque = new Imagen(RUTA_SPRITE, tanque);
		
		Imagen subImagen = spriteTanque.getSubimagen( 0 ,
				FILA_SPRITE_AMETRALLADORA * ALTO_SPRITE,
				spriteTanque.getAncho(),ALTO_SPRITE);
		spriteAmetralladora=new Animacion(subImagen,ANCHO_SPRITE,ALTO_SPRITE);
		spriteAmetralladora.setFps(FPS_AMETRALLADORA);
		
		subImagen = spriteTanque.getSubimagen( 0 ,
				FILA_SPRITE_LANZACOHETES * ALTO_SPRITE,
				spriteTanque.getAncho(),ALTO_SPRITE);
		spriteLanzaCohetes=new Animacion(subImagen,ANCHO_SPRITE,ALTO_SPRITE);
		spriteLanzaCohetes.setFps(FPS_LANZACOHETES);
		
		spriteActual=spriteLanzaCohetes;
		actualizar();
		
	}

}
