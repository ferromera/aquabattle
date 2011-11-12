package modelo.armamento;

public class BalaCanion extends Bala {
	private final int FUERZA=30;
	private final double VELOCIDAD=200.0;
	private final double ANCHO=30.0;
	private final double ALTO=30.0;
	
	public BalaCanion(){
		velocidad=VELOCIDAD;
		fuerza=FUERZA;
		setAlto(ALTO);
		setAncho(ANCHO);
	}
	public BalaCanion(double x, double y){
		velocidad=VELOCIDAD;
		fuerza=FUERZA;
		setAlto(ALTO);
		setAncho(ANCHO);
		setX(x);
		setY(y);
	}
	
}
