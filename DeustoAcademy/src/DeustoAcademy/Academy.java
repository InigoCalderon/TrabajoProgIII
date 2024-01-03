package DeustoAcademy;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import javax.swing.SwingUtilities;

import org.json.JSONArray;
import org.json.JSONObject;

import Ventanas.*;

public class Academy {

	// VARIABLES DE LA CALSE CONSTRUCTOR
	protected ArrayList<Administrador> administradores = new ArrayList<>();
	protected ArrayList<Estudiante> estudiantes = new ArrayList<>();
	protected ArrayList<Docente> docentes = new ArrayList<>();
	protected ArrayList<Grupo> grupos = new ArrayList<Grupo>();
	public HashMap<Estudiante, HashMap<Idioma, Boolean>> inscritosExamenFinal = new HashMap<>();
	public HashMap<Estudiante, HashMap<Idioma, String>> notasExamenFinal = new HashMap<>();
	
	// VARIABLES DE USO PARA MÉTODOS Y EL RESTO DEL PROGRAMA
	protected HashMap<Rols, HashMap<String, String>> claves = new HashMap<>();
	protected HashMap<Estudiante, HashMap<Grupo, HashMap<Tarea, String>>> notasTareas = new HashMap<>();
	protected ArrayList<Temario> temarioDATA = new ArrayList<>();

	public Academy(ArrayList<Administrador> administradores, ArrayList<Estudiante> estudiantes,
			ArrayList<Docente> docentes, ArrayList<Grupo> grupos) {
		super();
		for (Docente docente : docentes) {
			this.docentes.add(docente);
		}
		
		for (Estudiante estudiante : estudiantes) {
			
			HashMap<Idioma, Boolean> nuevo_hashMap1 = new HashMap<>();
			HashMap<Idioma, String> nuevo_hashMap2 = new HashMap<>();
			
			for (Idioma idioma : estudiante.getIdiomas()) {
				
				nuevo_hashMap1.put(idioma, false);
				nuevo_hashMap2.put(idioma, "Examen no realizado aún");
				
			}
			
			inscritosExamenFinal.put(estudiante, nuevo_hashMap1);
			notasExamenFinal.put(estudiante, nuevo_hashMap2);
			this.estudiantes.add(estudiante);
			
		}
		
		for (Administrador administrador : administradores) {
			this.administradores.add(administrador);
		}
		
		for (Grupo grupo : grupos) {
			this.grupos.add(grupo);
		}
		
	}

	public Academy() {
		super();
		this.administradores.clear();
		this.estudiantes.clear();
		this.docentes.clear();
		this.grupos.clear();
	}
	
	public ArrayList<Temario> getTemarioDATA() {
		return temarioDATA;
	}

	public void setTemarioDATA(ArrayList<Temario> temarioDATA) {
		this.temarioDATA = temarioDATA;
	}

	public HashMap<Estudiante, HashMap<Grupo, HashMap<Tarea, String>>> getNotasTareas() {
		return notasTareas;
	}

	public void setNotasTareas(HashMap<Estudiante, HashMap<Grupo, HashMap<Tarea, String>>> notasTareas) {
		this.notasTareas = notasTareas;
	}

	public HashMap<Estudiante, HashMap<Idioma, Boolean>> getInscritosExamenFinal() {
		return inscritosExamenFinal;
	}

	public void setInscritosExamenFinal(HashMap<Estudiante, HashMap<Idioma, Boolean>> inscritosExamenFinal) {
		this.inscritosExamenFinal = inscritosExamenFinal;
	}

	public HashMap<Estudiante, HashMap<Idioma, String>> getNotasExamenFinal() {
		return notasExamenFinal;
	}

	public void setNotasExamenFinal(HashMap<Estudiante, HashMap<Idioma, String>> notasExamenFinal) {
		this.notasExamenFinal = notasExamenFinal;
	}

	public void setClaves(HashMap<Rols, HashMap<String, String>> claves) {
		this.claves = claves;
	}

