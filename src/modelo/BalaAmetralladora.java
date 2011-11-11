package modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class BalaAmetralladora extends Bala {
	private long ultimoTiempo;
	private ElementoRectangularSolido elemColisionado;
	private final int FUERZA=20;
	private final int VELOCIDAD= 150;
	public BalaAmetralladora(){
		ultimoTiempo=new Date().getTime();
		elemColisionado=null;
	}
	public void vivir(){
		long tiempoActual=new Date().getTime();
		int intervaloTiempo=(int)(tiempoActual-ultimoTiempo);
		ultimoTiempo=tiempoActual;
		int movimientoRestante=(VELOCIDAD*intervaloTiempo)/1000;;
		while(movimientoRestante != 0){
			avanzar();
			if(colisionado()){
				impactar();
				return;
			}
		}
	}
	public void impactar(){
		if(elemColisionado != null)
			elemColisionado.recibirImpacto(FUERZA);
		destruir();
	}
	public boolean colisionado(){
		/*escenario = Escenario.getActual();
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
		}*/
		return false;
	}

}
