package modelo;

import java.util.*;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import excepciones.NoExisteBaseException;
import excepciones.NoPudoLeerXMLExeption;
import excepciones.YaExisteBaseException;

import titiritero.ObjetoVivo;
import titiritero.Posicionable;
import misc.ContadorDeInstancias;
import misc.DiccionarioDeSerializables;
import misc.FabricaElementos;
import misc.Nivel;
import misc.Observable;
import misc.Observador;

public class Escenario implements ObjetoVivo, Posicionable, Observable {
	private long id=ContadorDeInstancias.getId();

	private static final int ALTO = 720;
	private static final int ANCHO = 960;

	public static final String TAG = "objeto-escenario";
	private static final String TAG_OBJETOS_VIVOS = "objetos-vivos";
	private static final String TAG_OBJETOS_SOLIDOS = "objetos-solidos";
	private static final String TAG_OBSERVADORES = "observadores";
	private static final String TAG_ELEMENTOS = "elementos";
	private static final String TAG_BASE = "base";
	
	private static Escenario escenarioActual = null;
	private ArrayList<ObjetoVivo> objetosVivos = new ArrayList<ObjetoVivo>();
	private ArrayList<ElementoRectangularSolido> objetosSolidos = new ArrayList<ElementoRectangularSolido>();
	private ArrayList<Observador> observadores = new ArrayList<Observador>();
	private ArrayList<ElementoRectangular> elementos= new ArrayList<ElementoRectangular>();
	private Base base;

	public Escenario() {
		
	}

	public Escenario(Element element) throws NoPudoLeerXMLExeption {
		escenarioActual=this;
		NodeList hijos;
		Element elem;
		hijos = element.getChildNodes();
		if(hijos!=null && hijos.getLength()>0){
			for(int i=0;i<hijos.getLength();i++){
				elem = (Element) hijos.item(i);
				if(elem.getTagName().equals(TAG_OBJETOS_VIVOS))
					objetosVivos.add((ObjetoVivo)DiccionarioDeSerializables.getInstancia((Element)elem.getFirstChild()));
				else if(elem.getTagName().equals(TAG_OBJETOS_SOLIDOS))
					objetosSolidos.add((ElementoRectangularSolido)DiccionarioDeSerializables.getInstancia((Element)elem.getFirstChild()));
				else if(elem.getTagName().equals(TAG_OBSERVADORES))
					observadores.add((Observador)DiccionarioDeSerializables.getInstancia((Element)elem.getFirstChild()));
				else if(elem.getTagName().equals(TAG_ELEMENTOS))
					elementos.add((ElementoRectangular)DiccionarioDeSerializables.getInstancia((Element)elem.getFirstChild()));
				else if(elem.getTagName().equals(TAG_BASE))
					base=(Base)DiccionarioDeSerializables.getInstancia((Element)elem.getFirstChild());
				
			}
		}
	}

	public int getAlto() {
		return ALTO;
	}

	public int getAncho() {
		return ANCHO;
	}

	public double getX() {
		return 0.0;
	}

	public double getY() {
		return 0.0;
	}

	public static Escenario getActual() {
		if (escenarioActual == null) {
			escenarioActual = new Escenario();
		}
		return escenarioActual;
	}

	public static Escenario nuevaInstancia() {
		escenarioActual = new Escenario();
		return escenarioActual;
	}
	
	public void agregarBase(Base base) throws YaExisteBaseException {
		if (this.base != null)
			throw new YaExisteBaseException("ya existe una base");
		this.base = base;
		agregarObjetoSolido(base);
		agregarObjeto(base);
	}
	
	public void borrarBase() throws NoExisteBaseException {
		if (this.base == null)
			throw new NoExisteBaseException("no hay una base que borrar");
		borrarSolido(this.base);
		borrarObjeto(this.base);
		this.base = null;
		
	}
	
	public boolean tieneBase() {
		return this.base != null ;
	}
	
	public Base getBase() throws NoExisteBaseException {
		if (this.base == null)
			throw new NoExisteBaseException("no hay una base asignada al escenario");
		return this.base;
	}

	public void agregarObjetoVivo(ObjetoVivo objetoAgregar) {
		objetosVivos.add(objetoAgregar);
	}

	public void agregarObjetoSolido(ElementoRectangularSolido objetoAgregar) {
		objetosSolidos.add(objetoAgregar);
	}
	public void agregarObjeto(ElementoRectangular objetoAgregar) {
		elementos.add(objetoAgregar);
	}

	public void borrarObjetoVivo(ObjetoVivo objetoBorrar) {
		objetosVivos.remove(objetoBorrar);
	}

	public void borrarSolido(ElementoRectangularSolido objetoBorrar) {
		objetosSolidos.remove(objetoBorrar);
	}
	public void borrarObjeto(ElementoRectangular objetoBorrar) {
		elementos.remove(objetoBorrar);
	}

	public void vivir() {
		
		ArrayList<ObjetoVivo> vivos= new ArrayList<ObjetoVivo>(objetosVivos);
		
		Iterator<ObjetoVivo> iterador = vivos.iterator();
	
		while (iterador.hasNext()) {
			iterador.next().vivir();
		}

	}

	public void adscribir(Observador observador) {
		if (!observadores.contains(observador))
			observadores.add(observador);
	}

	public void quitar(Observador observador) {
		observadores.remove(observador);
	}

	public void notificar() {
		Iterator<Observador> it = observadores.iterator();
		while (it.hasNext()) {
			it.next().actualizar();
		}

	}

	public Iterator<ElementoRectangularSolido> getSolidos() {
		return objetosSolidos.iterator();
	}
	public Iterator<ElementoRectangular> getObjetos() {
		return elementos.iterator();
	}
	
	public int cantidadActualDeObjetosVivos(){
		return objetosVivos.size();
	}
	
	public int cantidadActualDeObjetosSolidos(){
		return objetosSolidos.size();
	}

	public void pausar() {
		Iterator<ObjetoVivo> iterador = objetosVivos.iterator();
		
		while (iterador.hasNext()) {
			iterador.next().pausar();
		}
		
		
	}

	public void reanudar() {
		Iterator<ObjetoVivo> iterador = objetosVivos.iterator();
		
		while (iterador.hasNext()) {
			iterador.next().reanudar();
		}
		
	}

}
