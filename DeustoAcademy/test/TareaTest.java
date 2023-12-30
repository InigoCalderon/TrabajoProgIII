import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import DeustoAcademy.*;

public class TareaTest {

	private Tarea t;
	
	@Before
    public void setUp() {
    	
        t = new Tarea("17/02/24", "Proyecto Final", "No os descuideis.");
    
	}
	
	@Test
    public void testGetComentario() {
        assertEquals("No os descuideis.", t.getComentario());
    }
	
	@Test
    public void testGetFecha_entrega() {
        assertEquals("17/02/24", t.getFecha_entrega());
    }
	
	@Test
    public void testGetTitulo() {
        assertEquals("Proyecto Final", t.getTitulo());
    }

}
