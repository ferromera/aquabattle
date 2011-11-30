package pantallas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import titiritero.ControladorJuego;
import vista.pantallas.VistaFinDelJuego;

public class FinDelJuego extends Pantalla {
	
	private static final int TIEMPO_EN_PANTALLA = 3000;
	static FinDelJuego instancia;
	
	
	public FinDelJuego(){
	}
	
	public static FinDelJuego getInstancia(){
		if(instancia == null){
			instancia = new FinDelJuego();
		}
		
		return instancia;
	}
	

	@Override
	public void vivir() {
	
		
	}




	@Override
	public void convertirEnActual() {
		ControladorJuego.getInstancia().agregarDibujable(VistaFinDelJuego.getInstancia());
		ControladorJuego.getInstancia().agregarObjetoVivo(this);
		Timer timer =new Timer(TIEMPO_EN_PANTALLA,new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(PantallaJuego.getInstancia().getPuntos()>PantallaPuntajesAltos.getInstancia().getMenorPuntaje())
					PantallaActual.getInstancia().cambiarA(PantallaGuardarPuntaje.getInstancia());
				else
					PantallaActual.getInstancia().cambiarA(MenuPrincipal.getInstance());
			}
		});
		timer.setRepeats(false);
		timer.start();
	
		
	}

	@Override
	public void dejarDeSerActual() {
		pausar();
		ControladorJuego.getInstancia().removerDibujable(VistaFinDelJuego.getInstancia());
		ControladorJuego.getInstancia().removerObjetoVivo(this);
	}

	@Override
	public void pausar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reanudar() {
		// TODO Auto-generated method stub
		
	}


}
