package misc;

import modelo.Base;
import modelo.Escenario;
import modelo.Explosion;
import modelo.ParedConcreto;
import modelo.ParedMetal;
import modelo.TanqueHeroe;
import modelo.armamento.BalaAmetralladora;
import modelo.armamento.BalaCanion;
import modelo.armamento.Cohete;

public class FabricaElementos {

	
	public static BalaAmetralladora crearBalaAmetralladora(){
		BalaAmetralladora bala = new BalaAmetralladora();
		Escenario.getActual().agregarObjeto(bala);
		Escenario.getActual().agregarObjetoSolido(bala);
		Escenario.getActual().agregarObjetoVivo(bala);
		return bala;
	}
	public static BalaCanion crearBalaCanion(){
		BalaCanion bala = new BalaCanion();
		Escenario.getActual().agregarObjeto(bala);
		Escenario.getActual().agregarObjetoSolido(bala);
		Escenario.getActual().agregarObjetoVivo(bala);
		return bala;
	}
	public static Cohete crearCohete(){
		Cohete cohete = new Cohete();
		Escenario.getActual().agregarObjeto(cohete);
		Escenario.getActual().agregarObjetoSolido(cohete);
		Escenario.getActual().agregarObjetoVivo(cohete);
		return cohete;
	}
	public static Explosion crearExplosion(double x,double y){
		Explosion explosion = new Explosion(x,y);
		Escenario.getActual().agregarObjeto(explosion);
		return explosion;
	}
	public static ParedConcreto crearParedConcreto(double x,double y){
		ParedConcreto pared = new ParedConcreto(x, y);
		Escenario.getActual().agregarObjeto(pared);
		Escenario.getActual().agregarObjetoSolido(pared);
		return pared;
	}
	public static ParedMetal crearParedMetal(double x,double y){
		ParedMetal pared = new ParedMetal(x, y);
		Escenario.getActual().agregarObjeto(pared);
		Escenario.getActual().agregarObjetoSolido(pared);
		return pared;
	}
	public static TanqueHeroe crearTanqueHeroe(){
		TanqueHeroe tanque = TanqueHeroe.nuevaInstancia();
		Escenario.getActual().agregarObjeto(tanque);
		Escenario.getActual().agregarObjetoSolido(tanque);
		Escenario.getActual().agregarObjetoVivo(tanque);
		
		return tanque;
	}
	public static Base crearBase(double x,double y){
		Base base = new Base(x, y);
		Escenario.getActual().agregarObjeto(base);
		Escenario.getActual().agregarObjetoSolido(base);
		return base;
	}
	public static BonusVida crearBonusVida(){
		BonusVida bonus= new BonusVida();
		bonus.posicionar();
		Escenario.getActual().agregarObjeto(bonus);
		Escenario.getActual().agregarObjetoVivo(bonus);
		return bonus;
	}
	public static BonusVida crearBonusAtaque(){
		BonusAtaque bonus= new BonusAtaque();
		bonus.posicionar();
		Escenario.getActual().agregarObjeto(bonus);
		Escenario.getActual().agregarObjetoVivo(bonus);
		return bonus;
	}
}
