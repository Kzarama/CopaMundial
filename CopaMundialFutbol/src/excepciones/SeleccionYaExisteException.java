package excepciones;

public class SeleccionYaExisteException extends Exception{
	
	public SeleccionYaExisteException() {
		super("Esta selecci�n ya existe");
	}
}
