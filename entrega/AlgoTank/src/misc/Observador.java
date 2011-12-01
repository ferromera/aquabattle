package misc;

public interface Observador extends SerializableXML {
	/*
	 * actualizar: debe actualizar el estado
	 * del observador basandose en el estado
	 * del objeto observado.
	 */
	public void actualizar();
}
