package titiritero.vista;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import titiritero.ControladorJuego;
import titiritero.SuperficieDeDibujo;

public class Panel extends java.awt.Panel implements SuperficieDeDibujo {


	private static final long serialVersionUID = -5159188497592488407L;
	private Image imagen;
	private Color color , background;
	

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Color getBackground() {
		return background;
	}

	public void setBackground(Color background) {
		this.background = background;
	}

	// es llamado internamente por el metodo repaint() de la clase Frame
	public void update(Graphics g) {
		paint(g);
	}

	public void paint(Graphics g) {
		g.drawImage(this.imagen, 0, 0, null);
	}
	
	public void actualizar() {
		this.repaint();
	}

	public void limpiar() {
		if(this.imagen == null)
			this.imagen = this.createImage(getSize().width, getSize().height);
		Graphics superficieParaDibujar =  this.imagen.getGraphics();
		superficieParaDibujar.setColor(color);// 
		superficieParaDibujar.fillRect(0, 0, this.getSize().width, this.getSize().height);
		this.setBackground(background);
	}

	public Object getBuffer(){
		return this.imagen.getGraphics();
	}
	
	public Panel(int ancho,int alto, ControladorJuego unControlador){
		this.addMouseListener(new MouseClickController(unControlador));
		this.addKeyListener(new KeyPressedController(unControlador));
		this.setSize(ancho, alto);
		this.setVisible(true);
		
	}
	public Panel(int ancho,int alto, ControladorJuego unControlador,Ventana ventana){
		ventana.add(this);
		this.addMouseListener(new MouseClickController(unControlador));
		this.addKeyListener(new KeyPressedController(unControlador));
		this.setSize(ancho, alto);
		this.setVisible(true);
		this.imagen = this.createImage(getSize().width, getSize().height);
		
	}
	public Panel(int ancho,int alto){
		this.setSize(ancho, alto);
		this.setVisible(true);	
	}
	public Panel(int ancho,int alto,Ventana ventana){
		
		this.setSize(ancho, alto);
		this.setVisible(true);	
		ventana.add(this);
		this.imagen = this.createImage(getSize().width, getSize().height);
	}
}
