package DeustoAcademy;

import java.awt.BorderLayout;
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

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

import org.json.JSONArray;
import org.json.JSONObject;

import Ventanas.*;


public class Academy {

	// VARIABLES DE LA CALSE CONSTRUCTOR
	protected ArrayList<Tarea> tareas = new ArrayList<>();
	protected ArrayList<Administrador> administradores = new ArrayList<>();
	protected ArrayList<Estudiante> estudiantes = new ArrayList<>();
	protected ArrayList<Docente> docentes = new ArrayList<>();
	protected ArrayList<Grupo> grupos = new ArrayList<Grupo>();
	public HashMap<Estudiante, HashMap<Idioma, Boolean>> inscritosExamenFinal = new HashMap<>();
	public HashMap<Estudiante, HashMap<Idioma, String>> notasExamenFinal = new HashMap<>();
	protected ArrayList<Temario> temarioDATA = new ArrayList<>();
	protected HashMap<Estudiante, HashMap<Grupo, HashMap<Tarea, String>>> notasTareas = new HashMap<>();
	
	// VARIABLES DE USO PARA MÉTODOS Y EL RESTO DEL PROGRAMA
	protected HashMap<Rols, HashMap<String, String>> claves = new HashMap<>();
	public static BaseDeDatos bd = new BaseDeDatos();

	public Academy(ArrayList<Administrador> administradores, ArrayList<Estudiante> estudiantes,
			ArrayList<Docente> docentes, ArrayList<Grupo> grupos, ArrayList<Temario> temarioDATA) {
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
		
		for (Temario temario : temarioDATA) {
			this.temarioDATA.add(temario);
		}
		
	}

	public Academy() {
		super();
		this.administradores.clear();
		this.estudiantes.clear();
		this.docentes.clear();
		this.grupos.clear();
		this.temarioDATA.clear();
	}
	
	public ArrayList<Temario> getTemarioDATA() {
		return temarioDATA;
	}

	public void setTemarioDATA(ArrayList<Temario> temarioDATA) {
		this.temarioDATA.clear();
		for (Temario temario : temarioDATA) {
			this.temarioDATA.add(temario);
		}
	}
	
	public ArrayList<Tarea> getTareas() {
		return tareas;
	}

	public void setTareas(ArrayList<Tarea> tareas) {
		this.tareas = tareas;
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
				+ temarioDATA + ", " + tareas + "]";
	}

	public HashMap<Rols, HashMap<String, String>> getClaves() {
		return claves;
	}

	public ArrayList<Grupo> getGrupos(){
		
		return grupos;
		
	}
	
	public void cargar_datos() {
		
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

			claves_primarias.put(docente.getUsuario(), docente.getContrasena());

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
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// DE AQUÍ SE SACARÁN LOS DATOS DE LA BASE DE DATOS/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	public static  void cargarEnBaseDeDatos(BaseDeDatos bd, Academy academy) {
		
		
		try {
			bd.connect("jdbc:sqlite:res/db/academy.db");
			academy.administradores = bd.cargarAdministradores();
			academy.estudiantes = bd.cargarEstudiantes();
			academy.docentes = bd.cargarDocentes();
			academy.grupos = bd.cargarGrupos(academy);
			academy.tareas = bd.cargarTarea();
			academy.inscritosExamenFinal = bd.cargarInscritosExamenFinal(academy);
			academy.notasExamenFinal = bd.cargarNotasExamenFinal(academy);
			academy.notasTareas = bd.cargarNotasTareas(academy);
			bd.cargarTemarioData(academy);
			bd.disconnect();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	}
	public void actuarlizarDatosEnBaseDeDatos(Rols rol, BaseDeDatos bd, Academy academy) {		// Basado en "actualizarDatos" 
		try {
			bd.connect("jdbc:sqlite:res/db/academy.db");
			
			if (rol == Rols.ESTUDIANTE) {
				for (Estudiante estudiante : this.estudiantes) {
					bd.guardarEstudiante(estudiante);
				}
				bd.guardarInscritosExamenFinal(this.inscritosExamenFinal);
				bd.guardarNotasExamenFinal(this.notasExamenFinal);
				bd.guardarNotasTareas(this.notasTareas);										
				for (Grupo grupo :this.grupos) {
					bd.guardarGrupo(grupo, academy);
				}
			}else if (rol == Rols.DOCENTE) {
				for (Docente docente : this.docentes) {
					bd.guardarDocente(docente);
				}
				for (Grupo grupo :this.grupos) {
					bd.guardarGrupo(grupo, academy);
				}
				for (Temario temario : temarioDATA) {
					bd.guardarTemarioDATA(temario);
				}
				for (Tarea tarea : this.tareas) {
					bd.guardarTarea(tarea);
				}
			}else if (rol == Rols.ADMINISTRADOR) {
				for (Administrador administrador : this.administradores) {
					bd.guardarAdministrador(administrador);
				}
			}else {
				for (Administrador administrador : this.administradores) {
					bd.guardarAdministrador(administrador);
				}
				for (Docente docente : this.docentes) {
					bd.guardarDocente(docente);
				}
				for (Estudiante estudiante : this.estudiantes) {
					bd.guardarEstudiante(estudiante);
				}
				bd.guardarInscritosExamenFinal(this.inscritosExamenFinal);
				bd.guardarNotasExamenFinal(this.notasExamenFinal);
				bd.guardarNotasTareas(this.notasTareas);																
				for (Grupo grupo :this.grupos) {
					bd.guardarGrupo(grupo, academy);
				}
				for (Temario temario : temarioDATA) {
					bd.guardarTemarioDATA(temario);
				}
				for (Tarea tarea : this.tareas) {
					bd.guardarTarea(tarea);
				}
				
			}
			
			bd.disconnect();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
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
				
				Academy A1 = new Academy();
				
				//A1.cargar_datos();		carga datos de ficheros
				
				try {
					bd.connect("jdbc:sqlite:res/db/academy.db");
					cargarEnBaseDeDatos(bd, A1);
					bd.disconnect();
					A1.actualizar_claves();
					new SelectRol(A1);
					
					logger.log(Level.FINE, "INICIA EL PROGRAMA"); // NO LA CARGA EN EL DOCUMENTO
					
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
			}
	
		});
	
	}

}