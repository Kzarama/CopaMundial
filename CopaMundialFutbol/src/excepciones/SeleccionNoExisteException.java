package excepciones;

public class SeleccionNoExisteException extends Exception {
	
	public SeleccionNoExisteException(String nombreInexistente) {
		super("La seleccion con el nombre "+nombreInexistente+" no existe");
	}

}
