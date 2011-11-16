package modelo;

import excepciones.NoSePudoPosicionarException;

public interface PosicionadorAleatorio {
	
	public abstract void posicionar(ElementoRectangular elemento) throws NoSePudoPosicionarException;

	
}
