package excepciones;

public class SeleccionYaExisteException extends Exception {
	public SeleccionYaExisteException() {
		super("LA SELECCION YA EXISTE");
	}
}
