import static org.junit.Assert.*;

import org.junit.Test;

import DeustoAcademy.Administrador;

public class AdministradorTest {

protected Administrador administrador;
	
	
	@Test
	public String TestGetNombre() {
		Administrador nueva = new Administrador("nombre", "apellido", "dni", "correo", 123, "usuario", "contraseña");
		assertEquals("nombre", nueva.getNombre());
	}
	@Test
	public String TestGetApellido() {
		Administrador nueva = new Administrador("nombre", "apellido", "dni", "correo", 123, "usuario", "contraseña");
		assertEquals("apellido",nueva.getApellido() );
		
	}
	@Test
	public String TestGetDni() {
		Administrador nueva = new Administrador("nombre", "apellido", "dni", "correo", 123, "usuario", "contraseña");
		assertEquals("dni",nueva.getDni() );
	}
	@Test
	public String TestGetCorreo() {
		Administrador nueva = new Administrador("nombre", "apellido", "dni", "correo", 123, "usuario", "contraseña");
		assertEquals("correo",nueva.getCorreo() );
	}
	@Test
	public int TestGetTelefono() {
		Administrador nueva = new Administrador("nombre", "apellido", "dni", "correo", 123, "usuario", "contraseña");
		assertEquals(123,nueva.getTelefono() );
	}
	@Test
	public String TestGetUsuario() {
		Administrador nueva = new Administrador("nombre", "apellido", "dni", "correo", 123, "usuario", "contraseña");
		assertEquals("usuario",nueva.getUsuario() );
	}
	@Test
	public String TestGetContraseña() {
		Administrador nueva = new Administrador("nombre", "apellido", "dni", "correo", 123, "usuario", "contraseña");
		assertEquals("contraseña",nueva.getContraseña() );
	}
	
	@Test
	public String TestSetNombre() {
		Administrador nueva = new Administrador("nombre", "apellido", "dni", "correo", 123, "usuario", "contraseña");
		assertEquals("modificacion", nueva.setNombre("modificacion"));
	}
	@Test
	public String TestSetApellido() {
		Administrador nueva = new Administrador("nombre", "apellido", "dni", "correo", 123, "usuario", "contraseña");
		assertEquals("modificacion", nueva.setApellido("modificacion"));
	}
	@Test
	public String TestSetDni() {
		Administrador nueva = new Administrador("nombre", "apellido", "dni", "correo", 123, "usuario", "contraseña");
		assertEquals("modificacion", nueva.setDni("modificacion"));
	}
	@Test
	public String TestSetCorreo() {
		Administrador nueva = new Administrador("nombre", "apellido", "dni", "correo", 123, "usuario", "contraseña");
		assertEquals("modificacion", nueva.setCorreo("modificacion"));
	}
	@Test
	public int TestSetTelefono() {
		Administrador nueva = new Administrador("nombre", "apellido", "dni", "correo", 123, "usuario", "contraseña");
		assertEquals(1111, nueva.setTelefono(1111));
	}
	@Test
	public String TestSetUsuario() {
		Administrador nueva = new Administrador("nombre", "apellido", "dni", "correo", 123, "usuario", "contraseña");
		assertEquals("modificacion", nueva.setUsuario("modificacion"));
	}
	@Test
	public String TestSetContraseña() {
		Administrador nueva = new Administrador("nombre", "apellido", "dni", "correo", 123, "usuario", "contraseña");
		assertEquals("modificacion", nueva.setContraseña("modificacion"));
	}
	
}
