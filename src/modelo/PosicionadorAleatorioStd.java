package modelo;

import excepciones.NoSePudoPosicionarException;

public class PosicionadorAleatorioStd  implements PosicionadorAleatorio{

	
	public void posicionar(ElementoRectangular elemento) throws NoSePudoPosicionarException{
		double x;
		double y;
		boolean posicionInvalida=true;
		int iteraciones=0;
		while(posicionInvalida){
				
			if(iteraciones > 1000)
				throw new NoSePudoPosicionarException();
			x= generarX();
			y= generarY();
			elemento.setX(x);
			elemento.setY(y);
			posicionInvalida=elemento.estaSuperpuesto();
		}
	}
	private double generarX(){
		return Math.random()*(Escenario.getActual().getAncho());
	}
	private double generarY(){
		return Math.random()*(Escenario.getActual().getAlto());
	}
}
