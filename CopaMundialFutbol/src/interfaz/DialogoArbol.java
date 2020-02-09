package interfaz;

import java.util.ArrayList;

import javax.swing.JDialog;

import mundo.Seleccion;

public class DialogoArbol extends JDialog{
	
	public static final int X = 1000;
	public static final int Y = 600;
	
	private InterfazCopaMundialFutbol principal;
	
	private PanelArbol panelArbol;
	
	public DialogoArbol(InterfazCopaMundialFutbol f) {
		super(f,true);
		principal = f;
		setTitle("Árbol de Selecciones");
		setSize(X, Y);
		panelArbol = new PanelArbol(this);
		add(panelArbol);
	}
	
	public Seleccion darRaiz() {
		return principal.darRaiz();
	}
	
	public ArrayList<Seleccion> darSelecciones() {
		return principal.darCampeonato().darListaSelecciones();
	}
	
}
