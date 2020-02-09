package interfaz;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import mundo.Seleccion;

public class PanelArbol extends JPanel{

	private DialogoArbol principal;
	
	public PanelArbol(DialogoArbol principal) {
		this.principal = principal;
	}
	
	public Seleccion darRaiz() {
		return principal.darRaiz();
	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(new ImageIcon(darRaiz().getImagen()).getImage(), 20, DialogoArbol.Y/2 - 40, 50, 30, this);
	}
	
}