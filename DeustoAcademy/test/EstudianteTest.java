import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import DeustoAcademy.Estudiante;
import DeustoAcademy.Idioma;


public class EstudianteTest {

	private Estudiante e;
	private ArrayList<Idioma> lista_idiomas = new ArrayList<>();

    @Before
    public void setUp() {
    	
    	lista_idiomas.add(Idioma.Castellano);
    	lista_idiomas.add(Idioma.Ingles);
        e = new Estudiante("John", "Smith", 56676579, "magoPOP@HOTmail.com", "76485746385J", "1", "1", lista_idiomas);
    }


    @Test
    public void testGetNombre() {
        assertEquals("John", e.getNombre());
    }

    @Test
    public void testGetApellido() {
        assertEquals("Smith", e.getApellido());
    }

    @Test
    public void testGetTelefono() {
        assertEquals(56676579, e.getTelefono());
    }


    @Test
    public void testGetCorreo() {
        assertEquals("magoPOP@HOTmail.com", e.getCorreo());
    }

    @Test
    public void testGetDni() {
        assertEquals("76485746385J", e.getDni());
    }

    @Test
    public void testGetUsuario() {
        assertEquals("1", e.getUsuario());
    }
    
    @Test
    public void testGetContrasena() {
        assertEquals("1", e.getContrasena());
    }
    
    @Test
    public void testGetIdiomas() {
        assertEquals(lista_idiomas, e.getIdiomas());
    }
    
    @Test
    public void testEquals() {
        Estudiante sameEstudiante = new Estudiante("John", "Smith", 56676579, "magoPOP@HOTmail.com", "76485746385J", "1", "1", lista_idiomas);
        assertTrue(e.equals(sameEstudiante));

        Estudiante differentEstudiante = new Estudiante("John", "Smith", 56676579, "magoPOP@HOTmail.com", "76485746385J", "2", "2", lista_idiomas);
        assertFalse(e.equals(differentEstudiante));

    }
}
