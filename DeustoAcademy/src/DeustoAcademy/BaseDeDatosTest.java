package DeustoAcademy;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BaseDeDatosTest {
	protected BaseDeDatos bd;
	
	
	@Before
	public void setUp() throws Exception {
		bd = new BaseDeDatos();
		try {
			bd.connect("jdbc:sqlite:res.db.prueba.db");			// Se usa una BD de pruebas para esto
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
	}

	@After
	public void tearDown() throws Exception {
		try {
			bd.disconnect();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	
	
	@Test
	public void TestcargarAdministradores() throws SQLException {
		ArrayList<Administrador> administradores = bd.cargarAdministradores();
		assertTrue(administradores.size() > 0);
		assertNotNull(administradores);
		
		for (Administrador administrador : administradores) {
			assertNotNull(administrador.getNombre());
			assertNotNull(administrador.getApellido());
			assertNotNull(administrador.getContrasena());
			assertNotNull(administrador.getCorreo());
			assertNotNull(administrador.getDni());
			assertNotNull(administrador.getTelefono());
			assertNotNull(administrador.getUsuario());
		}
		}
	@Test
	public void TestcargarEstudiantes() throws SQLException {
		ArrayList<Estudiante> estudiantes = bd.cargarEstudiantes();
		assertTrue(estudiantes.size() > 0);
		assertNotNull(estudiantes);
		
		for (Estudiante estudiante : estudiantes) {
			assertNotNull(estudiante.getNombre());
			assertNotNull(estudiante.getApellido());
			assertNotNull(estudiante.getContrasena());
			assertNotNull(estudiante.getCorreo());
			assertNotNull(estudiante.getDni());
			assertNotNull(estudiante.getTelefono());
			assertNotNull(estudiante.getUsuario());
		}
	}
	@Test
	public void TestcargarDocentes() throws SQLException {
		ArrayList<Docente> docentes = bd.cargarDocentes();
		assertTrue(docentes.size() > 0);
		assertNotNull(docentes);
		
		for (Docente docente : docentes) {
			assertNotNull(docente.getNombre());
			assertNotNull(docente.getApellido());
			assertNotNull(docente.getContrasena());
			assertNotNull(docente.getCorreo());
			assertNotNull(docente.getDni());
			assertNotNull(docente.getTelefono());
			assertNotNull(docente.getUsuario());
		}
	}
	@Test
	public void TestcargarGrupos() throws SQLException {
		ArrayList<Grupo> grupos = bd.cargarGrupos();
		assertTrue(grupos.size() > 0);
		assertNotNull(grupos);
		
		for (Grupo grupo : grupos) {
			assertNotNull(grupo.getNombre());
			assertNotNull(grupo.getDocente());
			assertNotNull(grupo.getIdioma());
			assertNotNull(grupo.getEstudiantes());
			assertNotNull(grupo.getTareas());
			
		}
	}
	@Test
	public void TestcargarInscritosExamenFinal() throws SQLException {
		HashMap<Estudiante, HashMap<Idioma, Boolean>> inscritosExamenFinal = bd.cargarInscritosExamenFinal();
		assertTrue(inscritosExamenFinal.size() > 0);
		assertNotNull(inscritosExamenFinal);
	}
	
	@Test
	public void TestcargarNotasTareas() throws SQLException {
		HashMap<Estudiante, HashMap<Grupo, HashMap<Tarea, String>>> notasTareas = bd.cargarNotasTareas();
		assertTrue(notasTareas.size() > 0);
		assertNotNull(notasTareas);
	}
	@Test
	public void TestcargarNotasExamenFinal() throws SQLException {
		HashMap<Estudiante, HashMap<Idioma, String>>   notasExamenFinal = bd.cargarNotasExamenFinal();
		assertTrue(notasExamenFinal.size() > 0);
		assertNotNull(notasExamenFinal);
	}
	@Test
	public void TestcargarTemarioData() throws SQLException{   					// NO LO SÉ HACER, QUE LO HAGA OTRO (UNAI) 	// NO LO SÉ HACER, QUE LO HAGA OTRO (UNAI) 	// NO LO SÉ HACER, QUE LO HAGA OTRO (UNAI)
																				// NO LO SÉ HACER, QUE LO HAGA OTRO (UNAI) 	// NO LO SÉ HACER, QUE LO HAGA OTRO (UNAI) 	// NO LO SÉ HACER, QUE LO HAGA OTRO (UNAI)
	}																			// NO LO SÉ HACER, QUE LO HAGA OTRO (UNAI) 	// NO LO SÉ HACER, QUE LO HAGA OTRO (UNAI) 	// NO LO SÉ HACER, QUE LO HAGA OTRO (UNAI)
	
	@Test
	public void TestguardarAdministrador() throws SQLException {
		Administrador admin = new Administrador("Unai", "Egusquiza", "16092526A", "unai@opendeusto.es", 634444233, "user", "pass");
		bd.guardarAdministrador(admin);
		
	}
}
