import static org.junit.Assert.assertNull;

import org.junit.Before;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import DeustoAcademy.Administrador;

public class AdministradorTest {
	
	protected Administrador nueva;

	@Before
	public void setUp() {
		 nueva = new Administrador("nombre", "apellido", "dni", "correo", 123, "usuario", "contraseña");
	}
	
	@Test
	public void TestGetNombre() {
		
		assertEquals("nombre", nueva.getNombre());
	}
	@Test
	public void TestGetApellido() {
		
		assertEquals("apellido",nueva.getApellido() );
		
	}
	@Test
	public void TestGetDni() {
		
		assertEquals("dni",nueva.getDni() );
	}
	@Test
	public void TestGetCorreo() {
		
		assertEquals("correo",nueva.getCorreo() );
	}
	@Test
	public void TestGetTelefono() {
		
		assertEquals(123,nueva.getTelefono() );
	}
	@Test
	public void TestGetUsuario() {
		
		assertEquals("usuario",nueva.getUsuario() );
	}
	@Test
	public void TestGetContraseña() {
		
		assertEquals("contraseña",nueva.getContrasena() );
	}
	
}
