package modelo;


import java.util.Date;


import excepciones.NoExisteElementoColisionadoException;

public class BalaAmetralladora extends Bala {
	private long ultimoTiempo;
	private final int FUERZA=20;
	private final double VELOCIDAD= 150;
	public BalaAmetralladora(){
		ultimoTiempo=new Date().getTime();
	}
	public void vivir(){
		long tiempoActual=new Date().getTime();
		int intervaloTiempo=(int)(tiempoActual-ultimoTiempo);
		ultimoTiempo=tiempoActual;
		double movimientoRestante=(VELOCIDAD*(double)intervaloTiempo/1000.0);

		while(movimientoRestante > 1.0){
			movimientoRestante--;
			avanzar();
			if(estaColisionado()){
				try {
					impactar(getColisionado());
				} catch (NoExisteElementoColisionadoException e) {
					e.printStackTrace();
				}
				return;
			}
		}
		avanzar(movimientoRestante);
		if(estaColisionado()){
			try {
				impactar(getColisionado());
			} catch (NoExisteElementoColisionadoException e) {
				e.printStackTrace();
			}
		}
	}
	public void impactar(ElementoRectangularSolido solido){
		if(solido != null)
			solido.recibirImpacto(FUERZA);
		destruir();
	}
	

}
