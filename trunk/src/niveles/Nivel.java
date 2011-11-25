package niveles;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import misc.FabricaBonusAtaque;
import misc.FabricaBonusVida;
import misc.FabricaElementos;
import misc.SorteadorBernoulli;
import misc.SorteadorBinario;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import excepciones.NoPudoLeerXMLExeption;
import excepciones.NoSePudoPosicionarException;
import excepciones.ProbabilidadInvalidaException;

public class Nivel {

	private boolean ganado;
	private boolean perdido;
	private String rutaXML;
	private String tagNivel;
	private static final String TAG_TANQUE_HEROE="heroe";
	private static final String TAG_PARED="pared";
	private static final String TAG_FLOTA="flota";
	private static final String TAG_POS_X="pos-x";
	private static final String TAG_POS_Y="pos-y";
	private static final String TAG_PARED_METAL="pared-metal";
	private static final String TAG_PARED_CONCRETO="pared-concreto";
	private static final String TAG_BONUS="bonus";
	private static final String TAG_BOUNS_ATAQUE = "bonus-ataque";
	private static final String TAG_BONUS_VIDA = "bonus-vida";
	private static final String TAG_BERNOULLI = "bernoulli";

	public Nivel(int numeroDeNivel,String ruta){
		tagNivel="nivel";
		tagNivel.concat(Integer.toString(numeroDeNivel));
		rutaXML=ruta;
		
	}
	public void cargar() throws NoPudoLeerXMLExeption {

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		Document docXML = null;
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();

			docXML = db.parse(rutaXML);

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (SAXException se) {
			se.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		Element raiz = docXML.getDocumentElement();
		if (raiz.getTagName().compareTo(tagNivel) == 0)
			throw new NoPudoLeerXMLExeption("La raiz " + raiz.getTagName()
					+ " no coincide con " + tagNivel);

		NodeList nodoTanqueHeroe = raiz.getElementsByTagName(TAG_TANQUE_HEROE);
		if (nodoTanqueHeroe == null)
			throw new NoPudoLeerXMLExeption("No se encontro el tag: "
					+ TAG_TANQUE_HEROE);
		if (nodoTanqueHeroe.getLength() != 1)
			throw new NoPudoLeerXMLExeption("Hay mas de un TanqueHeroe");

		Element elemHeroe = (Element) nodoTanqueHeroe.item(0);
		cargarHeroe(elemHeroe);

		NodeList nodosPared = raiz.getElementsByTagName(TAG_PARED);
		if (nodosPared != null)
			for (int i = 0; i < nodosPared.getLength(); i++) {
				Element elemPared = (Element) nodosPared.item(i);
				cargarPared(elemPared);
			}
		NodeList nodosFlotas = raiz.getElementsByTagName(TAG_FLOTA);
		if (nodosFlotas != null)
			for (int i = 0; i < nodosFlotas.getLength(); i++) {
				Element elemFlota = (Element) nodosFlotas.item(i);
				cargarFlota(elemFlota);
			}
		NodeList nodosBonus = raiz.getElementsByTagName(TAG_BONUS);
		if (nodosBonus != null)
			for (int i = 0; i < nodosBonus.getLength(); i++) {
				Element elemBonus = (Element) nodosBonus.item(i);
				cargarBonus(elemBonus);
			}

	}

	private void cargarBonus(Element elemBonus) throws NoPudoLeerXMLExeption {
		NodeList nodoBonusVida= elemBonus.getElementsByTagName(TAG_BONUS_VIDA);
		if(nodoBonusVida!=null){
			if(nodoBonusVida.getLength()>1)
				throw new NoPudoLeerXMLExeption("Debe haber a lo sumo un tag: "+TAG_BONUS_VIDA+" en el nodo "+elemBonus.getTagName());
			if(nodoBonusVida.getLength()==1){
				Element elemBonusVida = (Element) nodoBonusVida.item(0);
				cargarBonusVida(elemBonusVida);
			}
		}
		NodeList nodoBonusAtaque= elemBonus.getElementsByTagName(TAG_BOUNS_ATAQUE);
		if(nodoBonusAtaque!=null){
			if(nodoBonusAtaque.getLength()>1)
				throw new NoPudoLeerXMLExeption("Debe haber a lo sumo un tag: "+TAG_BOUNS_ATAQUE+" en el nodo "+elemBonus.getTagName());
			if(nodoBonusAtaque.getLength()==1){
				Element elemBonusAtaque = (Element) nodoBonusAtaque.item(0);
				cargarBonusAtaque(elemBonusAtaque);
			}
		}
		
		
	}

	private void cargarBonusAtaque(Element elemBonusAtaque) throws NoPudoLeerXMLExeption {
		SorteadorBinario sorteador= cargarSorteador(elemBonusAtaque);
		FabricaBonusAtaque fabAtaque= new FabricaBonusAtaque(sorteador);
		//PantallaJuego.addFabricaBonus(fabAtaque);
		
	}
	private SorteadorBinario cargarSorteador(Element elem) throws NoPudoLeerXMLExeption {
		NodeList nodoBernoulli= elem.getElementsByTagName(TAG_BERNOULLI);
		if(nodoBernoulli!=null){
			if(nodoBernoulli.getLength()>1)
				throw new NoPudoLeerXMLExeption("Debe haber a lo sumo un tag: "+TAG_BERNOULLI+" en el nodo "+elem.getTagName());
			if(nodoBernoulli.getLength()==1){
				Element elemBernoulli = (Element) nodoBernoulli.item(0);
				double probabilidad=Double.parseDouble(elemBernoulli.getTextContent());
				try {
					return new SorteadorBernoulli(probabilidad);
				} catch (ProbabilidadInvalidaException e) {
					throw new NoPudoLeerXMLExeption("Probabilidad invalida en el nodo "+elem.getTagName());
				}
			}
		}
		return new SorteadorBernoulli();
	}
	private void cargarBonusVida(Element elemBonusVida) throws NoPudoLeerXMLExeption {
		SorteadorBinario sorteador= cargarSorteador(elemBonusVida);
		FabricaBonusVida fabVida= new FabricaBonusVida(sorteador);
		//PantallaJuego.addFabricaBonus(fabVida);
		
	}
	private void cargarFlota(Element elemFlota) {
		// TODO Auto-generated method stub
		
	}

	private void cargarPared(Element elemPared) throws NoPudoLeerXMLExeption {
		NodeList nodosMetal= elemPared.getElementsByTagName(TAG_PARED_METAL);
		if(nodosMetal!=null)
			for(int i=0;i<nodosMetal.getLength();i++){
				Element elemMetal = (Element) nodosMetal.item(i);
				cargarParedMetal(elemMetal);
			}
		
		NodeList nodosConcreto= elemPared.getElementsByTagName(TAG_PARED_CONCRETO);
		if(nodosConcreto!=null)
			for(int i=0;i<nodosConcreto.getLength();i++){
				Element elemConcreto = (Element) nodosConcreto.item(i);
				cargarParedConcreto(elemConcreto);
			}			
	}

	private void cargarParedMetal(Element elemMetal) throws NoPudoLeerXMLExeption {
		System.out.println("pared metal");
		double x= getPosX(elemMetal);
		double y= getPosY(elemMetal);
		System.out.println("x "+x+" y "+y);
		try {
			FabricaElementos.crearParedMetal(x, y);
		} catch (NoSePudoPosicionarException e) {
			System.err.println("No se pudo posicionar pared de metal al cargar "+tagNivel);
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	private void cargarParedConcreto(Element elemConcreto) throws NoPudoLeerXMLExeption {
		System.out.println("pared concreto");
		double x= getPosX(elemConcreto);
		double y= getPosY(elemConcreto);
		System.out.println("x "+x+" y "+y);
		try {
			FabricaElementos.crearParedConcreto(x, y);
		} catch (NoSePudoPosicionarException e) {
			System.err.println("No se pudo posicionar pared de concreto al cargar "+tagNivel);
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		
	}

	private double getPosY(Element elem) throws NoPudoLeerXMLExeption {
		
		NodeList nodoY = elem.getElementsByTagName(TAG_POS_Y);
		if(nodoY==null)
			throw new NoPudoLeerXMLExeption("No se encontro tag: "+TAG_POS_Y+" en el nodo: "+elem.getTagName());
		if(nodoY.getLength()!=1)
			throw new NoPudoLeerXMLExeption("Debe haber solo un tag: "+TAG_POS_Y+" en el nodo "+elem.getTagName());
		Element elemY = (Element) nodoY.item(0);
		double y=Double.parseDouble(elemY.getTextContent());
		return y;
	}

	private double getPosX(Element elem) throws NoPudoLeerXMLExeption {
		
		NodeList nodoX = elem.getElementsByTagName(TAG_POS_X);
		if(nodoX==null)
			throw new NoPudoLeerXMLExeption("No se encontro tag: "+TAG_POS_X+" en el nodo: "+elem.getTagName());
		if(nodoX.getLength()!=1)
			throw new NoPudoLeerXMLExeption("Debe haber solo un tag: "+TAG_POS_X+" en el nodo "+elem.getTagName());
		Element elemX = (Element) nodoX.item(0);
		double x=Double.parseDouble(elemX.getTextContent());
		return x;
	}

	private void cargarHeroe(Element elemHeroe) throws NoPudoLeerXMLExeption {
		System.out.println("heroe");
		double x=getPosX(elemHeroe);
		double y=getPosY(elemHeroe);
		System.out.println("x "+x+" y "+y);
		
		try {
			FabricaElementos.crearTanqueHeroe(x,y);
		} catch (NoSePudoPosicionarException e) {
			System.err.println("No se pudo posicionar heroe al cargar "+tagNivel);
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		
	}

	public void setGanado(boolean b) {
		ganado = b;
	}

	public void setPerdido(boolean b) {
		perdido = b;
	}

	public boolean estaGanado() {
		return ganado;
	}

	public boolean estaPerdido() {
		return perdido;
	}
}
