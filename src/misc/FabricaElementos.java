package misc;

import org.w3c.dom.Element;

import pantallas.Boton;
import pantallas.Vida;
import controlador.ControladorPantallaJuego;
import controlador.ControladorTanqueHeroe;
import vista.VistaArmaTiradaCanion;
import vista.VistaArmaTiradaLanzaCohetes;
import vista.VistaBalaAmetralladora;
import vista.VistaBalaCanion;
import vista.VistaBase;
import vista.VistaBonusAtaque;
import vista.VistaBonusVida;
import vista.VistaCohete;
import vista.VistaEscenario;
import vista.VistaExplosion;
import vista.VistaParedConcreto;
import vista.VistaParedMetal;
import vista.VistaTanqueGrizzly;
import vista.VistaTanqueHeroe;
import vista.VistaTanqueIFV;
import vista.VistaTanqueMirage;
import vista.pantallas.VistaBoton;
import vista.pantallas.VistaMenuPrincipal;
import vista.pantallas.VistaPantallaJuego;
import vista.pantallas.VistaVida;
import excepciones.NoSePudoPosicionarException;
import excepciones.YaExisteBaseException;
import modelo.ArmaTiradaCanion;
import modelo.ArmaTiradaLanzaCohetes;
import modelo.Base;
import modelo.BonusAtaque;
import modelo.BonusVida;
import modelo.Escenario;
import modelo.Explosion;
import modelo.ParedConcreto;
import modelo.ParedMetal;
import modelo.PosicionadorAleatorioStd;
import modelo.TanqueEnemigo;
import modelo.TanqueGrizzly;
import modelo.TanqueHeroe;
import modelo.TanqueIFV;
import modelo.TanqueMirage;
import modelo.armamento.BalaAmetralladora;
import modelo.armamento.BalaCanion;
import modelo.armamento.Cohete;
import titiritero.ControladorJuego;

public class FabricaElementos {

	public static BalaAmetralladora crearBalaAmetralladora() {
		BalaAmetralladora bala = new BalaAmetralladora();
		Escenario.getActual().agregarObjeto(bala);
		Escenario.getActual().agregarObjetoSolido(bala);
		Escenario.getActual().agregarObjetoVivo(bala);
		VistaBalaAmetralladora vista = new VistaBalaAmetralladora(bala);
		VistaEscenario.getInstancia().agregarVista(vista);
		return bala;
	}

	public static BalaCanion crearBalaCanion() {
		BalaCanion bala = new BalaCanion();
		Escenario.getActual().agregarObjeto(bala);
		Escenario.getActual().agregarObjetoSolido(bala);
		Escenario.getActual().agregarObjetoVivo(bala);
		VistaBalaCanion vista = new VistaBalaCanion(bala);
		VistaEscenario.getInstancia().agregarVista(vista);
		return bala;
	}

	public static Cohete crearCohete() {
		Cohete cohete = new Cohete();
		Escenario.getActual().agregarObjeto(cohete);
		Escenario.getActual().agregarObjetoSolido(cohete);
		Escenario.getActual().agregarObjetoVivo(cohete);
		VistaCohete vista = new VistaCohete(cohete);
		VistaEscenario.getInstancia().agregarVista(vista);
		return cohete;
	}
	public static Explosion crearExplosion() {
		Explosion explosion = new Explosion();
		Escenario.getActual().agregarObjeto(explosion);
		Escenario.getActual().agregarObjetoVivo(explosion);
		VistaExplosion vista=new VistaExplosion(explosion);
		VistaEscenario.getInstancia().agregarVista(vista);
		return explosion;
	}
	public static Explosion crearExplosion(double x, double y) {
		Explosion explosion = new Explosion(x, y);
		Escenario.getActual().agregarObjeto(explosion);
		return explosion;
	}

