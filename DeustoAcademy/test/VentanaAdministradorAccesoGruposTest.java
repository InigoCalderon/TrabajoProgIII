import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import DeustoAcademy.Academy;
import DeustoAcademy.Docente;
import DeustoAcademy.Estudiante;
import DeustoAcademy.Grupo;
import DeustoAcademy.Idioma;
import DeustoAcademy.Tarea;
import Ventanas.VentanaAdministradorAccesoGrupos;

public class VentanaAdministradorAccesoGruposTest {
	protected VentanaAdministradorAccesoGrupos ventana;
	
	@Test
	public void TestEsAptoGrupoDocente() {
		
		
		Docente docente = new Docente("nombre", "apellido", "123", "gmail.com", 634, "usuario", "contraseña", Idioma.Frances);
		Grupo grupo = new Grupo(Idioma.Frances, "nombre", new Docente(), new ArrayList<Estudiante>(), new ArrayList<Tarea>());
		
		assertTrue(ventana.esAptoGrupoDocente(grupo, docente));
		
		Grupo grupo2 = new Grupo(Idioma.Castellano, "nombre", docente, new ArrayList<Estudiante>(), new ArrayList<Tarea>());
		assertFalse(ventana.esAptoGrupoDocente(grupo2, docente));
	}
	@Test
	public void TestEsAptoGrupoEstudiante() {
		
		Grupo grupo = new Grupo(Idioma.Frances, "nombre", new Docente(), new ArrayList<Estudiante>(), new ArrayList<Tarea>());
		Estudiante estudiante = new Estudiante("nombre", "apellido", 123, "gmail.com", "634", "usuario", "contraseña", new ArrayList<Idioma>());
		estudiante.getIdiomas().add(Idioma.Frances);
		
		assertTrue(ventana.esAptoGrupoEstudiante(grupo, estudiante));
		
		Estudiante estudiante2 = new Estudiante("nombre", "apellido", 123, "gmail.com", "634", "usuario", "contraseña", new ArrayList<Idioma>());
		estudiante2.getIdiomas().add(Idioma.Castellano);
		assertFalse(ventana.esAptoGrupoEstudiante(grupo, estudiante));
	}
	
	@Test
	public void TestActualizarCombos() {
		Docente nuevo = new Docente("nombre", "apellido", "1234", "@gmail.com", 123, "usuario", "contra", Idioma.Castellano);
		Estudiante estudiante = new Estudiante("nombre", "apellido", 123, "gmail.com", "634", "usuario", "contraseña", new ArrayList<Idioma>());
		Grupo grupo = new Grupo(Idioma.Frances, "nombre1", new Docente(), new ArrayList<Estudiante>(), new ArrayList<Tarea>());
		Grupo grupo2 = new Grupo(Idioma.Castellano, "nombre2", new Docente(), new ArrayList<Estudiante>(), new ArrayList<Tarea>());
		Academy academy = new Academy();
		academy.getDocentes().add(nuevo);
		academy.getEstudiantes().add(estudiante);
		academy.getGrupos().add(grupo);
		academy.getGrupos().add(grupo2);
		ventana.actualizarCombos(academy);
																				// No hay otra manera de comprobar que dentro de las combos estan esos datos
		assertEquals(1, ventana.comboDocentes.getItemCount());					// que contar si han sido ingresados
		assertEquals(1, ventana.comboEstudiantes.getItemCount());
		assertEquals(1, ventana.comboGrupos1.getItemCount());
		assertEquals(1, ventana.comboGrupos2.getItemCount());
	}
}
