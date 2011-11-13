package modelo.armamento;

import java.util.Date;

import misc.FabricaElementos;
import modelo.ElementoRectangularSolido;
import modelo.Escenario;

import excepciones.NoExisteElementoColisionadoException;
import titiritero.ObjetoVivo;
import titiritero.Posicionable;
import utils.Direccion;

public abstract class Bala extends ElementoRectangularSolido implements ObjetoVivo{
	boolean destruida;
	
	public Bala(){
		setX(0.0);
		setY(0.0);
		destruida=false;
		fuerza=10;
		velocidad=150.0;
		setOrientacion(Direccion.Norte());
		ultimoTiempo=new Date().getTime();
		
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
	
	protected void impactar(ElementoRectangularSolido solido){
		if(solido != null)
			solido.recibirImpacto(fuerza);
		destruir();
	}
	
	public void recibirImpacto(int fuerza){
		destruir();
	}
	protected void destruir(){
		FabricaElementos.crearExplosion(getX(),getY());
		Escenario.getActual().borrarSolido(this);
		Escenario.getActual().borrarObjetoVivo(this);
		destruida=true;
		notificar();
	}
	public boolean estaDestruida(){
		return destruida;
	}
	
	public int getResistencia(){
		return 1;
	}
	
	
}