	public static ParedConcreto crearParedConcreto(double x, double y) throws NoSePudoPosicionarException {
		ParedConcreto pared = new ParedConcreto(x, y);
		pared.posicionar();
		Escenario.getActual().agregarObjeto(pared);
		Escenario.getActual().agregarObjetoSolido(pared);
		VistaParedConcreto vista = new VistaParedConcreto(pared);
		VistaEscenario.getInstancia().agregarVista(vista);
		return pared;
	}

	public static ParedMetal crearParedMetal(double x, double y) throws NoSePudoPosicionarException {
		ParedMetal pared = new ParedMetal(x, y);
		pared.posicionar();
		Escenario.getActual().agregarObjeto(pared);
		Escenario.getActual().agregarObjetoSolido(pared);
		VistaParedMetal vista = new VistaParedMetal(pared);
		VistaEscenario.getInstancia().agregarVista(vista);
		return pared;
	}

	public static TanqueHeroe crearTanqueHeroe() throws NoSePudoPosicionarException {
		TanqueHeroe tanque = TanqueHeroe.nuevaInstancia();
		tanque.posicionar();
		Escenario.getActual().agregarObjeto(tanque);
		Escenario.getActual().agregarObjetoSolido(tanque);
		Escenario.getActual().agregarObjetoVivo(tanque);
		VistaTanqueHeroe vista = new VistaTanqueHeroe(tanque);
		VistaEscenario.getInstancia().agregarVista(vista);
		ControladorPantallaJuego.getInstancia().actualizar();

		return tanque;
	}

	public static TanqueHeroe crearTanqueHeroe(double x, double y) throws NoSePudoPosicionarException {
		TanqueHeroe tanque = TanqueHeroe.getInstancia();
		tanque.setX(x);
		tanque.setY(y);
		tanque.posicionar();
		Escenario.getActual().agregarObjeto(tanque);
		Escenario.getActual().agregarObjetoSolido(tanque);
		Escenario.getActual().agregarObjetoVivo(tanque);
		VistaTanqueHeroe vista = new VistaTanqueHeroe(tanque);
		VistaEscenario.getInstancia().agregarVista(vista);
		ControladorPantallaJuego.getInstancia().actualizar();

		return tanque;
	}
	public static TanqueHeroe crearNuevoTanqueHeroe(double x, double y) throws NoSePudoPosicionarException {
		TanqueHeroe tanque = TanqueHeroe.nuevaInstancia();
		tanque.setX(x);
		tanque.setY(y);
		tanque.posicionar();
		Escenario.getActual().agregarObjeto(tanque);
		Escenario.getActual().agregarObjetoSolido(tanque);
		Escenario.getActual().agregarObjetoVivo(tanque);
		VistaTanqueHeroe vista = new VistaTanqueHeroe(tanque);
		VistaEscenario.getInstancia().agregarVista(vista);
		ControladorPantallaJuego.getInstancia().actualizar();

		return tanque;
		
	}

	public static Base crearBase(double x, double y)
			throws YaExisteBaseException, NoSePudoPosicionarException {
		Base base = new Base(x, y);
		base.posicionar();
		Escenario.getActual().agregarBase(base);
		VistaBase vista = new VistaBase(base);
		VistaEscenario.getInstancia().agregarVista(vista);
		return base;
	}

	public static BonusVida crearBonusVida() throws NoSePudoPosicionarException {
		BonusVida bonus = new BonusVida(new PosicionadorAleatorioStd());
		Escenario.getActual().agregarObjeto(bonus);
		Escenario.getActual().agregarObjetoVivo(bonus);
		VistaBonusVida vista = new VistaBonusVida(bonus);
		VistaEscenario.getInstancia().agregarVista(vista);
		return bonus;
	}

	public static BonusAtaque crearBonusAtaque()
			throws NoSePudoPosicionarException {
		BonusAtaque bonus = new BonusAtaque(new PosicionadorAleatorioStd());
		Escenario.getActual().agregarObjeto(bonus);
		Escenario.getActual().agregarObjetoVivo(bonus);
		VistaBonusAtaque vista = new VistaBonusAtaque(bonus);
		VistaEscenario.getInstancia().agregarVista(vista);

		return bonus;
	}

