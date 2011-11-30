package misc;

import java.util.HashMap;
import java.util.LinkedList;

import modelo.ArmaTiradaCanion;
import modelo.ArmaTiradaLanzaCohetes;
import modelo.Base;
import modelo.BonusAtaque;
import modelo.BonusVida;
import modelo.ElementoRectangular;
import modelo.ElementoRectangularSolido;
import modelo.Escenario;
import modelo.Explosion;
import modelo.Flota;
import modelo.ParedConcreto;
import modelo.ParedMetal;
import modelo.PosicionadorAleatorioStd;
import modelo.Tanque;
import modelo.TanqueGrizzly;
import modelo.TanqueHeroe;
import modelo.TanqueIFV;
import modelo.TanqueMirage;
import modelo.ai.BotBordes;
import modelo.ai.BotCentro;
import modelo.armamento.Ametralladora;
import modelo.armamento.BalaAmetralladora;
import modelo.armamento.BalaCanion;
import modelo.armamento.Canion;
import modelo.armamento.Cohete;
import modelo.armamento.LanzaCohetes;
import modelo.mejoras.MejoraTanqueAtaque;
import modelo.mejoras.MejoraTanqueVida;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import pantallas.Vida;

import excepciones.NoPudoLeerXMLExeption;
import excepciones.NoSePudoPosicionarException;

import utils.Direccion;
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
import vista.pantallas.VistaPantallaJuego;
import vista.pantallas.VistaVida;

public class DiccionarioDeSerializables {

	private static HashMap<Long, SerializableXML> map = new HashMap<Long, SerializableXML>();
	private static HashMap<Long, Boolean> mapSerializados = new HashMap<Long, Boolean>();

	public static boolean fueSerializado(long id) {
		Boolean b = mapSerializados.get(new Long(id));
		if (b == null)
			return false;
		return b.booleanValue();
	}

	public static void marcarSerializado(long id) {
		mapSerializados.put(new Long(id), new Boolean(true));
	}

	public static void limpiar() {
		map.clear();
		mapSerializados.clear();
	}

	private static Long getID(Element element) {
		NodeList hijos;
		Element elem;
		hijos = element.getChildNodes();
		if (hijos != null && hijos.getLength() > 0) {
			for (int i = 0; i < hijos.getLength(); i++) {
				if (hijos.item(i).getNodeType() != Node.ELEMENT_NODE)
					continue;
				elem = (Element) hijos.item(i);
				if (elem.getTagName().equals(ContadorDeInstancias.TAG_ID))
					return new Long(elem.getTextContent());
			}
		}
		return new Long(-1);
	}