	public ArrayList<Administrador> getAdministradores() {
		return administradores;
	}

	public void setGrupos(ArrayList<Grupo> grupos) {
		this.grupos.clear();
		for (Grupo grupo : grupos) {
			this.grupos.add(grupo);
		}
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
		return "Academy [" + administradores + ", " + estudiantes + ", " + docentes
				+ ", " + grupos + ", " + inscritosExamenFinal + ", "
				+ notasExamenFinal + ", " + claves + ", " + notasTareas + ", "
				+ temarioDATA + "]";
	}

	public HashMap<Rols, HashMap<String, String>> getClaves() {
		return claves;
	}

	public ArrayList<Grupo> getGrupos(){
		
		return grupos;
		
	}
	
	public void cargar_datos() {

		// DE AQUÍ SE SACARÁN LOS DATOS DE LA BASE DE DATOS

		try {

			FileInputStream fis = new FileInputStream("res/temario.dat");
			ObjectInputStream ois = new ObjectInputStream(fis);

			this.temarioDATA = (ArrayList<Temario>) ois.readObject();

			ois.close();
			fis.close();

		} catch (FileNotFoundException e) {
			System.err.println("Error al encontrar el archivo.");
		} catch (IOException e) {
			System.err.println("Error al cargar los datos.");
		} catch (ClassNotFoundException e) {
			System.err.println("Error al cargar los datos, formato de fichero incorrecto.");
		}
		
		try {

			FileInputStream fis = new FileInputStream("res/estudiantes.dat");
			ObjectInputStream ois = new ObjectInputStream(fis);

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
		
		try {

			FileInputStream fis = new FileInputStream("res/inscritosExamenFinal.dat");
			ObjectInputStream ois = new ObjectInputStream(fis);

			this.inscritosExamenFinal = (HashMap<Estudiante, HashMap<Idioma, Boolean>>) ois.readObject();

			ois.close();
			fis.close();

		} catch (FileNotFoundException e) {
			System.err.println("Error al encontrar el archivo.");
		} catch (IOException e) {
			System.err.println("Error al cargar los datos.");
		} catch (ClassNotFoundException e) {
			System.err.println("Error al cargar los datos, formato de fichero incorrecto.");
		}
		
		try {

			FileInputStream fis = new FileInputStream("res/notasExamenFinal.dat");
			ObjectInputStream ois = new ObjectInputStream(fis);

			this.notasExamenFinal = (HashMap<Estudiante, HashMap<Idioma, String>>) ois.readObject();

			ois.close();
			fis.close();

		} catch (FileNotFoundException e) {
			System.err.println("Error al encontrar el archivo.");
		} catch (IOException e) {
			System.err.println("Error al cargar los datos.");
		} catch (ClassNotFoundException e) {
			System.err.println("Error al cargar los datos, formato de fichero incorrecto.");
		}
		
		try {

			FileInputStream fis = new FileInputStream("res/notasTareas.dat");
			ObjectInputStream ois = new ObjectInputStream(fis);

			this.notasTareas = (HashMap<Estudiante, HashMap<Grupo, HashMap<Tarea, String>>>) ois.readObject();

			ois.close();
			fis.close();

		} catch (FileNotFoundException e) {
			System.err.println("Error al encontrar el archivo.");
		} catch (IOException e) {
			System.err.println("Error al cargar los datos.");
		} catch (ClassNotFoundException e) {
			System.err.println("Error al cargar los datos, formato de fichero incorrecto.");
		}
		
		try {

			FileInputStream fis = new FileInputStream("res/grupos.dat");
			ObjectInputStream ois = new ObjectInputStream(fis);

			this.grupos = (ArrayList<Grupo>) ois.readObject();

			ois.close();
			fis.close();

		} catch (FileNotFoundException e) {
			System.err.println("Error al encontrar el archivo.");
		} catch (IOException e) {
			System.err.println("Error al cargar los datos.");
		} catch (ClassNotFoundException e) {
			System.err.println("Error al cargar los datos, formato de fichero incorrecto.");
		}

		try {

			FileInputStream fis = new FileInputStream("res/admins.dat");
			ObjectInputStream ois = new ObjectInputStream(fis);

			this.administradores = (ArrayList<Administrador>) ois.readObject();

			ois.close();
			fis.close();

		} catch (FileNotFoundException e) {
			System.err.println("Error al encontrar el archivo.");
		} catch (IOException e) {
			System.err.println("Error al cargar los datos.");
		} catch (ClassNotFoundException e) {
			System.err.println("Error al cargar los datos, formato de fichero incorrecto.");
		}

		try {

			FileInputStream fis = new FileInputStream("res/docentes.dat");
			ObjectInputStream ois = new ObjectInputStream(fis);

			this.docentes = (ArrayList<Docente>) ois.readObject();

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

	/*
	 * / String filename = JOptionPane.
	 * showInputDialog("Introduce el nombre del fichero para guardar los datos",
	 * "DATOS.dat");
	 */

	public void actualizar_datos(Rols rol) {

		// AQUÍ SE SUBIRÁN A LA BD, pero mientras tanto usamos ficheros

		if (rol == Rols.ESTUDIANTE) {
			
			try {

				FileOutputStream fos = new FileOutputStream("res/estudiantes.dat");
				ObjectOutputStream oos = new ObjectOutputStream(fos);

				oos.writeObject(this.estudiantes);

				oos.close();
				fos.close();

			} catch (IOException e) {

				System.err.println("Error guardando datos en " + "res/estudiantes.dat");

				e.printStackTrace();
			}
			
			try {

				FileOutputStream fos = new FileOutputStream("res/inscritosExamenFinal.dat");
				ObjectOutputStream oos = new ObjectOutputStream(fos);

				oos.writeObject(this.inscritosExamenFinal);

				oos.close();
				fos.close();

			} catch (IOException e) {

				System.err.println("Error guardando datos en " + "res/inscritosExamenFinal.dat");

				e.printStackTrace();
			}
					
			try {

				FileOutputStream fos = new FileOutputStream("res/notasExamenFinal.dat");
				ObjectOutputStream oos = new ObjectOutputStream(fos);

				oos.writeObject(this.notasExamenFinal);

				oos.close();
				fos.close();

			} catch (IOException e) {

				System.err.println("Error guardando datos en " + "res/notasExamenFinal.dat");

				e.printStackTrace();
			}
			
			try {

				FileOutputStream fos = new FileOutputStream("res/notasTareas.dat");
				ObjectOutputStream oos = new ObjectOutputStream(fos);

				oos.writeObject(this.notasTareas);

				oos.close();
				fos.close();

			} catch (IOException e) {

				System.err.println("Error guardando datos en " + "res/notasTareas.dat");

				e.printStackTrace();
			}
			
			try {

				FileOutputStream fos = new FileOutputStream("res/grupos.dat");
				ObjectOutputStream oos = new ObjectOutputStream(fos);

				oos.writeObject(this.grupos);

				oos.close();
				fos.close();

			} catch (IOException e) {

				System.err.println("Error guardando datos en " + "res/grupos.dat");

				e.printStackTrace();
			}
			
		} else if (rol == Rols.DOCENTE) {
			
			try {

				FileOutputStream fos = new FileOutputStream("res/docentes.dat");
				ObjectOutputStream oos = new ObjectOutputStream(fos);

				oos.writeObject(this.docentes);

				oos.close();
				fos.close();

			} catch (IOException e) {

				System.err.println("Error guardando datos en " + "res/docentes.dat");

				e.printStackTrace();
			}
			
			try {

				FileOutputStream fos = new FileOutputStream("res/grupos.dat");
				ObjectOutputStream oos = new ObjectOutputStream(fos);

				oos.writeObject(this.grupos);

				oos.close();
				fos.close();

			} catch (IOException e) {

				System.err.println("Error guardando datos en " + "res/grupos.dat");

				e.printStackTrace();
			}
			
		} else if (rol == Rols.ADMINISTRADOR) {
			
			try {

				FileOutputStream fos = new FileOutputStream("res/admins.dat");
				ObjectOutputStream oos = new ObjectOutputStream(fos);

				oos.writeObject(this.administradores);

				oos.close();
				fos.close();

			} catch (IOException e) {

				System.err.println("Error guardando datos en " + "res/admins.dat");

				e.printStackTrace();
			}
			
		} else {
			
			try {

				FileOutputStream fos = new FileOutputStream("res/temario.dat");
				ObjectOutputStream oos = new ObjectOutputStream(fos);

				oos.writeObject(this.temarioDATA);

				oos.close();
				fos.close();

			} catch (IOException e) {

				System.err.println("Error guardando datos en " + "res/temario.dat");

				e.printStackTrace();
			}
			
			try {

				FileOutputStream fos = new FileOutputStream("res/admins.dat");
				ObjectOutputStream oos = new ObjectOutputStream(fos);

				oos.writeObject(this.administradores);

				oos.close();
				fos.close();

			} catch (IOException e) {

				System.err.println("Error guardando datos en " + "res/admins.dat");

				e.printStackTrace();
			}

			try {

				FileOutputStream fos = new FileOutputStream("res/docentes.dat");
				ObjectOutputStream oos = new ObjectOutputStream(fos);

				oos.writeObject(this.docentes);

				oos.close();
				fos.close();

			} catch (IOException e) {

				System.err.println("Error guardando datos en " + "res/docentes.dat");

				e.printStackTrace();
			}

			try {

				FileOutputStream fos = new FileOutputStream("res/estudiantes.dat");
				ObjectOutputStream oos = new ObjectOutputStream(fos);

				oos.writeObject(this.estudiantes);

				oos.close();
				fos.close();

			} catch (IOException e) {

				System.err.println("Error guardando datos en " + "res/estudiantes.dat");

				e.printStackTrace();
			}

			try {

				FileOutputStream fos = new FileOutputStream("res/inscritosExamenFinal.dat");
				ObjectOutputStream oos = new ObjectOutputStream(fos);

				oos.writeObject(this.inscritosExamenFinal);

				oos.close();
				fos.close();

			} catch (IOException e) {

				System.err.println("Error guardando datos en " + "res/inscritosExamenFinal.dat");

				e.printStackTrace();
			}
			
			try {

				FileOutputStream fos = new FileOutputStream("res/notasExamenFinal.dat");
				ObjectOutputStream oos = new ObjectOutputStream(fos);

				oos.writeObject(this.notasExamenFinal);

				oos.close();
				fos.close();

			} catch (IOException e) {

				System.err.println("Error guardando datos en " + "res/notasExamenFinal.dat");

				e.printStackTrace();
			}
			
			try {

				FileOutputStream fos = new FileOutputStream("res/notasTareas.dat");
				ObjectOutputStream oos = new ObjectOutputStream(fos);

				oos.writeObject(this.notasTareas);

				oos.close();
				fos.close();

			} catch (IOException e) {

				System.err.println("Error guardando datos en " + "res/notasTareas.dat");

				e.printStackTrace();
			}
			
			try {

				FileOutputStream fos = new FileOutputStream("res/grupos.dat");
				ObjectOutputStream oos = new ObjectOutputStream(fos);

				oos.writeObject(this.grupos);

				oos.close();
				fos.close();

			} catch (IOException e) {

				System.err.println("Error guardando datos en " + "res/grupos.dat");

				e.printStackTrace();
			}
			
		}
		
	}

	public void actualizar_claves() {

		HashMap<String, String> claves_primarias = new HashMap<>();

		for (Estudiante estudiante : this.getEstudiantes()) {

			claves_primarias.put(estudiante.getUsuario(), estudiante.getContrasena());

		}

		getClaves().put(Rols.ESTUDIANTE, claves_primarias);
		claves_primarias = new HashMap<>();

		for (Administrador admin : this.getAdministradores()) {

			claves_primarias.put(admin.getUsuario(), admin.getContrasena());

		}

		getClaves().put(Rols.ADMINISTRADOR, claves_primarias);
		claves_primarias = new HashMap<>();

		for (Docente docente : this.getDocentes()) {

			claves_primarias.put(docente.getUsuario(), docente.getContraseña());

		}

		getClaves().put(Rols.DOCENTE, claves_primarias);
		claves_primarias = new HashMap<>();

		System.out.println("CLAVES ACTUALIZADAS");

		/*
		 * / SOLO PUEDE HABER UN ÚNICO NOMBRE DE USUARIO POR ROL.
		 */

	}
	public Float metodoActivoAcadaemia(Academy datos) {
		Float tarifa = 0f;
		Float sueldo = 0f;
		for (Estudiante estudiante : mapaTarifaEstudiante(datos).keySet()) {
			tarifa += mapaTarifaEstudiante(datos).get(estudiante);
			
		}
		for (Docente docente : mapaSueldoDocente(datos).keySet()) {
			sueldo += mapaSueldoDocente(datos).get(docente);
			
		}	
		return tarifa - sueldo;
		
	}
	public TreeMap<Estudiante, Float> mapaTarifaEstudiante(Academy datos){
		TreeMap<Estudiante, Float> mapa = new TreeMap<Estudiante, Float>();
		
		for (Estudiante estudiante : datos.getEstudiantes()) {
			Float tarifaMensual = 0f;
			if (estudiante.getIdiomas().contains(Idioma.Castellano)) {
				tarifaMensual += 125f;
			}
			if(estudiante.getIdiomas().contains(Idioma.Euskera)) {
				tarifaMensual += 130f;
			}
			if(estudiante.getIdiomas().contains(Idioma.Ingles)) {
				tarifaMensual += 140f;
			}
			if(estudiante.getIdiomas().contains(Idioma.Frances)) {
				tarifaMensual += 135f;
			}
			
			mapa.put(estudiante, tarifaMensual);
		}
		return mapa;
		
	}
	
	public TreeMap<Docente, Float> mapaSueldoDocente(Academy datos){
		TreeMap<Docente, Float> mapa = new TreeMap<Docente, Float>();
		
		for (Docente docente : datos.getDocentes()) {
			Float sueldoMensual = 0f;
			if (docente.getIdioma().equals(Idioma.Castellano)) {
				sueldoMensual += 150f;
			} else if(docente.getIdioma().equals(Idioma.Euskera)) {
				sueldoMensual += 160f;
			}else if(docente.getIdioma().equals(Idioma.Ingles)) {
				sueldoMensual += 170;
			}else if(docente.getIdioma().equals(Idioma.Frances)) {
				sueldoMensual += 155f;
			}
			
			for (Grupo grupo : datos.getGrupos()) {
			
				if(grupo.getDocente().equals(docente)) {
					sueldoMensual += 900f;
				}
			}
			mapa.put(docente, sueldoMensual);
			}
		return mapa;
		
	}
	
	public class comparadorEstudiantes implements Comparator<Estudiante>{

		@Override
		public int compare(Estudiante o1, Estudiante o2) {
			// TODO Auto-generated method stub
			if (o1.getNombre() != o2.getNombre()) return o1.getNombre().compareTo(o2.getNombre());
			return o1.getApellido().compareTo(o2.getApellido());
		}
		
	}
	public HashMap<String, ArrayList<Estudiante>> actualizarMapaEstudiante() {
		
		HashMap<String, ArrayList<Estudiante>> mapaEstudiante = new HashMap<>();
		
		for (Estudiante estudiante : estudiantes) {
			
			for (Grupo grupo: grupos) {
				
				if (grupo.getEstudiantes().contains(estudiante) && grupo.getIdioma()==Idioma.Castellano && !mapaEstudiante.containsKey("Castellano")) {
					
					mapaEstudiante.put("Castellano", new ArrayList<Estudiante>());
					mapaEstudiante.get("Castellano").add(estudiante);
					
				} else if(grupo.getEstudiantes().contains(estudiante) && grupo.getIdioma()==Idioma.Castellano && mapaEstudiante.containsKey("Castellano")) {
					
					mapaEstudiante.get("Castellano").add(estudiante);
					
				} if (grupo.getEstudiantes().contains(estudiante) && grupo.getIdioma()== Idioma.Ingles && !mapaEstudiante.containsKey("Ingles")) {
					
					mapaEstudiante.put("Ingles", new ArrayList<Estudiante>());
					mapaEstudiante.get("Ingles").add(estudiante);
					
				} else if(grupo.getEstudiantes().contains(estudiante) && grupo.getIdioma()== Idioma.Ingles && mapaEstudiante.containsKey("Ingles")) {
					
					mapaEstudiante.get("Ingles").add(estudiante);
					
				} if (grupo.getEstudiantes().contains(estudiante) && grupo.getIdioma()== Idioma.Euskera && !mapaEstudiante.containsKey("Euskera")) {
					
					mapaEstudiante.put("Euskera", new ArrayList<Estudiante>());
					mapaEstudiante.get("Euskera").add(estudiante);
					
				} else if(grupo.getEstudiantes().contains(estudiante) && grupo.getIdioma()== Idioma.Euskera && mapaEstudiante.containsKey("Euskera")) {
					
					mapaEstudiante.get("Euskera").add(estudiante);
					
				} if (grupo.getEstudiantes().contains(estudiante) && grupo.getIdioma()== Idioma.Frances && !mapaEstudiante.containsKey("Frances")) {
					
					mapaEstudiante.put("Frances", new ArrayList<Estudiante>());
					mapaEstudiante.get("Frances").add(estudiante);
					
				} else if(grupo.getEstudiantes().contains(estudiante) && grupo.getIdioma()== Idioma.Frances && mapaEstudiante.containsKey("Frances")) {
					
					mapaEstudiante.get("Frances").add(estudiante);
					
				}
				
			}
			
		}
		// Ordenamos todas las listas de estudiantes que hay por cada idioma del mapa
		for (java.util.Map.Entry<String, ArrayList<Estudiante>> e: mapaEstudiante.entrySet()) {
			Collections.sort(e.getValue(), new comparadorEstudiantes());
		}
		
		return mapaEstudiante;
		
	}
	
	
	// MÉTODO PARA ACTUALIZAR LAS NOTAS DE LAS TAREAS -- PARA ÍÑIGO
	// HE PUESTO RETURN PARA QUE SE MUESTRE ALGO PERO DEBERÁ SER ESTE MÉTODO VOID Y NO DEVOLVER NADA
	// SIMPLEMENTE MODIFICAR LAS NOTAS
	public HashMap<Estudiante, HashMap<Grupo, HashMap<Tarea, String>>> actualizarNotasTareas(Estudiante estudiante){
			
		HashMap<Grupo, HashMap<Tarea, String>> nuevo_hashMap = new HashMap<>();
		
		for (Grupo grupo : this.getGrupos()) {
			
			if (grupo.getEstudiantes().contains(estudiante)) {
				
				HashMap<Tarea, String> nuevo_HashMap2 = new HashMap<>();
				
				for (Tarea tarea : grupo.getTareas()) {
					
					// AQUÍ SE METE LA NOTA DE LA TAREA
					nuevo_HashMap2.put(tarea, "Sin califiar");
					
				}
				
				nuevo_hashMap.put(grupo, nuevo_HashMap2);
				nuevo_HashMap2.clear();
				
			}
			
		}
		
		return new HashMap<Estudiante, HashMap<Grupo, HashMap<Tarea, String>>>();
		
	}
	
	
	
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////BD EN PRUEBAS
/*/
public void baseDatos() throws SQLException {
try {
Class.forName("org.sqlite.JDBC");
} catch (ClassNotFoundException e) {
// TODO Auto-generated catch block
System.out.println("mensaje de error");
}
try {
Connection conn = DriverManager.getConnection("jdbc:sqlite:academy.db");

PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Administrador;");

ResultSet rs = stmt.executeQuery();

while (rs.next()) {
String nombre = rs.getString("nombre");
String apellido = rs.getString("apellido");
String dni = rs.getString("dni");
String correo = rs.getString("correo");
int telefono = rs.getInt("telefono");
String usuario = rs.getString("usuario");
String contrasena = rs.getString("contrasena");  // Nada de "ñ" !!!

Administrador administrador = new Administrador(nombre, apellido, dni, correo, telefono, usuario, contrasena);
this.administradores.add(administrador);
}

rs.close();

for (Administrador administrador : this.administradores) {

String plantilla = "INSERT INTO Administrador (nombre, apellido, dni, correo, telefono, usuario, contrasena) VALUES (?, ?, ?, ?, ?, ?, ?)";
PreparedStatement prepStmt = conn.prepareStatement(plantilla);

prepStmt.setString(1, administrador.getNombre());
prepStmt.setString(2, administrador.getApellido());
prepStmt.setString(3, administrador.getDni());
prepStmt.setString(4, administrador.getCorreo());
prepStmt.setInt(5, administrador.getTelefono());
prepStmt.setString(6, administrador.getUsuario());
prepStmt.setString(7, administrador.getContrasena());
prepStmt.executeUpdate();
}

stmt.close();

PreparedStatement stmtEstudiante = conn.prepareStatement("SELECT * FROM Estudiante;");
ResultSet rsEstudianteRecursos = stmtEstudiante.executeQuery();

while (rsEstudianteRecursos.next()) {
String nombre = rsEstudianteRecursos.getString("nombre");
String apellido = rsEstudianteRecursos.getString("apellido");
String dni = rsEstudianteRecursos.getString("dni");
String correo = rsEstudianteRecursos.getString("correo");
int telefono = rsEstudianteRecursos.getInt("telefono");
String usuario = rsEstudianteRecursos.getString("usuario");
String contrasena = rsEstudianteRecursos.getString("contrasena");
ArrayList<Idioma> idiomas = (ArrayList<Idioma>) rsEstudianteRecursos.getArray("idiomas");

Estudiante estudiante = new Estudiante(nombre, apellido, telefono, correo, dni, usuario, contrasena, idiomas);
this.estudiantes.add(estudiante);
}

rsEstudianteRecursos.close();

for (Estudiante estudiante : this.estudiantes) {

String plantilla = "INSERT INTO Estudiante (nombre, apellido, telefono, correo, dni, usuario, contrasena, idiomas) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
PreparedStatement preprsEstudianteRecursos = conn.prepareStatement(plantilla);

preprsEstudianteRecursos.setString(1, estudiante.getNombre());
preprsEstudianteRecursos.setString(2, estudiante.getApellido());
preprsEstudianteRecursos.setInt(3, estudiante.getTelefono());
preprsEstudianteRecursos.setString(4, estudiante.getCorreo());
preprsEstudianteRecursos.setString(5, estudiante.getDni());
preprsEstudianteRecursos.setString(6, estudiante.getUsuario());
preprsEstudianteRecursos.setString(7, estudiante.getContrasena());
preprsEstudianteRecursos.setArray(8, (Array) estudiante.getIdiomas());
preprsEstudianteRecursos.executeUpdate();
}

stmtEstudiante.close();

PreparedStatement stmtGrupo = conn.prepareStatement("SELECT * FROM Grupo;");
ResultSet rsGrupo = stmtGrupo.executeQuery();

while (rsGrupo.next()) {
Idioma idioma = Idioma.valueOf(rsGrupo.getString("idioma"));
String nombre = rsGrupo.getString("nombre");
Clob docente = rsGrupo.getClob("docente");
ArrayList<Estudiante> estudiantes = (ArrayList<Estudiante>) rsGrupo.getArray("estudiantes");
ArrayList<Tarea> tareas = (ArrayList<Tarea>) rsGrupo.getArray("tareas");

Grupo grupo = new Grupo((Idioma) idioma, nombre, (Docente) docente, estudiantes, tareas);
this.grupos.add(grupo);
}

rsGrupo.close();

for (Grupo grupo : this.grupos) {

String plantilla = "INSERT INTO Estudiante (nombre, apellido, telefono, correo, dni, usuario, contrasena, idiomas) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
PreparedStatement preprsGrupo = conn.prepareStatement(plantilla);

preprsGrupo.setBlob(1, (Idioma) grupo.getIdioma());
preprsGrupo.setString(2, grupo.getNombre());
preprsGrupo.setClob(3, (Clob) grupo.getDocente());
preprsGrupo.setArray(4, (Array) grupo.getEstudiantes());
preprsGrupo.setArray(5, (Array) grupo.getTareas());
preprsGrupo.executeUpdate();
}

stmtGrupo.close();

conn.close(); 

} catch (SQLException e) {
e.printStackTrace();
}

}
/*/
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

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
				this.administradores.add(new Administrador(nombre, apellido, dni, correo, telefono, usuario, contrasena));
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
			this.estudiantes.add(new Estudiante(nombre, apellido, telefono, correo, dni, usuario, contrasena, idiomas));
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
			this.docentes.add(new Docente(nombre, apellido, dni, correo, telefono, usuario, contrasena, idioma));
			}
		}
	}
	
