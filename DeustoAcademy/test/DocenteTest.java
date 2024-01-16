import static org.junit.Assert.assertEquals;

import org.junit.Test;

import domain.Docente;

public class DocenteTest {
	
	protected Docente Docente;
	
	
	@Test
	public void TestGetNombre() {
		Docente nueva = new Docente("nombre", "apellido", "dni", "correo", 123, "usuario", "contraseña", null);
		assertEquals("nombre", nueva.getNombre());
	}
	@Test
	public void TestGetApellido() {
		Docente nueva = new Docente("nombre", "apellido", "dni", "correo", 123, "usuario", "contraseña", null);
		assertEquals("apellido",nueva.getApellido() );
		
	}
	@Test
	public void TestGetIdioma() {
		Docente nueva = new Docente("nombre", "apellido", "dni", "correo", 123, "usuario", "contraseña", null);
		assertEquals(null,nueva.getIdioma() );
	}
	@Test
	public void TestGetDni() {
		Docente nueva = new Docente("nombre", "apellido", "dni", "correo", 123, "usuario", "contraseña", null);
		assertEquals("dni",nueva.getDni() );
	}
	@Test
	public void TestGetCorreo() {
		Docente nueva = new Docente("nombre", "apellido", "dni", "correo", 123, "usuario", "contraseña", null);
		assertEquals("correo",nueva.getCorreo() );
	}
	@Test
	public void TestGetTelefono() {
		Docente nueva = new Docente("nombre", "apellido", "dni", "correo", 123, "usuario", "contraseña", null);
		assertEquals(123,nueva.getTelefono() );
	}
	@Test
	public void TestGetUsuario() {
		Docente nueva = new Docente("nombre", "apellido", "dni", "correo", 123, "usuario", "contraseña", null);
		assertEquals("usuario",nueva.getUsuario() );
	}
	@Test
	public void TestGetContraseña() {
		Docente nueva = new Docente("nombre", "apellido", "dni", "correo", 123, "usuario", "contraseña", null);
		assertEquals("contraseña",nueva.getContrasena() );
	}
}