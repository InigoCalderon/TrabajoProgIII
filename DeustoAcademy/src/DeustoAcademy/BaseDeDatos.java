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
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONArray;
import org.json.JSONObject;



public class BaseDeDatos {
	
	protected static Connection conexion;

	private static Logger logger = Logger.getLogger(Academy.class.getName());
	public void connect(String base) throws ClassNotFoundException, SQLException {				// Conectar a la BD
		
		try {
			Class.forName("org.sqlite.JDBC");
			conexion = DriverManager.getConnection(base);
		} catch (Exception e) {
			e.printStackTrace();
			logger.log(Level.SEVERE, "No se logra conectar con la BD");
		}
	}
	
	
	public void disconnect() throws  SQLException{				// Cerrar conexion de la BD
		conexion.close();
	}
	
	public ArrayList<Administrador> cargarAdministradores() throws SQLException {
		ArrayList<Administrador> administradores = new ArrayList<Administrador>();
		try (PreparedStatement stmt = conexion.prepareStatement("SELECT * FROM administrador");
		ResultSet rs = stmt.executeQuery()) {
		
			while (rs.next()) {
				String nombre = rs.getString("nombre");
				String apellido = rs.getString("apellido");
				String dni = rs.getString("dni");
				String correo = rs.getString("correo");
				int telefono = rs.getInt("telefono");
				String usuario = rs.getString("usuario");
				String contrasena = rs.getString("contrasena");  // Nada de "ñ" !!!
				administradores.add(new Administrador(nombre, apellido, dni, correo, telefono, usuario, contrasena));
			}
		}
		return administradores;
	}
	
	public ArrayList<Estudiante> cargarEstudiantes() throws SQLException {
		ArrayList<Estudiante> estudiantes = new ArrayList<Estudiante>();
	    try (PreparedStatement stmt = conexion.prepareStatement("SELECT * FROM estudiante");
	         ResultSet rs = stmt.executeQuery()) {

	        while (rs.next()) {
	            String nombre = rs.getString("nombre");
	            String apellido = rs.getString("apellido");
	            String dni = rs.getString("dni");
	            String correo = rs.getString("correo");
	            int telefono = rs.getInt("telefono");
	            String usuario = rs.getString("usuario");
	            String contrasena = rs.getString("contrasena");

	            Array idiomasArray = rs.getArray("idiomas");
	            Object[] idiomasObjArray = (Object[]) idiomasArray.getArray();
	            ArrayList<Idioma> idiomas = new ArrayList<>();

	            for (Object idiomaObj : idiomasObjArray) {
	                if (idiomaObj instanceof String) {
	                    Idioma idioma = Idioma.valueOf((String) idiomaObj);
	                    idiomas.add(idioma);
	                }
	            }

	            estudiantes.add(new Estudiante(nombre, apellido, telefono, correo, dni, usuario, contrasena, idiomas));
	        }
	        return estudiantes;
	    }
	}
	
