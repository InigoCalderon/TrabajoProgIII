import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import DeustoAcademy.Academy;
import DeustoAcademy.Estudiante;
import DeustoAcademy.Idioma;
import Ventanas.VentanaAdiministradorAccesoEstudiantes;


public class VentanaAdministradorAccesoEstudiantesTest {
	protected VentanaAdiministradorAccesoEstudiantes ventana;
	protected Academy datos;
	
	
	
	@Test
	public void TestActualizarEstudiante() {
		String nombre = "Nombre";
		String apellido= "Apellido";
		String dni = "16092625P";
		String correo = "unai.egusquizadel@opendeusto.es";
		int telefono = 634444024;
		String user = "usuario";
		String pass = "1234";
		Estudiante nuevo = new Estudiante();
		
		ventana.actualizarEstudiante(nuevo);
		assertEquals(nombre, nuevo.getNombre());
		assertEquals(apellido, nuevo.getApellido());
		assertEquals(dni, nuevo.getDni());
		assertEquals(correo, nuevo.getCorreo());
		assertEquals(telefono, nuevo.getTelefono());
		assertEquals(user, nuevo.getUsuario());
		assertEquals(pass, nuevo.getContraseña());
		
	}
	
	@Test
	public void TestActualizarLista() {
		
		Estudiante nuevo = new Estudiante("nombre", "apellido", 1234, "@gmail.com", "123", "usuario", "contra", new ArrayList<Idioma>());
		Academy academy = new Academy();
		academy.getEstudiantes().add(nuevo);
		ventana.actualizarLista(academy);
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
