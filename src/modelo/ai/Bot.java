package modelo.ai;

import utils.Direccion;
import excepciones.NoExisteElementoColisionadoException;
import modelo.ElementoRectangular;
import modelo.ElementoRectangularSolido;
import modelo.Tanque;
import modelo.TanqueEnemigo;

public abstract class Bot {

	protected Tanque tanque;
	private ElementoRectangular objetivo;
	
	public Bot(Tanque tanque, ElementoRectangular objetivo) {
		this.tanque = tanque;
		this.setObjetivo(objetivo);
	}

	public abstract void actuar() ;

	protected void moverPor(Direccion primaria, Direccion secundaria) {
		ElementoRectangularSolido colisionPrimaria = null;
		boolean finDeEscenarioPorPrimaria=false;

		tanque.avanzarEnDireccion(primaria);
		if (tanque.estaColisionado()||tanque.fueraDeEscenario()) {
			// Bloqueado por primaria
			try {
				if(tanque.fueraDeEscenario())
					finDeEscenarioPorPrimaria=true;
				else
					colisionPrimaria = tanque.getColisionado();
			} catch (NoExisteElementoColisionadoException e1) {
				e1.printStackTrace();
			}
			tanque.retrocederEnDireccion(primaria);
			tanque.avanzarEnDireccion(secundaria);
			if (tanque.estaColisionado()||tanque.fueraDeEscenario()) {
				// Bloqueado por primaria y secundaria
				tanque.retrocederEnDireccion(secundaria);
				if(finDeEscenarioPorPrimaria){
					tanque.mover(secundaria);
					tanque.disparar();
				}else
				if (colisionPrimaria instanceof TanqueEnemigo) {
					// Enemigo en direccion primaria
					tanque.mover(secundaria);
					tanque.disparar();
				} else {
					// No hay enemigo en primaria
					tanque.mover(primaria);
					tanque.disparar();
				}
			} else {
				// Bloqueado primaria, secundaria libre
				tanque.retrocederEnDireccion(secundaria);
				tanque.mover(secundaria);
			}
		} else {
			// primaria libre
			tanque.retrocederEnDireccion(primaria);
			tanque.mover(primaria);
		}
	}
	
	protected void atacarEn(Direccion dir) {
		ElementoRectangularSolido visto = null;
		switch (dir.get()) {
		case Direccion.NORTE:
			visto = tanque.getSolidoVistoNorte();
			break;
		case Direccion.SUR:
			visto = tanque.getSolidoVistoSur();
			break;
		case Direccion.ESTE:
			visto = tanque.getSolidoVistoEste();
			break;
		case Direccion.OESTE:
			visto = tanque.getSolidoVistoOeste();
			break;
		}
		tanque.mover(dir);
		if (!(visto instanceof TanqueEnemigo))
			tanque.disparar();
	}

	public ElementoRectangular getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(ElementoRectangular objetivo) {
		this.objetivo = objetivo;
	}

}
