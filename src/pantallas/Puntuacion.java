package pantallas;

import titiritero.Posicionable;

public class Puntuacion implements Posicionable, Comparable<Puntuacion> {
	private String nombre;
	private int puntos;
	private int x,y;
	
	@Override
	public double getX() {
		return x;
	}
	@Override
	public double getY() {
		return y;
	}
	
	public Puntuacion(String nombre,int puntos){
		this.nombre=nombre;
		this.puntos=puntos;
		x=0;
		y=0;
	}
	public void setPosicion(int x, int y){
		this.x=x;
		this.y=y;
	}
	public String getNombre(){
		return nombre;
	}
	public int getPuntos(){
		return puntos;
	}
	@Override
	public int compareTo(Puntuacion p) {
		if(puntos >p.puntos)
			return -1;
		if(puntos < p.puntos)
			return 1;
		return 0;
	}
}
