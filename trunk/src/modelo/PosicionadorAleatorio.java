package modelo;

import excepciones.NoSePudoPosicionarException;

public abstract class PosicionadorAleatorio {
	protected ElementoRectangular elemento;
	
	public PosicionadorAleatorio(ElementoRectangular elemRectangular){
		setElemento(elemRectangular);
	}
	
	public abstract void posicionar() throws NoSePudoPosicionarException;

	public ElementoRectangular getElemento() {
		return elemento;
	}

	public void setElemento(ElementoRectangular elemento) {
		this.elemento = elemento;
	}
}
