package vista;

import java.awt.Color;
import java.awt.Font;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import excepciones.NoPudoLeerXMLExeption;

import misc.DiccionarioDeSerializables;
import modelo.ElementoRectangular;
import modelo.ParedMetal;
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

	private static final String TAG_TANQUE = "tanque";

	public static final String TAG = "objeto-vista-tanque";
	
	protected Animacion spriteActual;
	protected Tanque tanque;
	private TextoDinamico resistencia;
	
	public VistaTanque(Tanque tanque){
		this.tanque=tanque;
		resistencia=new TextoDinamico(new TextoResistencia(),Color.BLACK, new Font("Arial",Font.BOLD,16));
	}
	
	public VistaTanque(Element element) throws NoPudoLeerXMLExeption{
		NodeList hijos;
		Element elem;
		hijos = element.getChildNodes();
		if (hijos != null && hijos.getLength() > 0) {
			for (int i = 0; i < hijos.getLength(); i++) {
				elem = (Element) hijos.item(i);
				if (elem.getTagName().equals(TAG_TANQUE))
					tanque = (Tanque) DiccionarioDeSerializables
							.getInstancia((Element) elem.getFirstChild());
			}
		}
		
		resistencia=new TextoDinamico(new TextoResistencia(),Color.BLACK, new Font("Arial",Font.BOLD,16));
	}
	
	
	public void dibujar(SuperficieDeDibujo sup){
		spriteActual.dibujar(sup);
		resistencia.setPosicionable(new ElementoRectangular(tanque.getX()+10, tanque.getY()+tanque.getAlto()+15));
		resistencia.dibujar(sup);
	}
	
}

