package misc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public abstract class FabricaBonus implements ActionListener{
	private boolean produciendo;
	private SorteadorBinario sorteadorBinario;
	private int intervaloSorteo; //en mseg
	
	public FabricaBonus(SorteadorBinario sorteador){
		produciendo=false;
		intervaloSorteo=100;
		sorteadorBinario=sorteador;
	}

	public void comenzarProduccion(){
		produciendo=true;
		Timer timer = new Timer(intervaloSorteo, this);
		timer.start();
	}
	public void detenerProduccion(){
		produciendo=false;
	}
	public abstract void crearBonus();
	
	public void actionPerformed(ActionEvent e){
		if(!produciendo)
			return;
		if(sorteadorBinario.sortear())
			crearBonus();
		Timer timer = new Timer(intervaloSorteo, this);
		timer.start();
	}
	public int getIntervaloSorteo() {
		return intervaloSorteo;
	}
	public void setIntervaloSorteo(int intervaloSorteo){
		this.intervaloSorteo = intervaloSorteo;
	}
	public void setSorteadorBinario(SorteadorBinario sorteadorBinario) {
		this.sorteadorBinario = sorteadorBinario;
	}
	public boolean estaProduciendo(){
		return produciendo;
	}
}
