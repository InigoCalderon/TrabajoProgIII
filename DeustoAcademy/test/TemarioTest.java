import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import domain.Docente;
import domain.Grupo;
import domain.Idioma;
import domain.Temario;

public class TemarioTest {

	private Temario t;
	private HashMap<String, ArrayList<String>> data = new HashMap<>();
	private ArrayList<String> content = new ArrayList<>();
	private Grupo grupo = new Grupo(Idioma.Castellano, "nOMBREGRUPO", new Docente(), new ArrayList<>(), new ArrayList<>());

	@Before
    public void setUp() {
    	
		content.add("ESTO ES CONTENIDO DE UNA UNIDAD");
		data.put("Unit 1", content);
        t = new Temario(grupo, data);
        
    }
	
	@Test
    public void testGetGrupo() {
        assertEquals(grupo, t.getGrupo());
    }
	
	@Test
    public void testGetData() {
        assertEquals(data, t.getData());
    }
	
}
