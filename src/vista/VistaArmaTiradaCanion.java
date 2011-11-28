package vista;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import excepciones.NoPudoLeerXMLExeption;

import misc.ContadorDeInstancias;
import misc.DiccionarioDeSerializables;
import misc.Observador;
import modelo.ArmaTiradaCanion;
import modelo.TanqueHeroe;
import titiritero.Posicionable;
import titiritero.SuperficieDeDibujo;
import titiritero.vista.Animacion;
import titiritero.vista.Imagen;

public class VistaArmaTiradaCanion extends Vista implements Observador {
	private ArmaTiradaCanion armaTiradaCanion;
	private long id = ContadorDeInstancias.getId();

	private Animacion spriteActual;

	private static final String RUTA_SPRITE = "/sprites/SpriteBonusArmaCanion.png";

	private static final int ALTO_SPRITE = 50;
	private static final int ANCHO_SPRITE = 50;

	private static final double FPS_NORMAL_BONUS = 5.0;
	public static final String TAG = "objeto-vista-arma-tirada-canion";
	private static final String TAG_ARMA_CANION = "arma-canion";

	public VistaArmaTiradaCanion(ArmaTiradaCanion armaCanion) {
		this.armaTiradaCanion = armaCanion;
		armaCanion.adscribir(this);

		Imagen spriteBonus = new Imagen(RUTA_SPRITE, armaCanion);

		Imagen subImagen = spriteBonus.getSubimagen(0, 0,
				spriteBonus.getAncho(), ALTO_SPRITE);

		spriteActual = new Animacion(subImagen, ANCHO_SPRITE, ALTO_SPRITE);
		spriteActual.setFps(FPS_NORMAL_BONUS);

		actualizar();

	}

	public VistaArmaTiradaCanion(Element element) throws NoPudoLeerXMLExeption {
		NodeList hijos;
		Element elem;
		hijos = element.getChildNodes();
		if(hijos!=null && hijos.getLength()>0){
			for(int i=0;i<hijos.getLength();i++){
				elem = (Element) hijos.item(i);
				if(elem.getTagName().equals(TAG_ARMA_CANION))
					armaTiradaCanion=(ArmaTiradaCanion)DiccionarioDeSerializables.getInstancia((Element)elem.getFirstChild());
			}
		}
		armaTiradaCanion.adscribir(this);
		Imagen spriteBonus = new Imagen(RUTA_SPRITE, armaTiradaCanion);

		Imagen subImagen = spriteBonus.getSubimagen(0, 0,
				spriteBonus.getAncho(), ALTO_SPRITE);

		spriteActual = new Animacion(subImagen, ANCHO_SPRITE, ALTO_SPRITE);
		spriteActual.setFps(FPS_NORMAL_BONUS);

		actualizar();
	}

	public void dibujar(SuperficieDeDibujo sup) {
		spriteActual.dibujar(sup);
	}

	public Posicionable getPosicionable() {
		return this.armaTiradaCanion;
	}

	public void setPosicionable(Posicionable bonus) {
		this.armaTiradaCanion = (ArmaTiradaCanion) bonus;
		spriteActual.setPosicionable(bonus);

	}

	public void setBonusArmaCanion(ArmaTiradaCanion bonusArmaCanion) {
		setPosicionable(bonusArmaCanion);
	}

	public ArmaTiradaCanion getBonusArmaCanion() {
		return this.armaTiradaCanion;
	}

	@Override
	public void actualizar() {
		spriteActual.reproducir();
		if (this.armaTiradaCanion.estaBorrado()) {
			VistaEscenario.getInstancia().borrarVista(this);
		}

	}

}
