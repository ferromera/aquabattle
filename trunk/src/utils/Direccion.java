package utils;

public class Direccion {
	public static final int NORTE=0;
	public static final int SUR=1;
	public static final int ESTE=2;
	public static final int OESTE=3;
	int dir;
	public Direccion(){
		dir=NORTE;
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
