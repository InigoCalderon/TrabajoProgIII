import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import DeustoAcademy.*;

public class TareaTest {

	private Tarea t;
	
	@Before
    public void setUp() {
		
        t = new Tarea(LocalDate.of(2024, 2, 17), "Proyecto Final", "No os descuideis.");
    
	}
	
	@Test
    public void testGetComentario() {
        assertEquals("No os descuideis.", t.getComentario());
    }
	
	@Test
    public void testGetFecha_entrega() {
        assertEquals(LocalDate.of(2024, 2, 17), t.getFecha_entrega());
    }
	
	@Test
    public void testGetTitulo() {
        assertEquals("Proyecto Final", t.getTitulo());
    }

}
