package DeustoAcademy;

import java.util.ArrayList;

public class Grupo   {
	protected Idioma idioma;
	protected String nombre;
	protected Docente docente;
	protected ArrayList<Estudiante> estudiantes;
	
	public int capacidad_estudiantes = 0;

	public Grupo(Idioma idioma, String nombre, Docente docente, ArrayList<Estudiante> estudiantes) {
		super();
		this.idioma = idioma;
		this.nombre = nombre;
		this.docente = docente;
		this.estudiantes = new ArrayList<>();
		
		while (capacidad_estudiantes < 20) {

			for (Estudiante estudiante : estudiantes) {
				this.estudiantes.add(estudiante);
				this.capacidad_estudiantes += 1;
			}
			
		}
			
	}

	public Grupo() {
		super();
		this.estudiantes.clear();
		this.capacidad_estudiantes = 0;
	}

	public Grupo(Grupo g) {
		super();
		this.idioma = g.idioma;
		this.nombre = g.nombre;
		this.docente = g.docente;
		while (capacidad_estudiantes < 20) {

			for (Estudiante estudiante : estudiantes) {
				this.estudiantes.add(estudiante);
				this.capacidad_estudiantes += 1;
			}
			
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
		while (capacidad_estudiantes < 20) {

			for (Estudiante estudiante : estudiantes) {
				this.estudiantes.add(estudiante);
				this.capacidad_estudiantes += 1;
			}
			
		}
	}

	@Override
	public String toString() {
		return "Grupo " + idioma + ", " + nombre + ", " + docente + ", "
				+ estudiantes + ", " + capacidad_estudiantes + "]";
	}

}
