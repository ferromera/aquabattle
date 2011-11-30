package modelo.mejoras;

import misc.SerializableXML;

public abstract class MejoraTanque implements Mejora,SerializableXML {
	public abstract void pausar();
	public abstract void reanudar();

}
