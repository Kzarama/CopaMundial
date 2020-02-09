package excepciones;

public class CampoVacioException extends Exception {
	
	public CampoVacioException() {
		super("Hay un campo vacío");
	}
}
