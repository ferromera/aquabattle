package modelo.mejoras;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import excepciones.NoPudoLeerXMLExeption;

import misc.ContadorDeInstancias;
import misc.DiccionarioDeSerializables;
import modelo.Tanque;

public class MejoraTanqueVida extends MejoraTanque {
	public static final String TAG = "objeto-mejora-tanque-vida";
	private static final String TAG_PORCENTAJE_VIDA = "porcentaje-vida";
	private static final String TAG_TANQUE = "tanque";

	private long id=ContadorDeInstancias.getId();
	
	private final double PORCENTAJE_VIDA;
	private Tanque tanque;
	
	public MejoraTanqueVida(double porcentaje ,Tanque tanque) {
		PORCENTAJE_VIDA=porcentaje;
		this.tanque=tanque;
		tanque.agregarMejora(this);
	}
	
	public MejoraTanqueVida(Element element) throws NoPudoLeerXMLExeption {
		double porcentajeVida=0;
		NodeList hijos;
		Element elem;
		hijos = element.getChildNodes();
		if(hijos!=null && hijos.getLength()>0){
			for(int i=0;i<hijos.getLength();i++){
				elem = (Element) hijos.item(i);
				if(elem.getTagName().equals(TAG_PORCENTAJE_VIDA))
					porcentajeVida=Double.parseDouble(elem.getTextContent());
				else if(elem.getTagName().equals(TAG_TANQUE))
					tanque=(Tanque)DiccionarioDeSerializables.getInstancia((Element)elem.getFirstChild());
			}
		}
		PORCENTAJE_VIDA=porcentajeVida;

	}

	public void mejorar(){
		tanque.mejorarVida(PORCENTAJE_VIDA);
		tanque.quitarMejora(this);
	}
	public void deshacer(){
		// Esta mejora no se deshace.
	}

	@Override
	public void pausar() {
		
	}

	@Override
	public void reanudar() {
		
	}
}
