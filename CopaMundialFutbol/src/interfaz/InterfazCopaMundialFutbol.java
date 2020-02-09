package interfaz;

import java.awt.GridLayout;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import excepciones.CampoVacioException;
import excepciones.JugadorNoExisteException;
import excepciones.SeleccionNoExisteException;
import excepciones.SeleccionYaExisteException;
import mundo.Campeonato;
import mundo.Jugador;
import mundo.Seleccion;

public class InterfazCopaMundialFutbol extends JFrame{
	
	private Campeonato campeonato;
	
	private PanelSelecciones panelSelecciones;
	private PanelJugadores panelJugadores;
	private PanelInformacionJugador panelInformacionJugador;
	private DialogoAgregarSeleccion dialogo;
	private DialogoAgregarJugador dialogoJ;
	private DecimalFormat df;
	
	public InterfazCopaMundialFutbol() {
		setTitle("Copa Mundial de la FIFA");
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}catch(Exception e){	
		}
		setSize(1000, 550);
		setLayout(new GridLayout(1,3));
		setLocationRelativeTo(null);
		campeonato = new Campeonato();
		panelSelecciones = new PanelSelecciones(this);
		panelJugadores = new PanelJugadores(this);
		panelInformacionJugador = new PanelInformacionJugador(this);
		df = new DecimalFormat("#.00");
	
		add(panelSelecciones);
		add(panelJugadores);
		add(panelInformacionJugador);
			
		cargarCampeonato();
		
		if(campeonato.estaVacia()) {
			mostrarJugadores();
			
			mostrarSelecciones();
		}
	
	}

	public void mostrarSelecciones() {
		panelSelecciones.refrescarLista(campeonato.darListaSelecciones());
	}
	
	public Seleccion darRaiz() {
		return campeonato.darRaiz();
	}
	
	public Campeonato darCampeonato() {
		return campeonato;
	}
	
	public void mostrarJugadores() {
		Seleccion s = panelSelecciones.darSeleccion();
		Jugador j=s.getPrimero(s);
		ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
		while(j!=null) {
			jugadores.add(j);
			j=j.getSiguiente();
		}
		panelJugadores.refrescarLista(jugadores);
	}
	
	public void cargarCampeonato() {
		FileInputStream fileInStr = null;
		ObjectInputStream entrada = null;
		Seleccion seleccion;
		try{
			fileInStr = new FileInputStream("datos/campeonato.dat");
			entrada = new ObjectInputStream(fileInStr);
			seleccion = (Seleccion)entrada.readObject();
			campeonato.modificarPrimero(seleccion);
		} catch (FileNotFoundException e) {
	       e.printStackTrace();
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        
	    } finally {
	        try {
	             if (fileInStr != null) 
	                fileInStr.close();
	             if (entrada != null) 
	                entrada.close();
	        } catch (IOException e) {
	             e.printStackTrace();
	        }
	    }
	}
	
	public void guardarCampeonato() {
		FileOutputStream fileOutS = null;
		ObjectOutputStream salida = null;
		Seleccion s= campeonato.darRaiz();

		try {
			fileOutS = new FileOutputStream("datos/campeonato.dat");
			salida = new ObjectOutputStream(fileOutS);
			
			salida.writeObject(s);
			
			salida.close();
			fileOutS.close();
		}catch(FileNotFoundException e){
			System.out.println(e.getMessage());
		}catch(IOException e){
			System.out.println(e.getMessage());
		}
	}
	
	public void agregarSeleccion() throws SeleccionYaExisteException{
		try {
			dialogo.dispose();
			Seleccion s = new Seleccion(dialogo.darPais(), dialogo.darRutaArchivo(), dialogo.darPuntos());
//			if(campeonato.seleccionYaExiste(dialogo.darPais())) {
//				throw new SeleccionYaExisteException();
//			}
			campeonato.insertarSeleccion(s);
			mostrarSelecciones();
			System.out.println(campeonato.darRaiz().getImagen());
		}catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Hay un dato erróneo", "Error", JOptionPane.ERROR_MESSAGE);
		}catch(CampoVacioException s) {
			JOptionPane.showMessageDialog(this, s.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}

	}
	
	
