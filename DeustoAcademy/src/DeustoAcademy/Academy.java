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
import Ventanas.*;

public class Academy {

	protected ArrayList<Administrador> administradores = new ArrayList<>();
	protected ArrayList<Estudiante> estudiantes = new ArrayList<>();
	protected ArrayList<Docente> docentes = new ArrayList<>();

	protected HashMap<Rols, HashMap<String, String>> claves = new HashMap<>();

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

	public HashMap<Rols, HashMap<String, String>> getClaves() {
		return claves;
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

	public void actualizar_datos() {

		// AQUÍ SE SUBIRÁN A LA BD, pero mientras tanto usamos ficheros

		try {

			FileOutputStream fos = new FileOutputStream("res/admins.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			oos.writeObject(this.administradores);

			oos.close();
			fos.close();

		} catch (IOException e) {

			System.err.println("Error guardando pedidos en " + "res/admins.dat");

			e.printStackTrace();
		}

		try {

			FileOutputStream fos = new FileOutputStream("res/docentes.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			oos.writeObject(this.docentes);

			oos.close();
			fos.close();

		} catch (IOException e) {

			System.err.println("Error guardando pedidos en " + "res/docentes.dat");

			e.printStackTrace();
		}

		try {

			FileOutputStream fos = new FileOutputStream("res/estudiantes.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			oos.writeObject(this.estudiantes);

			oos.close();
			fos.close();

		} catch (IOException e) {

			System.err.println("Error guardando pedidos en " + "res/estudiantes.dat");

			e.printStackTrace();
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

	private static Logger logger = Logger.getLogger(Academy.class.getName());

	public static void main(String[] args) {

		logger.log(Level.FINE, "INICIA EL PROGRAMA"); // NO LA CARGA EN EL DOCUMENTO

		Academy A1 = new Academy();

		A1.cargar_datos();

		A1.actualizar_claves();

		new SelectRol(A1);

	}

}