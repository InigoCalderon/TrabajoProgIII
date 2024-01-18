package db;

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

import domain.Administrador;
import domain.Docente;
import domain.Estudiante;
import domain.Grupo;
import domain.Idioma;
import domain.Tarea;
import domain.Temario;
import main.Academy;


public class BaseDeDatos {
	
	public static Connection conexion;

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
				administradores.add(new Administrador(nombre, apellido, dni, correo, telefono, usuario, contrasena));
			}
		}
		return administradores;
	}
	
	public ArrayList<Estudiante> cargarEstudiantes() throws SQLException {
		ArrayList<Estudiante> estudiantes = new ArrayList<Estudiante>();
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
			docentes.add(new Docente(nombre, apellido, dni, correo, telefono, usuario, contrasena, idioma));
			}
		}
		return docentes;
	}
	
	public ArrayList<Grupo> cargarGrupos(Academy academy) throws SQLException {
		ArrayList<Grupo> grupos = new ArrayList<Grupo>();
		try (PreparedStatement stmt = conexion.prepareStatement("SELECT * FROM Grupo");
	         ResultSet rs = stmt.executeQuery()) {

	        while (rs.next()) {
	            Idioma idioma = Idioma.valueOf(rs.getString("idioma"));
	            String nombre = rs.getString("nombre");
	            
	            Docente docenteObtenido = null;
	            
	            for (Docente docente : academy.getDocentes()) {
					if (docente.getUsuario().compareToIgnoreCase(rs.getString("docente")) == 0) {
						docenteObtenido = docente;
					}
				}
	            
	            List<String> listEstduiantesString = Arrays.asList(rs.getString("estudiantes").split(","));
	            ArrayList<Estudiante> estudiantes = new ArrayList<>();
	            
	            for (Estudiante estudiante : academy.getEstudiantes()) {
	            	
	            	for (String string : listEstduiantesString) {
	            		if (estudiante.getUsuario().compareToIgnoreCase(string) == 0) {
	            			estudiantes.add(estudiante);
						}
					}
					
				}
	            
	            List<String> listTareasString = Arrays.asList(rs.getString("tareas").split(","));
	            ArrayList<Tarea> tareas = new ArrayList<>();
	            
	            for (Tarea tarea : academy.getTareas()) {
	            	
	            	for (String string : listTareasString) {
	            		if (tarea.getId().compareToIgnoreCase(string) == 0) {
	            			tareas.add(tarea);
						}
					}
					
				}

	            grupos.add(new Grupo(idioma, nombre, docenteObtenido, estudiantes, tareas));
	        }
	    }
		return grupos;
	}
	
	public HashMap<Estudiante, HashMap<Idioma, Boolean>>  cargarInscritosExamenFinal(Academy academy) throws SQLException {
		HashMap<Estudiante, HashMap<Idioma, Boolean>> inscritosExamenFinal = new HashMap<Estudiante, HashMap<Idioma,Boolean>>();
		try (PreparedStatement stmt = conexion.prepareStatement("SELECT * FROM inscritosExamenFinal");
		ResultSet rs = stmt.executeQuery()) {
				
			while (rs.next()) {
			
				Estudiante estudianteObtenido = null;
	            
	            for (Estudiante estudiante : academy.getEstudiantes()) {
					if (estudiante.getUsuario().compareToIgnoreCase(rs.getString("estudiante")) == 0) {
						estudianteObtenido = estudiante;
					}
				}
				
				Idioma idioma = Idioma.valueOf(rs.getString("idioma"));
				
				HashMap<Idioma, Boolean> nuevo_mapa = new HashMap<>();
				nuevo_mapa.put(idioma, rs.getBoolean("bool"));
				
				inscritosExamenFinal.put(estudianteObtenido, nuevo_mapa);
			
			}
		}
		return inscritosExamenFinal;
	}
	
	public HashMap<Estudiante, HashMap<Grupo, HashMap<Tarea, String>>> cargarNotasTareas(Academy academy) throws SQLException {
		HashMap<Estudiante, HashMap<Grupo, HashMap<Tarea, String>>> notasTareas = new HashMap<Estudiante, HashMap<Grupo,HashMap<Tarea,String>>>();
		try (PreparedStatement stmt = conexion.prepareStatement("SELECT * FROM notasTareas");
		ResultSet rs = stmt.executeQuery()) {
			
			ArrayList<Estudiante> estudiantesConInfo = new ArrayList<>();
		
			while (rs.next()) {
			
				Estudiante estudianteObtenido = null;
	            
	            for (Estudiante estudiante : academy.getEstudiantes()) {
					if (estudiante.getUsuario().compareToIgnoreCase(rs.getString("estudiante")) == 0) {
						estudianteObtenido = estudiante;
					}
				}

				Grupo grupoObtenido = null;
	            
	            for (Grupo grupo : academy.getGrupos()) {
					if (grupo.getNombre().compareToIgnoreCase(rs.getString("grupo")) == 0) {
						grupoObtenido = grupo;
					}
				}
				
				Tarea tareaObtenido = null;
	            
	            for (Tarea tarea : academy.getTareas()) {
					if (tarea.getId().compareToIgnoreCase(rs.getString("tarea")) == 0) {
						tareaObtenido = tarea;
					}
				}
				
	            String nota = rs.getString("nota");
				
	            HashMap<Grupo, HashMap<Tarea, String>> ordenGrupos = new HashMap<>();
	            HashMap<Tarea, String> ordenTareas = new HashMap<>();
	            
	            ordenGrupos.put(grupoObtenido, ordenTareas);
	            ordenTareas.put(tareaObtenido, nota);
	            
	            if (estudiantesConInfo.contains(estudianteObtenido)) {
	            	notasTareas.get(estudianteObtenido).put(grupoObtenido, ordenTareas);
				} else {
					notasTareas.put(estudianteObtenido, ordenGrupos);
					estudiantesConInfo.add(estudianteObtenido);
				}
			}
		}
		return notasTareas;
	}
	
	public HashMap<Estudiante, HashMap<Idioma, String>> cargarNotasExamenFinal(Academy academy) throws SQLException {
		HashMap<Estudiante, HashMap<Idioma, String>> notasExamenFinal = new HashMap<Estudiante, HashMap<Idioma,String>>();
		try (PreparedStatement stmt = conexion.prepareStatement("SELECT * FROM notasExamenFinal");
		ResultSet rs = stmt.executeQuery()) {
			
			while (rs.next()) {
			
				Estudiante estudianteObtenido = null;
	            
	            for (Estudiante estudiante : academy.getEstudiantes()) {
					if (estudiante.getUsuario().compareToIgnoreCase(rs.getString("estudiante")) == 0) {
						estudianteObtenido = estudiante;
					}
				}
				
				Idioma idioma = Idioma.valueOf(rs.getString("idioma"));
				
				HashMap<Idioma, String> nuevo_mapa = new HashMap<>();
				nuevo_mapa.put(idioma, rs.getString("nota"));
				
				notasExamenFinal.put(estudianteObtenido, nuevo_mapa);
			
			}
		}
		return notasExamenFinal;
	}
	
	public ArrayList<Tarea> cargarTarea() throws SQLException {
		
		ArrayList<Tarea> lista = new ArrayList<>();
		
		try (PreparedStatement stmt = conexion.prepareStatement("SELECT * FROM tareas");
				ResultSet rs = stmt.executeQuery()) {
		    	
		        while (rs.next()) {

		        	Tarea nuevaTarea = new Tarea();
		        	nuevaTarea.setId(rs.getString("id"));
		        	nuevaTarea.setFecha_creacion(LocalDate.parse(rs.getString("fecha_entrega"), DateTimeFormatter.ofPattern("dd-MM-yy")));
		        	nuevaTarea.setFecha_entrega(LocalDate.parse(rs.getString("fecha_entrega"), DateTimeFormatter.ofPattern("dd-MM-yy")));
		        	nuevaTarea.setTitulo(rs.getString("titulo"));
		        	nuevaTarea.setComentario(rs.getString("comentario"));
		            lista.add(nuevaTarea);
		            
		        }
		  }
		return lista;
    }
	
	public void cargarTemarioData(Academy academy) throws SQLException {
		
	    try (PreparedStatement stmt = conexion.prepareStatement("SELECT * FROM temarioData");
	         ResultSet rs = stmt.executeQuery()) {
	    	
	    	ArrayList<Temario> gruposConTemario = new ArrayList<>();
	    	ArrayList<String> unidadesAnadidas = new ArrayList<>();
	    	ArrayList<String> gruposAnadidos = new ArrayList<>();
	    	ArrayList<Temario> temariosAnadidos = new ArrayList<>();
	    	
	        while (rs.next()) {
	        	try {
	        		Grupo grupoObtenido = null;
		            
		            for (Grupo grupo : academy.getGrupos()) {
						if (grupo.getNombre().compareToIgnoreCase(rs.getString("grupo")) == 0) {
							grupoObtenido = grupo;
						}
					}
					
		            String unidad = rs.getString("unidad");
		            
		            String contenido = rs.getString("contenido");                
		                  
		            if (unidadesAnadidas.contains(unidad) && gruposAnadidos.contains(grupoObtenido.getNombre())) {
		            	
		            	Temario temarioElegido = null;
		            	
		            	for (Temario temario : temariosAnadidos) {
							
		            		if (temario.getGrupo().getNombre().compareToIgnoreCase(grupoObtenido.getNombre()) == 0) {
								temarioElegido = temario;
							}
		            		
						}
		  
		            	// Añadir contenido nuevo
		            	
		            	gruposConTemario.get(gruposConTemario.indexOf(temarioElegido)).getData().get(unidad).add(contenido);
		            	academy.getTemarioDATA().get(gruposConTemario.indexOf(temarioElegido)).getData().get(unidad).add(contenido);
		            	
		            } else if (gruposAnadidos.contains(grupoObtenido.getNombre())) {
						
		            	ArrayList<String> nuevaLista = new ArrayList<>();
			            nuevaLista.add(contenido);
			            
			            Temario temarioElegido = null;
		            	
		            	for (Temario temario : temariosAnadidos) {
							
		            		if (temario.getGrupo().getNombre().compareToIgnoreCase(grupoObtenido.getNombre()) == 0) {
								temarioElegido = temario;
							}
		            		
						}
			            
		            	// creasa un nuevo espacio donde añadir contenido, creas unidades 
		            	
						gruposConTemario.get(gruposConTemario.indexOf(temarioElegido)).getData().put(unidad, nuevaLista);
						academy.getTemarioDATA().get(gruposConTemario.indexOf(temarioElegido)).getData().put(unidad, nuevaLista);
						
		            } else if (grupoObtenido != null && buscarGrupo(grupoObtenido, academy)) {
						
		            	Temario temario = new Temario(grupoObtenido, new HashMap<>());
		                ArrayList<String> nuevaLista = new ArrayList<>();
		                nuevaLista.add(contenido);
		                temario.getData().put(unidad, nuevaLista);
		                academy.getTemarioDATA().add(temario);
		                gruposConTemario.add(temario);
		                temariosAnadidos.add(temario);
						
		                // meter contenido por primera vez en un temario
		                
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
	        	
	        }
	    }
	}

	public Boolean buscarGrupo(Grupo grupoBuscado, Academy academy) {
	    for (Grupo grupo : academy.getGrupos()) {
	        if (grupo.getNombre().equalsIgnoreCase(grupoBuscado.getNombre())) {
	            return true;
	        }
	    }
	    return false;
	}
	
	/// GUARDADO
	
	public void guardarTarea(Tarea tarea) throws SQLException {
        try {
            String insertQuery = "INSERT INTO tareas (id, fecha_creacion, fecha_entrega, titulo, comentario) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = conexion.prepareStatement(insertQuery)) {
                preparedStatement.setString(1, tarea.getId());
                preparedStatement.setString(2, tarea.getFecha_creacion().toString());
                preparedStatement.setString(3, tarea.getFecha_entrega().toString());
                preparedStatement.setString(4, tarea.getTitulo());
                preparedStatement.setString(5, tarea.getComentario());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            
        }
    }
	
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
                preparedStatement.setString(8, convertirListaACadena(estudiante.getIdiomas()));
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            
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
            
        }
    }

    public void guardarGrupo(Grupo grupo, Academy academy) throws SQLException {
        try {
            String insertQuery = "INSERT INTO Grupo (idioma, nombre, docente, estudiantes, tareas) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = conexion.prepareStatement(insertQuery)) {
                
            	try {
            		preparedStatement.setString(1, grupo.getIdioma().toString());
                    preparedStatement.setString(2, grupo.getNombre());
                    
                    preparedStatement.setString(3, grupo.getDocente().toString());
                    				
                    ArrayList<String> arrayListEstudiantesUsusarios = new ArrayList<>();
                    ArrayList<String> arrayListTareasIds = new ArrayList<>();
                    for (Estudiante estudiante : academy.getEstudiantes()) {
                    	arrayListEstudiantesUsusarios.add(estudiante.getUsuario());
    				}
                    for (Tarea tarea : academy.getTareas()) {
                    	arrayListTareasIds.add(tarea.getId());
    				}
                    preparedStatement.setString(4, String.join(",", arrayListEstudiantesUsusarios));
                    preparedStatement.setString(5, String.join(",", arrayListTareasIds));
                    preparedStatement.executeUpdate();
				} catch (Exception e) {
					// TODO: handle exception
				}
            }
        } catch (SQLException e) {
           
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

                    if (estudiante == null) {
						break;
					}
                    
                    pstmt.setString(1, estudiante.toString());
                    pstmt.setString(2, idioma.name());
                    pstmt.setString(3, nota);
                    pstmt.executeUpdate();
                }
            }
        } catch (Exception e) {
        	
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
                        
                    	if (estudiante == null) {
							break;
						}
                    	preparedStatement.setString(1, estudiante.toString());
                        preparedStatement.setString(2, idiomaBooleanEntry.getKey().name());
                        preparedStatement.setBoolean(3, idiomaBooleanEntry.getValue());
                        preparedStatement.executeUpdate();
						
                    	
                    }
                }
            }
        } catch (SQLException e) {
            
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

	                        if (estudiante == null) {
								break;
							}
	                        
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
    
    private String convertirListaACadena(List<?> lista) {
        // Convierte la lista a una cadena de texto separada por comas
        return lista.stream().map(Object::toString).collect(Collectors.joining(","));
    }
    
    private List<String> convertirCadenaALista(String cadena) {
        // Convierte la cadena a una lista
        return Arrays.asList(cadena.split(","));
    }
    
}