//	public void modificarSeleccion(int i) {
//		mostrarDialogoAgregarJugador();
//		if(dialogo.darPresionado() != false) {
//			campeonato.eliminarSeleccionPorPosicion(i);
//		}
//	}
//	
	public void buscarSeleccion() {
		try {
			String nombre = JOptionPane.showInputDialog("Ingrese el nombre de la selección a buscar");
			if(nombre.equals("")) {
			
			}
			else{
				if(campeonato.estaVacia()) {
					JOptionPane.showMessageDialog(this, "No hay selecciones");
				}else {
					Seleccion actual = campeonato.buscarSeleccion(nombre);
					JOptionPane.showMessageDialog(this, "País: "+actual.getPais()+"\nPuntos: "+actual.getPuntos()+"\nPromedio Altura: "+df.format(actual.getPromedioAltura())+" cm\nPromedio Edad: "+" años"+
						"\nPromedio FIFA: "+df.format(actual.getPromedioFIFA())+"\nImagen: "+actual.getImagen(), nombre, JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}catch(SeleccionNoExisteException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}catch(NullPointerException s) {
			
		}
//		}catch(ArithmeticException f) {
//			JOptionPane.showMessageDialog(this, "La selección no tiene jugadores");
//		}
	}

	public void agregarJugador() {
		Seleccion s= panelSelecciones.darSeleccion();
		try {
			dialogoJ.dispose();
			String imagen = dialogoJ.darImagen();
			String nombre = dialogoJ.darNombre();
			String posicion = dialogoJ.darPosicion(); 
			int puntajeFIFA = dialogoJ.darPuntajeFIFA();
			String fechaNac = dialogoJ.darFechaNacimiento();
			double altura = dialogoJ.darAltura();
			
			if(campeonato.jugadorYaExiste(nombre, s)) {
				modificarJugador();
			}
			Jugador j= new Jugador(imagen, nombre, posicion, puntajeFIFA, fechaNac, altura, null);
			
			campeonato.insertarJugador(s, j);
			refrescarInformacionJugador(imagen, nombre, posicion, puntajeFIFA, fechaNac, altura);
			mostrarJugadores();
			
		}catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Hay un dato erróneo", "Error", JOptionPane.ERROR_MESSAGE);
		}catch(CampoVacioException r) {
			JOptionPane.showMessageDialog(this, r.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void modificarJugador() {
		Seleccion s = panelSelecciones.darSeleccion();
		campeonato.eliminarJugadorPorPosicion(s, panelJugadores.darPosicion());
		
	}
	
	public void eliminarJugador() {
		try {
			Seleccion s = panelSelecciones.darSeleccion();
			String nombre = JOptionPane.showInputDialog("Inserte el nombre del jugador a eliminar");
			if(nombre.equals("")) {
			
			}else {
				campeonato.eliminarJugadorPorNombre(s, nombre);
				JOptionPane.showMessageDialog(this, "Jugador eliminado");
				mostrarJugadores();
			}
			
		}catch(JugadorNoExisteException s) {
			JOptionPane.showMessageDialog(this, s.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}catch(NullPointerException e) {
			
		}
	}
	
	public void buscarJugador() {
		try {
			Seleccion s = panelSelecciones.darSeleccion();
			String nombre = JOptionPane.showInputDialog("Inserte el nombre del jugador a buscar");
			if(nombre.equals("")) {
			
			}else {
				Jugador actual = campeonato.buscarJugador(s, nombre);
				JOptionPane.showMessageDialog(this, "Nombre: "+actual.getNombre()+"\nPuntaje FIFA: "+actual.getPuntajeFIFA()+"\nAltura: "+actual.getAltura()+" cm\nEdad: "+actual.calcularEdad()+" años"+
						"\nPosición: "+actual.getPosicion()+"\nImagen: "+actual.getImagen());
				
				mostrarJugadores();
			}
			
		}catch(JugadorNoExisteException s) {
			JOptionPane.showMessageDialog(this, s.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}catch(NullPointerException e) {
			
		}
	}
	
	public void refrescarInformacionJugador(String imagen, String nombre, String posicion, int puntajeFIFA, String fechaNac, double altura) {
		panelInformacionJugador.cambiarImagen(imagen);
		panelInformacionJugador.refrescarImagen(imagen);
		panelInformacionJugador.refrescarNombre(nombre);
		panelInformacionJugador.refrescarPosicion(posicion);
		panelInformacionJugador.refrescarFechaNacimiento(fechaNac);
		panelInformacionJugador.refrescarAltura(altura);
		panelInformacionJugador.refrescarPuntajeFIFA(puntajeFIFA);
	}
	
	public void actualizarDatos(Jugador j) {
		refrescarInformacionJugador(j.getImagen(), j.getNombre(), j.getPosicion(), j.getPuntajeFIFA(), j.getFechaNac(), j.getAltura());
	}
	
	public void mostrarSiguiente() {
		Seleccion s = panelSelecciones.darSeleccion();
		Jugador j = panelJugadores.darJugador();
		Jugador j2 = campeonato.darSiguienteJugador(s, j);
		if(j2 ==null) {
			j2 = campeonato.darPrimerJugador(s);
			actualizarDatos(j2);
			panelJugadores.darListaJugadores().setSelectedIndex(0);
		}else {
			actualizarDatos(j2);
			panelJugadores.darListaJugadores().setSelectedIndex(panelJugadores.darListaJugadores().getSelectedIndex()+1);
		}
	}
	
	public void mostrarAnterior() {
		Seleccion s = panelSelecciones.darSeleccion();
		int i = panelJugadores.darPosicion();
		Jugador j2 = campeonato.localizarJugadorAnteriorPorPosicion(s, i);
		if(j2 == null) {
			j2 = campeonato.darUltimo(s);
			actualizarDatos(j2);
			panelJugadores.darListaJugadores().setSelectedIndex(campeonato.darNumeroJugadores(s)-1);
		}else {
			actualizarDatos(j2);
			panelJugadores.darListaJugadores().setSelectedIndex(panelJugadores.darListaJugadores().getSelectedIndex()-1);
		}
	}
	
	public void mostrarPrimero() {
		Seleccion s = panelSelecciones.darSeleccion();
		Jugador j2 = campeonato.darPrimerJugador(s);
		actualizarDatos(j2);
		panelJugadores.darListaJugadores().setSelectedIndex(0);
	}
	
	public void mostrarDialogoArbol() {
		DialogoArbol arbol = new DialogoArbol(this);
		arbol.setLocationRelativeTo(this);
		arbol.setVisible(true);
	}
	
	public void mostrarDialogoAgregarJugador() {
		dialogoJ = new DialogoAgregarJugador(this);
		dialogoJ.setLocationRelativeTo(this);
		dialogoJ.setVisible(true);
		
	}
	
	public void mostrarDialogoAgregarSeleccion() {
		dialogo = new DialogoAgregarSeleccion(this);
		dialogo.setLocationRelativeTo(this);
		dialogo.setVisible(true);
	}
	public void dispose() {
		int op = JOptionPane.showConfirmDialog(this, "¿Desea guardar?");
		if(op == 0) {
			guardarCampeonato();
			System.exit(0);
		}else if(op == 1) {
			System.exit(0);
		}
	}
	public static void main(String[] args) {
		InterfazCopaMundialFutbol cf = new InterfazCopaMundialFutbol();
		cf.setVisible(true);
	}
}
