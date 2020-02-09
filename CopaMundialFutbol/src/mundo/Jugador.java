package mundo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Jugador implements Serializable {
	
	private String imagen;
	private String nombre;
	private String posicion;
	private int puntajeFIFA;
	private String fechaNac;
	private double altura;
	
	private Jugador siguiente, anterior;
	
	public Jugador(String imagen, String nombre, String posicion, int puntajeFIFA, String fechaNac, double altura, Jugador siguiente) {
		this.imagen = imagen;
		this.nombre = nombre;
		this.posicion = posicion;
		this.puntajeFIFA = puntajeFIFA;
		this.fechaNac = fechaNac;
		this.altura = altura;
	}
	
	public int calcularEdad() {
		DateTimeFormatter date = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate fechaNacimiento = LocalDate.parse(fechaNac, date);
		LocalDate fechaActual = LocalDate.now();
		Period periodo = Period.between(fechaNacimiento, fechaActual);
		int edad = periodo.getYears();
		return edad;
	}
	
	public Jugador getSiguiente() {
		return siguiente;
	}
	
	public void setSiguiente(Jugador j) {
		siguiente= j;
	}
	
	public Jugador getAnterior() {
		return anterior;
	}
	
	public void setAnterior(Jugador j) {
		anterior = j;
	}

	public String getImagen() {
		return imagen;
	}

	public String getNombre() {
		return nombre;
	}

	public String getPosicion() {
		return posicion;
	}

	public int getPuntajeFIFA() {
		return puntajeFIFA;
	}

	public String getFechaNac() {
		return fechaNac;
	}

	public double getAltura() {
		return altura;
	}

	public void desconectarSiguiente() {
		siguiente = siguiente.siguiente;
	}
	
	public String toString() {
		return nombre;
	}
}
