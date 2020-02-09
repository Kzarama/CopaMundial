package excepciones;

public class SeleccionYaExisteException extends Exception{
	
	public SeleccionYaExisteException() {
		super("Esta selección ya existe");
	}
}
