package modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class BalaAmetralladora extends Bala {
	private long ultimoTiempo;
	private ElementoRectangularSolido elemColisionado;
	private final int FUERZA=20;
	private final double VELOCIDAD= 150;
	public BalaAmetralladora(){
		ultimoTiempo=new Date().getTime();
		elemColisionado=null;
	}
	public void vivir(){
		long tiempoActual=new Date().getTime();
		int intervaloTiempo=(int)(tiempoActual-ultimoTiempo);
		ultimoTiempo=tiempoActual;
		double movimientoRestante=(VELOCIDAD*(double)intervaloTiempo/1000.0);

		while(movimientoRestante > 1.0){
			movimientoRestante--;
			avanzar();
			if(colisionado()){
				impactar();
				return;
			}
		}
		avanzar(movimientoRestante);
		if(colisionado())
			impactar();
	}
	public void impactar(){
		if(elemColisionado != null)
			elemColisionado.recibirImpacto(FUERZA);
		destruir();
	}
	public boolean colisionado(){
		Escenario escenario = Escenario.getActual();
		ArrayList<ElementoRectangularSolido> solidos= escenario.getSolidos();
		Iterator<ElementoRectangularSolido> it = solidos.iterator();
		while(it.hasNext()){
			ElementoRectangularSolido solido= it.next();
			if( solido.equals(this))
				continue;
			if(superpuestoCon(solido)){
				elemColisionado=solido;
				return true;
			}
		}
		return false;
	}

}
