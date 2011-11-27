package modelo;

import org.w3c.dom.Element;

import pantallas.PantallaJuego;

import excepciones.NoPudoLeerXMLExeption;

public abstract class TanqueEnemigo extends Tanque {
	public static final String TAG="tanque-enemigo";
	private int puntosOtorgados=20;
	public TanqueEnemigo(){}
	public TanqueEnemigo(Element element) throws NoPudoLeerXMLExeption{
		super((Element)element.getElementsByTagName(Tanque.TAG).item(0));
	}
	
	protected void destruir() {
		Escenario.getActual().borrarObjetoVivo(this);
		Escenario.getActual().borrarSolido(this);
		Escenario.getActual().borrarObjeto(this);
		PantallaJuego.getInstancia().sumarPuntos(puntosOtorgados);
		tirarArma();
		destruido=true;
		notificar();
	}
	protected abstract void tirarArma();
	protected void setPuntosOtorgados(int puntos){
		puntosOtorgados=puntos;
	}
}