	public static Object getInstancia(Element element) {
		Long id = getID(element);
		SerializableXML instancia = map.get(id);
		if (instancia != null)
			return instancia;
		if (element.getTagName().equals(BotBordes.TAG)) {
			instancia = new BotBordes();
			map.put(id, instancia);
			instancia.fromElementoXML(element);
		} else if (element.getTagName().equals(BotCentro.TAG)) {
			instancia = new BotCentro();
			map.put(id, instancia);
			instancia.fromElementoXML(element);
		} else if (element.getTagName().equals(Ametralladora.TAG)) {
			instancia = new Ametralladora();
			map.put(id, instancia);
			instancia.fromElementoXML(element);
		} else if (element.getTagName().equals(BalaAmetralladora.TAG)) {
			instancia = new BalaAmetralladora();
			map.put(id, instancia);
			instancia.fromElementoXML(element);
		} else if (element.getTagName().equals(BalaCanion.TAG)) {
			instancia = new BalaCanion();
			map.put(id, instancia);
			instancia.fromElementoXML(element);
		} else if (element.getTagName().equals(Canion.TAG)) {
			instancia = new Canion();
			map.put(id, instancia);
			instancia.fromElementoXML(element);
		} else if (element.getTagName().equals(Cohete.TAG)) {
			instancia = new Cohete();
			map.put(id, instancia);
			instancia.fromElementoXML(element);
		} else if (element.getTagName().equals(LanzaCohetes.TAG)) {
			instancia = new LanzaCohetes();
			map.put(id, instancia);
			instancia.fromElementoXML(element);
		} else if (element.getTagName().equals(MejoraTanqueAtaque.TAG)) {
			instancia = new MejoraTanqueAtaque();
			map.put(id, instancia);
			instancia.fromElementoXML(element);
		} else if (element.getTagName().equals(MejoraTanqueVida.TAG)) {
			instancia = new MejoraTanqueVida();
			map.put(id, instancia);
			instancia.fromElementoXML(element);
		} else if (element.getTagName().equals(ArmaTiradaCanion.TAG)) {
			instancia = new ArmaTiradaCanion();
			map.put(id, instancia);
			instancia.fromElementoXML(element);
		} else if (element.getTagName().equals(ArmaTiradaLanzaCohetes.TAG)) {
			instancia = new ArmaTiradaLanzaCohetes();
			map.put(id, instancia);
			instancia.fromElementoXML(element);
		} else if (element.getTagName().equals(Base.TAG)) {
			instancia = new Base();
			map.put(id, instancia);
			instancia.fromElementoXML(element);
		} else if (element.getTagName().equals(BonusAtaque.TAG)) {
			instancia = new BonusAtaque();
			map.put(id, instancia);
			instancia.fromElementoXML(element);
		} else if (element.getTagName().equals(BonusVida.TAG)) {
			instancia = new BonusVida();
			map.put(id, instancia);
			instancia.fromElementoXML(element);
		} else if (element.getTagName().equals(ElementoRectangular.TAG)) {
			instancia = new ElementoRectangular();
			map.put(id, instancia);
			instancia.fromElementoXML(element);
		} else if (element.getTagName().equals(Escenario.TAG)) {
			System.out.println("NUEVO ESCENARIO");
			instancia = Escenario.nuevaInstancia();
			map.put(id, instancia);
			instancia.fromElementoXML(element);
		} else if (element.getTagName().equals(Explosion.TAG)) {
			instancia = new Explosion();
			map.put(id, instancia);
			instancia.fromElementoXML(element);
		} else if (element.getTagName().equals(Flota.TAG)) {
			instancia = new Flota();
			map.put(id, instancia);
			instancia.fromElementoXML(element);
		} else if (element.getTagName().equals(ParedConcreto.TAG)) {
			instancia = new ParedConcreto();
			map.put(id, instancia);
			instancia.fromElementoXML(element);
		} else if (element.getTagName().equals(ParedMetal.TAG)) {
			instancia = new ParedMetal();
			map.put(id, instancia);
			instancia.fromElementoXML(element);
		} else if (element.getTagName().equals(PosicionadorAleatorioStd.TAG)) {
			instancia = new PosicionadorAleatorioStd();
			map.put(id, instancia);
			instancia.fromElementoXML(element);
		} else if (element.getTagName().equals(TanqueGrizzly.TAG)) {
			instancia = new TanqueGrizzly();
			map.put(id, instancia);
			instancia.fromElementoXML(element);
		} else if (element.getTagName().equals(TanqueHeroe.TAG)) {
			instancia = new TanqueHeroe();
			map.put(id, instancia);
			instancia.fromElementoXML(element);
		} else if (element.getTagName().equals(TanqueIFV.TAG)) {
			instancia = new TanqueIFV();
			map.put(id, instancia);
			instancia.fromElementoXML(element);
		} else if (element.getTagName().equals(TanqueMirage.TAG)) {
			instancia = new TanqueMirage();
			map.put(id, instancia);
			instancia.fromElementoXML(element);
		} else if (element.getTagName().equals(Vida.TAG)) {
			instancia = new Vida();
			map.put(id, instancia);
			instancia.fromElementoXML(element);
		} else if (element.getTagName().equals(VistaPantallaJuego.TAG)) {
			instancia = new VistaPantallaJuego();
			map.put(id, instancia);
			instancia.fromElementoXML(element);
		} else if (element.getTagName().equals(VistaVida.TAG)) {
			instancia = new VistaVida();
			map.put(id, instancia);
			instancia.fromElementoXML(element);
		} else if (element.getTagName().equals(VistaArmaTiradaCanion.TAG)) {
			instancia = new VistaArmaTiradaCanion();
			map.put(id, instancia);
			instancia.fromElementoXML(element);
		} else if (element.getTagName().equals(VistaArmaTiradaLanzaCohetes.TAG)) {
			instancia = new VistaArmaTiradaLanzaCohetes();
			map.put(id, instancia);
			instancia.fromElementoXML(element);
		} else if (element.getTagName().equals(VistaBalaAmetralladora.TAG)) {
			instancia = new VistaBalaAmetralladora();
			map.put(id, instancia);
			instancia.fromElementoXML(element);
		} else if (element.getTagName().equals(VistaBalaCanion.TAG)) {
			instancia = new VistaBalaCanion();
			map.put(id, instancia);
			instancia.fromElementoXML(element);
		} else if (element.getTagName().equals(VistaBase.TAG)) {
			instancia = new VistaBase();
			map.put(id, instancia);
			instancia.fromElementoXML(element);
		} else if (element.getTagName().equals(VistaBonusAtaque.TAG)) {
			instancia = new VistaBonusAtaque();
			map.put(id, instancia);
			instancia.fromElementoXML(element);
		} else if (element.getTagName().equals(VistaBonusVida.TAG)) {
			instancia = new VistaBonusVida();
			map.put(id, instancia);
			instancia.fromElementoXML(element);
		} else if (element.getTagName().equals(VistaCohete.TAG)) {
			instancia = new VistaCohete();
			map.put(id, instancia);
			instancia.fromElementoXML(element);
		} else if (element.getTagName().equals(VistaEscenario.TAG)) {
			instancia = VistaEscenario.nuevaInstancia();
			map.put(id, instancia);
			instancia.fromElementoXML(element);
		} else if (element.getTagName().equals(VistaExplosion.TAG)) {
			instancia = new VistaExplosion();
			map.put(id, instancia);
			instancia.fromElementoXML(element);
		} else if (element.getTagName().equals(VistaParedConcreto.TAG)) {
			instancia = new VistaParedConcreto();
			map.put(id, instancia);
			instancia.fromElementoXML(element);
		} else if (element.getTagName().equals(VistaParedMetal.TAG)) {
			instancia = new VistaParedMetal();
			map.put(id, instancia);
			instancia.fromElementoXML(element);
		} else if (element.getTagName().equals(VistaTanqueGrizzly.TAG)) {
			instancia = new VistaTanqueGrizzly();
			map.put(id, instancia);
			instancia.fromElementoXML(element);
		} else if (element.getTagName().equals(VistaTanqueHeroe.TAG)) {
			instancia = new VistaTanqueHeroe();
			map.put(id, instancia);
			instancia.fromElementoXML(element);
		} else if (element.getTagName().equals(VistaTanqueIFV.TAG)) {
			instancia = new VistaTanqueIFV();
			map.put(id, instancia);
			instancia.fromElementoXML(element);
		} else if (element.getTagName().equals(VistaTanqueMirage.TAG)) {
			instancia = new VistaTanqueMirage();
			map.put(id, instancia);
			instancia.fromElementoXML(element);
		} else if (element.getTagName().equals(FabricaBonusAtaque.TAG)) {
			instancia = new FabricaBonusAtaque();
			map.put(id, instancia);
			instancia.fromElementoXML(element);
		} else if (element.getTagName().equals(FabricaBonusVida.TAG)) {
			instancia = new FabricaBonusVida();
			map.put(id, instancia);
			instancia.fromElementoXML(element);
		} else if (element.getTagName().equals(Nivel.TAG)) {
			instancia = new Nivel();
			map.put(id, instancia);
			instancia.fromElementoXML(element);
		} else if (element.getTagName().equals(SorteadorBernoulli.TAG)) {
			instancia = new SorteadorBernoulli();
			map.put(id, instancia);
			instancia.fromElementoXML(element);
		} else if (element.getTagName().equals(Direccion.TAG)) {
			instancia = new Direccion();
			map.put(id, instancia);
			instancia.fromElementoXML(element);
		}
		return instancia;
	}

}
