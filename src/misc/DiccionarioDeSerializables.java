package misc;

import java.util.HashMap;

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
import org.w3c.dom.NodeList;

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

	private static HashMap<Long, Object> map = new HashMap<Long, Object>();

	private static Long getID(Element element) {
		NodeList hijos;
		Element elem;
		hijos = element.getChildNodes();
		if (hijos != null && hijos.getLength() > 0) {
			for (int i = 0; i < hijos.getLength(); i++) {
				elem = (Element) hijos.item(i);
				if (elem.getTagName().equals(ContadorDeInstancias.TAG_ID))
					return new Long(elem.getTextContent());
			}
		}
		return new Long(-1);
	}

	public static Object getInstancia(Element element)
			throws NoPudoLeerXMLExeption {
		Long id = getID(element);
		Object instancia = map.get(id);
		if (instancia != null)
			return instancia;
		if (element.getTagName().equals(BotBordes.TAG)) {
			instancia = new BotBordes(element);
			map.put(id, instancia);
		} else if (element.getTagName().equals(BotCentro.TAG)) {
			instancia = new BotCentro(element);
			map.put(id, instancia);
		} else if (element.getTagName().equals(Ametralladora.TAG)) {
			instancia = new Ametralladora(element);
			map.put(id, instancia);
		} else if (element.getTagName().equals(BalaAmetralladora.TAG)) {
			instancia = new BalaAmetralladora(element);
			map.put(id, instancia);
		} else if (element.getTagName().equals(BalaCanion.TAG)) {
			instancia = new BalaCanion(element);
			map.put(id, instancia);
		} else if (element.getTagName().equals(Canion.TAG)) {
			instancia = new Canion(element);
			map.put(id, instancia);
		} else if (element.getTagName().equals(Cohete.TAG)) {
			instancia = new Cohete(element);
			map.put(id, instancia);
		} else if (element.getTagName().equals(LanzaCohetes.TAG)) {
			instancia = new LanzaCohetes(element);
			map.put(id, instancia);
		} else if (element.getTagName().equals(MejoraTanqueAtaque.TAG)) {
			instancia = new MejoraTanqueAtaque(element);
			map.put(id, instancia);
		} else if (element.getTagName().equals(MejoraTanqueVida.TAG)) {
			instancia = new MejoraTanqueVida(element);
			map.put(id, instancia);
		} else if (element.getTagName().equals(ArmaTiradaCanion.TAG)) {
			instancia = new ArmaTiradaCanion(element);
			map.put(id, instancia);
		} else if (element.getTagName().equals(ArmaTiradaLanzaCohetes.TAG)) {
			instancia = new ArmaTiradaLanzaCohetes(element);
			map.put(id, instancia);
		} else if (element.getTagName().equals(Base.TAG)) {
			instancia = new Base(element);
			map.put(id, instancia);
		} else if (element.getTagName().equals(BonusAtaque.TAG)) {
			instancia = new BonusAtaque(element);
			map.put(id, instancia);
		} else if (element.getTagName().equals(BonusVida.TAG)) {
			instancia = new BonusVida(element);
			map.put(id, instancia);
		} else if (element.getTagName().equals(ElementoRectangular.TAG)) {
			instancia = new ElementoRectangular(element);
			map.put(id, instancia);
		} else if (element.getTagName().equals(Escenario.TAG)) {
			instancia = new Escenario(element);
			map.put(id, instancia);
		} else if (element.getTagName().equals(Explosion.TAG)) {
			instancia = new Explosion(element);
			map.put(id, instancia);
		} else if (element.getTagName().equals(Flota.TAG)) {
			instancia = new Flota(element);
			map.put(id, instancia);
		} else if (element.getTagName().equals(ParedConcreto.TAG)) {
			instancia = new ParedConcreto(element);
			map.put(id, instancia);
		} else if (element.getTagName().equals(ParedMetal.TAG)) {
			instancia = new ParedMetal(element);
			map.put(id, instancia);
		} else if (element.getTagName()
				.equals(PosicionadorAleatorioStd.TAG)) {
			instancia = new PosicionadorAleatorioStd(element);
			map.put(id, instancia);
		} else if (element.getTagName().equals(TanqueGrizzly.TAG)) {
			instancia = new TanqueGrizzly(element);
			map.put(id, instancia);
		} else if (element.getTagName().equals(TanqueHeroe.TAG)) {
			instancia = new TanqueHeroe(element);
			map.put(id, instancia);
		} else if (element.getTagName().equals(TanqueIFV.TAG)) {
			instancia = new TanqueIFV(element);
			map.put(id, instancia);
		} else if (element.getTagName().equals(TanqueMirage.TAG)) {
			instancia = new TanqueMirage(element);
			map.put(id, instancia);
		} else if (element.getTagName().equals(VistaPantallaJuego.TAG)) {
			instancia = new VistaPantallaJuego(element);
			map.put(id, instancia);
		} else if (element.getTagName().equals(VistaVida.TAG)) {
			instancia = new VistaVida(element);
			map.put(id, instancia);
		} else if (element.getTagName().equals(VistaArmaTiradaCanion.TAG)) {
			instancia = new VistaArmaTiradaCanion(element);
			map.put(id, instancia);
		} else if (element.getTagName().equals(
				VistaArmaTiradaLanzaCohetes.TAG)) {
			instancia = new VistaArmaTiradaLanzaCohetes(element);
			map.put(id, instancia);
		} else if (element.getTagName().equals(VistaBalaAmetralladora.TAG)) {
			instancia = new VistaBalaAmetralladora(element);
			map.put(id, instancia);
		} else if (element.getTagName().equals(VistaBalaCanion.TAG)) {
			instancia = new VistaBalaCanion(element);
			map.put(id, instancia);
		} else if (element.getTagName().equals(VistaBase.TAG)) {
			instancia = new VistaBase(element);
			map.put(id, instancia);
		} else if (element.getTagName().equals(VistaBonusAtaque.TAG)) {
			instancia = new VistaBonusAtaque(element);
			map.put(id, instancia);
		} else if (element.getTagName().equals(VistaBonusVida.TAG)) {
			instancia = new VistaBonusVida(element);
			map.put(id, instancia);
		} else if (element.getTagName().equals(VistaCohete.TAG)) {
			instancia = new VistaCohete(element);
			map.put(id, instancia);
		} else if (element.getTagName().equals(VistaEscenario.TAG)) {
			instancia = new VistaEscenario(element);
			map.put(id, instancia);
		} else if (element.getTagName().equals(VistaExplosion.TAG)) {
			instancia = new VistaExplosion(element);
			map.put(id, instancia);
		} else if (element.getTagName().equals(VistaParedConcreto.TAG)) {
			instancia = new VistaParedConcreto(element);
			map.put(id, instancia);
		} else if (element.getTagName().equals(VistaParedMetal.TAG)) {
			instancia = new VistaParedMetal(element);
			map.put(id, instancia);
		} else if (element.getTagName().equals(VistaTanqueGrizzly.TAG)) {
			instancia = new VistaTanqueGrizzly(element);
			map.put(id, instancia);
		} else if (element.getTagName().equals(VistaTanqueHeroe.TAG)) {
			instancia = new VistaTanqueHeroe(element);
			map.put(id, instancia);
		} else if (element.getTagName().equals(VistaTanqueIFV.TAG)) {
			instancia = new VistaTanqueIFV(element);
			map.put(id, instancia);
		} else if (element.getTagName().equals(VistaTanqueMirage.TAG)) {
			instancia = new VistaTanqueMirage(element);
			map.put(id, instancia);
		} else if (element.getTagName().equals(FabricaBonusAtaque.TAG)) {
			instancia = new FabricaBonusAtaque(element);
			map.put(id, instancia);
		} else if (element.getTagName().equals(FabricaBonusVida.TAG)) {
			instancia = new FabricaBonusVida(element);
			map.put(id, instancia);
		} else if (element.getTagName().equals(Nivel.TAG)) {
			instancia = new Nivel(element);
			map.put(id, instancia);
		} else if (element.getTagName().equals(SorteadorBernoulli.TAG)) {
			instancia = new SorteadorBernoulli(element);
			map.put(id, instancia);
		} else if (element.getTagName().equals(Direccion.TAG)) {
			instancia = new Direccion(element);
			map.put(id, instancia);
		}
		return instancia;
	}

}
