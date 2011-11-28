package misc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import modelo.TanqueEnemigo;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import excepciones.NoPudoLeerXMLExeption;

public abstract class FabricaBonus implements ActionListener{
	public static final String TAG = "objeto-fabrica-bonus";
	private static final String TAG_SORTEADOR = "sorteador";
	private static final String TAG_INTERVALO_SORTEO = "intervalo-sorteo";
	private static final String TAG_PRODUCIENDO = "produciendo";
	private boolean produciendo;
	private SorteadorBinario sorteadorBinario;
	private int intervaloSorteo; //en mseg
	private Timer timer;
	
	public FabricaBonus(SorteadorBinario sorteador){
		produciendo=true;
		intervaloSorteo=1000;
		sorteadorBinario=sorteador;
		timer = new Timer(intervaloSorteo, this);
		timer.start();
	}

	public FabricaBonus(Element element) throws NoPudoLeerXMLExeption {
		NodeList hijos;
		Element elem;
		hijos = element.getChildNodes();
		if(hijos!=null && hijos.getLength()>0){
			for(int i=0;i<hijos.getLength();i++){
				elem = (Element) hijos.item(i);
				if(elem.getTagName().equals(TAG_SORTEADOR))
					sorteadorBinario=(SorteadorBinario) DiccionarioDeSerializables.getInstancia((Element)elem.getFirstChild());
				else if(elem.getTagName().equals(TAG_PRODUCIENDO))
					produciendo=Boolean.parseBoolean(elem.getTextContent());
				else if(elem.getTagName().equals(TAG_INTERVALO_SORTEO))
					intervaloSorteo=Integer.parseInt(elem.getTextContent());
			}
		}
		timer = new Timer(intervaloSorteo, this);
		timer.start();
	}

	public void comenzarProduccion(){
		if(produciendo)
			return;
		produciendo=true;
	}
	public void detenerProduccion(){
		produciendo=false;
	}
	public abstract void crearBonus();
	
	public void actionPerformed(ActionEvent e){
		if(produciendo==false)
			return;
		if(sorteadorBinario.sortear())
			crearBonus();
	}
	public int getIntervaloSorteo() {
		return intervaloSorteo;
	}
	public void setIntervaloSorteo(int intervaloSorteo){
		this.intervaloSorteo = intervaloSorteo;
	}
	public void setSorteadorBinario(SorteadorBinario sorteadorBinario) {
		this.sorteadorBinario = sorteadorBinario;
	}
	public boolean estaProduciendo(){
		return produciendo;
	}
}
