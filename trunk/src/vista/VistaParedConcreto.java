package vista;


import misc.Observador;
import modelo.ParedConcreto;
import titiritero.Posicionable;
import titiritero.SuperficieDeDibujo;
import titiritero.vista.Imagen;

public class VistaParedConcreto extends Vista implements Observador{
	private ParedConcreto pared;
	private Imagen sprite;
	
	private final String RUTA_SPRITE_BaseNormal = "/sprites/SpriteParedConcretoNormal.png"; 
	private final String RUTA_SPRITE_BaseDestruida = "/sprites/SpriteParedConcretoDestruida.png";
	
	public VistaParedConcreto(ParedConcreto paredConcreto){
		this.pared = paredConcreto;
		paredConcreto.adscribir(this);
		sprite = new Imagen(RUTA_SPRITE_BaseNormal, paredConcreto);
		actualizar();
		
	}
	
	
	public void dibujar(SuperficieDeDibujo sup) {
		sprite.dibujar(sup);
		System.out.println("Dibujada Pared Concreto");
	}
	
	public Posicionable getPosicionable() {
		return this.pared;
	}
	
	public void setPosicionable(Posicionable paredPos) {
		this.pared = (ParedConcreto) paredPos;
		sprite.setPosicionable(paredPos);
	}
	
	public void setParedConcreto(ParedConcreto pared) {
		setPosicionable(pared);
	}

	public ParedConcreto getParedConcreto() {
		return pared;
	}
	
	
	@Override
	public void actualizar() {
		if (pared.estaDestruida()){
			Imagen nuevaImagen = new Imagen(RUTA_SPRITE_BaseDestruida, pared);
			sprite = nuevaImagen;
			//Borrarla del escenario
		}
	};
	
	
	
	
}
