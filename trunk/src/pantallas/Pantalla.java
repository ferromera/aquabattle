package pantallas;


import titiritero.ObjetoVivo;
import titiritero.Posicionable;


public abstract class Pantalla implements Posicionable,ObjetoVivo {

	public abstract void cambiarA(Pantalla pantalla);
	@Override
	public double getX() {
		return 0;
	}

	@Override
	public double getY() {
		return 0;
	}

}
