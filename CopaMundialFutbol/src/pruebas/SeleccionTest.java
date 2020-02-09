package pruebas;

import org.junit.jupiter.api.Test;

import excepciones.JugadorNoExisteException;
import junit.framework.TestCase;
import mundo.Jugador;
import mundo.Seleccion;

class SeleccionTest extends TestCase{

	Seleccion seleccion;
	
	@Test
	private void escenarioUno() {
		seleccion = new Seleccion("Colombia", "colombia.jpg", 145);
		seleccion.insertarJugador(new Jugador("flacao.jpg", "Flacao", "Delantero", 365, "05/04/1986", 173, null));
		seleccion.insertarJugador(new Jugador("james.jpg", "James", "Portero", 400, "05/07/1991", 174, null));
		seleccion.insertarJugador(new Jugador("cuadrado.jpg", "Cuadradon", "Lateral", 156, "24/01/1992", 175, null));
	}
	
	private void escenarioDos() {
		seleccion = new Seleccion("Brasil", "brasil.jpg", 452);
	}
	
	@Test
	public void testDarPais() {
		escenarioUno();
		assertEquals("Colombia", seleccion.getPais());
	}
	
	@Test
	public void testDarImagen() {
		escenarioUno();
		assertEquals("colombia.jpg", seleccion.getImagen());
	}
	
	@Test
	public void testDarPuntos() {
		escenarioUno();
		assertEquals(145, seleccion.getPuntos());
	}
	
	@Test
	void testDarNumeroJugadores() {
		escenarioUno();
		assertEquals(3, seleccion.darNumeroJugadores());
	}
	
	@Test
	public void testGetPromedioAltura() {
		escenarioUno();
		assertEquals(174.0, seleccion.getPromedioAltura());
	}
	
	@Test
	public void testGetPromedioEdad() {
		escenarioUno();
		assertEquals(28.0, seleccion.getPromedioEdad());
	}
	
	@Test
	public void testGetPromedioFIFA() {
		escenarioUno();
		assertEquals(307.0, seleccion.getPromedioFIFA());
	}
	
	@Test
	public void testEstaVaciaTrue() {
		escenarioDos();
		assertTrue(seleccion.estaVacia());
	}
	
	@Test
	public void testEstaVaciaFalse() {
		escenarioUno();
		assertFalse(seleccion.estaVacia());
	}
	
	@Test
	public void testInsertarJugador() {
		escenarioDos();
		int viejo = seleccion.darNumeroJugadores();
		seleccion.insertarJugador(new Jugador("neymar.jpg", "Neymar", "Delantero", 476, "01/10/1992", 173, null));
		int actual = seleccion.darNumeroJugadores();
		assertEquals(actual, viejo+1);
	}
	
	@Test
	public void testBuscarJugadorExistente() {
		escenarioUno();
		Jugador j = null;
		try {
			j = seleccion.buscarJugador(seleccion.getPrimero(seleccion), "Flacao");
		}catch(JugadorNoExisteException e) {
			
		}
		assertEquals("Flacao", j.getNombre());
	}
	
	@Test
	public void testBuscarJugadorInexistente() {
		escenarioUno();
		String ms = "";
		try {
			seleccion.buscarJugador(seleccion.getPrimero(seleccion), "Ordoñez");
		}catch(JugadorNoExisteException e) {
			ms = e.getMessage();
		}
		assertEquals("El jugador con el nombre Ordoñez no existe en esta selección", ms);
	}
	
	@Test
	public void testLocalizarAnteriorPorNombre() {
		escenarioUno();
		Jugador j1 = seleccion.localizarAnteriorPorNombre(seleccion.getPrimero(seleccion), null, "James");
		Jugador j2 = null;
		try {
			j2 = seleccion.buscarJugador(seleccion.getPrimero(seleccion), "Flacao");
		}catch(JugadorNoExisteException e) {
			
		}
		assertEquals(j2, j1);
	}
	
	@Test
	public void testEliminarJugadorPorNombre() {
		escenarioUno();
		int viejo = seleccion.darNumeroJugadores();
		try{
			seleccion.eliminarJugadorPorNombre("James");
		}catch(JugadorNoExisteException e) {
			
		}
		int actual = seleccion.darNumeroJugadores();
		assertEquals(actual, viejo-1);
	}
	
	@Test
	public void testEliminarJugadorInexistentePorNombre() {
		escenarioDos();
		String ms = "";
		try {
			seleccion.eliminarJugadorPorNombre("Quintero");
		}catch(JugadorNoExisteException s) {
			ms = s.getMessage();
		}
		assertEquals("El jugador con el nombre Quintero no existe en esta selección", ms);
	}
	
	@Test
	public void testLocalizarAnteriorPorPosicion() {
		escenarioUno();
		Jugador j1 = seleccion.localizarAnteriorPorPosicion(1);
		Jugador j2 = null;
		try {
			j2 = seleccion.buscarJugador(seleccion.getPrimero(seleccion), "Flacao");
		}catch(JugadorNoExisteException e) {
			
		}
		assertEquals(j2, j1);
	}
	
	@Test
	public void testEliminarJugadorPorPosicion() {
		escenarioUno();
		int viejo = seleccion.darNumeroJugadores();
		seleccion.eliminarJugadorPorPosicion(1);
		int nuevo = seleccion.darNumeroJugadores();
		assertEquals(nuevo, viejo-1);
	}
	
	@Test
	public void jugadorYaExisteTrue() {
		escenarioUno();
		assertTrue(seleccion.jugadorYaExiste(seleccion.getPrimero(seleccion), "Flacao"));
	}
	
	@Test
	public void jugadorYaExisteFalse() {
		escenarioUno();
		assertFalse(seleccion.jugadorYaExiste(seleccion.getPrimero(seleccion), "Larvo"));
	}
}
