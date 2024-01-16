import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;


import domain.Docente;
import domain.Estudiante;
import domain.Grupo;
import domain.Idioma;
import domain.Tarea;


public class GrupoTest {

	private Grupo g;
	private ArrayList<Estudiante> lista_estudiantes = new ArrayList<>();
	private ArrayList<Tarea> lista_tareas = new ArrayList<>();
	private Docente docente = new Docente("John", "Smith", "76485746385J", "magoPOP@HOTmail.com", 56676579, "1", "1", Idioma.Castellano);
	
	@Before
    public void setUp() {
    	
    	lista_estudiantes.add(new Estudiante());
    	
        g = new Grupo(Idioma.Castellano, "nOMBREGRUPO", docente, lista_estudiantes, lista_tareas);
    }
	
	@Test
    public void testGetIdioma() {
        assertEquals(Idioma.Castellano, g.getIdioma());
    }
	
	@Test
    public void testGetDocente() {
        assertEquals(docente, g.getDocente());
    }
	
	@Test
    public void testGetEstudiantes() {
        assertEquals(lista_estudiantes, g.getEstudiantes());
    }
	
	@Test
    public void testGetTareas() {
        assertEquals(lista_tareas, g.getTareas());
    }
	
	@Test
	public void testGetCapacidad_estudiantes() {
		assertEquals(1 , g.getCapacidad_estudiantes());
	}
	
	@Test
    public void testEquals() {
		Grupo sameGrupo = new Grupo(Idioma.Castellano, "nOMBREGRUPO", docente, lista_estudiantes, lista_tareas);
        assertTrue(g.equals(sameGrupo));

        Grupo differentGrupo = new Grupo(Idioma.Castellano, "nOMBREGRUPO", docente, new ArrayList<>(), lista_tareas);
        assertFalse(g.equals(differentGrupo));

    }
	
}
