package interfaz;

import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import excepciones.JugadorNoExisteException;
import excepciones.JugadorYaExisteException;
import excepciones.SeleccionNoExisteException;
import excepciones.SeleccionYaExisteException;
import modelo.Jugador;
import modelo.Mundial;
import modelo.Seleccion;

public class InterfazPrincipal extends JFrame {
	
	private PanelSelecciones panSelecciones;
	private PanelJugadores panJugadores;
	private PanelInformacion panInfo;
	private PanelASeleccion panASel;
	private PanelAJugador panAJug;
	private Mundial mundial;
	
	public InterfazPrincipal() {
		
		
		mundial = new Mundial();
		panSelecciones = new PanelSelecciones(this);
		panJugadores = new PanelJugadores(this);
		panInfo = new PanelInformacion(this);
		
		cargarDatos();
		refrescarListaSelecciones();
		refrescarListaJugadores();
		
		setLayout(new GridLayout(1, 3));
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1300, 700);
		setTitle("Copa Mundial De La FIFA");
		
		add(panSelecciones);
		add(panJugadores);
		add(panInfo);
		
		pack();
		
	}
	
	public void mostrarASeleccion(boolean bool) {
		panASel = new PanelASeleccion(this, bool);
		panASel.setLocationRelativeTo(this);
		panASel.setVisible(true);
	}
	
	public void mostrarAJugador(boolean bolo) {
		panAJug = new PanelAJugador(this, bolo);
		panAJug.setLocationRelativeTo(this);
		panAJug.setVisible(true);
	}
	
	public void agregarSeleccion() throws SeleccionYaExisteException {
		try {
			if(mundial.localizarSeleccion(panASel.getPais()) != null) {
				throw new SeleccionYaExisteException();
			}
		} catch (SeleccionNoExisteException e) {
			mundial.insertarSeleccion(new Seleccion(panASel.getPais(), panASel.getImagen(), panASel.getPuntos(), null));
			panASel.dispose();
		}
	}
	
	public void eliminarSeleccion() {
		String nombre = JOptionPane.showInputDialog(null, "INGRESE SELECCION A BORRAR");
		try {
			mundial.eliminarSeleccion(nombre);
		} catch (SeleccionNoExisteException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void buscarSeleccion() {
		String nombre = JOptionPane.showInputDialog(null, "INGRESE NOMBRE");
		try {
			calcularPromedioAltura(nombre);
			calcularPromedioEdad(nombre);
			calcularPromedioFIFA(nombre);
			JOptionPane.showMessageDialog(null, mundial.localizarSeleccion(nombre));
		} catch (HeadlessException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
		} catch (SeleccionNoExisteException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public String getNombreSeleccion() {
		return panSelecciones.getSeleccion();
	}
	
	public Seleccion getSeleccion() {
		Seleccion sele = null;
		try {
			sele = mundial.localizarSeleccion(panSelecciones.getSeleccion());
		} catch (SeleccionNoExisteException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		return sele;
	}
	
	public void modificarSeleccion() {
		try {
			mostrarASeleccion(false);
			String nombreN = panASel.getPais();
			int puntosN = panASel.getPuntos();
			String imagenN = panASel.getImagen();
			mundial.localizarSeleccion(panSelecciones.getSeleccion()).setPais(nombreN);
			mundial.localizarSeleccion(panSelecciones.getSeleccion()).setImagen(imagenN);
			mundial.localizarSeleccion(panSelecciones.getSeleccion()).setPuntos(puntosN);
			panASel.dispose();
		} catch(SeleccionNoExisteException e) {
			
		} catch(NullPointerException e) {
			
		}
	}
	
	public void agregarJugador() throws JugadorYaExisteException {
		Seleccion sele = null;
		try {
			sele = mundial.localizarSeleccion(panSelecciones.getSeleccion());
			if((mundial.localizarSeleccion(panSelecciones.getSeleccion()).localizar(panAJug.getNombre())) != null){
				modificarJugador(new Jugador(panAJug.getImagen(), panAJug.getNombre(), panAJug.getPosicion(), panAJug.getFechaNac(), panAJug.getFIFAPtj(), panAJug.getAltura(), null));
			}
		} catch (SeleccionNoExisteException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
		} catch (JugadorNoExisteException e) {
			sele.insertarJugador(new Jugador(panAJug.getImagen(), panAJug.getNombre(), panAJug.getPosicion(), panAJug.getFechaNac(), panAJug.getFIFAPtj(), panAJug.getAltura(), null));
			panAJug.dispose();
		}
	}
	
	public void eliminarJugador() {
		String nombre = JOptionPane.showInputDialog(null, "INGRESE JUGADOR A BORRAR");
		try {
			mundial.localizarSeleccion(panSelecciones.getSeleccion()).eliminarJugador(nombre);
		} catch(JugadorNoExisteException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
		} catch(SeleccionNoExisteException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void buscarJugador() {
		String nombre = JOptionPane.showInputDialog(null, "Ingrese nombre");;
		try {
			JOptionPane.showMessageDialog(null, mundial.localizarSeleccion(panSelecciones.getSeleccion()).localizar(nombre));
		} catch (HeadlessException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
		} catch (SeleccionNoExisteException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
		} catch (JugadorNoExisteException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void modificarJugador(Jugador j) {
		try {
			String seleccion = panSelecciones.getSeleccion();
			mundial.localizarSeleccion(seleccion).localizar(j.getNombre()).setNombre(j.getNombre());
			mundial.localizarSeleccion(seleccion).localizar(j.getNombre()).setImagen(j.getImagen());
			mundial.localizarSeleccion(seleccion).localizar(j.getNombre()).setPosicion(j.getPosicion());
			mundial.localizarSeleccion(seleccion).localizar(j.getNombre()).setFechaNacimiento(j.getFechaNacimiento());
			mundial.localizarSeleccion(seleccion).localizar(j.getNombre()).setPuntajeFIFA(j.getPuntajeFIFA());
			mundial.localizarSeleccion(seleccion).localizar(j.getNombre()).setAltura(j.getAltura());
		} catch(SeleccionNoExisteException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
		} catch(JugadorNoExisteException e) {
			
		} catch(NullPointerException e) {
			
		}
		panAJug.dispose();
	}
	
	public ArrayList getArraylistSeleccciones() {
		ArrayList selecciones = null;
		selecciones = new ArrayList();
		Seleccion temp = mundial.getPrimeraSeleccion();
		while(temp != null) {
			selecciones.add(temp.getPais());
			temp = temp.getSiguienteSeleccion();
		}
		return selecciones;
	}
	
	public void refrescarListaSelecciones() {
		panSelecciones.refrescarLista(getArraylistSeleccciones());
	}
	
	public ArrayList getArrayListJugadores() {
		ArrayList jugadores = new ArrayList();
		String nom = panSelecciones.getSeleccion();
		try {
			Seleccion sele = mundial.localizarSeleccion(nom);
			Jugador temp = sele.getPrimerJugador();
			while(temp != null) {
				jugadores.add(temp.getNombre());
				temp = temp.getSiguiente();
			}
		} catch (SeleccionNoExisteException e) {
//			JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		
		return jugadores;
	}
	
	public void refrescarListaJugadores() {
		panJugadores.refrescarLista(getArrayListJugadores());
	}
	
	public void refrescarInformacion() {
		try {
			if(mundial.localizarSeleccion(panSelecciones.getSeleccion()).JugadoresVacio()) {
				panInfo.cambiarDatos(new Jugador("", "", "", "", 0, 0, null));
			} else {
				panInfo.cambiarDatos(mundial.localizarSeleccion(panSelecciones.getSeleccion()).localizar(panJugadores.darJugador()));
			}
		} catch (JugadorNoExisteException e) {
			
		} catch (SeleccionNoExisteException e) {
			
		}
	}
	
	public void cargarDatos() {
		FileInputStream fileInStr = null;
		ObjectInputStream entrada = null;
		Seleccion seleccion;
		try {
			fileInStr = new FileInputStream("archivos/selecciones.dat");
			entrada = new ObjectInputStream(fileInStr);
	        seleccion = (Seleccion) entrada.readObject();
	        mundial.setSeleccion(seleccion);
		} catch (Exception e) {
		} finally {
            try {
                if (fileInStr != null) {
                	fileInStr.close();
                }
                if (entrada != null) {
                    entrada.close();
                }
            } catch (IOException e) {
            	JOptionPane.showMessageDialog(null, "HA OCURRIDO UN ERROR,\n POR FAVOR VUELVA A INTENTARLO");
            }
		}
	}
	
	public void guardarDatos() {
		FileOutputStream fileOutS = null;
		ObjectOutputStream salida = null;
		try {
			fileOutS = new FileOutputStream("archivos/selecciones.dat");
			salida = new ObjectOutputStream(fileOutS);
			salida.writeObject(mundial.getPrimeraSeleccion());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "HA OCURRIDO UN ERROR,\n POR FAVOR VUELVA A INTENTARLO");
		} finally {
            try {
                if (fileOutS != null) {
                	fileOutS.close();
                }
                if (salida != null) {
                	salida.close();
                }
            } catch (IOException e) {
            	JOptionPane.showMessageDialog(null, "HA OCURRIDO UN ERROR,\n POR FAVOR VUELVA A INTENTARLO");
            }
		}
	}
	
	public double calcularPromedioAltura(String nombre) {
		Seleccion sele;
		double promedioAltura = 0;
		try {
			sele = mundial.localizarSeleccion(nombre);
			promedioAltura = sele.calcularPromedioAltura();
		} catch (JugadorNoExisteException e) {
			
		} catch (SeleccionNoExisteException e) {
			
		}
		return promedioAltura;
	}
	
	public double calcularPromedioEdad(String nombre) {
		Seleccion sele;
		double promedioEdad = 0;
		try {
			sele = mundial.localizarSeleccion(nombre);
			promedioEdad = sele.calcularPromedioEdad();
		} catch (JugadorNoExisteException e) {
			
		} catch (SeleccionNoExisteException e) {
			
		}
		return promedioEdad;
	}
	
	public double calcularPromedioFIFA(String nombre) {
		Seleccion sele;
		double promedioFIFA = 0;
		try {
			sele = mundial.localizarSeleccion(nombre);
			promedioFIFA = sele.calcularPromedioFIFA();
		} catch (JugadorNoExisteException e) {
			
		} catch (SeleccionNoExisteException e) {
			
		}
		return promedioFIFA;
	}
	
	public Jugador getJugador() {
		Jugador juga = null;
		try {
			juga = (mundial.localizarSeleccion(panSelecciones.getSeleccion())).localizar(panJugadores.darJugador());
		} catch (SeleccionNoExisteException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
		} catch (JugadorNoExisteException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		return juga;
	}
	
	public void getjugadorAnterior() {
		try {
			panJugadores.setSelectIndex(1, -1);
			if((mundial.localizarSeleccion(panSelecciones.getSeleccion()).localizarJugadorAnterior(panJugadores.darJugador())) != null)
				panInfo.cambiarDatos((mundial.localizarSeleccion(panSelecciones.getSeleccion()).localizarJugadorAnterior(panJugadores.darJugador())));
		} catch (SeleccionNoExisteException e) {
			
		} catch (JugadorNoExisteException e) {
			
		}
	}
	
	public void getPrimerJugador() {
		try {
			panJugadores.setSelectIndex(0, 0);
			panInfo.cambiarDatos((mundial.localizarSeleccion(panSelecciones.getSeleccion()).getPrimerJugador()));
		} catch (SeleccionNoExisteException e) {
			
		}
	}
	
	public void getSiguienteJugador() {
		try {
			Seleccion s = mundial.localizarSeleccion(panSelecciones.getSeleccion());
			Jugador j = (mundial.localizarSeleccion(s.getPais())).localizar(panJugadores.darJugador()).getSiguiente();
			if(j == null){
				getPrimerJugador();
			} else {
				panJugadores.setSelectIndex(1, 1);
				panInfo.cambiarDatos(j);
			}
		} catch (SeleccionNoExisteException e) {
			
		} catch (JugadorNoExisteException e) {
			
		}
	}
	
	public static void main(String [] args ) {
		InterfazPrincipal i = new InterfazPrincipal();
		i.setExtendedState(i.getExtendedState() | JFrame.MAXIMIZED_BOTH);
	}

}