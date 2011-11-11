package misc;


public interface Observable {
	public void adscribir(Observador obs);
	public void quitar(Observador obs);
	public void notificar();
}
