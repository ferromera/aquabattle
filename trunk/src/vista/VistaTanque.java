package vista;

import java.awt.Color;
import java.awt.Font;

import modelo.ElementoRectangular;
import modelo.Tanque;
import titiritero.SuperficieDeDibujo;
import titiritero.vista.Animacion;
import titiritero.vista.ObjetoDeTexto;
import titiritero.vista.TextoDinamico;

public abstract class VistaTanque extends Vista {
	public class TextoResistencia implements ObjetoDeTexto{

		@Override
		public String getTexto() {
			return Integer.toString(tanque.getResistencia());
		}

	}
	
	protected Animacion spriteActual;
	protected Tanque tanque;
	private TextoDinamico resistencia;
	
	public VistaTanque(Tanque tanque){
		this.tanque=tanque;
		resistencia=new TextoDinamico(new TextoResistencia(),Color.BLACK, new Font("Arial",Font.BOLD,16));
	}
	
	
	public void dibujar(SuperficieDeDibujo sup){
		spriteActual.dibujar(sup);
		resistencia.setPosicionable(new ElementoRectangular(tanque.getX()+10, tanque.getY()+tanque.getAlto()+15));
		resistencia.dibujar(sup);
	}
	
}
