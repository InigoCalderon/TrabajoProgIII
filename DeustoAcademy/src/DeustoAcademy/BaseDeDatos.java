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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

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
	            String idiomasString = rs.getString("idiomas");
	            List<String> lista = convertirCadenaALista(idiomasString);
	            ArrayList<Idioma> idiomas = new ArrayList<>();
	            
	            for (String idioma : lista) {
					idiomas.add(Idioma.valueOf(idioma));
				}
	            
	            estudiantes.add(new Estudiante(nombre, apellido, telefono, correo, dni, usuario, contrasena, (ArrayList<Idioma>) idiomas));
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

	            String docenteClob = rs.getString("docente");
	            Docente docente = stringToDocente(docenteClob);

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
			
				String estudianteString = rs.getString("estudiante");
				Estudiante estudiante = stringToEstudiante(estudianteString); 
				
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
			
			ArrayList<Estudiante> estudiantesConInfo = new ArrayList<>();
		
			while (rs.next()) {
			
				String estudianteString = rs.getString("estudiante");
				Estudiante estudiante = stringToEstudiante(estudianteString); 
				
				String grupoString = rs.getString("grupo");
				Grupo grupo = stringToGrupo(grupoString);
				
				String tareaString = rs.getString("tarea");
				Tarea tarea = stringToTarea(tareaString);
				
	            String nota = rs.getString("nota");
				
	            HashMap<Grupo, HashMap<Tarea, String>> ordenGrupos = new HashMap<>();
	            HashMap<Tarea, String> ordenTareas = new HashMap<>();
	            
	            ordenGrupos.put(grupo, ordenTareas);
	            ordenTareas.put(tarea, nota);
	            
	            if (estudiantesConInfo.contains(estudiante)) {
	            	notasTareas.get(estudiante).put(grupo, ordenTareas);
				} else {
					notasTareas.put(estudiante, ordenGrupos);
					estudiantesConInfo.add(estudiante);
				}
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
				String estudianteString = rs.getString("estudiante");
				Estudiante estudiante = stringToEstudiante(estudianteString);
				
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
	    	ArrayList<String> unidadesAnadidas = new ArrayList<>();
	    	ArrayList<String> gruposAnadidos = new ArrayList<>();
	    	ArrayList<Temario> temariosAnadidos = new ArrayList<>();
	    	
	        while (rs.next()) {

	        	String grupoString = rs.getString("grupo");
				Grupo grupo = stringToGrupo(grupoString);
				
	            String unidad = rs.getString("unidad");
	            
	            String contenido = rs.getString("contenido");                
	                  
	            if (unidadesAnadidas.contains(unidad) && gruposAnadidos.contains(grupo.getNombre())) {
	            	
	            	Temario temarioElegido = null;
	            	
	            	for (Temario temario : temariosAnadidos) {
						
	            		if (temario.getGrupo().getNombre().compareToIgnoreCase(grupo.getNombre()) == 0) {
							temarioElegido = temario;
						}
	            		
					}
	  
	            	gruposConTemario.get(gruposConTemario.indexOf(temarioElegido)).getData().get(unidad).add(contenido);
	            	academy.getTemarioDATA().get(gruposConTemario.indexOf(temarioElegido)).getData().get(unidad).add(contenido);
	            	
	            } else if (gruposAnadidos.contains(grupo.getNombre())) {
					
	            	ArrayList<String> nuevaLista = new ArrayList<>();
		            nuevaLista.add(contenido);
		            
		            Temario temarioElegido = null;
	            	
	            	for (Temario temario : temariosAnadidos) {
						
	            		if (temario.getGrupo().getNombre().compareToIgnoreCase(grupo.getNombre()) == 0) {
							temarioElegido = temario;
						}
	            		
					}
		            
					gruposConTemario.get(gruposConTemario.indexOf(temarioElegido)).getData().put(unidad, nuevaLista);
					academy.getTemarioDATA().get(gruposConTemario.indexOf(temarioElegido)).getData().put(unidad, nuevaLista);
					
	            } else if (grupo != null && buscarGrupo(grupo, academy)) {
					
	            	Temario temario = new Temario(grupo, new HashMap<>());
	                ArrayList<String> nuevaLista = new ArrayList<>();
	                nuevaLista.add(contenido);
	                temario.getData().put(unidad, nuevaLista);
	                academy.getTemarioDATA().add(temario);
	                gruposConTemario.add(temario);
	                temariosAnadidos.add(temario);
					
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
            String insertQuery = "INSERT INTO administrador (nombre, apellido, dni, correo, telefono, usuario, contrasena) VALUES (?, ?, ?, ?, ?, ?, ?)";
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
            String insertQuery = "INSERT INTO estudiante (nombre, apellido, dni, correo, telefono, usuario, contrasena, idiomas) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = conexion.prepareStatement(insertQuery)) {
                preparedStatement.setString(1, estudiante.getNombre());
                preparedStatement.setString(2, estudiante.getApellido());
                preparedStatement.setString(3, estudiante.getDni());
                preparedStatement.setString(4, estudiante.getCorreo());
                preparedStatement.setInt(5, estudiante.getTelefono());
                preparedStatement.setString(6, estudiante.getUsuario());
                preparedStatement.setString(7, estudiante.getContrasena());
                preparedStatement.setString(8, convertirListaACadena(estudiante.getIdiomas()));
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void guardarDocente(Docente docente) throws SQLException {
        try {
            String insertQuery = "INSERT INTO docente (nombre, apellido, dni, correo, telefono, usuario, contrasena, idioma) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
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
            String insertQuery = "INSERT INTO grupo (idioma, nombre, docente, estudiantes, tareas) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = conexion.prepareStatement(insertQuery)) {
                preparedStatement.setString(1, grupo.getIdioma().toString());
                preparedStatement.setString(2, grupo.getNombre());
                preparedStatement.setString(3, grupo.getDocente().toString());
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

                    pstmt.setString(1, estudiante.toString());
                    pstmt.setString(2, idioma.name());
                    pstmt.setString(3, nota);
                    pstmt.executeUpdate();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void guardarInscritosExamenFinal(HashMap<Estudiante, HashMap<Idioma, Boolean>> inscritosExamenFinal) throws SQLException {
        try {
            String insertQuery = "INSERT INTO inscritosExamenFinal (estudiante, idioma, bool) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = conexion.prepareStatement(insertQuery)) {
            	for (HashMap.Entry<Estudiante, HashMap<Idioma, Boolean>> entry : inscritosExamenFinal.entrySet()) {
                    Estudiante estudiante = entry.getKey();
                    HashMap<Idioma, Boolean> idiomasBooleanMap = entry.getValue();
                    for (HashMap.Entry<Idioma, Boolean> idiomaBooleanEntry : idiomasBooleanMap.entrySet()) {
                        preparedStatement.setString(1, estudiante.toString());
                        preparedStatement.setString(2, idiomaBooleanEntry.getKey().name());
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

	                        preparedStatement.setString(1, estudiante.toString());
	                        preparedStatement.setString(2, grupo.toString());
	                        preparedStatement.setString(3, tarea.toString());
	                        preparedStatement.setString(4, nota);
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
        		
            	preparedStatement.setString(1, temario.getGrupo().toString());
            	
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
    
    private Array convertirListaAArray(List<?> lista) throws SQLException {
        return conexion.createArrayOf("VARCHAR", lista.toArray());
    }
    
    private String convertirListaACadena(List<?> lista) {
        // Convierte la lista a una cadena de texto separada por comas
        return lista.stream().map(Object::toString).collect(Collectors.joining(","));
    }
    
    private List<String> convertirCadenaALista(String cadena) {
        // Convierte la cadena a una lista
        return Arrays.asList(cadena.split(","));
    }
    
    public static Docente stringToDocente(String docenteString) {
        
        // Docente [nombre=Juan, apellido=Perez, dni=12345678, correo=juan@example.com, telefono=123456, usuario=juanito, contrasena=secreta, idioma=ESPAÑOL]
        
        String[] parts = docenteString
                .replace("Docente [", "")
                .replace("]", "")
                .split(", ");

        String nombre = null, apellido = null, dni = null, correo = null, usuario = null, contrasena = null, idiomaStr = null;
        int telefono = 0;

        for (String part : parts) {
            String[] keyValue = part.split("=");
            String key = keyValue[0].trim();
            String value = keyValue[1].trim();

            switch (key) {
                case "nombre":
                    nombre = value;
                    break;
                case "apellido":
                    apellido = value;
                    break;
                case "dni":
                    dni = value;
                    break;
                case "correo":
                    correo = value;
                    break;
                case "telefono":
                    telefono = Integer.parseInt(value);
                    break;
                case "usuario":
                    usuario = value;
                    break;
                case "contrasena":
                    contrasena = value;
                    break;
                case "idioma":
                    idiomaStr = value;
                    break;

            }
        }

        Idioma idioma = Idioma.valueOf(idiomaStr);

        return new Docente(nombre, apellido, dni, correo, telefono, usuario, contrasena, idioma);
    }
    
    public static Estudiante stringToEstudiante(String estudianteString) {

        // Estudiante [nombre=Juan, apellido=Perez, telefono=123456, correo=juan@example.com, dni=12345678, usuario=juanito, contrasena=secreta, idiomas=[ESPAÑOL, INGLES, FRANCES]]

        String[] parts = estudianteString
                .replace("Estudiante [", "")
                .replace("]", "")
                .split(", ");

        String nombre = null, apellido = null, dni = null, correo = null, usuario = null, contrasena = null, idiomasStr = null;
        int telefono = 0;
        
        for (String part : parts) {
            String[] keyValue = part.split("=");
            String key = keyValue[0].trim();
            if (keyValue.length >= 2) {
                String value = keyValue[1].trim();

	            switch (key) {
	                case "nombre":
	                    nombre = value;
	                    break;
	                case "apellido":
	                    apellido = value;
	                    break;
	                case "telefono":
	                    telefono = Integer.parseInt(value);
	                    break;
	                case "correo":
	                    correo = value;
	                    break;
	                case "dni":
	                    dni = value;
	                    break;
	                case "usuario":
	                    usuario = value;
	                    break;
	                case "contrasena":
	                    contrasena = value;
	                    break;
	                case "idiomas":
	                    idiomasStr = value;
	                    break;
	            }
            }
        }

        // Tratamiento especial para el campo idiomas
        String[] idiomasArray = (idiomasStr != null) ? idiomasStr.substring(1, idiomasStr.length() - 1).split(", ") : new String[0];
        ArrayList<Idioma> idiomasList = new ArrayList<>();
        for (String idioma : idiomasArray) {
            idiomasList.add(Idioma.valueOf(idioma));
        }

        return new Estudiante(nombre, apellido, telefono, correo, dni, usuario, contrasena, idiomasList);
    }
    
    public static Grupo stringToGrupo(String grupoString) {

        // Grupo [idioma=ESPAÑOL, nombre=Grupo1, docente=Docente [nombre=Profesor, apellido=Apellido, dni=12345678, correo=profesor@example.com, telefono=123456789, usuario=profesor, contrasena=secreta, idioma=ESPAÑOL], estudiantes=[], tareas=[], capacidad_estudiantes=0]

        String[] parts = grupoString
                .replace("Grupo [", "")
                .replace("]", "")
                .split(", ");

        Idioma idioma = null;
        String nombre = null;
        Docente docente = null;
        ArrayList<Estudiante> estudiantes = null;
        ArrayList<Tarea> tareas = null;
        int capacidadEstudiantes = 0;

        for (String part : parts) {
            String[] keyValue = part.split("=");
            String key = keyValue[0].trim();
            String value = keyValue[1].trim();

            switch (key) {
                case "idioma":
                    idioma = Idioma.valueOf(value);
                    break;
                case "nombre":
                    nombre = value;
                    break;
                case "docente":
                    docente = stringToDocente(value);
                    break;
                case "estudiantes":
                    // Tratamiento especial para el campo estudiantes
                    estudiantes = new ArrayList<>();
                    if (!value.equals("[]")) {
                        String[] estudiantesArray = value.substring(1, value.length() - 1).split(", ");
                        for (String estudiante : estudiantesArray) {
                            estudiantes.add(stringToEstudiante(estudiante));
                        }
                    }
                    break;
                case "tareas":
                    // Tratamiento especial para el campo tareas
                    tareas = new ArrayList<>();
                    if (!value.equals("[]")) {
                        String[] tareasArray = value.substring(1, value.length() - 1).split(", ");
                        for (String tarea : tareasArray) {
                            tareas.add(stringToTarea(tarea));
                        }
                    }
                    break;
                case "capacidad_estudiantes":
                    capacidadEstudiantes = Integer.parseInt(value);
                    break;

            }
        }

        Grupo grupo = new Grupo(idioma, nombre, docente, estudiantes, tareas);
        grupo.setCapacidad_estudiantes(capacidadEstudiantes);
        return grupo;
    }
    
    public static Tarea stringToTarea(String tareaString) {
    	
        // Tarea [fecha_creacion=2023-01-01, fecha_entrega=2023-01-15, titulo=Tarea1, comentario=Descripción de la tarea]

        String[] parts = tareaString
                .replace("Tarea [", "")
                .replace("]", "")
                .split(", ");

        LocalDate fechaCreacion = null;
        LocalDate fechaEntrega = null;
        String titulo = null;
        String comentario = null;

        for (String part : parts) {
            String[] keyValue = part.split("=");
            String key = keyValue[0].trim();
            String value = keyValue[1].trim();

            switch (key) {
                case "fecha_creacion":
                    fechaCreacion = LocalDate.parse(value);
                    break;
                case "fecha_entrega":
                    fechaEntrega = LocalDate.parse(value);
                    break;
                case "titulo":
                    titulo = value;
                    break;
                case "comentario":
                    comentario = value;
                    break;

            }
        }

        return new Tarea(fechaEntrega, titulo, comentario);
    }
    
}