	public static void insertarTanqueEnemigo(TanqueEnemigo tanque) throws NoSePudoPosicionarException {
		if (tanque.getClass() == TanqueGrizzly.class)
			insertarTanqueGrizzly((TanqueGrizzly) tanque);
		else if (tanque.getClass() == TanqueIFV.class)
			insertarTanqueIFV((TanqueIFV) tanque);
		else if (tanque.getClass() == TanqueMirage.class)
			insertarTanqueMirage((TanqueMirage) tanque);

	}

	public static void insertarTanqueMirage(TanqueMirage tanque) throws NoSePudoPosicionarException {
		tanque.posicionar();
		tanque.reanudar();
		Escenario.getActual().agregarObjeto(tanque);
		Escenario.getActual().agregarObjetoSolido(tanque);
		Escenario.getActual().agregarObjetoVivo(tanque);
		VistaTanqueMirage vista =new VistaTanqueMirage(tanque);
		VistaEscenario.getInstancia().agregarVista(vista);
		
	}

	public static void insertarTanqueIFV(TanqueIFV tanque) throws NoSePudoPosicionarException {
		tanque.posicionar();
		tanque.reanudar();
		Escenario.getActual().agregarObjeto(tanque);
		Escenario.getActual().agregarObjetoSolido(tanque);
		Escenario.getActual().agregarObjetoVivo(tanque);
		VistaTanqueIFV vista =new VistaTanqueIFV(tanque);
		VistaEscenario.getInstancia().agregarVista(vista);
		
	}

	public static void insertarTanqueGrizzly(TanqueGrizzly tanque) throws NoSePudoPosicionarException {
		tanque.posicionar();
		tanque.reanudar();
		Escenario.getActual().agregarObjeto(tanque);
		Escenario.getActual().agregarObjetoSolido(tanque);
		Escenario.getActual().agregarObjetoVivo(tanque);
		VistaTanqueGrizzly vista =new VistaTanqueGrizzly(tanque);
		VistaEscenario.getInstancia().agregarVista(vista);
		
	}
	
	
	public static ArmaTiradaCanion crearArmaTiradaCanion(double posX,double posY)
			throws NoSePudoPosicionarException {
		ArmaTiradaCanion arma = new ArmaTiradaCanion(posX,posY);
		
		Escenario.getActual().agregarObjeto(arma);
		Escenario.getActual().agregarObjetoVivo(arma);
		VistaArmaTiradaCanion vista = new VistaArmaTiradaCanion(arma);
		VistaEscenario.getInstancia().agregarVista(vista);

		return arma;
	}
	
	public static ArmaTiradaLanzaCohetes crearArmaTiradaLanzaCohetes(double posX,double posY)
			throws NoSePudoPosicionarException {
		ArmaTiradaLanzaCohetes arma = new ArmaTiradaLanzaCohetes(posX,posY);
		Escenario.getActual().agregarObjeto(arma);
		Escenario.getActual().agregarObjetoVivo(arma);
		VistaArmaTiradaLanzaCohetes vista = new VistaArmaTiradaLanzaCohetes(arma);
		VistaEscenario.getInstancia().agregarVista(vista);

		return arma;
	}

	public static Vida crearVida(int x, int y) {
		Vida vida=new Vida(x,y);
		VistaVida vista=new VistaVida(vida);
		VistaPantallaJuego.getInstancia().agregarVistaVida(vista);
		return vida;
	}
	
	public static Boton crearBoton(int x, int y) {
		Boton boton = new Boton(x,y);
		VistaBoton vista = new VistaBoton(boton);
		VistaMenuPrincipal.getInstancia().agregarVistaBoton(vista);
		return boton;
	}



}
