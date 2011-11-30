package vista.pantallas;

import java.awt.Color;
import java.awt.Font;


import org.w3c.dom.Document;
import org.w3c.dom.Element;


import pantallas.Puntuacion;

import titiritero.Posicionable;
import titiritero.SuperficieDeDibujo;
import titiritero.vista.ObjetoDeTexto;
import titiritero.vista.TextoDinamico;
import vista.Vista;


public class VistaPuntuacion extends Vista {
	public class TextoPuntuacion implements ObjetoDeTexto {
		private static final int longitud = 18;
		@Override
		public String getTexto() {
			String str=puntuacion.getNombre();
			for(int i=str.length(); i<longitud;i++)
				str=str+" ";
			return str+Integer.toString(puntuacion.getPuntos());	
		}
	}
	private Puntuacion puntuacion;
	private TextoDinamico texto;
	public VistaPuntuacion(Puntuacion p){
		puntuacion=p;
		texto = new TextoDinamico(new TextoPuntuacion(), Color.WHITE,
				new Font("Courier New", Font.BOLD, 50));
		texto.setPosicionable(p);
	}

	@Override
	public void dibujar(SuperficieDeDibujo superfice) {
		texto.dibujar(superfice);
		
	}

	@Override
	public Posicionable getPosicionable() {
		return puntuacion;
	}

	@Override
	public void setPosicionable(Posicionable posicionable) {
		puntuacion=(Puntuacion)posicionable;
		
	}

	@Override
	public Element getElementoXML(Document doc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void fromElementoXML(Element element) {
		// TODO Auto-generated method stub
		
	}

}
