package utils;


public class Direccion {
	public static final int NORTE=0;
	public static final int SUR=1;
	public static final int ESTE=2;
	public static final int OESTE=3;
	int dir;
	public Direccion() {
		dir=NORTE;
	}
	public static Direccion Este(){
		Direccion dir =new Direccion();
		dir.setEste();
		return dir;
	}
	public static Direccion Oeste(){
		Direccion dir =new Direccion();
		dir.setOeste();
		return dir;
	}
	public static Direccion Sur(){
		Direccion dir =new Direccion();
		dir.setSur();
		return dir;
	}
	public static Direccion Norte(){
		Direccion dir =new Direccion();
		dir.setNorte();
		return dir;
	}
	public void setNorte(){
		dir=NORTE;
	}
	public void setSur(){
		dir=SUR;
	}
	public void setEste(){
		dir=ESTE;
	}
	public void setOeste(){
		dir=OESTE;
	}
	public int get(){
		return dir;
	}
	public boolean esNorte(){
		return dir==NORTE;
	}
	public boolean esSur(){
		return dir==SUR;
	}
	public boolean esEste(){
		return dir==ESTE;
	}
	public boolean esOeste(){
		return dir==OESTE;
	}
}
