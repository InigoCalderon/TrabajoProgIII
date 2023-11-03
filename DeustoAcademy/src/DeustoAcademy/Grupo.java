package DeustoAcademy;

import java.util.ArrayList;

public class Grupo extends Idioma{

	protected String nombre;
	protected Docente docente;
	protected ArrayList<Estudiante> estudiantes;
	
	public Grupo(String idioma, String nombre, Docente docente, ArrayList<Estudiante> estudiantes) {
		super(idioma);
		this.nombre = nombre;
		this.docente = docente;
		this.estudiantes = new ArrayList<>();
		for (Estudiante estudiante : estudiantes) {
			this.estudiantes.add(estudiante);
		}
	}
	
	public Grupo() {
		super();
		this.estudiantes = new ArrayList<>();
	}
	
	public Grupo(Grupo g) {
		super(g.idioma);
		this.nombre = g.nombre;
		this.docente = g.docente;
		this.estudiantes = new ArrayList<>();
		for (Estudiante estudiante : g.estudiantes) {
			this.estudiantes.add(estudiante);
		}
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
		this.estudiantes = new ArrayList<>();
		for (Estudiante estudiante : estudiantes) {
			this.estudiantes.add(estudiante);
		}
	}

	@Override
	public String toString() {
		return "Grupo [nombre=" + nombre + ", docente=" + docente + ", estudiantes=" + estudiantes + ", idioma="
				+ idioma + "]";
	}	
	
}
