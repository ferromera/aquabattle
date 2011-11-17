package modelo.ai;

import utils.Direccion;
import excepciones.NoExisteElementoColisionadoException;
import modelo.ElementoRectangular;
import modelo.ElementoRectangularSolido;
import modelo.Tanque;
import modelo.TanqueEnemigo;

public abstract class Bot {

	protected Tanque tanque;
	protected ElementoRectangular objetivo;
	
	public Bot(Tanque tanque, ElementoRectangular objetivo) {
		this.tanque = tanque;
		this.objetivo = objetivo;
	}

	public abstract void actuar() ;

	protected void moverPor(Direccion primaria, Direccion secundaria) {
		ElementoRectangularSolido colisionPrimaria = null;
		tanque.avanzarEnDireccion(primaria);
		if (tanque.estaColisionado()) {
			// Bloqueado por primaria
			try {
				colisionPrimaria = tanque.getColisionado();
			} catch (NoExisteElementoColisionadoException e1) {
				e1.printStackTrace();
			}
			tanque.retrocederEnDireccion(primaria);
			tanque.avanzarEnDireccion(secundaria);
			if (tanque.estaColisionado()) {
				// Bloqueado por primaria y secundaria
				tanque.retrocederEnDireccion(secundaria);
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

}
