package vista;


import org.w3c.dom.Document;
import org.w3c.dom.Element;

import titiritero.vista.Animacion;
import titiritero.vista.Imagen;
import titiritero.Posicionable;
import utils.Direccion;
import misc.ContadorDeInstancias;
import misc.DiccionarioDeSerializables;
import misc.Observador;
import modelo.TanqueHeroe;
import modelo.armamento.Ametralladora;
import modelo.armamento.Canion;

public class VistaTanqueHeroe extends VistaTanque implements Observador {
	private long id = ContadorDeInstancias.getId();

	private static final int ORDEN = 3;

	private Animacion spriteNormalAmetralladora;
	private Animacion spriteNormalCanion;
	private Animacion spriteNormalLanzaCohetes;
	private Animacion spriteMejoradoAmetralladora;
	private Animacion spriteMejoradoCanion;
	private Animacion spriteMejoradoLanzaCohetes;
	private Animacion spriteDestruido;

	private static final String RUTA_SPRITE = "/sprites/SpriteAlgoTank.png";

	private static final int ALTO_SPRITE = 50;
	private static final int ANCHO_SPRITE = 50;

	// FILA DE LOS SPRITES INDIVIDUALES EN EL SPRITE GENERAL
	private static final int FILA_SPRITE_NORMAL_AMETRALLADORA = 0;
	private static final int FILA_SPRITE_NORMAL_CANION = 1;
	private static final int FILA_SPRITE_NORMAL_LANZACOHETES = 2;
	private static final int FILA_SPRITE_MEJORADO_AMETRALLADORA = 3;
	private static final int FILA_SPRITE_MEJORADO_CANION = 4;
	private static final int FILA_SPRITE_MEJORADO_LANZACOHETES = 5;
	private static final int FILA_SPRITE_DESTRUIDO = 6;

	// FPS DE CADA SPRITE
	private static final double FPS_NORMAL_AMETRALLADORA = 25.0;
	private static final double FPS_NORMAL_CANION = 25.0;
	private static final double FPS_NORMAL_LANZACOHETES = 25.0;
	private static final double FPS_MEJORADO_AMETRALLADORA = 25.0;
	private static final double FPS_MEJORADO_CANION = 25.0;
	private static final double FPS_MEJORADO_LANZACOHETES = 25.0;
	private static final double FPS_DESTRUIDO = 4.0;

	public static final String TAG = "objeto-vista-tanque-heroe";
	
	public VistaTanqueHeroe(){
		
	}
	public VistaTanqueHeroe(TanqueHeroe tanque) {
		super(tanque);
		tanque.adscribir(this);
		orden = ORDEN;

		Imagen spriteTanque = new Imagen(RUTA_SPRITE, tanque);

		Imagen subImagen = spriteTanque.getSubimagen(0,
				FILA_SPRITE_NORMAL_AMETRALLADORA * ALTO_SPRITE,
				spriteTanque.getAncho(), ALTO_SPRITE);
		spriteNormalAmetralladora = new Animacion(subImagen, ANCHO_SPRITE,
				ALTO_SPRITE);
		spriteNormalAmetralladora.setFps(FPS_NORMAL_AMETRALLADORA);

		subImagen = spriteTanque.getSubimagen(0, FILA_SPRITE_NORMAL_CANION
				* ALTO_SPRITE, spriteTanque.getAncho(), ALTO_SPRITE);
		spriteNormalCanion = new Animacion(subImagen, ANCHO_SPRITE, ALTO_SPRITE);
		spriteNormalCanion.setFps(FPS_NORMAL_CANION);

		subImagen = spriteTanque.getSubimagen(0,
				FILA_SPRITE_NORMAL_LANZACOHETES * ALTO_SPRITE,
				spriteTanque.getAncho(), ALTO_SPRITE);
		spriteNormalLanzaCohetes = new Animacion(subImagen, ANCHO_SPRITE,
				ALTO_SPRITE);
		spriteNormalLanzaCohetes.setFps(FPS_NORMAL_LANZACOHETES);

		subImagen = spriteTanque.getSubimagen(0,
				FILA_SPRITE_MEJORADO_AMETRALLADORA * ALTO_SPRITE,
				spriteTanque.getAncho(), ALTO_SPRITE);
		spriteMejoradoAmetralladora = new Animacion(subImagen, ANCHO_SPRITE,
				ALTO_SPRITE);
		spriteMejoradoAmetralladora.setFps(FPS_MEJORADO_AMETRALLADORA);

		subImagen = spriteTanque.getSubimagen(0, FILA_SPRITE_MEJORADO_CANION
				* ALTO_SPRITE, spriteTanque.getAncho(), ALTO_SPRITE);
		spriteMejoradoCanion = new Animacion(subImagen, ANCHO_SPRITE,
				ALTO_SPRITE);
		spriteMejoradoCanion.setFps(FPS_MEJORADO_CANION);

		subImagen = spriteTanque.getSubimagen(0,
				FILA_SPRITE_MEJORADO_LANZACOHETES * ALTO_SPRITE,
				spriteTanque.getAncho(), ALTO_SPRITE);
		spriteMejoradoLanzaCohetes = new Animacion(subImagen, ANCHO_SPRITE,
				ALTO_SPRITE);
		spriteMejoradoLanzaCohetes.setFps(FPS_MEJORADO_LANZACOHETES);

		subImagen = spriteTanque.getSubimagen(0, FILA_SPRITE_DESTRUIDO
				* ALTO_SPRITE, spriteTanque.getAncho(), ALTO_SPRITE);
		spriteDestruido = new Animacion(subImagen, ANCHO_SPRITE, ALTO_SPRITE);
		spriteDestruido.setFps(FPS_DESTRUIDO);

		spriteActual = spriteNormalAmetralladora;
		actualizar();
	}

	

