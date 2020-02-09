package excepciones;

public class JugadorNoExisteException extends Exception {

	public JugadorNoExisteException(String nombreInexistente) {
		super("El jugador con el nombre "+nombreInexistente+" no existe en esta selección");
	}
	
}
