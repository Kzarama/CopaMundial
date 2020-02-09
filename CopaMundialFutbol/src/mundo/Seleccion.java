package mundo;

import java.io.Serializable;
import java.util.ArrayList;

import excepciones.JugadorNoExisteException;
import excepciones.SeleccionYaExisteException;
public class Seleccion implements Serializable, Comparable<Seleccion> {

	private Jugador primero, ultimo;
	private Seleccion derecho, izquierdo;
	private int numeroJugadores;
	
	private String pais;
	private int puntos;
	private double promedioAltura;
	private double promedioEdad;
	private double promedioFIFA;
	private String imagen;
	
	public Seleccion(String pais, String imagen, int puntos) {
		this.pais = pais;
		this.imagen = imagen;
		this.puntos = puntos;
		numeroJugadores = 0;
		primero = null;
	}
	
	public Seleccion darDerecho() {
		return derecho;
	}
	
	public void modificarDerecho(Seleccion s) {
		this.derecho = s;
	}
	
	public Seleccion darIzquierdo() {
		return izquierdo;
	}
	
	public void modificarIzquierdo(Seleccion s) {
		this.izquierdo = s;
	}
	
	public void preorden(ArrayList<Seleccion> s) {
		s.add(this);
		if(izquierdo != null) {
			izquierdo.preorden(s);
		}
		if(derecho != null) {
			derecho.preorden(s); 
		}
	}
	
	public void agregarSeleccion(Seleccion nuevo) throws SeleccionYaExisteException{
		if(pais.equalsIgnoreCase(nuevo.getPais())) {
			throw new SeleccionYaExisteException();
		}
		if(compareTo(nuevo) > 0) {
			if(izquierdo == null) {
				izquierdo = nuevo;
			}else {
				izquierdo.agregarSeleccion(nuevo);
			}
		}else {
			if(derecho == null) {
				derecho = nuevo;
			}else {
				derecho.agregarSeleccion(nuevo);
			}
		}
	}
	
	public Seleccion buscarSeleccion(String nombre){
		if(pais.compareToIgnoreCase(nombre) == 0) {
			return this;
		}
		Seleccion s = null;
		if(izquierdo != null) {
			s = izquierdo.buscarSeleccion(nombre);
		}
		
		if(s == null && derecho != null) {
			return derecho.buscarSeleccion(nombre);
		}
		
		return s;
		
	}
	
	public Seleccion darMenor() {
		return (izquierdo == null) ? this : izquierdo.darMenor();
	}
	
	public Seleccion darMayor() {
		return (derecho == null) ? this : derecho.darMayor();
	}
	
	public boolean esHoja() {
		return derecho == null && izquierdo == null;
	}
	
	public int darAltura() {
		if(esHoja()) {
			return 1;
		}else {
			int a1 = (izquierdo == null) ? 0 : izquierdo.darAltura();
			int a2 = (derecho == null) ? 0 : derecho.darAltura();
			return 1 + Math.max(a1, a2);
		}
	}
	
	public int darPeso() {
		int p1 = (izquierdo == null) ? 0 : izquierdo.darPeso();
		int p2 = (derecho == null) ? 0 : derecho.darPeso();
		return 1 + p1 + p2;
	}
	
	public Jugador darSiguienteJugador(Jugador j) {
		return j.getSiguiente();
	}

	public int darNumeroJugadores() {
		return numeroJugadores;
	}
	
	public double getPromedioAltura() {
		Jugador actual = primero;
		int sumaAltura = 0;
		while(actual!=null) {
			sumaAltura+=actual.getAltura();
			actual = actual.getSiguiente();
		}
		return sumaAltura/numeroJugadores;
	}
	
	public int getPuntos() {
		return puntos;
	}
	
	public double getPromedioEdad() {
		Jugador actual = primero;
		int sumaEdad = 0;
		while(actual!=null) {
			sumaEdad+=actual.calcularEdad();
			actual = actual.getSiguiente();
		}
		promedioEdad = sumaEdad/numeroJugadores;
		return promedioEdad;
	}
	
	public double getPromedioFIFA() {
		Jugador actual = primero;
		double sumaFIFA = 0;
		while(actual!=null) {
			sumaFIFA+=actual.getPuntajeFIFA();
			actual = actual.getSiguiente();
		}
		promedioFIFA = sumaFIFA/numeroJugadores;
		return promedioFIFA;
	}
	
