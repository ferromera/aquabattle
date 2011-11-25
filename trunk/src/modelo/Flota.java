package modelo;

import java.util.ArrayList;
import java.util.Iterator;

import excepciones.NoSePudoPosicionarException;

import misc.FabricaElementos;

public class Flota {
	private ArrayList<TanqueEnemigo> tanques;
	
	public void agregar(){
		Iterator<TanqueEnemigo> it= tanques.iterator();
		while(it.hasNext()){
			try {
				FabricaElementos.insertarTanqueEnemigo(it.next());
			} catch (NoSePudoPosicionarException e) {
				System.err.println("No se pudo posicionar tanque de la flota");
			}
		}
	}
	
	public void agregarTanque(TanqueEnemigo tanque){
		tanques.add(tanque);
	}
	public void borrarTanque(TanqueEnemigo tanque){
		tanques.remove(tanque);
	}

}
