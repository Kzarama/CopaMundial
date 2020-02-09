package modelo;

import java.io.Serializable;

public class Jugador implements Serializable {
	
	private String imagen, nombre, posicion, fechaNacimiento;
	private int puntajeFIFA;
	private double altura;
	private Jugador siguiente;
	
	public Jugador(String img, String nom, String pos, String fec, int pun, double alt, Jugador s) {
		
		this.imagen = img;
		this.nombre = nom;
		this.posicion = pos;
		this.fechaNacimiento = fec;
		this.puntajeFIFA = pun;
		this.altura = alt;
		this.siguiente = s;
		
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPosicion() {
		return posicion;
	}

	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public int getPuntajeFIFA() {
		return puntajeFIFA;
	}

	public void setPuntajeFIFA(int pFIFA) {
		this.puntajeFIFA = pFIFA;
	}
	
	public double getAltura() {
		return altura;
	}

	public void setAltura(double altura) {
		this.altura = altura;
	}

	public Jugador getSiguiente() {
		return siguiente;
	}

	public void setSiguiente(Jugador siguiente) {
		this.siguiente = siguiente;
	}
	
	public void cambiarSiguiente(Jugador j) {
		siguiente = j;
	}
	
	public void desconectarSiguiente() {
		siguiente = siguiente.siguiente;
	}
	
	@Override
	public String toString() {
		return "NOMBRE: " + nombre + "\nPOSICION: " + posicion + "\nPUNTAJE FIFA: " + puntajeFIFA + "\nFECHA NACIMIENTO: " + fechaNacimiento + "\nALTURA: " + altura;
	}
}