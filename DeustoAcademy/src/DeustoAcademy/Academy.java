package DeustoAcademy;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
	
	protected ArrayList<Administrador> administradores = new ArrayList<>();
	protected ArrayList<Estudiante> estudiantes = new ArrayList<>();
	protected ArrayList<Docente> docentes = new ArrayList<>();
	
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
		this.administradores.clear();
		this.estudiantes.clear();
		this.docentes.clear();
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
	
	public void cargar_datos() {
		
		// DE AQUÍ SE SACARÁN LOS DATOS DE LA BASE DE DATOS
		
		String fichero = "Datos.dat";
		
		try {
			
			FileInputStream fis = new FileInputStream(fichero);
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			this.administradores = (ArrayList<Administrador>) ois.readObject();
			this.docentes = (ArrayList<Docente>) ois.readObject();
			this.estudiantes = (ArrayList<Estudiante>) ois.readObject();
			
			ois.close(); 
			fis.close();
			
		} catch (FileNotFoundException e) {
			System.err.println("Error al encontrar el archivo.");
		} catch (IOException e) {
			System.err.println("Error al cargar los datos.");
		} catch (ClassNotFoundException e) {
			System.err.println("Error al cargar los datos, formato de fichero incorrecto.");
		}
		
	}
	
	/*/
	 * String filename = JOptionPane.showInputDialog("Introduce el nombre del fichero para guardar los datos", "DATOS.dat");
	 */
	
	public void actualizar_datos() {
		
		// AQUÍ SE SUBIRÁN A LA BD, pero mientras tanto usamos ficheros
		
		String fichero = "Datos.dat";
		
		try {
			
			FileOutputStream fos = new FileOutputStream(fichero);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(this.administradores);
			oos.writeObject(this.estudiantes);
			oos.writeObject(this.docentes);
			
			oos.close();
			fos.close();
			
		} catch (IOException e) {
			
			System.err.println("Error guardando pedidos en " + fichero);
			
			e.printStackTrace();
		}
		
	}
	
	//private static final Logger logger = LoggerFactory.getLogger(Academy.class.getName());
	//private static final Logger logger = Logger.getLogger(Academy.class.getName());
	// NO DEJA IMPORTAR LAS LIBRERRÍAS NECESARIAS
	
	public static void main(String[] args) {
		
		
		Academy A1 = new Academy();
		A1.cargar_datos();
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