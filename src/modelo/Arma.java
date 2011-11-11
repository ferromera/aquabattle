package modelo;



public abstract class Arma {
	public abstract void disparar();
	public boolean equals(Arma a){
		return (getClass().equals(a.getClass()));
	}
}
