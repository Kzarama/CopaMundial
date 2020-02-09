package mundo;

import java.util.ArrayList;

import excepciones.JugadorNoExisteException;
import excepciones.SeleccionNoExisteException;
import excepciones.SeleccionYaExisteException;

public class Campeonato {
	
	private Seleccion raiz;
	private int numeroSelecciones;

	
	public Campeonato() {
		numeroSelecciones = 0;
		raiz = null;
	}
	
	public int darNumeroSelecciones() {
		return numeroSelecciones;
	}
	
	public Seleccion darRaiz() {
		return raiz;	
	}
	
	public void modificarPrimero(Seleccion s) {
		this.raiz = s;
	}
	
	public int darAlturaArbol() {
		return raiz.darAltura();
	}
	
	public int darPesoArbol() {
		return raiz.darPeso();
	}
	
	public boolean estaVacia() {
		if(raiz == null) {
			return true;
		}else {
			return false;
		}
	}
	
	public void insertarSeleccion(Seleccion s) throws SeleccionYaExisteException {
		if(raiz == null) {
			raiz = s;
		}else {
			raiz.agregarSeleccion(s);
		}
		numeroSelecciones++;
	}
	
	public Seleccion buscarSeleccion(String nombre) throws SeleccionNoExisteException {
		Seleccion s = raiz.buscarSeleccion(nombre);
		if(s == null) {
			throw new SeleccionNoExisteException(nombre);
		}else {
			return s;
		}
	}
	
	public ArrayList<Seleccion> darListaSelecciones(){
		if(raiz == null) {
			return null;
		}else {
			ArrayList<Seleccion> selecciones = new ArrayList<Seleccion>();
			raiz.preorden(selecciones);
			return selecciones;
		}
	}

	public void insertarJugador(Seleccion s, Jugador j) {
		s.insertarJugador(j);
	}
	
	public Jugador localizarJugadorAnteriorPorPosicion(Seleccion s, int i) {
		return s.localizarAnteriorPorPosicion(i);
	}
	
	public int darNumeroJugadores(Seleccion s) {
		return s.darNumeroJugadores();
	}
	
	public Jugador darUltimo(Seleccion s) {
		return s.getUltimo(s.getPrimero(s));
	}
	
	public void eliminarJugadorPorNombre(Seleccion s, String nombre) throws JugadorNoExisteException{
		s.eliminarJugadorPorNombre(nombre);
	}
	
	public void eliminarJugadorPorPosicion(Seleccion s, int posicion) {
		s.eliminarJugadorPorPosicion(posicion);
	}
	
	public boolean jugadorYaExiste(String nombre, Seleccion s) {
		return s.jugadorYaExiste(s.getPrimero(s), nombre);
	}
	
	public Jugador darPrimerJugador(Seleccion s) {
		return s.getPrimero(s);
	}
	
	public Jugador buscarJugador(Seleccion s, String nombre) throws JugadorNoExisteException{
		return s.buscarJugador(s.getPrimero(s), nombre);
	}
	
	public Jugador darSiguienteJugador(Seleccion s, Jugador j) {
		return s.darSiguienteJugador(j);
	}
}
