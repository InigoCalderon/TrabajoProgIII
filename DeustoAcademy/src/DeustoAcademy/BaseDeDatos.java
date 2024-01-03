package DeustoAcademy;

import java.io.IOException;
import java.io.Reader;
import java.io.StringWriter;
import java.sql.Array;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;



public class BaseDeDatos {
	protected static Connection conexion;
	
	protected Academy academy;
	
	public void connect() throws ClassNotFoundException, SQLException {				// Conectar a la BD
		Class.forName("org.sqlite.JDBC");
		
		conexion = DriverManager.getConnection("jdbc:sqlite:academy.db");
	}
	
	
	public void disconnect() throws  SQLException{				// Cerrar conexion de la BD
		conexion.close();
	}
	
	public  ArrayList<Administrador>  cargarAdministradores() throws SQLException {
		ArrayList<Administrador> resultado = new ArrayList<Administrador>();
		try (PreparedStatement stmt = conexion.prepareStatement("SELECT * FROM Administrador");
		ResultSet rs = stmt.executeQuery()) {
		
			while (rs.next()) {
				String nombre = rs.getString("nombre");
				String apellido = rs.getString("apellido");
				String dni = rs.getString("dni");
				String correo = rs.getString("correo");
				int telefono = rs.getInt("telefono");
				String usuario = rs.getString("usuario");
				String contrasena = rs.getString("contrasena");  // Nada de "ñ" !!!
				resultado.add(new Administrador(nombre, apellido, dni, correo, telefono, usuario, contrasena));
				
			}
		}
		return resultado;
	}
	
	public ArrayList<Estudiante> cargarEstudiantes() throws SQLException {
		ArrayList<Estudiante> resultado = new ArrayList<Estudiante>();
		try (PreparedStatement stmt = conexion.prepareStatement("SELECT * FROM Estudiante");
		ResultSet rs = stmt.executeQuery()) {
		
			while (rs.next()) {
			String nombre = rs.getString("nombre");
			String apellido = rs.getString("apellido");
			String dni = rs.getString("dni");
			String correo = rs.getString("correo");
			int telefono = rs.getInt("telefono");
			String usuario = rs.getString("usuario");
			String contrasena = rs.getString("contrasena");
			ArrayList<Idioma> idiomas = (ArrayList<Idioma>) rs.getArray("idiomas");
			resultado.add(new Estudiante(nombre, apellido, telefono, correo, dni, usuario, contrasena, idiomas));
			}
		}
		return resultado;
	}
	
	public ArrayList<Docente> cargarDocentes( ) throws SQLException {
		ArrayList<Docente> resultado = new ArrayList<Docente>();
		try (PreparedStatement stmt = conexion.prepareStatement("SELECT * FROM Docente");
		ResultSet rs = stmt.executeQuery()) {
		
			while (rs.next()) {
			String nombre = rs.getString("nombre");
			String apellido = rs.getString("apellido");
			String dni = rs.getString("dni");
			String correo = rs.getString("correo");
			int telefono = rs.getInt("telefono");
			String usuario = rs.getString("usuario");
			String contrasena = rs.getString("contrasena");
			Idioma idioma = Idioma.valueOf(rs.getString("idioma"));
			resultado.add(new Docente(nombre, apellido, dni, correo, telefono, usuario, contrasena, idioma));
			}
		}
		return resultado;
	}
	
	public HashMap<Estudiante, HashMap<Idioma, Boolean>> cargarInscritosExamenFinal( HashMap<Estudiante, HashMap<Idioma, Boolean>> inscritosExamenFinal) throws SQLException {
		HashMap<Estudiante, HashMap<Idioma, Boolean>> resultado = new HashMap<Estudiante, HashMap<Idioma,Boolean>>();
		try (PreparedStatement stmt = conexion.prepareStatement("SELECT * FROM inscritosExamenFinal");
		ResultSet rs = stmt.executeQuery()) {
			
			inscritosExamenFinal.clear(); ///////////////////////////////////////////////////////// ¿ESTA BIEN?
			
		
			while (rs.next()) {
			
			// Obtener Clob para Estudiante
			Clob estudianteClob = rs.getClob("estudiante");
			Estudiante estudiante = clobToEstudiante(estudianteClob);
			
			Idioma idioma = Idioma.valueOf(rs.getString("idioma"));
			
			HashMap<Idioma, Boolean> nuevo_mapa = new HashMap<>();
			nuevo_mapa.put(idioma, rs.getBoolean("boolean"));
			
			resultado.put(estudiante, nuevo_mapa);
			
			}
		}
		return resultado;
	}
	
