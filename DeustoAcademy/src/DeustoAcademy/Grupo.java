package DeustoAcademy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Grupo implements Serializable, Comparable<Grupo> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Idioma idioma;
	protected String nombre;
	protected Docente docente;
	protected ArrayList<Estudiante> estudiantes;
	protected ArrayList<Tarea> tareas;
	
	// AÑADIR
	protected HashMap<Estudiante, HashMap<Tarea, Integer>> notaTareas = new HashMap<>();
	
	private int capacidad_estudiantes = 0;

	public Grupo(Idioma idioma, String nombre, Docente docente, ArrayList<Estudiante> estudiantes, ArrayList<Tarea> tareas) {
		super();
		this.idioma = idioma;
		this.nombre = nombre;
		this.docente = docente;
		this.estudiantes = new ArrayList<>();
		
		for (Estudiante estudiante : estudiantes) {
			this.estudiantes.add(estudiante);
			this.setCapacidad_estudiantes(this.getCapacidad_estudiantes() + 1);
		}
		
		this.tareas = new ArrayList<>();
		for (Tarea tarea : tareas) {
			this.tareas.add(tarea);
		}
			
	}

	public Grupo() {
		super();
		this.estudiantes.clear();
		this.setCapacidad_estudiantes(0);
		this.tareas.clear();
	}

	public Grupo(Grupo g) {
		super();
		this.idioma = g.idioma;
		this.nombre = g.nombre;
		this.docente = g.docente;
		this.estudiantes = new ArrayList<>();
		
		for (Estudiante estudiante : g.estudiantes) {
			this.estudiantes.add(estudiante);
			this.setCapacidad_estudiantes(this.getCapacidad_estudiantes() + 1);
		}
		
		this.tareas = new ArrayList<>();
		for (Tarea tarea : g.tareas) {
			this.tareas.add(tarea);
		}
	}
	
	public ArrayList<Tarea> getTareas() {
		return tareas;
	}

	public void setTareas(ArrayList<Tarea> tareas) {
		this.tareas = new ArrayList<>();
		for (Tarea tarea : tareas) {
			this.tareas.add(tarea);
		}
	}

	public Idioma getIdioma() {
		return idioma;
	}
	
	public void setIdioma(Idioma idioma) {
		this.idioma = idioma;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Docente getDocente() {
		return docente;
	}

	public void setDocente(Docente docente) {
		this.docente = docente;
	}

	public ArrayList<Estudiante> getEstudiantes() {
		return estudiantes;
	}

	public void setEstudiantes(ArrayList<Estudiante> estudiantes) {
		this.estudiantes.clear();
	
		for (Estudiante estudiante : estudiantes) {
			this.estudiantes.add(estudiante);
			this.setCapacidad_estudiantes(this.getCapacidad_estudiantes() + 1);
		}
	}

	@Override
	public String toString() {
		return "Grupo [" + idioma + ", " + nombre + ", " + docente + ", "
				+ estudiantes + ", " + tareas + ", " + getCapacidad_estudiantes() + "]";
	}

	@Override
	public int compareTo(Grupo o) {
		
		// LOS GRUPOS DEBEN TENER NOMBRES DISTINTOS PORQUE FUNCIONAN COMO UN IDENTIFICADOR PARA PODER COMPARARLOS
		return this.nombre.compareToIgnoreCase(o.nombre);
		
	}

	public int getCapacidad_estudiantes() {
		return capacidad_estudiantes;
	}

	public void setCapacidad_estudiantes(int capacidad_estudiantes) {
		this.capacidad_estudiantes = capacidad_estudiantes;
	}
	
	

}
