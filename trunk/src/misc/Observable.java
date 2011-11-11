package misc;


public interface Observable {
	/*
	 * adscribir: debe agregar obs a una 
	 * coleccion de observadores asociados con
	 * el objeto observable.
	 */
	public void adscribir(Observador obs);
	/*
	 * quitar: debe remover obs de la 
	 * coleccion de observadores asociados con
	 * el objeto observable.
	 */
	public void quitar(Observador obs);
	/*
	 * notificar: debe actualizar todos los observadores
	 * asociados con el objeto observable.
	 */
	public void notificar();
}
