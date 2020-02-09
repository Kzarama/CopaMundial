package pruebas;

import org.junit.jupiter.api.Test;

import junit.framework.TestCase;
import mundo.Jugador;

class JugadorTest extends TestCase {

	private Jugador jugador;
	
	private void escenarioUno() {
		jugador = new Jugador("img/james.jpg", "James Rodriguez", "Volante", 254, "21/07/1992", 174, null);
	}
	
	@Test
	public void testDarImagen() {
		escenarioUno();
		assertEquals("img/james.jpg", jugador.getImagen());
	}
	
	@Test
	public void testDarNombre() {
		escenarioUno();
		assertEquals("James Rodriguez", jugador.getNombre());
	}
	
	@Test
	public void testDarPosicion() {
		escenarioUno();
		assertEquals("Volante", jugador.getPosicion());
	}
	
	@Test
	public void testDarPuntajeFIFA() {
		escenarioUno();
		assertEquals(254, jugador.getPuntajeFIFA());
	}
	
	@Test
	public void testDarFechaNacimiento() {
		escenarioUno();
		assertEquals("21/07/1992", jugador.getFechaNac());
	}
	
	@Test
	public void testDarAltura() {
		escenarioUno();
		assertEquals(174.0, jugador.getAltura());
	}
	
	@Test
	public void testCalcularEdad() {
		escenarioUno();
		assertEquals(25, jugador.calcularEdad());
	}
	
	@Test
	public void testToString() {
		escenarioUno();
		assertEquals("James Rodriguez", jugador.toString());
	}

}
