package titiritero;

import misc.SerializableXML;

public interface ObjetoVivo extends SerializableXML {
	
	public void vivir();

	public void pausar();

	public void reanudar();
	
}
