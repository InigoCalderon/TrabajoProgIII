package DeustoAcademy;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.LogManager;
/*/
import java.util.logging.Logger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/*/
import Ventanas.*;


public class Academy {
	
	public ArrayList<Administrador> administradores = new ArrayList<>();
	public ArrayList<Estudiante> estudiantes = new ArrayList<>();
	public ArrayList<Docente> docentes = new ArrayList<>();
	
	public Academy(ArrayList<Administrador> administradores, ArrayList<Estudiante> estudiantes,
			ArrayList<Docente> docentes) {
		super();
		for (Docente docente : docentes) {
			this.docentes.add(docente);
		}
		for (Estudiante estudiante : estudiantes) {
			this.estudiantes.add(estudiante);
		}
		for (Administrador administrador : administradores) {
			this.administradores.add(administrador);
		}
	}
	
	public Academy() {
		super();
		this.administradores = new ArrayList<Administrador>();
		this.estudiantes = new ArrayList<Estudiante>();
		this.docentes = new ArrayList<Docente>();
	}
	
	public Academy(Academy a) {
		super();
		for (Docente docente : a.docentes) {
			this.docentes.add(docente);
		}
		for (Estudiante estudiante : a.estudiantes) {
			this.estudiantes.add(estudiante);
		}
		for (Administrador administrador : a.administradores) {
			this.administradores.add(administrador);
		}
	}

	public ArrayList<Administrador> getAdministradores() {
		return administradores;
	}

	public void setAdministradores(ArrayList<Administrador> administradores) {
		this.administradores.clear();
		for (Administrador administrador : administradores) {
			this.administradores.add(administrador);
		}
	}

	public ArrayList<Estudiante> getEstudiantes() {
		return estudiantes;
	}

	public void setEstudiantes(ArrayList<Estudiante> estudiantes) {
		this.estudiantes.clear();
		for (Estudiante estudiante : estudiantes) {
			this.estudiantes.add(estudiante);
		}
	}

	public ArrayList<Docente> getDocentes() {
		return docentes;
	}

	public void setDocentes(ArrayList<Docente> docentes) {
		this.docentes.clear();
		for (Docente docente : docentes) {
			this.docentes.add(docente);
		}
	}

	@Override
	public String toString() {
		return "Academy [administradores=" + administradores + ", estudiantes=" + estudiantes + ", docentes=" + docentes
				+ "]";
	}

	public static HashMap<String, String> Claves(Academy a){
		
		HashMap<String, String> claves = new HashMap<>();

		for (Administrador admin : a.administradores) {
			claves.put(admin.getUsuario(), admin.getContraseña());					
			}
		for (Estudiante estudiante : a.estudiantes) {
			claves.put(estudiante.getUsuario(), estudiante.getContraseña());					
			}
		for (Docente docente : a.docentes) {
			claves.put(docente.getUsuario(), docente.getContraseña());					
			}
		
		return claves;
		
	}
	
	
	public static void cargar_datos(String fichero) {
		
		// DE AQUÍ SE SACARÁN LOS DATOS DE LA BASE DE DATOS
		
	}
	
	public static void guardar_datos(String fichero, ArrayList<?> datos, Rols rol) {
		
		// AQUÍ SE SUBIRÁN A LA BD, pero mientras tanto usamos ficheros
		
		/*/
		
		FileWriter fw = null;
        PrintWriter pw = null;
        String linea = null;
        
        try {
        	
            fw = new FileWriter(fichero);
            pw = new PrintWriter(fw);

            for (Object o : datos) {
            	linea = "";
            	if (rol == Rols.ADMINISTRADOR) {
            		linea += ((Administrador) o).getNombre() + ";" + 
               			 	 ((Administrador) o).getApellido() + ";" +
               			 	 ((Administrador) o).getTelefono() + ";" +
               			 	 ((Administrador) o).getCorreo() + ";" +
               			 	 ((Administrador) o).getDni() + ";" +
               			     ((Administrador) o).getUsuario() + ";" +
               			     ((Administrador) o).getContraseña();
            	} if (rol == Rols.DOCENTE) {
            		linea += ((Docente) o).getNombre() + ";" + 
              			 	 ((Docente) o).getApellido() + ";" +
            			 	 ((Docente) o).getTelefono() + ";" +
            			     ((Docente) o).getCorreo() + ";" +
            			     ((Docente) o).getDni() + ";" + 
              			 	 ((Docente) o).getUsuario() + ";" +
              			     ((Docente) o).getContraseña();
            	} if (rol == Rols.ESTUDIANTE) {
            		linea += ((Estudiante) o).getNombre() + ";" + 
            				 ((Estudiante) o).getApellido() + ";" +
         			         ((Estudiante) o).getTelefono() + ";" +
         			         ((Estudiante) o).getCorreo() + ";" +
         			         ((Estudiante) o).getDni() + ";" + 
         			         ((Estudiante) o).getUsuario() + ";" +
         			         ((Estudiante) o).getContraseña();	
            	} else {System.out.println("Error al guardar datos");}
            }
            	pw.println(linea);
            	
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
	           if (null != fichero) {fw.close();}
	           } catch (Exception e2) {
	        	   e2.printStackTrace();
	        }

        }
        
        /*/
		
	}
	
	//private static final Logger logger = LoggerFactory.getLogger(Academy.class.getName());
	//private static final Logger logger = Logger.getLogger(Academy.class.getName());
	
	public static void main(String[] args) {
		
		
		Academy A1 = new Academy();
		SelectRol v = new SelectRol(A1);
		
		/*/
		try (FileInputStream fis = new FileInputStream("logger.properties")) {
			LogManager.getLogManager().readConfiguration(fis);
		} catch (IOException e) {
			logger.log(Level.ALL, "No se pudo leer el fichero de configuración del logger");
		}
		/*/
		//guardar_datos("AdminsLOG.dat", A1.administradores, Rols.ADMINISTRADOR);
		
		//VentanaChat v2 = new VentanaChat();
		
	}

}