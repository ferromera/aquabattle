package pruebas.modelo.armamento;

import modelo.ElementoRectangularSolido;
import modelo.Escenario;
import modelo.Tanque;
import modelo.TanqueHeroe;
import modelo.armamento.Ametralladora;
import modelo.armamento.BalaAmetralladora;
import modelo.armamento.BalaCanion;
import modelo.armamento.Canion;
import modelo.armamento.Cohete;
import modelo.armamento.LanzaCohetes;
import java.util.Iterator;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;

import utils.Direccion;

import excepciones.NoExisteArmaSeleccionadaException;

public class PruebaArmas {

	Ametralladora ametralladora;
	Canion canion;
	LanzaCohetes lanzaCohetes;
	Tanque heroe;
	Escenario escenario;

	@Before
	public void setUp() {
		escenario = Escenario.clear();
		heroe = TanqueHeroe.getInstancia();
		heroe.setX(100.0);
		heroe.setY(200.0);
		heroe.setAlto(50);
		heroe.setAncho(50);
		heroe.setOrientacion(Direccion.Este());
		ametralladora = new Ametralladora(heroe); // Arma Nº1
		canion = new Canion(heroe);// Arma Nº2
		lanzaCohetes = new LanzaCohetes(heroe);// Arma Nº3
		try {
			heroe.seleccionarArma(ametralladora);
		} catch (NoExisteArmaSeleccionadaException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void dispararAmetralladora(){
		heroe.disparar();
		/*
		 * Se debe generar una bala de ametralladora
		 * en frente del tanque y centrada en y.
		 * 
		 */
		Iterator<ElementoRectangularSolido> it= escenario.getSolidos();
		BalaAmetralladora balaCreada=null;
		while(it.hasNext()){
			ElementoRectangularSolido solido = it.next();
			if(solido.getClass() == BalaAmetralladora.class){
				balaCreada=(BalaAmetralladora)solido;
				break;
			}
		}
		if(balaCreada==null){
			Assert.assertTrue(false);
			return;
		}
		double xBala= balaCreada.getX();
		double yBala= balaCreada.getY();
		Assert.assertTrue(xBala==151.0);
		Assert.assertTrue(yBala>219.99 && yBala<220.01);
		Assert.assertTrue(balaCreada.getOrientacion().get()== Direccion.ESTE);
	}

	@Test
	public void dispararCanionNorte() {
		try {
			heroe.seleccionarArma(canion);
		} catch (NoExisteArmaSeleccionadaException e) {
			e.printStackTrace();
		}
		heroe.orientarNorte();
		heroe.disparar();
		/*
		 * Se debe generar una bala de canion
		 * en frente del tanque y centrada en x.
		 * 
		 */
		Iterator<ElementoRectangularSolido> it= escenario.getSolidos();
		BalaCanion balaCreada=null;
		while(it.hasNext()){
			ElementoRectangularSolido solido = it.next();
			if(solido.getClass() == BalaCanion.class){
				balaCreada=(BalaCanion)solido;
				break;
			}
		}
		if(balaCreada==null){
			Assert.assertTrue(false);
			return;
		}
		double xBala= balaCreada.getX();
		double yBala= balaCreada.getY();
		//Ancho de bala= 30 -> xCentrada= 110
		Assert.assertTrue(xBala>109.99 && xBala<110.01);
		//xTanque= 200 , Alto de Bala = 30 -> yBala=169
		Assert.assertTrue(yBala==169.0);
		Assert.assertTrue(balaCreada.getOrientacion().get()== Direccion.NORTE);
	}

	@Test
	public void dispararLanzaCohetesSur() {
		try {
			heroe.seleccionarArma(lanzaCohetes);
		} catch (NoExisteArmaSeleccionadaException e) {
			e.printStackTrace();
		}
		heroe.orientarSur();
		heroe.disparar();
		/*
		 * Se debe generar un cohete
		 * en frente del tanque y centrada en x.
		 * 
		 */
		Iterator<ElementoRectangularSolido> it= escenario.getSolidos();
		Cohete balaCreada=null;
		while(it.hasNext()){
			ElementoRectangularSolido solido = it.next();
			if(solido.getClass() == Cohete.class){
				balaCreada=(Cohete)solido;
				break;
			}
		}
		if(balaCreada==null){
			Assert.assertTrue(false);
			return;
		}
		double xBala= balaCreada.getX();
		double yBala= balaCreada.getY();
		//Ancho de bala= 50 -> xCentrada= 100
		Assert.assertTrue(xBala>99.99 && xBala<100.01);
		//xTanque= 200 , Alto de Bala = 50 -> yBala=251
		Assert.assertTrue(yBala==251.0);
		Assert.assertTrue(balaCreada.getOrientacion().get()== Direccion.SUR);
	}

}
