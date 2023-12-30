package DeustoAcademy;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.Temporal;
import java.util.ArrayList;

public class Tarea implements Serializable {

	private static final long serialVersionUID = 1L;
	
	protected LocalDate fecha_creacion = LocalDate.now();
	protected LocalDate fecha_entrega; // 17/02/22
	protected String titulo;
	protected String comentario;

	public Tarea(LocalDate fecha_entrega, String titulo, String comentario) {
		super();
		this.fecha_entrega = fecha_entrega;
		this.titulo = titulo;
		this.comentario = comentario;
	}

	public Tarea() {
		super();
	}

	public Tarea(Tarea t) {
		super();
		this.fecha_creacion = t.fecha_creacion;
		this.fecha_entrega = t.fecha_entrega;
		this.titulo = t.titulo;
		this.comentario = t.comentario;
	}

	public LocalDate getFecha_creacion() {
		return fecha_creacion;
	}

	public void setFecha_creacion(LocalDate fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}

	public LocalDate getFecha_entrega() {
		return fecha_entrega;
	}

	public void setFecha_entrega(LocalDate fecha_entrega) {
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

	@Override
	public String toString() {
		return "Tarea [" + fecha_creacion + ", " + fecha_entrega + ", " + titulo
				+ ", " + comentario + "]";
	}

}
