package modelo;

import excepciones.SeleccionNoExisteException;

public class Mundial {
	
	private Seleccion primeraSeleccion;
	
	public Mundial() {
		primeraSeleccion = null;
	}
	
	public Seleccion getPrimeraSeleccion() {
		return primeraSeleccion;
	}
	
	public void setSeleccion(Seleccion s) {
		primeraSeleccion = s;
	}
	
	public boolean seleccionesVacio() {
		if(primeraSeleccion == null) {
			return true;
		} else {
			return false;
		}
	}
	
	public void insertarSeleccion(Seleccion s) {
		if(seleccionesVacio()) {
			primeraSeleccion = s;
		} else {
			Seleccion actual = primeraSeleccion;
			while(actual.getSiguienteSeleccion() != null) {
				actual = actual.getSiguienteSeleccion();
			}
			actual.setSiguienteSeleccion(s);
		}
	}
	
	public Seleccion localizarSeleccion(String nombre) throws SeleccionNoExisteException {
		Seleccion actual = primeraSeleccion;
		while(actual != null && !actual.getPais().equals(nombre)) {
			actual = actual.getSiguienteSeleccion();
		}
		if(actual == null) {
			throw new SeleccionNoExisteException();
		} else {
			return actual;
		}
	}
	
	public Seleccion localizarSeleccionAnterior(String nombreSel) throws SeleccionNoExisteException{
		Seleccion anterior = null;
		Seleccion actual = primeraSeleccion;
		while(actual != null && !(nombreSel.equals(actual.getPais()))) {
			anterior = actual;
			actual = actual.getSiguienteSeleccion();
		}
		if(actual == null) {
			throw new SeleccionNoExisteException();
		} else {
			return anterior;
		}
	}
	
	public void eliminarSeleccion(String nombre) throws SeleccionNoExisteException{
		if(primeraSeleccion == null) {
			throw new SeleccionNoExisteException();
		} else if(nombre.equals(primeraSeleccion.getPais())) {
			primeraSeleccion = primeraSeleccion.getSiguienteSeleccion();
		} else {
			Seleccion anterior = localizarSeleccionAnterior(nombre);
			if(anterior == null) {
				throw new SeleccionNoExisteException();
			}
			anterior.desconectarSiguiente();
		}
	}	
}