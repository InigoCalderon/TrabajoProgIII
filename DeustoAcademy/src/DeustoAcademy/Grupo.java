package DeustoAcademy;

import java.io.Serializable;
import java.util.ArrayList;

public class Grupo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Idioma idioma;
	protected String nombre;
	protected Docente docente;
	protected ArrayList<Estudiante> estudiantes;
	protected ArrayList<Tarea> tareas;
	
	public int capacidad_estudiantes = 0;

	public Grupo(Idioma idioma, String nombre, Docente docente, ArrayList<Estudiante> estudiantes, ArrayList<Tarea> tareas) {
		super();
		this.idioma = idioma;
		this.nombre = nombre;
		this.docente = docente;
		this.estudiantes = new ArrayList<>();
		
		if (capacidad_estudiantes < 20) {

			if (!(estudiantes.size() == 0)) {
				
				for (Estudiante estudiante : estudiantes) {
					
					this.estudiantes.add(estudiante);
					this.capacidad_estudiantes += 1;
					
				}
				
			}
			
		}
		
		this.tareas = new ArrayList<>();
		for (Tarea tarea : tareas) {
			this.tareas.add(tarea);
		}
			
	}

	public Grupo() {
		super();
		this.estudiantes.clear();
		this.capacidad_estudiantes = 0;
		this.tareas.clear();
	}

	public Grupo(Grupo g) {
		super();
		this.idioma = g.idioma;
		this.nombre = g.nombre;
		this.docente = g.docente;
		if (capacidad_estudiantes < 20) {

			if (!(estudiantes.size() == 0)) {
				
				for (Estudiante estudiante : g.estudiantes) {
					
					this.estudiantes.add(estudiante);
					this.capacidad_estudiantes += 1;
					
				}
				
			}
			
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
		if (capacidad_estudiantes < 20) {

			if (!(estudiantes.size() == 0)) {
				
				for (Estudiante estudiante : estudiantes) {
					
					this.estudiantes.add(estudiante);
					this.capacidad_estudiantes += 1;
					
				}
				
			}
			
		}
	}

	@Override
	public String toString() {
		return "Grupo [" + idioma + ", " + nombre + ", " + docente + ", "
				+ estudiantes + ", " + tareas + ", " + capacidad_estudiantes + "]";
	}

}
