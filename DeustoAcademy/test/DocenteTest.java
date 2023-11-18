import static org.junit.Assert.assertEquals;

import org.junit.Test;

import DeustoAcademy.Docente;

public class DocenteTest {

	protected Docente docente;
	
	@Test
	public String TestGetNombre() {
		Docente nueva = new Docente("nombre", "apellido", "dni", "correo", 123, "usuario", "contraseña");
		assertEquals("nombre", nueva.getNombre());
	}
	@Test
	public String TestGetApellido() {
		Docente nueva = new Docente("nombre", "apellido", "dni", "correo", 123, "usuario", "contraseña");
		assertEquals("apellido",nueva.getApellido() );
		
	}
	@Test
	public String TestGetDni() {
		Docente nueva = new Docente("nombre", "apellido", "dni", "correo", 123, "usuario", "contraseña");
		assertEquals("dni",nueva.getDni() );
	}
	@Test
	public String TestGetCorreo() {
		Docente nueva = new Docente("nombre", "apellido", "dni", "correo", 123, "usuario", "contraseña");
		assertEquals("correo",nueva.getCorreo() );
	}
	@Test
	public int TestGetTelefono() {
		Docente nueva = new Docente("nombre", "apellido", "dni", "correo", 123, "usuario", "contraseña");
		assertEquals(123,nueva.getTelefono() );
	}
	@Test
	public String TestGetContraseña() {
		Docente nueva = new Docente("nombre", "apellido", "dni", "correo", 123, "usuario", "contraseña");
		assertEquals("usuario",nueva.getContraseña() );
	}
	@Test
	public String TestGetUsuario() {
		Docente nueva = new Docente("nombre", "apellido", "dni", "correo", 123, "usuario", "contraseña");
		assertEquals("contraseña",nueva.getUsuario() );
	}
	
	@Test
	public String TestSetNombre() {
		Docente nueva = new Docente("nombre", "apellido", "dni", "correo", 123, "usuario", "contraseña");
		assertEquals("modificacion", nueva.setNombre("modificacion"));
	}
	@Test
	public String TestSetApellido() {
		Docente nueva = new Docente("nombre", "apellido", "dni", "correo", 123, "usuario", "contraseña");
		assertEquals("modificacion", nueva.setApellido("modificacion"));
	}
	@Test
	public String TestSetDni() {
		Docente nueva = new Docente("nombre", "apellido", "dni", "correo", 123, "usuario", "contraseña");
		assertEquals("modificacion", nueva.setDni("modificacion"));
	}
	@Test
	public String TestSetCorreo() {
		Docente nueva = new Docente("nombre", "apellido", "dni", "correo", 123, "usuario", "contraseña");
		assertEquals("modificacion", nueva.setCorreo("modificacion"));
	}
	@Test
	public int TestSetTelefono() {
		Docente nueva = new Docente("nombre", "apellido", "dni", "correo", 123, "usuario", "contraseña");
		assertEquals(1111, nueva.setTelefono(1111));
	}
	@Test
	public String TestSetUsuario() {
		Docente nueva = new Docente("nombre", "apellido", "dni", "correo", 123, "usuario", "contraseña");
		assertEquals("modificacion", nueva.setUsuario("modificacion"));
	}
	@Test
	public String TestSetContraseña() {
		Docente nueva = new Docente("nombre", "apellido", "dni", "correo", 123, "usuario", "contraseña");
		assertEquals("modificacion", nueva.setContraseña("modificacion"));
	}
}