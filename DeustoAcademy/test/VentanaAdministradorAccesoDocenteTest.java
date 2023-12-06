import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import DeustoAcademy.Academy;
import DeustoAcademy.Docente;
import DeustoAcademy.Idioma;
import Ventanas.VentanaAdministradorAccesoDocente;

public class VentanaAdministradorAccesoDocenteTest {
	protected VentanaAdministradorAccesoDocente ventana;
	protected Academy datos;
	
	
	
	@Test
	public void TestActualizarDocente() {
		String nombre = "Nombre";
		String apellido= "Apellido";
		String dni = "16092625P";
		String correo = "unai.egusquizadel@opendeusto.es";
		int telefono = 634444024;
		String user = "usuario";
		String pass = "1234";
		Docente nuevo = new Docente();
		
		ventana.actualizarDocente(nuevo);
		assertEquals(nombre, nuevo.getNombre());
		assertEquals(apellido, nuevo.getApellido());
		assertEquals(dni, nuevo.getDni());
		assertEquals(correo, nuevo.getCorreo());
		assertEquals(telefono, nuevo.getTelefono());
		assertEquals(user, nuevo.getUsuario());
		assertEquals(pass, nuevo.getContraseña());
		
	}
	
	@Test
	public void TestActualizarCombos() {
		Docente nuevo = new Docente("nombre", "apellido", "1234", "@gmail.com", 123, "usuario", "contra", Idioma.Castellano);
		Academy academy = new Academy();
		academy.getDocentes().add(nuevo);
		ventana.actualizarCombos(academy);
		assertTrue(ventana.modeloLista.contains(nuevo));
	}
	
	@Test
	public void TestLimpiarCampos() {
		ventana.limpiarCampos();
		assertEquals("", ventana.textoNombre);
		assertEquals("", ventana.textoApellido);
		assertEquals("", ventana.textoDni);
		assertEquals("", ventana.textoTelefono);
		assertEquals("", ventana.textoContraseña);
		assertEquals("", ventana.textoCorreo);
		assertEquals("", ventana.textoUsuario);
		
	}
	
	@Test
	public void TestCamposRellenos() {
		
		ventana.textoNombre.setText("");
		assertFalse(ventana.camposRellenos());
		
		ventana.textoNombre.setText("nombre");
		ventana.textoApellido.setText("apellido");
		ventana.textoDni.setText("1243124");
		ventana.textoTelefono.setText(124124+"");
		ventana.textoContraseña.setText("pass");
		ventana.textoCorreo.setText("@gmail.com");
		ventana.textoUsuario.setText("usuario");
		
		assertTrue(ventana.camposRellenos());
	}
}