	public ArrayList<Docente> cargarDocentes() throws SQLException {
		ArrayList<Docente> docentes = new ArrayList<Docente>();
		try (PreparedStatement stmt = conexion.prepareStatement("SELECT * FROM docente");
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
			docentes.add(new Docente(nombre, apellido, dni, correo, telefono, usuario, contrasena, idioma));
			}
		}
		return docentes;
	}
	
	public ArrayList<Grupo> cargarGrupos() throws SQLException {
		ArrayList<Grupo> grupos = new ArrayList<Grupo>();
		try (PreparedStatement stmt = conexion.prepareStatement("SELECT * FROM grupo");
	         ResultSet rs = stmt.executeQuery()) {

	        while (rs.next()) {
	            Idioma idioma = Idioma.valueOf(rs.getString("idioma"));
	            String nombre = rs.getString("nombre");

	            Clob docenteClob = rs.getClob("docente");
	            Docente docente = clobToDocente(docenteClob);

	            Array estudiantesArray = rs.getArray("estudiantes");
	            ArrayList<Estudiante> estudiantes = arrayToListaTipoEstudiante(estudiantesArray);

	            Array tareasArray = rs.getArray("tareas");
	            ArrayList<Tarea> tareas = arrayToListaTipoTarea(tareasArray);

	            grupos.add(new Grupo(idioma, nombre, docente, estudiantes, tareas));
	        }
	    }
		return grupos;
	}
	
	public HashMap<Estudiante, HashMap<Idioma, Boolean>>  cargarInscritosExamenFinal() throws SQLException {
		HashMap<Estudiante, HashMap<Idioma, Boolean>> inscritosExamenFinal = new HashMap<Estudiante, HashMap<Idioma,Boolean>>();
		try (PreparedStatement stmt = conexion.prepareStatement("SELECT * FROM inscritosExamenFinal");
		ResultSet rs = stmt.executeQuery()) {
				
			while (rs.next()) {
			
				// Obtener Clob para Estudiante
				Clob estudianteClob = rs.getClob("estudiante");
				Estudiante estudiante = clobToEstudiante(estudianteClob);
				
				Idioma idioma = Idioma.valueOf(rs.getString("idioma"));
				
				HashMap<Idioma, Boolean> nuevo_mapa = new HashMap<>();
				nuevo_mapa.put(idioma, rs.getBoolean("bool"));
				
				inscritosExamenFinal.put(estudiante, nuevo_mapa);
			
			}
		}
		return inscritosExamenFinal;
	}
	
	public HashMap<Estudiante, HashMap<Grupo, HashMap<Tarea, String>>> cargarNotasTareas() throws SQLException {
		HashMap<Estudiante, HashMap<Grupo, HashMap<Tarea, String>>> notasTareas = new HashMap<Estudiante, HashMap<Grupo,HashMap<Tarea,String>>>();
		try (PreparedStatement stmt = conexion.prepareStatement("SELECT * FROM notasTareas");
		ResultSet rs = stmt.executeQuery()) {
		
			while (rs.next()) {
			
				Clob estudianteClob = rs.getClob("estudiante");
				Estudiante estudiante = clobToEstudiante(estudianteClob);
				
				Clob grupoClob = rs.getClob("grupo");
				Grupo grupo = clobToGrupo(grupoClob);
				
				Clob tareaClob = rs.getClob("tarea");
				Tarea tarea = clobToTarea(tareaClob);
				
	            String nota = rs.getString("nota");
				
	            HashMap<Grupo, HashMap<Tarea, String>> ordenGrupos = new HashMap<>();
	            HashMap<Tarea, String> ordenTareas = new HashMap<>();
	            
	            ordenGrupos.put(grupo, ordenTareas);
	            ordenTareas.put(tarea, nota);
	            notasTareas.put(estudiante, ordenGrupos);
			
			}
		}
		return notasTareas;
	}
	
	public HashMap<Estudiante, HashMap<Idioma, String>>  cargarNotasExamenFinal() throws SQLException {
		HashMap<Estudiante, HashMap<Idioma, String>>   notasExamenFinal = new HashMap<Estudiante, HashMap<Idioma,String>>();
		try (PreparedStatement stmt = conexion.prepareStatement("SELECT * FROM notasExamenFinal");
		ResultSet rs = stmt.executeQuery()) {
			
			while (rs.next()) {
			
				// Obtener Clob para Estudiante
				Clob estudianteClob = rs.getClob("estudiante");
				Estudiante estudiante = clobToEstudiante(estudianteClob);
				
				Idioma idioma = Idioma.valueOf(rs.getString("idioma"));
				
				HashMap<Idioma, String> nuevo_mapa = new HashMap<>();
				nuevo_mapa.put(idioma, rs.getString("nota"));
				
				notasExamenFinal.put(estudiante, nuevo_mapa);
			
			}
		}
		return notasExamenFinal;
	}
	
	public void cargarTemarioData(Academy academy) throws SQLException {
		
	    try (PreparedStatement stmt = conexion.prepareStatement("SELECT * FROM temarioData");
	         ResultSet rs = stmt.executeQuery()) {
	    	
	    	ArrayList<Temario> gruposConTemario = new ArrayList<>();
	    	String ultimaUnidad = null;
	    	String ultimoGrupo = null;
	    	Temario ultimoTemario = null;
	    	
	        while (rs.next()) {

	        	Clob grupoClob = rs.getClob("grupo");
				Grupo grupo = clobToGrupo(grupoClob);
				
	            String unidad = rs.getString("unidad");
	            
	            String contenido = rs.getString("contenido");                
	                  
	            if (unidad.equalsIgnoreCase(ultimaUnidad) && grupo.getNombre().equalsIgnoreCase(ultimoGrupo)) {
	            	
	            	gruposConTemario.get(gruposConTemario.indexOf(ultimoTemario)).getData().get(unidad).add(contenido);
	            	academy.getTemarioDATA().get(gruposConTemario.indexOf(ultimoTemario)).getData().get(unidad).add(contenido);
	            	
	            } else if (grupo.getNombre().equalsIgnoreCase(ultimoGrupo)) {
					
	            	ArrayList<String> nuevaLista = new ArrayList<>();
		            nuevaLista.add(contenido);
					gruposConTemario.get(gruposConTemario.indexOf(ultimoTemario)).getData().put(unidad, nuevaLista);
					academy.getTemarioDATA().get(gruposConTemario.indexOf(ultimoTemario)).getData().put(unidad, nuevaLista);
					ultimaUnidad = unidad;
					
	            } else if (grupo != null && buscarGrupo(grupo, academy)) {
					
	            	Temario temario = new Temario(grupo, new HashMap<>());
	                ArrayList<String> nuevaLista = new ArrayList<>();
	                nuevaLista.add(contenido);
	                temario.getData().put(unidad, nuevaLista);
	                academy.getTemarioDATA().add(temario);
	                gruposConTemario.add(temario);
	                ultimoTemario = temario;
	                ultimaUnidad = unidad;
	                ultimoGrupo = grupo.getNombre();
					
				}
	        }
	    }
	}

	public Boolean buscarGrupo(Grupo grupoBuscado, Academy academy) {
	    for (Grupo grupo : academy.grupos) {
	        if (grupo.getNombre().equalsIgnoreCase(grupoBuscado.getNombre())) {
	            return true;
	        }
	    }
	    return false;
	}
	
	public Grupo clobToGrupo(Clob clob) throws SQLException {
        try (Reader reader = clob.getCharacterStream();
             StringWriter writer = new StringWriter()) {
            char[] buffer = new char[8192];
            int bytesRead;
            while ((bytesRead = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, bytesRead);
            }
            String grupoString = writer.toString();

            // Conversión de cadena JSON a objeto Grupo usando JSON-java

            JSONObject jsonGrupo = new JSONObject(grupoString);

            Idioma idioma = Idioma.valueOf(jsonGrupo.getString("idioma"));
            String nombre = jsonGrupo.getString("nombre");

            Clob docenteClob = ((ResultSet) jsonGrupo).getClob("docente");
            Docente docente = clobToDocente(docenteClob);

            Array estudiantesArray = (Array) jsonGrupo.getJSONArray("estudiantes");
            ArrayList<Estudiante> estudiantes = arrayToListaTipoEstudiante(estudiantesArray);

            Array tareasArray = (Array) jsonGrupo.getJSONArray("tareas");
            ArrayList<Tarea> tareas = arrayToListaTipoTarea(tareasArray);

            Grupo grupo = new Grupo(idioma, nombre, docente, estudiantes, tareas);

            return grupo;
        } catch (IOException e) {
        	 logger.log(Level.SEVERE, "No se ha podido cargar correctamente el Grupo");
            e.printStackTrace();
        }
       
        
        return null;
    }
	
	public Tarea clobToTarea(Clob clob) throws SQLException {
	    try (Reader reader = clob.getCharacterStream();
	         StringWriter writer = new StringWriter()) {
	        char[] buffer = new char[8192];
	        int bytesRead;
	        while ((bytesRead = reader.read(buffer)) != -1) {
	            writer.write(buffer, 0, bytesRead);
	        }
	        String tareaString = writer.toString();

	        // Conversión de cadena JSON a objeto Tarea usando JSON-java
	        JSONObject jsonTarea = new JSONObject(tareaString);

	        // Extraer valores específicos del objeto JSON y construir un objeto Tarea
	        LocalDate fechaEntrega = LocalDate.parse(jsonTarea.getString("fechaEntrega"), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	        String titulo = jsonTarea.getString("titulo");
	        String comentario = jsonTarea.getString("comentario");

	        return new Tarea(fechaEntrega, titulo, comentario);
	        
	    } catch (IOException e) {
	    	logger.log(Level.SEVERE, "No se ha podido cargar correctamente la Tarea");
	        e.printStackTrace();
	    }
	    
	    
	    return null;
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
			logger.log(Level.SEVERE, "No se ha podido cargar corrctamente el DOcente");
			e.printStackTrace();
		}
		
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
			logger.log(Level.SEVERE, "No se ha podido cargar corrctamente el Estudiante");
			e.printStackTrace();
		}
		
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
	
	/// GUARDADO
	
	public void guardarAdministrador( Administrador administrador) throws SQLException {
        try {
            String insertQuery = "INSERT INTO Administrador (nombre, apellido, dni, correo, telefono, usuario, contrasena) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = conexion.prepareStatement(insertQuery)) {
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

    public void guardarEstudiante(Estudiante estudiante) throws SQLException {
        try {
            String insertQuery = "INSERT INTO Estudiante (nombre, apellido, dni, correo, telefono, usuario, contrasena, idiomas) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = conexion.prepareStatement(insertQuery)) {
                preparedStatement.setString(1, estudiante.getNombre());
                preparedStatement.setString(2, estudiante.getApellido());
                preparedStatement.setString(3, estudiante.getDni());
                preparedStatement.setString(4, estudiante.getCorreo());
                preparedStatement.setInt(5, estudiante.getTelefono());
                preparedStatement.setString(6, estudiante.getUsuario());
                preparedStatement.setString(7, estudiante.getContrasena());
                preparedStatement.setArray(8, convertirListaAArray(estudiante.getIdiomas()));
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void guardarDocente(Docente docente) throws SQLException {
        try {
            String insertQuery = "INSERT INTO Docente (nombre, apellido, dni, correo, telefono, usuario, contrasena, idioma) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = conexion.prepareStatement(insertQuery)) {
                preparedStatement.setString(1, docente.getNombre());
                preparedStatement.setString(2, docente.getApellido());
                preparedStatement.setString(3, docente.getDni());
                preparedStatement.setString(4, docente.getCorreo());
                preparedStatement.setInt(5, docente.getTelefono());
                preparedStatement.setString(6, docente.getUsuario());
                preparedStatement.setString(7, docente.getContrasena());
                preparedStatement.setString(8, docente.getIdioma().toString());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void guardarGrupo(Grupo grupo) throws SQLException {
        try {
            String insertQuery = "INSERT INTO Grupo (idioma, nombre, docente, estudiantes, tareas) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = conexion.prepareStatement(insertQuery)) {
                preparedStatement.setString(1, grupo.getIdioma().toString());
                preparedStatement.setString(2, grupo.getNombre());
                preparedStatement.setString(3, convertirObjetoAString(grupo.getDocente()));
                preparedStatement.setArray(4, convertirListaAArray(grupo.getEstudiantes()));
                preparedStatement.setArray(5, convertirListaAArray(grupo.getTareas()));
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void guardarNotasExamenFinal(HashMap<Estudiante, HashMap<Idioma, String>> notasExamenFinal) throws SQLException {
        String sql = "INSERT INTO notasExamenFinal (estudiante, idioma, nota) VALUES (?, ?, ?)";

        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            for (Entry<Estudiante, HashMap<Idioma, String>> entry : notasExamenFinal.entrySet()) {
                Estudiante estudiante = entry.getKey();
                HashMap<Idioma, String> notasPorIdioma = entry.getValue();

                for (Map.Entry<Idioma, String> notaEntry : notasPorIdioma.entrySet()) {
                    Idioma idioma = notaEntry.getKey();
                    String nota = notaEntry.getValue();

                    // Convertir el objeto Estudiante a CLOB
                    Clob estudianteClob = convertirObjetoAClob(estudiante);

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
    
    public void guardarInscritosExamenFinal(HashMap<Estudiante, HashMap<Idioma, Boolean>> inscritosExamenFinal) throws SQLException {
        try {
            String insertQuery = "INSERT INTO inscritosExamenFinal (estudiante, idioma, boolean) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = conexion.prepareStatement(insertQuery)) {
                for (Map.Entry<Estudiante, HashMap<Idioma, Boolean>> entry : inscritosExamenFinal.entrySet()) {
                    Estudiante estudiante = entry.getKey();
                    HashMap<Idioma, Boolean> idiomasBooleanMap = entry.getValue();
                    for (Map.Entry<Idioma, Boolean> idiomaBooleanEntry : idiomasBooleanMap.entrySet()) {
                        preparedStatement.setClob(1, convertirObjetoAClob(estudiante));
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
    
    public void guardarNotasTareas(HashMap<Estudiante, HashMap<Grupo, HashMap<Tarea, String>>> notasTareas) throws SQLException {

    	try {
	        String insertNotasTareasSQL = "INSERT INTO notasTareas (estudiante, grupo, tarea, nota) VALUES (?, ?, ?, ?)";
	        try (PreparedStatement preparedStatement = conexion.prepareStatement(insertNotasTareasSQL)) {
	
	            for (Estudiante estudiante : notasTareas.keySet()) {
	                HashMap<Grupo, HashMap<Tarea, String>> ordenGrupos = notasTareas.get(estudiante);
	
	                for (Grupo grupo : ordenGrupos.keySet()) {
	                    HashMap<Tarea, String> ordenTareas = ordenGrupos.get(grupo);
	
	                    for (Tarea tarea : ordenTareas.keySet()) {
	                        String nota = ordenTareas.get(tarea);
	
	                        // Convertir objetos a Clob
	                        Clob estudianteClob = convertirObjetoAClob(estudiante);
	                        Clob grupoClob = convertirObjetoAClob(grupo);
	                        Clob tareaClob = convertirObjetoAClob(tarea);
	
	                        // Establecer valores en la declaración preparada
	                        preparedStatement.setClob(1, estudianteClob);
	                        preparedStatement.setClob(2, grupoClob);
	                        preparedStatement.setClob(3, tareaClob);
	                        preparedStatement.setString(4, nota);
	
	                        // Ejecutar la inserción
	                        preparedStatement.executeUpdate();
	                    }
	                }
	            }
         	}
        } catch (SQLException e) {
        	e.printStackTrace();
        }
    }
    
    public void guardarTemarioDATA(Temario temario) {

            // Guardar información detallada del Temario (unidad y contenidos)
            String insertDetalleSQL = "INSERT INTO temarioData (grupo, unidad, contenido) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = conexion.prepareStatement(insertDetalleSQL)) {
        		
            	preparedStatement.setClob(1, convertirObjetoAClob(temario.getGrupo()));
            	
            	for (String unidad : temario.getData().keySet()) {
            		
                    ArrayList<String> contenidos = temario.getData().get(unidad);

                    for (String contenido : contenidos) {
                    	preparedStatement.setString(2, unidad);
                        preparedStatement.setString(3, contenido);
                        preparedStatement.executeUpdate();
                    }
                }
               
            } catch (SQLException e) {
            	e.printStackTrace();
			}
    }
    
    private Clob convertirObjetoAClob(Object objeto) throws SQLException {
        
        String objetoString = objeto.toString();

        try (StringReader reader = new StringReader(objetoString)) {
            // Crear un Clob y escribir el contenido del StringReader en el Clob
            Clob clob = conexion.createClob();
            try (PreparedStatement pstmt = conexion.prepareStatement("SELECT ?")) {
                pstmt.setClob(1, reader);
                pstmt.execute();
                return clob;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    private Array convertirListaAArray(List<?> lista) throws SQLException {
        return conexion.createArrayOf("VARCHAR", lista.toArray());
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
