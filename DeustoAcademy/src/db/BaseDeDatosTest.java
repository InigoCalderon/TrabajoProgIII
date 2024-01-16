package db;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Array;
import java.sql.Clob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import domain.Administrador;
import domain.Docente;
import domain.Estudiante;
import domain.Grupo;
import domain.Idioma;
import domain.Tarea;
import main.Academy;

public class BaseDeDatosTest {
	protected BaseDeDatos bd;
	protected Academy academy = new Academy();
	
	
	@Before
	public void setUp() throws Exception {
		bd = new BaseDeDatos();
		try {
			bd.connect("jdbc:sqlite:res.db.academyPruebas.db");			// Se usa una BD de pruebas para esto
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
		assertTrue("cargarAdministradores da una lista vacía",administradores.size() > 0);
		assertNotNull("cargarAdministradores es null",administradores);
		
		for (Administrador administrador : administradores) {
			assertNotNull("Nombre de administrador es null",administrador.getNombre());
			assertNotNull("Apellido de administrador es null",administrador.getApellido());
			assertNotNull("Contraseña de administrador es null",administrador.getContrasena());
			assertNotNull("Correo de administrador es null",administrador.getCorreo());
			assertNotNull("Dni de administrador es null",administrador.getDni());
			assertNotNull("Telefono de administrador es null",administrador.getTelefono());
			assertNotNull("Usuario de administrador es null",administrador.getUsuario());
		}
		}
	@Test
	public void TestcargarEstudiantes() throws SQLException {
		ArrayList<Estudiante> estudiantes = bd.cargarEstudiantes();
		assertTrue("cargarEstudiantes da una lista vacía",estudiantes.size() > 0);
		assertNotNull("cargarEstudiantes es null",estudiantes);
		
		for (Estudiante estudiante : estudiantes) {
			assertNotNull("Nombre de estudiante es null",estudiante.getNombre());
			assertNotNull("Apellido de estudiante es null",estudiante.getApellido());
			assertNotNull("contraseña de estudiante es null",estudiante.getContrasena());
			assertNotNull("correo de estudiante es null",estudiante.getCorreo());
			assertNotNull("dni de estudiante es null",estudiante.getDni());
			assertNotNull("telefono de estudiante es null",estudiante.getTelefono());
			assertNotNull("usuario de estudiante es null",estudiante.getUsuario());
		}
	}
	@Test
	public void TestcargarDocentes() throws SQLException {
		ArrayList<Docente> docentes = bd.cargarDocentes();
		assertTrue("cargarDocentes da una lista vacía",docentes.size() > 0);
		assertNotNull("cargarDocentes es null",docentes);
		
		for (Docente docente : docentes) {
			assertNotNull("Nombre de docente es null",docente.getNombre());
			assertNotNull("apellido de docente es null",docente.getApellido());
			assertNotNull("contraseña de docente es null",docente.getContrasena());
			assertNotNull("correo de docente es null",docente.getCorreo());
			assertNotNull("dni de docente es null",docente.getDni());
			assertNotNull("telefono de docente es null",docente.getTelefono());
			assertNotNull("usuario de docente es null",docente.getUsuario());
		}
	}
	@Test
	public void TestcargarGrupos() throws SQLException {
		ArrayList<Grupo> grupos = bd.cargarGrupos(academy);
		assertTrue("cargarGrupos da una lista vacía",grupos.size() > 0);
		assertNotNull("cargarGrupos es null",grupos);
		
		for (Grupo grupo : grupos) {
			assertNotNull("Nombre de grupo es null",grupo.getNombre());
			assertNotNull("docente de grupo es null",grupo.getDocente());
			assertNotNull("idioma de grupo es null",grupo.getIdioma());
			assertNotNull("estudiantes de grupo es null",grupo.getEstudiantes());
			assertNotNull("tareas de grupo es null",grupo.getTareas());
			
		}
	}
	@Test
	public void TestcargarInscritosExamenFinal() throws SQLException {
		HashMap<Estudiante, HashMap<Idioma, Boolean>> inscritosExamenFinal = bd.cargarInscritosExamenFinal(academy);
		assertTrue("cargarInscritosExamenFinal da una lista vacía",inscritosExamenFinal.size() > 0);
		assertNotNull("cargarInscritosExamenFinal es null",inscritosExamenFinal);
	}
	
	@Test
	public void TestcargarNotasTareas() throws SQLException {
		HashMap<Estudiante, HashMap<Grupo, HashMap<Tarea, String>>> notasTareas = bd.cargarNotasTareas(academy);
		assertTrue("cargarNotasTareas da una lista vacía",notasTareas.size() > 0);
		assertNotNull("cargarNotasTareas es null",notasTareas);
	}
	@Test
	public void TestcargarNotasExamenFinal() throws SQLException {
		HashMap<Estudiante, HashMap<Idioma, String>>   notasExamenFinal = bd.cargarNotasExamenFinal(academy);
		assertTrue("cargarNotasExamenFinal da una lista vacía",notasExamenFinal.size() > 0);
		assertNotNull("cargarNotasExamenFinal es null",notasExamenFinal);
	}
	@Test
	public void TestcargarTemarioData() throws SQLException{   					// NO LO SÉ HACER, QUE LO HAGA OTRO (UNAI) 	// NO LO SÉ HACER, QUE LO HAGA OTRO (UNAI) 	// NO LO SÉ HACER, QUE LO HAGA OTRO (UNAI)
																				// NO LO SÉ HACER, QUE LO HAGA OTRO (UNAI) 	// NO LO SÉ HACER, QUE LO HAGA OTRO (UNAI) 	// NO LO SÉ HACER, QUE LO HAGA OTRO (UNAI)
	}																			// NO LO SÉ HACER, QUE LO HAGA OTRO (UNAI) 	// NO LO SÉ HACER, QUE LO HAGA OTRO (UNAI) 	// NO LO SÉ HACER, QUE LO HAGA OTRO (UNAI)
	
	@Test
	public void TestguardarAdministrador() throws SQLException {
		Administrador admin = new Administrador("Unai", "Egusquiza", "16092526A", "unai@opendeusto.es", 634444233, "user", "pass");
		bd.guardarAdministrador(admin);
		
		try (PreparedStatement stmt = BaseDeDatos.conexion.prepareStatement("SELECT * FROM Administrador WHERE DNI = "+admin.getDni());
				ResultSet rs = stmt.executeQuery()) {
					while (rs.next()) {
						String nombre = rs.getString("nombre");
						String apellido = rs.getString("apellido");
						String dni = rs.getString("dni");
						String correo = rs.getString("correo");
						int telefono = rs.getInt("telefono");
						String usuario = rs.getString("usuario");
						String contrasena = rs.getString("contrasena");  // Nada de "ñ" !!!
						Administrador administrador =	new Administrador(nombre, apellido, dni, correo, telefono, usuario, contrasena);
						assertEquals("No coinciden los nombres de administradores",admin.getNombre(), administrador.getNombre());
						assertEquals("No coinciden los apellidos de administradores",admin.getApellido(), administrador.getApellido());
						assertEquals("No coinciden los dnis de administradores",admin.getDni(), administrador.getDni());
						assertEquals("No coinciden los correos de administradores",admin.getCorreo(), administrador.getCorreo());
						assertEquals("No coinciden los telefonos de administradores",admin.getTelefono(), administrador.getTelefono());
						assertEquals("No coinciden los usuarios de administradores",admin.getUsuario(), administrador.getUsuario());
						assertEquals("No coinciden los contraseñas de administradores",admin.getContrasena(), administrador.getContrasena());
					}
		}
	}
	@Test
	public void TestguardarEstudiante() throws SQLException {
		Estudiante estu = new Estudiante("Unai", "Egus", 634444233, "unai@opendeusto.es", "16092526A", "user", "pass", new ArrayList<Idioma>());
		bd.guardarEstudiante(estu);
		
		try (PreparedStatement stmt = bd.conexion.prepareStatement("SELECT * FROM Estudiante WHERE DNI = "+estu.getDni());
				ResultSet rs = stmt.executeQuery()) {
				
					while (rs.next()) {
					String nombre = rs.getString("nombre");
					String apellido = rs.getString("apellido");
					String dni = rs.getString("dni");
					String correo = rs.getString("correo");
					int telefono = rs.getInt("telefono");
					String usuario = rs.getString("usuario");
					String contrasena = rs.getString("contrasena");
					ArrayList<Idioma> idiomas = (ArrayList<Idioma>) rs.getArray("idiomas");
					Estudiante estudiante = new Estudiante(nombre, apellido, telefono, correo, dni, usuario, contrasena, idiomas);
					assertEquals("No coinciden los nombres de estudiantes",estu.getNombre(), estudiante.getNombre());
					assertEquals("No coinciden los apellidos de estudiantes",estu.getApellido(), estudiante.getApellido());
					assertEquals("No coinciden los dnis de estudiantes",estu.getDni(), estudiante.getDni());
					assertEquals("No coinciden los crrreos de estudiantes",estu.getCorreo(), estudiante.getCorreo());
					assertEquals("No coinciden los telefonos de estudiantes",estu.getTelefono(), estudiante.getTelefono());
					assertEquals("No coinciden los users de estudiantes",estu.getUsuario(), estudiante.getUsuario());
					assertEquals("No coinciden las contraseñas de estudiantes",estu.getContrasena(), estudiante.getContrasena());
					
					}
		}
	}
	
	@Test
	public void TestguardarDocente() throws SQLException {
		Docente doce = new Docente("Unai", "Egus", "16092526A", "unai@opendeusto.es", 634444233, "user", "pass", Idioma.Castellano);
		bd.guardarDocente(doce);
		
		try (PreparedStatement stmt = bd.conexion.prepareStatement("SELECT * FROM Docente WHERE dni = "+ doce.getDni());
				ResultSet rs = stmt.executeQuery()) {
				
					while (rs.next()) {
					String nombre = rs.getString("nombre");
					String apellido = rs.getString("apellido");
					String dni = rs.getString("dni");
					String correo = rs.getString("correo");
					int telefono = rs.getInt("telefono");
					String usuario = rs.getString("usuario");
					String contrasena = rs.getString("contrasena");
					Idioma idioma = Idioma.valueOf(rs.getString("idioma"));
					Docente docente = new Docente(nombre, apellido, dni, correo, telefono, usuario, contrasena, idioma);
					assertEquals("No coinciden los nombres de docentes",doce.getNombre(), docente.getNombre());
					assertEquals("No coinciden los apellidos de docentes",doce.getApellido(), docente.getApellido());
					assertEquals("No coinciden los dnis de docentes",doce.getDni(), docente.getDni());
					assertEquals("No coinciden los correos de docentes",doce.getCorreo(), docente.getCorreo());
					assertEquals("No coinciden los telefonos de docentes",doce.getTelefono(), docente.getTelefono());
					assertEquals("No coinciden los usuarios de docentes",doce.getUsuario(), docente.getUsuario());
					assertEquals("No coinciden las contraseñas de docentes",doce.getContrasena(), docente.getContrasena());
					assertEquals("No coinciden los idiomas de docentes",doce.getIdioma().toString(), docente.getIdioma().toString());
					}
		}
		
	}
	@Test
	public void TestcargarGrupo() throws SQLException {
		Docente doce = new Docente("Unai", "Egus", "16092526A", "unai@opendeusto.es", 634444233, "user", "pass", Idioma.Castellano);
		Grupo gru = new Grupo(Idioma.Castellano, "A", doce, new ArrayList<Estudiante>(), new ArrayList<Tarea>());
		bd.guardarGrupo(gru, academy);
		
		
		try (PreparedStatement stmt = bd.conexion.prepareStatement("SELECT * FROM Grupo WHERE nombre = "+ gru.getNombre());
		         ResultSet rs = stmt.executeQuery()) {

		        while (rs.next()) {
		        	Idioma idioma = Idioma.valueOf(rs.getString("idioma"));
		            String nombre = rs.getString("nombre");
		            
		            Docente docenteObtenido = null;
		            
		            for (Docente docente : academy.getDocentes()) {
						if (docente.getUsuario().compareToIgnoreCase(rs.getString("docente")) == 0) {
							docenteObtenido = docente;
						}
					}
		            
		            List<String> listEstduiantesString = Arrays.asList(rs.getString("estudiantes").split(","));
		            ArrayList<Estudiante> estudiantes = new ArrayList<>();
		            
		            for (Estudiante estudiante : academy.getEstudiantes()) {
		            	
		            	for (String string : listEstduiantesString) {
		            		if (estudiante.getUsuario().compareToIgnoreCase(string) == 0) {
		            			estudiantes.add(estudiante);
							}
						}
						
					}
		            
		            List<String> listTareasString = Arrays.asList(rs.getString("tareas").split(","));
		            ArrayList<Tarea> tareas = new ArrayList<>();
		            
		            for (Tarea tarea : academy.getTareas()) {
		            	
		            	for (String string : listTareasString) {
		            		if (tarea.getId().compareToIgnoreCase(string) == 0) {
		            			tareas.add(tarea);
							}
						}
						
					}

		            Grupo grupo = new Grupo(idioma, nombre, docenteObtenido, estudiantes, tareas);
		            assertEquals("No coinciden los nombres de grupos",gru.getNombre(), grupo.getNombre());
					assertEquals("No coinciden los idiomas de grupos",gru.getIdioma().toString(), grupo.getIdioma().toString());
					assertEquals("No coinciden los docentes de grupos",gru.getDocente().toString(), grupo.getDocente().toString());
		        }
		    }
	}
}
