package pruebas.modelo.armamento;

import utils.Direccion;
import modelo.armamento.BalaAmetralladora;
import modelo.armamento.BalaCanion;
import modelo.armamento.Cohete;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;

public class PruebaBalas {
	BalaAmetralladora balaAmetralladora;
	BalaCanion balaCanion;
	Cohete cohete;
	
	@Before
	public void setUp() {

		balaAmetralladora = new BalaAmetralladora();
		balaAmetralladora.setX(100.0);
		balaAmetralladora.setY(200.0);
		balaAmetralladora.setOrientacion(Direccion.Este());

		balaCanion = new BalaCanion();
		balaCanion.setX(200.0);
		balaCanion.setY(300.0);
		balaCanion.setOrientacion(Direccion.Norte());
		cohete = new Cohete();
		cohete.setX(400.0);
		cohete.setY(0.0);
		cohete.setOrientacion(Direccion.Sur());

	}
	
	@Test
	public void vivirUnSegBalaAmetralladora() {

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}// paso 1 segundo.
		
		balaAmetralladora.vivir();
		double x= balaAmetralladora.getX();
		double y= balaAmetralladora.getY();
	
		/*
		 * X : posicion inicial = 100.0, velocidadX = 150 pixel/seg
		 *  => posicion luego de 1 seg = 250.0
		 */
		Assert.assertTrue(x<250.01 && x>249.99);
		/*
		 * Y : posicion inicial = 200.0, velocidadY = 0 pixel/seg
		 *  => posicion luego de 1 seg = 200.0
		 */
		Assert.assertTrue(y<200.01 && y>199.99);
		
	}
	@Test
	public void vivirVeinteMiliSegBalaAmetralladora() {

		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}// paso 20 mseg.
		
		balaAmetralladora.vivir();
		double x= balaAmetralladora.getX();
		double y= balaAmetralladora.getY();
		
		/*
		 * X : posicion inicial = 100.0, velocidadX = 150 pixel/seg
		 *  => posicion luego de 20 mseg = 103.0
		 */
		Assert.assertTrue(x<103.01 && x>102.99);
		/*
		 * Y : posicion inicial = 200.0, velocidadY = 0 pixel/seg
		 *  => posicion luego de 20 mseg = 200.0
		 */
		Assert.assertTrue(y<200.01 && y>199.99);
		
	}
	@Test
	public void vivirNoventaMiliSegBalaAmetralladora() {

		try {
			Thread.sleep(30);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}// paso 30 mseg.
		balaAmetralladora.vivir();
		try {
			Thread.sleep(30);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}// paso 30 mseg.
		balaAmetralladora.vivir();
		try {
			Thread.sleep(30);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}// paso 30 mseg.
		balaAmetralladora.vivir();
		
		double x= balaAmetralladora.getX();
		double y= balaAmetralladora.getY();
		
		/*
		 * X : posicion inicial = 100.0, velocidadX = 150 pixel/seg
		 *  => posicion luego de 90 mseg = 113.5
		 */
		Assert.assertTrue(x<113.51 && x>113.49);
		/*
		 * Y : posicion inicial = 200.0, velocidadY = 0 pixel/seg
		 *  => posicion luego de 90 mseg = 200.0
		 */
		Assert.assertTrue(y<200.01 && y>199.99);
	}
	@Test
	public void vivir90MiliSegNorteBalaAmetralladora(){
		balaAmetralladora.setOrientacion(Direccion.Norte());
		try {
			Thread.sleep(30);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}// paso 30 mseg.
		balaAmetralladora.vivir();
		try {
			Thread.sleep(30);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}// paso 30 mseg.
		balaAmetralladora.vivir();
		try {
			Thread.sleep(30);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}// paso 30 mseg.
		balaAmetralladora.vivir();
		double x= balaAmetralladora.getX();
		double y= balaAmetralladora.getY();
		
		/*
		 * X : posicion inicial = 100.0, velocidadX = 0 pixel/seg
		 *  => posicion luego de 90 mseg = 100.0
		 */
		Assert.assertTrue(x<100.01 && x>99.99);
		/*
		 * Y : posicion inicial = 200.0, velocidadY = -150.0 pixel/seg
		 *  => posicion luego de 90 mseg = 186.5
		 */
		Assert.assertTrue(y<186.51 && y>186.49);
	}
	@Test
	public void vivirUnSegBalaCanion() {

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}// paso 1 segundo.
		
		balaCanion.vivir();
		double x= balaCanion.getX();
		double y= balaCanion.getY();
	
		/*
		 * X : posicion inicial = 200.0, velocidadX = 0 pixel/seg
		 *  => posicion luego de 1 seg = 200.0
		 */
		Assert.assertTrue(x<200.01 && x>199.99);
		/*
		 * Y : posicion inicial = 300.0, velocidadY = -200 pixel/seg
		 *  => posicion luego de 1 seg = 100.0
		 */
		Assert.assertTrue(y<100.01 && y>99.99);
		
	}
	@Test
	public void vivirVeinteMiliSegBalaCanion() {

		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}// paso 20 mseg.
		
		balaCanion.vivir();
		double x= balaCanion.getX();
		double y= balaCanion.getY();
		
		/*
		 * X : posicion inicial = 200.0, velocidadX = 0 pixel/seg
		 *  => posicion luego de 20 mseg = 200.0
		 */
		Assert.assertTrue(x<200.01 && x>199.99);
		/*
		 * Y : posicion inicial = 300.0, velocidadY = -200 pixel/seg
		 *  => posicion luego de 20 mseg = 296.0
		 */
		Assert.assertTrue(y<296.01 && y>295.99);
		
	}
	@Test
	public void vivirNoventaMiliSegBalaCalion() {

		try {
			Thread.sleep(30);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}// paso 30 mseg.
		balaCanion.vivir();
		try {
			Thread.sleep(30);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}// paso 30 mseg.
		balaCanion.vivir();
		try {
			Thread.sleep(30);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}// paso 30 mseg.
		balaCanion.vivir();
		
		double x= balaCanion.getX();
		double y= balaCanion.getY();
		
		/*
		 * X : posicion inicial = 200.0, velocidadX = 0 pixel/seg
		 *  => posicion luego de 90 mseg = 200.0
		 */
		Assert.assertTrue(x<200.01 && x>199.99);
		/*
		 * Y : posicion inicial = 300.0, velocidadY = -200 pixel/seg
		 *  => posicion luego de 90 mseg = 282.0
		 */
		Assert.assertTrue(y<282.01 && y>281.99);
	}
	@Test
	public void vivir90MiliSegOesteBalaCanion(){
		balaCanion.setOrientacion(Direccion.Oeste());
		try {
			Thread.sleep(30);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}// paso 30 mseg.
		balaCanion.vivir();
		try {
			Thread.sleep(30);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}// paso 30 mseg.
		balaCanion.vivir();
		try {
			Thread.sleep(30);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}// paso 30 mseg.
		balaCanion.vivir();
		double x= balaCanion.getX();
		double y= balaCanion.getY();
		
		/*
		 * X : posicion inicial = 200.0, velocidadX = -200 pixel/seg
		 *  => posicion luego de 90 mseg = 182.0
		 */
		Assert.assertTrue(x<182.01 && x>181.99);
		/*
		 * Y : posicion inicial = 300.0, velocidadY = 0.0 pixel/seg
		 *  => posicion luego de 90 mseg = 300.0
		 */
		Assert.assertTrue(y<300.01 && y>299.99);
	}
	
	public void vivirUnSegCohete() {

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}// paso 1 segundo.
		
		cohete.vivir();
		double x= cohete.getX();
		double y= cohete.getY();
	
		/*
		 * X : posicion inicial = 400.0, velocidadX = 0 pixel/seg
		 *  => posicion luego de 1 seg = 400.0
		 */
		Assert.assertTrue(x<400.01 && x>399.99);
		/*
		 * Y : posicion inicial = 0.0, velocidadY = 300 pixel/seg
		 *  => posicion luego de 1 seg = 300.0
		 */
		Assert.assertTrue(y<300.01 && y>299.99);
		
	}
	@Test
	public void vivirVeinteMiliSegCohete() {

		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}// paso 20 mseg.
		
		cohete.vivir();
		double x= cohete.getX();
		double y= cohete.getY();
		
		/*
		 * X : posicion inicial = 400.0, velocidadX = 0 pixel/seg
		 *  => posicion luego de 20 mseg = 400.0
		 */
		Assert.assertTrue(x<400.01 && x>399.99);
		/*
		 * Y : posicion inicial = 0.0, velocidadY = 300 pixel/seg
		 *  => posicion luego de 20 mseg = 6.0
		 */
		Assert.assertTrue(y<6.01 && y>5.99);
		
	}
	@Test
	public void vivirNoventaMiliSegCohete() {

		try {
			Thread.sleep(30);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}// paso 30 mseg.
		cohete.vivir();
		try {
			Thread.sleep(30);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}// paso 30 mseg.
		cohete.vivir();
		try {
			Thread.sleep(30);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}// paso 30 mseg.
		cohete.vivir();
		
		double x= cohete.getX();
		double y= cohete.getY();
		
		/*
		 * X : posicion inicial = 400.0, velocidadX = 0 pixel/seg
		 *  => posicion luego de 90 mseg = 400.0
		 */
		Assert.assertTrue(x<400.01 && x>399.99);
		/*
		 * Y : posicion inicial = 0.0, velocidadY = 300 pixel/seg
		 *  => posicion luego de 90 mseg = 27.0
		 */
		Assert.assertTrue(y<27.01 && y>26.99);
	}
	@Test
	public void vivir90MiliSegOesteCohete(){
		cohete.setOrientacion(Direccion.Oeste());
		try {
			Thread.sleep(30);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}// paso 30 mseg.
		cohete.vivir();
		try {
			Thread.sleep(30);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}// paso 30 mseg.
		cohete.vivir();
		try {
			Thread.sleep(30);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}// paso 30 mseg.
		cohete.vivir();
		double x= cohete.getX();
		double y= cohete.getY();
		
		/*
		 * X : posicion inicial = 400.0, velocidadX = -300 pixel/seg
		 *  => posicion luego de 90 mseg = 373.0
		 */
		Assert.assertTrue(x<373.01 && x>372.99);
		/*
		 * Y : posicion inicial = 0.0, velocidadY = 0.0 pixel/seg
		 *  => posicion luego de 90 mseg = 0.0
		 */
		Assert.assertTrue(y<0.01 && y>-0.01);
	}
	
}