	public boolean estaVacia() {
		if(primero == null) {
			return true;
		}else {
			return false;
		}
	}
	
	public void insertarJugador(Jugador j) {
		if(estaVacia()) {
			primero = new Jugador(j.getImagen(),j.getNombre(), j.getPosicion(),j.getPuntajeFIFA(), j.getFechaNac(), j.getAltura(),null);
			numeroJugadores++;
		} else {
			insertarJugador(primero, j);
		}
	}
	
	public void insertarJugador(Jugador actual, Jugador porInsertar) {
		if(actual.getSiguiente() == null) {
			actual.setSiguiente(porInsertar);
			numeroJugadores++;
		} else {
			insertarJugador(actual.getSiguiente(), porInsertar);
		}
	}
	
	public Jugador buscarJugador(Jugador actual, String nombre) throws JugadorNoExisteException {
		if(actual != null) {
			if(actual.getNombre().equalsIgnoreCase(nombre)) {
				return actual;
			} else {
				return buscarJugador(actual.getSiguiente(), nombre);
			}
		} else {
			throw new JugadorNoExisteException(nombre);
		}
	}
	
	public Jugador localizarAnteriorPorNombre(Jugador actual, Jugador anterior, String nombre) {
		if(actual.getNombre().equalsIgnoreCase(nombre)) {
			return anterior;
		} else {
			return localizarAnteriorPorNombre(actual.getSiguiente(), actual, nombre);
		}
	}
	
	public void eliminarJugadorPorNombre(String nombre) throws JugadorNoExisteException {
		if(primero == null) {
			throw new JugadorNoExisteException(nombre);
		} else if (nombre.equalsIgnoreCase(primero.getNombre())) {
			primero = primero.getSiguiente();
		} else {
			Jugador anterior = localizarAnteriorPorNombre(primero, null, nombre);
			if( anterior == null) {
				throw new JugadorNoExisteException(nombre);
			}
			anterior.desconectarSiguiente();
			
		}
		numeroJugadores--;
	}
	
	public Jugador localizarAnteriorPorPosicion(int i) {
		Jugador anterior = null;
		Jugador actual = primero;
		int contador = 0;
		while(actual != null && contador != i) {
			anterior = actual;
			actual = actual.getSiguiente();
			contador++;
		}
		return actual != null ? anterior : null;
	}
	
	public void eliminarJugadorPorPosicion(int i) {
		if (i == 0) {
			primero = primero.getSiguiente();
		} else {
			Jugador anterior = localizarAnteriorPorPosicion(i);
			anterior.desconectarSiguiente();
		}
		numeroJugadores--;
	}
	
	public boolean jugadorYaExiste(Jugador j, String nombre) {
		if(j != null) {
			if(j.getNombre().equalsIgnoreCase(nombre)) {
				return true;
			} else {
				return jugadorYaExiste(j.getSiguiente(), nombre);
			}
		}
		return false;
	}
	
	public String getPais() {
		return pais;
	}
	
	public String getImagen() {
		return imagen;
	}

	public Jugador getPrimero(Seleccion s) {
		return primero;
	}

	public void setPrimero(Jugador primero) {
		this.primero = primero;
	}
	
	public Jugador getUltimo(Jugador actual) {
		if(actual.getSiguiente() == null) {
			return actual;
		} else {
			return getUltimo(actual.getSiguiente());
		}
	}
	
	public void setUltimo(Jugador ultimo) {
		this.ultimo = ultimo;
	}
	
	public String toString() {
		return pais;
	}
	
	public StringBuilder toString(StringBuilder prefix, boolean isTail, StringBuilder sb) {
	    if(derecho!=null) {
	        derecho.toString(new StringBuilder().append(prefix).append(isTail ? "│   " : "    "), false, sb);
	    }
	    sb.append(prefix).append(isTail ? "└── " : "┌── ").append(pais).append("\n");
	    if(izquierdo!=null) {
	        izquierdo.toString(new StringBuilder().append(prefix).append(isTail ? "    " : "│   "), true, sb);
	    }
	    return sb;
	}

	@Override
	public int compareTo(Seleccion s) {
		if(puntos > s.getPuntos()) {
			return 1;
		}else if(puntos < s.getPuntos()) {
			return -1;
		}else {
			return 0;
		}
		
	}

}
