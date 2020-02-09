package modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import excepciones.JugadorNoExisteException;

public class Seleccion implements Serializable {
	
	private String pais, imagen;
	private int puntos;
	private double promedioAltura, promedioEdad, promedioFIFA;
	private Seleccion siguienteSeleccion;
	private Jugador primerJugador;
	
	public Seleccion(String pa, String img, int pun, Jugador s) {
		this.pais = pa;
		this.imagen = img;
		this.puntos = pun;
		this.primerJugador = s;
	}
	
	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	public double getPromedioAltura() {
		return promedioAltura;
	}

	public void setPromedioAltura(double promedioAltura) {
		this.promedioAltura = promedioAltura;
	}

	public double getPromedioEdad() {
		return promedioEdad;
	}

	public void setPromedioEdad(double promedioEdad) {
		this.promedioEdad = promedioEdad;
	}

	public double getPromedioFIFA() {
		return promedioFIFA;
	}

	public void setPromedioFIFA(double promedioFIFA) {
		this.promedioFIFA = promedioFIFA;
	}

	public Seleccion getSiguienteSeleccion() {
		return siguienteSeleccion;
	}
	
	public void setSiguienteSeleccion(Seleccion siguienteSeleccion) {
		this.siguienteSeleccion = siguienteSeleccion;
	}
	
	public void cambiarSiguiente(Seleccion s) {
		siguienteSeleccion = s;
	}
	
	public void desconectarSiguiente() {
		siguienteSeleccion = siguienteSeleccion.siguienteSeleccion;
	}
	
	public Jugador getPrimerJugador() {
		return primerJugador;
	}

	public void setPrimerJugador(Jugador primerJugador) {
		this.primerJugador = primerJugador;
	}
	
	public boolean JugadoresVacio() {
		if(primerJugador == null) {
			return true;
		} else {
			return false;
		}
	}
	
	public void insertarJugador(Jugador j) {
		if(JugadoresVacio()) {
			primerJugador = j;
		} else {
			Jugador actual = primerJugador;
			while(actual.getSiguiente() != null) {
				actual = actual.getSiguiente();
			}
			actual.setSiguiente(j);
		}
	}
	
	public Jugador localizar(String nombre) throws JugadorNoExisteException {
		Jugador actual = primerJugador;
		while(actual != null && !actual.getNombre().equals(nombre)) {
			actual = actual.getSiguiente();
		}
		if(actual == null) {
			throw new JugadorNoExisteException();
		} else {
			return actual;
		}
	}
	
	public Jugador localizarJugadorAnterior(String nombre) throws JugadorNoExisteException {
		Jugador anterior = null;
		Jugador actual = primerJugador;
		while(actual != null && !actual.getNombre().equals(nombre)) {
			anterior = actual;
			actual = actual.getSiguiente();
		}
		if(actual == null) {
			throw new JugadorNoExisteException();
		} else {
			return anterior;
		}
	}
	
	public void eliminarJugador(String nombre) throws JugadorNoExisteException {
		if(primerJugador == null) {
			throw new JugadorNoExisteException();
		} else if(nombre.equals(primerJugador.getNombre())) {
			primerJugador = primerJugador.getSiguiente();
		} else {
			Jugador anterior = localizarJugadorAnterior(nombre);
			if(anterior == null) {
				throw new JugadorNoExisteException();
			}
			anterior.desconectarSiguiente();
		}
	}
	
	public double calcularPromedioAltura() throws JugadorNoExisteException {
		double promedioAltura = 0;
		int numeroJugadores = 0;
		Jugador temp = primerJugador;
		if(temp != null) {
			while(temp != null) {
				numeroJugadores++;
				promedioAltura += temp.getAltura();
				temp = temp.getSiguiente();
			}
			promedioAltura = promedioAltura /numeroJugadores;
			setPromedioAltura(promedioAltura);
			return promedioAltura;
		} else {
			throw new JugadorNoExisteException();
		}
	}
	
	public double calcularPromedioEdad() throws JugadorNoExisteException {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate ahora = LocalDate.now();
		Jugador temp = primerJugador;
		double promedioEdad = 0;
		int numeroJugadores = 0;
		if(temp != null) {
			while(temp != null) {
				LocalDate fecNac = LocalDate.parse(temp.getFechaNacimiento(), dtf);
				Period periodo = Period.between(fecNac, ahora);
				numeroJugadores++;
				promedioEdad += periodo.getYears();
				temp = temp.getSiguiente();
			}
			promedioEdad = promedioEdad /numeroJugadores;
			setPromedioEdad(promedioEdad);
			return promedioEdad;
		} else {
			throw new JugadorNoExisteException();
		}
	}
	
	public double calcularPromedioFIFA() throws JugadorNoExisteException {
		double promedioFIFA = 0;
		int numeroJugadores = 0;
		Jugador temp = primerJugador;
		if(temp != null) {
			while(temp != null) {
				numeroJugadores++;
				promedioFIFA += temp.getPuntajeFIFA();
				temp = temp.getSiguiente();
			}
			promedioFIFA = promedioFIFA /numeroJugadores;
			setPromedioFIFA(promedioFIFA);
			return promedioFIFA;
		} else {
			throw new JugadorNoExisteException();
		}
	}
	
	@Override
	public String toString() {
		return "PAIS: " + pais + "\nPUNTOS: " + puntos + "\nALTURA PROMEDIO: " + promedioAltura + "\nPROMEDIO FIFA: " + promedioFIFA + "\nEDAD PROMEDIO: " + promedioEdad;
	}
	
}