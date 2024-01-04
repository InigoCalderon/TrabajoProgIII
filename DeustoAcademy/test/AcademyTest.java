import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import DeustoAcademy.Academy;
import DeustoAcademy.Docente;
import DeustoAcademy.Estudiante;
import DeustoAcademy.Grupo;
import DeustoAcademy.Idioma;
import DeustoAcademy.Tarea;

public class AcademyTest {
	
	protected Academy datos;
	protected Estudiante prueba;
	
	@Before
	public  void setUp() throws Exception {
		 datos = new Academy();
		 prueba = new Estudiante("prueba", "apellido", 634440245, "@opendeusto.es", "16023431P", "user", "pass", new ArrayList<Idioma>());
		prueba.getIdiomas().add(Idioma.Castellano);
		datos.getEstudiantes().add(prueba);
	}
	
	@Test
	public void TestmetodoActivoAcadaemia(){
		
		
		
		Float numero = datos.metodoActivoAcadaemia(datos);
		assertTrue(numero > 0); // Caso de solo haber ingresos (tarifas)
		
		Academy datos2 = new Academy();
		Docente docente2 = new Docente("nombre2", "apellido2", "16092524P", "@opendeusto.es", 634440235, "user2", "pass2", Idioma.Frances);
		Grupo grupo = new Grupo(Idioma.Frances, "grupo", docente2, new ArrayList<Estudiante>(), new ArrayList<Tarea>());
		datos2.getDocentes().add(docente2);
		Float numero2 = datos2.metodoActivoAcadaemia(datos2);
		assertTrue(numero2 < 0); // Caso de solo haber gastos (sueldos)
		
	}
	
	@Test
	public void TestmapaTarifaEstudiante() {
		
		
		TreeMap<Estudiante, Float> mapa = datos.mapaTarifaEstudiante(datos);
		 // Caso de solo estudiar castellano
		assertTrue(mapa.get(prueba) ==125f );
		
		Academy datos2 = new Academy();
		ArrayList<Idioma> lista =	new ArrayList<Idioma>();
		lista.add(Idioma.Frances);
		lista.add(Idioma.Euskera);
		Estudiante prueba2 = new Estudiante("prueba2", "apellido2", 632240245, "@opendeusto.es", "16623431P", "user2", "pass2", lista);
		datos2.getEstudiantes().add(prueba2);
		TreeMap<Estudiante, Float> mapa2 = datos2.mapaTarifaEstudiante(datos2);
		assertTrue(mapa2.get(prueba2) == 265 );
		
		// Caso de estudiar varios idiomas
	}
	
	@Test
	public void TestmapaSueldoDocente() {
		
		
		Docente docente2 = new Docente("nombre", "apellido", "16044524P", "@opendeusto.es", 634488235, "user", "pass", Idioma.Castellano);
		datos.getDocentes().add(docente2);
		Grupo grupo = new Grupo(Idioma.Castellano, "grupo", docente2, new ArrayList<Estudiante>(), new ArrayList<Tarea>());
		datos.getGrupos().add(grupo);
		TreeMap<Docente, Float> mapa2 = datos.mapaSueldoDocente(datos);
		// Caso de un solo docente que imparte Castellano y además está en un grupo
		assertTrue( mapa2.get(docente2) ==1050f );
	}
	
	@Test
	public void TestactualizarMapaEstudiante() {
		
		Docente docente2 = new Docente("nombre", "apellido", "16044524P", "@opendeusto.es", 634488235, "user", "pass", Idioma.Castellano);
		datos.getDocentes().add(docente2);
		ArrayList<Estudiante> lista = new ArrayList<Estudiante>();
		lista.add(prueba);
		Grupo grupo = new Grupo(Idioma.Castellano, "grupo", docente2, lista , new ArrayList<Tarea>());
		datos.getGrupos().add(grupo);
		
		HashMap<String, ArrayList<Estudiante>> mapa = datos.actualizarMapaEstudiante();
		assertTrue(mapa.get("Castellano").contains(prueba));  // Caso de tener un estudiante de Castellano
	}
}
