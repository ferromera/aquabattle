package utils;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import misc.ContadorDeInstancias;


public class Direccion {
	
	public static final String TAG = "objeto-direccion";

	private static final String TAG_DIR = "dir";

	private long id=ContadorDeInstancias.getId();
	
	public static final int NORTE=0;
	public static final int SUR=1;
	public static final int ESTE=2;
	public static final int OESTE=3;
	int dir;
	
	public Direccion() {
		dir=NORTE;
	}
	public Direccion(Element element) {
		NodeList hijos;
		Element elem;
		hijos = element.getChildNodes();
		if(hijos!=null && hijos.getLength()>0){
			for(int i=0;i<hijos.getLength();i++){
				elem = (Element) hijos.item(i);
				if(elem.getTagName().equals(TAG_DIR))
					dir=Integer.parseInt(elem.getTextContent());
			}
		}
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
