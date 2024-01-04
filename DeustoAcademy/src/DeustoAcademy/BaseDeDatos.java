package DeustoAcademy;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
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
import java.util.Map;
import java.util.Map.Entry;

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
	
	public void cargarEnBD() {
		
		try {
			
			Class.forName("org.sqlite.JDBC");
			String url = "jdbc:sqlite:academy.db";
			
			try (Connection conn = DriverManager.getConnection(url)) {
				cargarAdministradores(conn);
				cargarEstudiantes(conn);
				cargarDocentes(conn);
				cargarGrupos(conn);
				cargarInscritosExamenFinal(conn);
				cargarNotasExamenFinal(conn);
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void cargarAdministradores(Connection conn) throws SQLException {
		try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Administrador");
		ResultSet rs = stmt.executeQuery()) {
		
			while (rs.next()) {
				String nombre = rs.getString("nombre");
				String apellido = rs.getString("apellido");
				String dni = rs.getString("dni");
				String correo = rs.getString("correo");
				int telefono = rs.getInt("telefono");
				String usuario = rs.getString("usuario");
				String contrasena = rs.getString("contrasena");  // Nada de "ñ" !!!
				academy.administradores.add(new Administrador(nombre, apellido, dni, correo, telefono, usuario, contrasena));
			}
		}
	}
	
	private void cargarEstudiantes(Connection conn) throws SQLException {
		try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Estudiante");
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
			academy.estudiantes.add(new Estudiante(nombre, apellido, telefono, correo, dni, usuario, contrasena, idiomas));
			}
		}
	}
	
	private void cargarDocentes(Connection conn) throws SQLException {
		try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Docente");
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
			academy.docentes.add(new Docente(nombre, apellido, dni, correo, telefono, usuario, contrasena, idioma));
			}
		}
	}
	
	private void cargarInscritosExamenFinal(Connection conn) throws SQLException {
		try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM inscritosExamenFinal");
		ResultSet rs = stmt.executeQuery()) {
		
			academy.inscritosExamenFinal.clear();
		
			while (rs.next()) {
			
			// Obtener Clob para Estudiante
			Clob estudianteClob = rs.getClob("estudiante");
			Estudiante estudiante = clobToEstudiante(estudianteClob);
			
			Idioma idioma = Idioma.valueOf(rs.getString("idioma"));
			
			HashMap<Idioma, Boolean> nuevo_mapa = new HashMap<>();
			nuevo_mapa.put(idioma, rs.getBoolean("boolean"));
			
			academy.inscritosExamenFinal.put(estudiante, nuevo_mapa);
			
			}
		}
	}
	
	private void cargarNotasExamenFinal(Connection conn) throws SQLException {
		try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM notasExamenFinal");
		ResultSet rs = stmt.executeQuery()) {
		
			academy.notasExamenFinal.clear();
			
			while (rs.next()) {
			
			// Obtener Clob para Estudiante
			Clob estudianteClob = rs.getClob("estudiante");
			Estudiante estudiante = clobToEstudiante(estudianteClob);
			
			Idioma idioma = Idioma.valueOf(rs.getString("idioma"));
			
			HashMap<Idioma, String> nuevo_mapa = new HashMap<>();
			nuevo_mapa.put(idioma, rs.getString("nota"));
			
			academy.notasExamenFinal.put(estudiante, nuevo_mapa);
			
			}
		}
	}
	
	private void cargarGrupos(Connection conn) throws SQLException {
		try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Grupo");
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
			
			academy.grupos.add(new Grupo(idioma, nombre, docente, estudiantes, tareas));
			
			}
		}
	}
	
	private Docente clobToDocente(Clob clob) throws SQLException {
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
	
	private Estudiante clobToEstudiante(Clob clob) throws SQLException {
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
	
	private ArrayList<Idioma> convertirJSONArrayALista(JSONArray jsonArray) {
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
	private ArrayList<Estudiante> arrayToListaTipoEstudiante(Array array) throws SQLException {
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
	private ArrayList<Tarea> arrayToListaTipoTarea(Array array) throws SQLException {
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
	
	/// GUARDADO
	
	public void guardarAdministrador(Connection conn, Administrador administrador) throws SQLException {
        try {
            String insertQuery = "INSERT INTO Administrador (nombre, apellido, dni, correo, telefono, usuario, contrasena) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = conn.prepareStatement(insertQuery)) {
                preparedStatement.setString(1, administrador.getNombre());
                preparedStatement.setString(2, administrador.getApellido());
                preparedStatement.setString(3, administrador.getDni());
                preparedStatement.setString(4, administrador.getCorreo());
                preparedStatement.setInt(5, administrador.getTelefono());
                preparedStatement.setString(6, administrador.getUsuario());
                preparedStatement.setString(7, administrador.getContrasena());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void guardarEstudiante(Connection conn, Estudiante estudiante) throws SQLException {
        try {
            String insertQuery = "INSERT INTO Estudiante (nombre, apellido, dni, correo, telefono, usuario, contrasena, idiomas) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = conn.prepareStatement(insertQuery)) {
                preparedStatement.setString(1, estudiante.getNombre());
                preparedStatement.setString(2, estudiante.getApellido());
                preparedStatement.setString(3, estudiante.getDni());
                preparedStatement.setString(4, estudiante.getCorreo());
                preparedStatement.setInt(5, estudiante.getTelefono());
                preparedStatement.setString(6, estudiante.getUsuario());
                preparedStatement.setString(7, estudiante.getContrasena());
                preparedStatement.setArray(8, convertirListaAArray(conn, estudiante.getIdiomas()));
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void guardarDocente(Connection conn, Docente docente) throws SQLException {
        try {
            String insertQuery = "INSERT INTO Docente (nombre, apellido, dni, correo, telefono, usuario, contrasena, idioma) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = conn.prepareStatement(insertQuery)) {
                preparedStatement.setString(1, docente.getNombre());
                preparedStatement.setString(2, docente.getApellido());
                preparedStatement.setString(3, docente.getDni());
                preparedStatement.setString(4, docente.getCorreo());
                preparedStatement.setInt(5, docente.getTelefono());
                preparedStatement.setString(6, docente.getUsuario());
                preparedStatement.setString(7, docente.getContraseña());
                preparedStatement.setString(8, docente.getIdioma().toString());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void guardarGrupo(Connection conn, Grupo grupo) throws SQLException {
        try {
            String insertQuery = "INSERT INTO Grupo (idioma, nombre, docente, estudiantes, tareas) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = conn.prepareStatement(insertQuery)) {
                preparedStatement.setString(1, grupo.getIdioma().toString());
                preparedStatement.setString(2, grupo.getNombre());
                preparedStatement.setString(3, convertirObjetoAString(grupo.getDocente()));
                preparedStatement.setArray(4, convertirListaAArray(conn, grupo.getEstudiantes()));
                preparedStatement.setArray(5, convertirListaAArray(conn, grupo.getTareas()));
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void guardarNotasExamenFinal(Connection conn, HashMap<Estudiante, HashMap<Idioma, String>> notasExamenFinal) throws SQLException {
        String sql = "INSERT INTO notasExamenFinal (estudiante, idioma, nota) VALUES (?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (Entry<Estudiante, HashMap<Idioma, String>> entry : notasExamenFinal.entrySet()) {
                Estudiante estudiante = entry.getKey();
                HashMap<Idioma, String> notasPorIdioma = entry.getValue();

                for (Map.Entry<Idioma, String> notaEntry : notasPorIdioma.entrySet()) {
                    Idioma idioma = notaEntry.getKey();
                    String nota = notaEntry.getValue();

                    // Convertir el objeto Estudiante a CLOB
                    Clob estudianteClob = convertirObjetoAClob(conn, estudiante);

                    // Establecer los parámetros en el PreparedStatement
                    pstmt.setClob(1, estudianteClob);
                    pstmt.setString(2, idioma.name());
                    pstmt.setString(3, nota);

                    // Ejecutar la inserción
                    pstmt.executeUpdate();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void guardarInscritosExamenFinal(Connection conn, HashMap<Estudiante, HashMap<Idioma, Boolean>> inscritosExamenFinal) throws SQLException {
        try {
            String insertQuery = "INSERT INTO inscritosExamenFinal (estudiante, idioma, boolean) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = conn.prepareStatement(insertQuery)) {
                for (Map.Entry<Estudiante, HashMap<Idioma, Boolean>> entry : inscritosExamenFinal.entrySet()) {
                    Estudiante estudiante = entry.getKey();
                    HashMap<Idioma, Boolean> idiomasBooleanMap = entry.getValue();
                    for (Map.Entry<Idioma, Boolean> idiomaBooleanEntry : idiomasBooleanMap.entrySet()) {
                        preparedStatement.setClob(1, convertirObjetoAClob(conn, estudiante));
                        preparedStatement.setString(2, idiomaBooleanEntry.getKey().toString());
                        preparedStatement.setBoolean(3, idiomaBooleanEntry.getValue());
                        preparedStatement.executeUpdate();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private Clob convertirObjetoAClob(Connection conn, Object objeto) throws SQLException {
        
        String objetoString = objeto.toString();

        try (StringReader reader = new StringReader(objetoString)) {
            // Crear un Clob y escribir el contenido del StringReader en el Clob
            Clob clob = conn.createClob();
            try (PreparedStatement pstmt = conn.prepareStatement("SELECT ?")) {
                pstmt.setClob(1, reader);
                pstmt.execute();
                return clob;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    private Array convertirListaAArray(Connection conn, List<?> lista) throws SQLException {
        return conn.createArrayOf("VARCHAR", lista.toArray());
    }
	
    private String convertirObjetoAString(Object objeto) {
        try (StringWriter writer = new StringWriter()) {

            writer.write(objeto.toString());
            return writer.toString();
            
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
	
}
