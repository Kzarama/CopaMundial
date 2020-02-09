package pruebas;
import org.junit.jupiter.api.Test;

import excepciones.SeleccionNoExisteException;
import excepciones.SeleccionYaExisteException;
import junit.framework.TestCase;
import mundo.Campeonato;
import mundo.Seleccion;

class CampeonatoTest extends TestCase{
	
	private Campeonato campeonato;
	
	private void escenarioUno() throws SeleccionYaExisteException {
		campeonato = new Campeonato();
		campeonato.insertarSeleccion(new Seleccion("Colombia", "colombia.jpg", 375));
		campeonato.insertarSeleccion(new Seleccion("Brasil", "brasil.jpg", 672));
		campeonato.insertarSeleccion(new Seleccion("España", "españa.jpg", 243));
	}
	
	private void escenarioDos() {
		campeonato = new Campeonato();
	}

	@Test
	public void testDarNumeroSelecciones() {
		try {
			escenarioUno();
		} catch (SeleccionYaExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(3, campeonato.darNumeroSelecciones());	
	}
	
	@Test
	public void testEstaVaciaTrue() {
		escenarioDos();
		assertTrue(campeonato.estaVacia());
	}
	
	@Test
	public void testEstaVaciaFalse() {
		try {
			escenarioUno();
		} catch (SeleccionYaExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertFalse(campeonato.estaVacia());
	}
	
	@Test
	public void testBuscarSeleccionExistente() {
		try {
			escenarioUno();
		} catch (SeleccionYaExisteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Seleccion s = null;
		try {
			s = campeonato.buscarSeleccion("Colombia");
		}catch(SeleccionNoExisteException e) {
			
		}
		assertEquals("Colombia", s.getPais());
	}
	
	@Test
	public void testBuscarSeleccionInexistente() {
		try {
			escenarioUno();
		} catch (SeleccionYaExisteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String ms = "";
		try {
			campeonato.buscarSeleccion("Perú");
		}catch(SeleccionNoExisteException e) {
			ms = e.getMessage();
		}
		assertEquals("La seleccion con el nombre Perú no existe", ms);
	}
	
	@Test
	public void insertarSeleccion() {
		escenarioDos();
		int viejo = campeonato.darNumeroSelecciones();
		try {
			campeonato.insertarSeleccion(new Seleccion("Argentina", "argentina.jpg", 672));
		} catch (SeleccionYaExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int nuevo = campeonato.darNumeroSelecciones();
		assertEquals(nuevo, viejo+1);
	}
	
//	@Test
//	public void testLocalizarAnteriorPorNombre() {
//		escenarioUno();
//		Seleccion s1 =campeonato.localizarAnteriorPorNombre("Brasil");
//		Seleccion s2 = null;
//		try {
//			s2 = campeonato.buscarSeleccion("Colombia");
//		}catch(SeleccionNoExisteException e) {
//			
//		}
//		assertEquals(s2, s1);
//	}
	
//	@Test
//	public void testEliminarSeleccionPorNombre() {
//		escenarioUno();
//		int viejo = campeonato.darNumeroSelecciones();
//		try{
//			campeonato.eliminarSeleccionPorNombre("Colombia");
//		}catch(SeleccionNoExisteException e) {
//			
//		}
//		int nuevo = campeonato.darNumeroSelecciones();
//		assertEquals(nuevo, viejo-1);
//	}
	
//	@Test
//	public void testEliminarSeleccionInexistentePorNombre() {
//		escenarioUno();
//		String ms = "";
//		try {
//			campeonato.eliminarSeleccionPorNombre("Argentina");
//		}catch(SeleccionNoExisteException e) {
//			ms = e.getMessage();
//		}
//		assertEquals("La seleccion con el nombre Argentina no existe", ms);
//	}
	
//	@Test
//	public void testLocalizarAnteriorPorPosicion() {
//		escenarioUno();
//		Seleccion s1 = campeonato.localizarAnteriorPorPosicion(1);
//		Seleccion s2 = null;
//		try {
//			s2 = campeonato.buscarSeleccion("Colombia");
//		}catch(SeleccionNoExisteException e) {
//			
//		}
//		assertEquals(s2, s1);
//	}
	
//	@Test
//	public void testSeleccionYaExisteTrue() {
//		escenarioUno();
//		assertTrue(campeonato.seleccionYaExiste("Colombia"));
//		
//	}
//	
//	@Test
//	public void testSeleccionYaExisteFalse() {
//		escenarioUno();
//		assertFalse(campeonato.seleccionYaExiste("Argentina"));
//		
//	}
//	
//	@Test
//	public void testEliminarSeleccionPorPosicion() {
//		escenarioUno();
//		int viejo = campeonato.darNumeroSelecciones();
//		campeonato.eliminarSeleccionPorPosicion(1);
//		int nuevo = campeonato.darNumeroSelecciones();
//		assertEquals(nuevo, viejo-1);
//	}

}
