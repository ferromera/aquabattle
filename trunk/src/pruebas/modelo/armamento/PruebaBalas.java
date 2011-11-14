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
		//inicializa el tiempo
		balaAmetralladora.vivir();
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
		Assert.assertTrue(x<=250.1 && x>=249.9);
		/*
		 * Y : posicion inicial = 200.0, velocidadY = 0 pixel/seg
		 *  => posicion luego de 1 seg = 200.0
		 */
		Assert.assertTrue(y<=200.1 && y>=199.9);
		
	}
	@Test
	public void vivirVeinteMiliSegBalaAmetralladora() {
		//inicializa el tiempo
		balaAmetralladora.vivir();
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
		Assert.assertTrue(x<=103.1 && x>=102.9);
		/*
		 * Y : posicion inicial = 200.0, velocidadY = 0 pixel/seg
		 *  => posicion luego de 20 mseg = 200.0
		 */
		Assert.assertTrue(y<=200.1 && y>=199.9);
		
	}
	@Test
	public void vivirNoventaMiliSegBalaAmetralladora() {
		//inicializa el tiempo
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
		Assert.assertTrue(x<=113.6 && x>=113.4);
		/*
		 * Y : posicion inicial = 200.0, velocidadY = 0 pixel/seg
		 *  => posicion luego de 90 mseg = 200.0
		 */
		Assert.assertTrue(y<=200.1 && y>=199.9);
	}
	@Test
	public void vivir90MiliSegNorteBalaAmetralladora(){
		balaAmetralladora.setOrientacion(Direccion.Norte());
		//inicializa el tiempo
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
		Assert.assertTrue(x<=100.1 && x>=99.9);
		/*
		 * Y : posicion inicial = 200.0, velocidadY = -150.0 pixel/seg
		 *  => posicion luego de 90 mseg = 186.5
		 */
		Assert.assertTrue(y<=186.6 && y>=186.4);
	}
	@Test
	public void vivirUnSegBalaCanion() {
		//inicializa el tiempo
		balaCanion.vivir();
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
		Assert.assertTrue(x<=200.1 && x>=199.9);
		/*
		 * Y : posicion inicial = 300.0, velocidadY = -200 pixel/seg
		 *  => posicion luego de 1 seg = 100.0
		 */
		Assert.assertTrue(y<=100.1 && y>=99.9);
		
	}
	@Test
	public void vivirVeinteMiliSegBalaCanion() {
		//inicializa el tiempo
		balaCanion.vivir();
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
		Assert.assertTrue(x<=200.1 && x>=199.9);
		/*
		 * Y : posicion inicial = 300.0, velocidadY = -200 pixel/seg
		 *  => posicion luego de 20 mseg = 296.0
		 */
		Assert.assertTrue(y<296.01 && y>295.99);
		
	}
	@Test
	public void vivirNoventaMiliSegBalaCalion() {
		//inicializa el tiempo
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
		Assert.assertTrue(x<=200.1 && x>=199.9);
		/*
		 * Y : posicion inicial = 300.0, velocidadY = -200 pixel/seg
		 *  => posicion luego de 90 mseg = 282.0
		 */
		Assert.assertTrue(y<=282.1 && y>=281.9);
	}
	@Test
	public void vivir90MiliSegOesteBalaCanion(){
		balaCanion.setOrientacion(Direccion.Oeste());
		//inicializa el tiempo
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
		Assert.assertTrue(x<=182.1 && x>=181.9);
		/*
		 * Y : posicion inicial = 300.0, velocidadY = 0.0 pixel/seg
		 *  => posicion luego de 90 mseg = 300.0
		 */
		Assert.assertTrue(y<=300.1 && y>=299.9);
	}
	
	public void vivirUnSegCohete() {
		//inicializa el tiempo
		cohete.vivir();
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
		Assert.assertTrue(x<=400.1 && x>=399.9);
		/*
		 * Y : posicion inicial = 0.0, velocidadY = 300 pixel/seg
		 *  => posicion luego de 1 seg = 300.0
		 */
		Assert.assertTrue(y<=300.1 && y>=299.9);
		
	}
	@Test
	public void vivirVeinteMiliSegCohete() {
		//inicializa el tiempo
		cohete.vivir();
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
		Assert.assertTrue(x<=400.1 && x>=399.9);
		/*
		 * Y : posicion inicial = 0.0, velocidadY = 300 pixel/seg
		 *  => posicion luego de 20 mseg = 6.0
		 */
		Assert.assertTrue(y<=6.1 && y>=5.9);
		
	}
	@Test
	public void vivirNoventaMiliSegCohete() {
		//inicializa el tiempo
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
		Assert.assertTrue(x<=400.1 && x>=399.9);
		/*
		 * Y : posicion inicial = 0.0, velocidadY = 300 pixel/seg
		 *  => posicion luego de 90 mseg = 27.0
		 */
		Assert.assertTrue(y<=27.1 && y>=26.9);
	}
	@Test
	public void vivir90MiliSegOesteCohete(){
		cohete.setOrientacion(Direccion.Oeste());
		//inicializa el tiempo
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
		Assert.assertTrue(x<=373.1 && x>=372.9);
		/*
		 * Y : posicion inicial = 0.0, velocidadY = 0.0 pixel/seg
		 *  => posicion luego de 90 mseg = 0.0
		 */
		Assert.assertTrue(y<=0.1 && y>=-0.1);
	}
	
}