	public HashMap<Estudiante, HashMap<Idioma, String>> cargarNotasExamenFinal(HashMap<Estudiante, HashMap<Idioma, String>> notasExamenFinal) throws SQLException {
		HashMap<Estudiante, HashMap<Idioma, String>> resultado = new HashMap<Estudiante, HashMap<Idioma,String>>();
		try (PreparedStatement stmt = conexion.prepareStatement("SELECT * FROM notasExamenFinal");
		ResultSet rs = stmt.executeQuery()) {
			
			notasExamenFinal.clear(); ///////////////////////////////////////////////////////// ¿ESTA BIEN?
			
			
			while (rs.next()) {
			
			// Obtener Clob para Estudiante
			Clob estudianteClob = rs.getClob("estudiante");
			Estudiante estudiante = clobToEstudiante(estudianteClob);
			
			Idioma idioma = Idioma.valueOf(rs.getString("idioma"));
			
			HashMap<Idioma, String> nuevo_mapa = new HashMap<>();
			nuevo_mapa.put(idioma, rs.getString("nota"));
			
			resultado.put(estudiante, nuevo_mapa);
			
			}
		}
		return resultado;
	}
	
	public ArrayList<Grupo> cargarGrupos() throws SQLException {
		ArrayList<Grupo> resultado = new ArrayList<Grupo>();
		try (PreparedStatement stmt = conexion.prepareStatement("SELECT * FROM Grupo");
		ResultSet rs = stmt.executeQuery()) {
		
			while (rs.next()) {
			Idioma idioma = Idioma.valueOf(rs.getString("idioma"));
			String nombre = rs.getString("nombre");
			
			// Obtener Clob para docente
			Clob docenteClob = rs.getClob("docente");
			Docente docente = clobToDocente(docenteClob);
			
			// Obtener Array para estudiantes
			Array estudiantesArray = rs.getArray("estudiantes");
			ArrayList<Estudiante> estudiantes = arrayToListaTipoEstudiante(estudiantesArray); // Ajusta según tu implementación real de conversión
			
			// Obtener Array para tareas
			Array tareasArray = rs.getArray("tareas");
			ArrayList<Tarea> tareas = arrayToListaTipoTarea(tareasArray); // Ajusta según tu implementación real de conversión
			
			resultado.add(new Grupo(idioma, nombre, docente, estudiantes, tareas));
			
			}
		}
		return resultado;
	}
	