	private void cargarInscritosExamenFinal(Connection conn) throws SQLException {
		try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM inscritosExamenFinal");
		ResultSet rs = stmt.executeQuery()) {
		
			this.inscritosExamenFinal.clear();
		
			while (rs.next()) {
			
			// Obtener Clob para Estudiante
			Clob estudianteClob = rs.getClob("estudiante");
			Estudiante estudiante = clobToEstudiante(estudianteClob);
			
			Idioma idioma = Idioma.valueOf(rs.getString("idioma"));
			
			HashMap<Idioma, Boolean> nuevo_mapa = new HashMap<>();
			nuevo_mapa.put(idioma, rs.getBoolean("boolean"));
			
			this.inscritosExamenFinal.put(estudiante, nuevo_mapa);
			
			}
		}
	}
	
	private void cargarNotasExamenFinal(Connection conn) throws SQLException {
		try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM notasExamenFinal");
		ResultSet rs = stmt.executeQuery()) {
		
			this.notasExamenFinal.clear();
			
			while (rs.next()) {
			
			// Obtener Clob para Estudiante
			Clob estudianteClob = rs.getClob("estudiante");
			Estudiante estudiante = clobToEstudiante(estudianteClob);
			
			Idioma idioma = Idioma.valueOf(rs.getString("idioma"));
			
			HashMap<Idioma, String> nuevo_mapa = new HashMap<>();
			nuevo_mapa.put(idioma, rs.getString("nota"));
			
			this.notasExamenFinal.put(estudiante, nuevo_mapa);
			
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
			
			this.grupos.add(new Grupo(idioma, nombre, docente, estudiantes, tareas));
			
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
    
	private static Logger logger = Logger.getLogger(Academy.class.getName());
	
	public static void main(String[] args) {
	
		SwingUtilities.invokeLater(new Runnable() {
	
			@Override
			public void run() {
	
				try (FileInputStream fis = new FileInputStream("res/logger.properties")) {
				LogManager.getLogManager().readConfiguration(fis);
				} catch (IOException e) {
				logger.log(Level.SEVERE, "No se pudo leer el fichero de configuración del logger");
				}
				
				logger.log(Level.FINE, "INICIA EL PROGRAMA"); // NO LA CARGA EN EL DOCUMENTO
				
				Academy A1 = new Academy();
				
				A1.cargar_datos();
				
				A1.actualizar_claves();
				
				new SelectRol(A1);
	
			}
	
		});
	
	}

}