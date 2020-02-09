package pruebas;

import org.junit.Test;

import excepciones.JugadorNoExisteException;
import excepciones.SeleccionNoExisteException;
import junit.framework.TestCase;
import modelo.Jugador;
import modelo.Mundial;
import modelo.Seleccion;

public class SeleccionTest extends TestCase {
	
	Seleccion s;
	Mundial m;
	
	private void escenario1() {
		s = new Seleccion("Alemania", "", 0, null);
	}
	
	private void escenario2() {
		s = new Seleccion("Alemania", "", 0, null);
	}
	
	private void escenario3() {
		s = new Seleccion("Alemania", "", 0, null);
	}
	
	private void escenario4() {
		s = new Seleccion("Alemania", "", 0, null);
	}
	
	private void escenario5() {
		m = new Mundial();
		s = new Seleccion("Alemania", "", 0, null);
	}
	
	private void escenario6() {
		m = new Mundial();
		s = new Seleccion("Alemania", "", 0, null);
	}
	
	private void escenario7() {
		s = new Seleccion("Alemania", "", 0, null);
	}
	
	private void escenario8() {
		s = new Seleccion("Alemania", "", 0, null);
	}
	
	private void escenario9() {
		s = new Seleccion("Alemania", "", 0, null);
	}
	
	private void escenario10() {
		s = new Seleccion("Alemania", "", 0, null);
	}
	
	private void escenario11() {
		s = new Seleccion("Alemania", "", 0, null);
	}
	
	private void escenario12() {
		s = new Seleccion("Alemania", "", 0, null);
	}
	
	private void escenario13() {
		s = new Seleccion("Alemania", "", 0, null);
	}
	
	private void escenario14() {
		s = new Seleccion("Alemania", "", 0, null);
	}
	
	private void escenario15() {
		s = new Seleccion("Alemania", "", 0, null);
	}
	
	private void escenario16() {
		s = new Seleccion("Alemania", "", 0, null);
	}
	
	@Test
	public void testGetPais() {
		escenario1();
		assertEquals("Alemania", s.getPais());
	}
	
	@Test
	public void testGetImagen() {
		escenario2();
		assertEquals("", s.getImagen());
	}
	
	@Test
	public void testGetPuntos() {
		escenario3();
		assertEquals(0, s.getPuntos());
	}
	
	@Test
	public void testGetSiguienteSeleccion() {
		escenario4();
		assertNull(s.getSiguienteSeleccion());
	}
	
	@Test
	public void testSetSiguienteSeleccion() {
		escenario5();
		s.setSiguienteSeleccion(new Seleccion("Inglaterra", "", 0, null));
		m.setSeleccion(s);
		try {
			assertEquals("Inglaterra", m.localizarSeleccion("Inglaterra").getPais());
		} catch (SeleccionNoExisteException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCambiarSiguiente() {
		escenario6();
		s.cambiarSiguiente(new Seleccion("Belgica", "", 0, null));
		m.setSeleccion(s);
		assertEquals("Belgica", m.getPrimeraSeleccion().getSiguienteSeleccion().getPais());
	}
	
	@Test
	public void testDesconectarSiguiente() {
		escenario7();
		s.setSiguienteSeleccion(new Seleccion("Belgica", "", 0, null));
		s.desconectarSiguiente();
		assertNull(s.getSiguienteSeleccion());
	}
	
	@Test
	public void testGetPrimerJugador() {
		escenario8();
		assertNull(s.getPrimerJugador());
	}
	
	@Test
	public void testJugadoresVacio() {
		escenario9();
		assertTrue(s.JugadoresVacio());
	}
	
	@Test
	public void testIngresarJugador() {
		escenario10();
		s.insertarJugador(new Jugador("", "Neuer", "Portero", "27/03/1986", 94, 193, null));
		assertEquals("Neuer", s.getPrimerJugador().getNombre());
	}
	
	@Test
	public void testLocalizar() {
		escenario11();
		s.insertarJugador(new Jugador("", "Neuer", "Portero", "27/03/1986", 94, 193, null));
		try {
			assertEquals("Neuer", s.localizar("Neuer").getNombre());
		} catch (JugadorNoExisteException e) {
			
		}
	}
	
	@Test
	public void testLocalizarJugadorAnterior() {
		escenario12();
		s.insertarJugador(new Jugador("", "Neuer", "Portero", "27/03/1986", 94, 193, null));
		s.insertarJugador(new Jugador("", "Kroos", "Centrocampista", "04/01/1990", 89, 183, null));
		try {
			assertEquals("Neuer", s.localizarJugadorAnterior("Kroos").getNombre());
		} catch (JugadorNoExisteException e) {
			
		}
	}
	
	@Test
	public void testEliminarJugador() {
		escenario13();
		s.insertarJugador(new Jugador("", "Neuer", "Portero", "27/03/1986", 94, 193, null));
		try {
			s.eliminarJugador("Neuer");
		} catch (JugadorNoExisteException e) {
			e.printStackTrace();
		}
		assertNull(s.getPrimerJugador());
	}
	
	@Test
	public void testCalcularPromedioAltura() {
		escenario14();
		s.insertarJugador(new Jugador("", "Neuer", "Portero", "27/03/1986", 94, 193, null));
		try {
			assertEquals(193.0, s.calcularPromedioAltura());
		} catch (JugadorNoExisteException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCalcularPromedioEdad() {
		escenario15();
		s.insertarJugador(new Jugador("", "Neuer", "Portero", "27/03/1986", 94, 193, null));
		try {
			assertEquals(32.0, s.calcularPromedioEdad());
		} catch (JugadorNoExisteException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCalcularPromedioFIFA() {
		escenario16();
		s.insertarJugador(new Jugador("", "Neuer", "Portero", "27/03/1986", 94, 193, null));
		try {
			assertEquals(94.0, s.calcularPromedioFIFA());
		} catch (JugadorNoExisteException e) {
			e.printStackTrace();
		}
	}
	
}
