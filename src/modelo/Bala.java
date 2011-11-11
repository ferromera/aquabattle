package modelo;

import titiritero.ObjetoVivo;
import titiritero.Posicionable;
import utils.Direccion;

public abstract class Bala extends ElementoRectangularSolido implements ObjetoVivo, Posicionable{
	boolean destruida;
	
	public Bala(){
		setX(0);
		setY(0);
		destruida=false;
		Direccion dir= new Direccion();
		dir.setNorte();
		setOrientacion(dir);
	}

	public abstract void vivir();
	
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
	
}
