package pruebas;

import org.junit.Test;

import junit.framework.TestCase;
import modelo.Jugador;

public class JugadorTest extends TestCase {
	
	Jugador j;
	
	private void escenario1() {
		j = new Jugador("", "Neuer", "Portero", "27/03/1986", 94, 193, null);
	}
	
	private void escenario2() {
		j = new Jugador("", "Neuer", "Portero", "27/03/1986", 94, 193, null);
	}
	
	private void escenario3() {
		j = new Jugador("", "Neuer", "Portero", "27/03/1986", 94, 193, null);
	}
	
	private void escenario4() {
		j = new Jugador("", "Neuer", "Portero", "27/03/1986", 94, 193, null);
	}
	
	private void escenario5() {
		j = new Jugador("", "Neuer", "Portero", "27/03/1986", 94, 193, null);
	}
	
	private void escenario6() {
		j = new Jugador("", "Neuer", "Portero", "27/03/1986", 94, 193, null);
	}
	
	private void escenario7() {
		j = new Jugador("", "Neuer", "Portero", "27/03/1986", 94, 193, null);
	}
	
	private void escenario8() {
		j = new Jugador("", "Neuer", "Portero", "27/03/1986", 94, 193, null);
	}
	
	private void escenario9() {
		j = new Jugador("", "Neuer", "Portero", "27/03/1986", 94, 193, null);
	}
	
	private void escenario10() {
		j = new Jugador("", "Neuer", "Portero", "27/03/1986", 94, 193, null);
	}
	
	@Test
	public void testGetImagen() {
		escenario1();
		assertEquals("", j.getImagen());
	}
	
	@Test
	public void testGetNombre() {
		escenario2();
		assertEquals("Neuer", j.getNombre());
	}
	
	@Test
	public void testGetPosicion() {
		escenario3();
		assertEquals("Portero", j.getPosicion());
	}
	
	@Test
	public void testGetFechaNacimiento() {
		escenario4();
		assertEquals("27/03/1986", j.getFechaNacimiento());
	}
	
	@Test
	public void testGetPuntajeFIFA() {
		escenario5();
		assertEquals(94, j.getPuntajeFIFA());
	}
	
	@Test
	public void testGetAltura() {
		escenario6();
		assertEquals(193.0, j.getAltura());
	}
	
	@Test
	public void testGetSiguiente() {
		escenario7();
		assertNull(j.getSiguiente());
	}
	
	@Test
	public void testSetSiguiente() {
		escenario8();
		j.setSiguiente(new Jugador("", "Kroos", "Centrocampista", "04/01/1990", 89, 183, null));
		assertEquals("Kroos", j.getSiguiente().getNombre());
	}
	
	@Test
	public void testCambiarSiguiente() {
		escenario9();
		j.cambiarSiguiente(new Jugador("", "Kroos", "Centrocampista", "04/01/1990", 89, 183, null));
		assertEquals("Kroos", j.getSiguiente().getNombre());
	}
	
	@Test
	public void testDesconectarSiguiente() {
		escenario10();
		j.setSiguiente(new Jugador("", "Kroos", "Centrocampista", "04/01/1990", 89, 183, null));
		j.desconectarSiguiente();
		assertNull(j.getSiguiente());
	}
	
}