	public Posicionable getPosicionable() {
		return tanque;
	}

	public void setPosicionable(Posicionable tanque) {
		this.tanque = (TanqueHeroe) tanque;
		spriteNormalAmetralladora.setPosicionable(tanque);
		spriteNormalCanion.setPosicionable(tanque);
		spriteNormalLanzaCohetes.setPosicionable(tanque);
		spriteMejoradoAmetralladora.setPosicionable(tanque);
		spriteMejoradoCanion.setPosicionable(tanque);
		spriteMejoradoLanzaCohetes.setPosicionable(tanque);
		spriteDestruido.setPosicionable(tanque);
	}

	public void setTanque(TanqueHeroe tanque) {
		setPosicionable(tanque);
	}

	public TanqueHeroe getTanque() {
		return (TanqueHeroe) tanque;
	}

	@Override
	public void actualizar() {
		spriteActual.detener();
		if (tanque.estaDestruido()) {
			VistaEscenario.getInstancia().borrarVista(this);
		} else {
			if (tanque.tieneDisparoMejorado()) {
				if (tanque.getArmaActual().getClass() == Ametralladora.class)
					spriteActual = spriteMejoradoAmetralladora;
				else if (tanque.getArmaActual().getClass() == Canion.class)
					spriteActual = spriteMejoradoCanion;
				else
					spriteActual = spriteMejoradoLanzaCohetes;
			} else {
				if (tanque.getArmaActual().getClass() == Ametralladora.class)
					spriteActual = spriteNormalAmetralladora;
				else if (tanque.getArmaActual().getClass() == Canion.class)
					spriteActual = spriteNormalCanion;
				else
					spriteActual = spriteNormalLanzaCohetes;
			}
			if (tanque.enMovimiento())
				spriteActual.reproducir();
			else
				spriteActual.detener();
		}
		actualizarOrientacion();
	}

	private void actualizarOrientacion() {
		switch (tanque.getOrientacion().get()) {
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
		tanque.adscribir(this);
		orden = ORDEN;

		Imagen spriteTanque = new Imagen(RUTA_SPRITE, tanque);

		Imagen subImagen = spriteTanque.getSubimagen(0,
				FILA_SPRITE_NORMAL_AMETRALLADORA * ALTO_SPRITE,
				spriteTanque.getAncho(), ALTO_SPRITE);
		spriteNormalAmetralladora = new Animacion(subImagen, ANCHO_SPRITE,
				ALTO_SPRITE);
		spriteNormalAmetralladora.setFps(FPS_NORMAL_AMETRALLADORA);

		subImagen = spriteTanque.getSubimagen(0, FILA_SPRITE_NORMAL_CANION
				* ALTO_SPRITE, spriteTanque.getAncho(), ALTO_SPRITE);
		spriteNormalCanion = new Animacion(subImagen, ANCHO_SPRITE, ALTO_SPRITE);
		spriteNormalCanion.setFps(FPS_NORMAL_CANION);

		subImagen = spriteTanque.getSubimagen(0,
				FILA_SPRITE_NORMAL_LANZACOHETES * ALTO_SPRITE,
				spriteTanque.getAncho(), ALTO_SPRITE);
		spriteNormalLanzaCohetes = new Animacion(subImagen, ANCHO_SPRITE,
				ALTO_SPRITE);
		spriteNormalLanzaCohetes.setFps(FPS_NORMAL_LANZACOHETES);

		subImagen = spriteTanque.getSubimagen(0,
				FILA_SPRITE_MEJORADO_AMETRALLADORA * ALTO_SPRITE,
				spriteTanque.getAncho(), ALTO_SPRITE);
		spriteMejoradoAmetralladora = new Animacion(subImagen, ANCHO_SPRITE,
				ALTO_SPRITE);
		spriteMejoradoAmetralladora.setFps(FPS_MEJORADO_AMETRALLADORA);

		subImagen = spriteTanque.getSubimagen(0, FILA_SPRITE_MEJORADO_CANION
				* ALTO_SPRITE, spriteTanque.getAncho(), ALTO_SPRITE);
		spriteMejoradoCanion = new Animacion(subImagen, ANCHO_SPRITE,
				ALTO_SPRITE);
		spriteMejoradoCanion.setFps(FPS_MEJORADO_CANION);

		subImagen = spriteTanque.getSubimagen(0,
				FILA_SPRITE_MEJORADO_LANZACOHETES * ALTO_SPRITE,
				spriteTanque.getAncho(), ALTO_SPRITE);
		spriteMejoradoLanzaCohetes = new Animacion(subImagen, ANCHO_SPRITE,
				ALTO_SPRITE);
		spriteMejoradoLanzaCohetes.setFps(FPS_MEJORADO_LANZACOHETES);

		subImagen = spriteTanque.getSubimagen(0, FILA_SPRITE_DESTRUIDO
				* ALTO_SPRITE, spriteTanque.getAncho(), ALTO_SPRITE);
		spriteDestruido = new Animacion(subImagen, ANCHO_SPRITE, ALTO_SPRITE);
		spriteDestruido.setFps(FPS_DESTRUIDO);

		spriteActual = spriteNormalAmetralladora;
		actualizar();
		
	}
}
