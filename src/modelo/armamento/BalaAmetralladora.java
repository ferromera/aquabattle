package modelo.armamento;



public class BalaAmetralladora extends Bala {
	private final int FUERZA=20;
	private final double VELOCIDAD=150.0;
	private final double ANCHO=10.0;
	private final double ALTO=10.0;
	
	public BalaAmetralladora(){
		velocidad=VELOCIDAD;
		fuerza=FUERZA;
		setAlto(ALTO);
		setAncho(ANCHO);
	}
	public BalaAmetralladora(double x,double y){
		velocidad=VELOCIDAD;
		fuerza=FUERZA;
		setAlto(ALTO);
		setAncho(ANCHO);
		setX(x);
		setY(y);
	}
	

}
