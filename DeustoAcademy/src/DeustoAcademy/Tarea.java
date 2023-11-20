package DeustoAcademy;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Tarea {

	protected String fecha_creacion = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(ZonedDateTime.now());
	protected String fecha_entrega = null;
	protected String titulo;
	protected String comentario;
	protected int calificacion;
	protected int n_docs_adjuntos;
	protected ArrayList<String> nombre_docs_adjuntos;

	public Tarea(String fecha_creacion, String fecha_entrega, String titulo, String comentario, int calificacion, int n_docs_adjuntos,
			ArrayList<String> nombre_docs_adjuntos) {
		super();
		this.fecha_creacion = fecha_creacion;
		this.fecha_entrega = fecha_entrega;
		this.titulo = titulo;
		this.comentario = comentario;
		this.calificacion = calificacion;
		this.n_docs_adjuntos = n_docs_adjuntos;
		this.nombre_docs_adjuntos = new ArrayList<>();
		for (String doc_nombre : nombre_docs_adjuntos) {
			this.nombre_docs_adjuntos.add(doc_nombre);
		}
	}

	public Tarea() {
		super();
		this.calificacion = 0;
		this.n_docs_adjuntos = 0;
		this.nombre_docs_adjuntos = new ArrayList<>();
	}

	public Tarea(Tarea t) {
		super();
		this.fecha_creacion = t.fecha_creacion;
		this.fecha_entrega = t.fecha_entrega;
		this.titulo = t.titulo;
		this.comentario = t.comentario;
		this.calificacion = t.calificacion;
		this.n_docs_adjuntos = t.n_docs_adjuntos;
		this.nombre_docs_adjuntos = new ArrayList<>();
		for (String doc_nombre : t.nombre_docs_adjuntos) {
			this.nombre_docs_adjuntos.add(doc_nombre);
		}
	}

	public int getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(int calificacion) {
		this.calificacion = calificacion;
	}

	public String getFecha_creacion() {
		return fecha_creacion;
	}

	public void setFecha_creacion(String fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}

	public String getFecha_entrega() {
		return fecha_entrega;
	}

	public void setFecha_entrega(String fecha_entrega) {
		this.fecha_entrega = fecha_entrega;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public int getN_docs_adjuntos() {
		return n_docs_adjuntos;
	}

	public void setN_docs_adjuntos(int n_docs_adjuntos) {
		this.n_docs_adjuntos = n_docs_adjuntos;
	}

	public ArrayList<String> getNombre_docs_adjuntos() {
		return nombre_docs_adjuntos;
	}

	public void setNombre_docs_adjuntos(ArrayList<String> nombre_docs_adjuntos) {
		this.nombre_docs_adjuntos = nombre_docs_adjuntos;
	}

	@Override
	public String toString() {
		return "Tarea [" + fecha_creacion + ", " + fecha_entrega + ", " + titulo
				+ ", " + comentario + ", " + calificacion + ", " + n_docs_adjuntos + ", "
				+ nombre_docs_adjuntos + "]";
	}

}