	public Docente clobToDocente(Clob clob) throws SQLException {
		try (Reader reader = clob.getCharacterStream();
		StringWriter writer = new StringWriter()) {
			char[] buffer = new char[8192];
			int bytesRead;
			while ((bytesRead = reader.read(buffer)) != -1) {
				writer.write(buffer, 0, bytesRead);
			}
			String docenteString = writer.toString();
			
			// Conversión de cadena JSON a objeto Docente sin Gson
			JSONObject jsonDocente = new JSONObject(docenteString);
			
			// Ajusta la lógica para extraer valores específicos del objeto JSON y construir un objeto Docente.
			String nombre = jsonDocente.getString("nombre");
			String apellido = jsonDocente.getString("apellido");
			String dni = jsonDocente.getString("dni");
			String correo = jsonDocente.getString("correo");
			int telefono = jsonDocente.getInt("telefono");
			String usuario = jsonDocente.getString("usuario");
			String contrasena = jsonDocente.getString("contrasena");
			Idioma idioma = Idioma.valueOf(jsonDocente.getString("idioma"));
			
			return new Docente(nombre, apellido, dni, correo, telefono, usuario, contrasena, idioma);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("No se ha podido cargar corrctamente el DOcente");
		return null;
	}
	
	public Estudiante clobToEstudiante(Clob clob) throws SQLException {
		try (Reader reader = clob.getCharacterStream();
		StringWriter writer = new StringWriter()) {
			char[] buffer = new char[8192];
			int bytesRead;
			while ((bytesRead = reader.read(buffer)) != -1) {
				writer.write(buffer, 0, bytesRead);
			}
			String estudianteString = writer.toString();
			
			// Conversión de cadena JSON a objeto Estudiante sin Gson
			JSONObject jsonEstudiante = new JSONObject(estudianteString);
			
			// Ajusta la lógica para extraer valores específicos del objeto JSON y construir un objeto Docente.
			String nombre = jsonEstudiante.getString("nombre");
			String apellido = jsonEstudiante.getString("apellido");
			String dni = jsonEstudiante.getString("dni");
			String correo = jsonEstudiante.getString("correo");
			int telefono = jsonEstudiante.getInt("telefono");
			String usuario = jsonEstudiante.getString("usuario");
			String contrasena = jsonEstudiante.getString("contrasena");
			ArrayList<Idioma> idiomas = convertirJSONArrayALista(jsonEstudiante.getJSONArray("idiomas"));
			
			return new Estudiante(nombre, apellido, telefono, correo, dni, usuario, contrasena, idiomas);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("No se ha podido cargar corrctamente el Estudiante");
		return null;
	}
	
	public ArrayList<Idioma> convertirJSONArrayALista(JSONArray jsonArray) {
		ArrayList<Idioma> listaIdiomas = new ArrayList<>();
		
		for (int i = 0; i < jsonArray.length(); i++) {            
			try {
				String idiomaStr = jsonArray.getString(i);
				Idioma idioma = Idioma.valueOf(idiomaStr);
				listaIdiomas.add(idioma);
			} catch (Exception e) {
			// TODO: handle exception
			}
		}
		
		return listaIdiomas;
	}
	
	// Método para convertir Array a Lista de Tipo Estudiante
	public ArrayList<Estudiante> arrayToListaTipoEstudiante(Array array) throws SQLException {
		ArrayList<Estudiante> listafinal = new ArrayList<>();
		try (ResultSet rs = array.getResultSet()) {
			while (rs.next()) {
				// Aquí necesitas crear instancias de la clase Estudiante
				Estudiante estudiante = new Estudiante();
				estudiante.setNombre(rs.getString("nombre"));
				estudiante.setApellido(rs.getString("apellido"));
				estudiante.setTelefono(rs.getInt("telefono"));
				estudiante.setCorreo(rs.getString("correo"));
				estudiante.setDni(rs.getString("dni"));
				estudiante.setUsuario(rs.getString("usuario"));
				estudiante.setContrasena(rs.getString("contraseña"));
				Array idiomasArray = rs.getArray("idiomas");
				List<Idioma> lista = new ArrayList<Idioma>();
				if (idiomasArray != null) {
					Object[] idiomasObjArray = (Object[]) idiomasArray.getArray();
					for (Object idiomaObj : idiomasObjArray) {
						lista.add(Idioma.valueOf((String) idiomaObj));
					}
				}
				estudiante.setIdiomas((ArrayList<Idioma>) lista);
				listafinal.add(estudiante);
			}
		}
		return listafinal;
	}
	
	// Método para convertir Array a Lista de Tipo Tarea
	public ArrayList<Tarea> arrayToListaTipoTarea(Array array) throws SQLException {
		ArrayList<Tarea> listafinal = new ArrayList<>();
		try (ResultSet rs = array.getResultSet()) {
			while (rs.next()) {
				// Aquí necesitas crear instancias de la clase Tarea
				Tarea tarea = new Tarea();
				tarea.setFecha_entrega(LocalDate.parse(rs.getString("fechaEntrega"), DateTimeFormatter.ofPattern("yyyy-MM-dd")));  //LocalDate.of(2024, 1, 27)  --> en string 2024-01-27
				tarea.setTitulo(rs.getString("titulo"));
				tarea.setComentario(rs.getString("comentario"));
				listafinal.add(tarea);
			}
		}
		return listafinal;
	}
}
