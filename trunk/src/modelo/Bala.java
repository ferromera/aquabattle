package modelo;

import java.util.Date;

import excepciones.NoExisteElementoColisionadoException;
import titiritero.ObjetoVivo;
import titiritero.Posicionable;
import utils.Direccion;

public abstract class Bala extends ElementoRectangularSolido implements ObjetoVivo, Posicionable{
	boolean destruida;
	
	public Bala(){
		setX(0.0);
		setY(0.0);
		destruida=false;
		fuerza=10;
		velocidad=150.0;
		Direccion dir= new Direccion();
		dir.setNorte();
		setOrientacion(dir);
	}

	private long ultimoTiempo;
	protected int fuerza;
	protected double velocidad;
	
	public void vivir(){
		long tiempoActual=new Date().getTime();
		int intervaloTiempo=(int)(tiempoActual-ultimoTiempo);
		ultimoTiempo=tiempoActual;
		double movimientoRestante=(velocidad*(double)intervaloTiempo/1000.0);

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
			solido.recibirImpacto(fuerza);
		destruir();
	}
	
	public void recibirImpacto(int fuerza){
		destruir();
	}
	protected void destruir(){
		/*FabricaElementos.crearExplosion(posX,posY);
		escenario= Escenario.getActual();
		escenario.borrar(this);
		destruida=true;
		notificar();*/
	}
	public boolean estaDestruida(){
		return destruida;
	}
	
	public int getResistencia(){
		return 1;
	}
	
	
}
