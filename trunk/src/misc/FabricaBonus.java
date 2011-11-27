package misc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public abstract class FabricaBonus implements ActionListener{
	private boolean produciendo;
	private SorteadorBinario sorteadorBinario;
	private int intervaloSorteo; //en mseg
	private Timer timer;
	
	public FabricaBonus(SorteadorBinario sorteador){
		produciendo=false;
		intervaloSorteo=1000;
		sorteadorBinario=sorteador;
		timer = new Timer(intervaloSorteo, this);
		timer.start();
	}

	public void comenzarProduccion(){
		if(produciendo)
			return;
		produciendo=true;
		System.out.println(this+"Se comenzo la produccion");
	}
	public void detenerProduccion(){
		produciendo=false;
		System.out.println(this+"Se detuvo la produccion");
	}
	public abstract void crearBonus();
	
	public void actionPerformed(ActionEvent e){
		if(produciendo==false)
			return;
		if(sorteadorBinario.sortear())
			crearBonus();
		System.out.println(this+" " +produciendo);
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
