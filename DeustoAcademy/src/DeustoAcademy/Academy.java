package DeustoAcademy;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import javax.swing.SwingUtilities;

import Ventanas.*;

public class Academy {

	// VARIABLES DE LA CALSE CONSTRUCTOR
	protected ArrayList<Administrador> administradores = new ArrayList<>();
	protected ArrayList<Estudiante> estudiantes = new ArrayList<>();
	protected ArrayList<Docente> docentes = new ArrayList<>();
	protected ArrayList<Grupo> grupos = new ArrayList<Grupo>();
	public HashMap<Estudiante, HashMap<Idioma, Boolean>> inscritosExamenFinal = new HashMap<>();
	public HashMap<Estudiante, HashMap<Idioma, String>> notasExamenFinal = new HashMap<>();
	public HashMap<Estudiante, HashMap<Idioma, String>> notasTareas = new HashMap<>();
	
	// VARIABLES DE USO PARA MÉTODOS Y EL RESTO DEL PROGRAMA
	protected HashMap<Rols, HashMap<String, String>> claves = new HashMap<>();

	public Academy(ArrayList<Administrador> administradores, ArrayList<Estudiante> estudiantes,
			ArrayList<Docente> docentes, ArrayList<Grupo> grupos) {
		super();
		for (Docente docente : docentes) {
			this.docentes.add(docente);
		}
		
		for (Estudiante estudiante : estudiantes) {
			
			HashMap<Idioma, Boolean> nuevo_hashMap1 = new HashMap<>();
			HashMap<Idioma, String> nuevo_hashMap2 = new HashMap<>();
			HashMap<Idioma, String> nuevo_hashMap3 = new HashMap<>();
			
			for (Idioma idioma : estudiante.getIdiomas()) {
				
				nuevo_hashMap1.put(idioma, false);
				nuevo_hashMap2.put(idioma, "Examen no realizado aún");
				nuevo_hashMap3.put(idioma, "Tarea no entregada aún");
				
			}
			
			inscritosExamenFinal.put(estudiante, nuevo_hashMap1);
			notasExamenFinal.put(estudiante, nuevo_hashMap2);
			notasTareas.put(estudiante, nuevo_hashMap3);
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

	public Academy(Academy a) {
		super();
		
		for (Docente docente : a.docentes) {
			this.docentes.add(docente);
		}
		
		for (Estudiante estudiante : a.estudiantes) {
			
			HashMap<Idioma, Boolean> nuevo_hashMap1 = new HashMap<>();
			HashMap<Idioma, String> nuevo_hashMap2 = new HashMap<>();
			HashMap<Idioma, String> nuevo_hashMap3 = new HashMap<>();
			
			for (Idioma idioma : estudiante.getIdiomas()) {
				
				nuevo_hashMap1.put(idioma, false);
				nuevo_hashMap2.put(idioma, "Examen no realizado aún");
				nuevo_hashMap3.put(idioma, "Tarea no entregada aún");
				
			}
			
			inscritosExamenFinal.put(estudiante, nuevo_hashMap1);
			notasExamenFinal.put(estudiante, nuevo_hashMap2);
			notasTareas.put(estudiante, nuevo_hashMap3);
			this.estudiantes.add(estudiante);
		}
		
		for (Administrador administrador : a.administradores) {
			this.administradores.add(administrador);
		}
		
		for (Grupo grupo : a.grupos) {
			this.grupos.add(grupo);
		}
	}
	
	
	
	public HashMap<Estudiante, HashMap<Idioma, String>> getNotasTareas() {
		return notasTareas;
	}

	public void setNotasTareas(HashMap<Estudiante, HashMap<Idioma, String>> notasTareas) {
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
				+ notasExamenFinal + ", " + notasTareas + ", " + claves + "]";
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

			this.notasTareas = (HashMap<Estudiante, HashMap<Idioma, String>>) ois.readObject();

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

			claves_primarias.put(estudiante.getUsuario(), estudiante.getContraseña());

		}

		getClaves().put(Rols.ESTUDIANTE, claves_primarias);
		claves_primarias = new HashMap<>();

		for (Administrador admin : this.getAdministradores()) {

			claves_primarias.put(admin.getUsuario(), admin.getContraseña());

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
		
		return mapaEstudiante;
		
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