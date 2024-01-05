package DeustoAcademy;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BaseDeDatosTest {
	protected BaseDeDatos bd;
	
	
	@Before
	public void setUp() throws Exception {
		bd.connect();
		
	}

	@After
	public void tearDown() throws Exception {
		bd.disconnect();
	}
	
	
	
	@Test
	public void TestcargarAdministradores() {
		
		}
	@Test
	public void TestcargarEstudiantes() {
		
	}
	
	public void TestcargarDocentes() {
		
	}
	}
