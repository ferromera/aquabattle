package vista;

import misc.SerializableXML;
import titiritero.Dibujable;


public abstract class Vista implements Comparable<Vista>,Dibujable,SerializableXML {
	protected int orden=1;
	public int getOrden(){
		return orden;
	}
	public int compareTo(Vista vista){
		if(this.getOrden()<vista.getOrden())
			return -1;
		if(this.getOrden()>vista.getOrden())
			return 1;
		return 0;
	}
}
