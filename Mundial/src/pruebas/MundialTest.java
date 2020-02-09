package pruebas;

import org.junit.Test;

import excepciones.SeleccionNoExisteException;
import junit.framework.TestCase;
import modelo.Mundial;
import modelo.Seleccion;

public class MundialTest extends TestCase {
	
	Mundial m;
	
	private void escenario1() {
		m = new Mundial();
	}
	
	private void escenario2() {
		m = new Mundial();
	}
	
	private void escenario3() {
		m = new Mundial();
	}
	
	private void escenario4() {
		m = new Mundial();
	}
	
	private void escenario5() {
		m = new Mundial();
	}
	
	@Test
	public void testSeleccionesVacio() {
		escenario1();
		assertTrue(m.seleccionesVacio());
	}
	
	@Test
	public void testInsertarSeleccion() {
		escenario2();
		m.insertarSeleccion(new Seleccion("Alemania", "", 0, null));
		try {
			assertEquals(m.localizarSeleccion("Alemania"), m.getPrimeraSeleccion());
		} catch (SeleccionNoExisteException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testLocalizarSeleccion() {
		escenario3();
		m.insertarSeleccion(new Seleccion("Alemania", "", 0, null));
		try {
			assertEquals("Alemania", m.localizarSeleccion("Alemania").getPais());
		} catch (SeleccionNoExisteException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testLocalizarSeleccionAnterior() {
		escenario4();
		m.insertarSeleccion(new Seleccion("Alemania", "", 0, null));
		m.insertarSeleccion(new Seleccion("España", "", 0, null));
		try {
			assertEquals(m.localizarSeleccion("Alemania"), m.localizarSeleccionAnterior("España"));
		} catch (SeleccionNoExisteException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testEliminarSeleccion() {
		escenario5();
		m.insertarSeleccion(new Seleccion("Alemania", "", 0, null));
		try {
			m.eliminarSeleccion("Alemania");
			assertNull(m.getPrimeraSeleccion());
		} catch (SeleccionNoExisteException e) {
			e.printStackTrace();
		}
	}
	
}
