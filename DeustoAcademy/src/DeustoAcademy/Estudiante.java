package DeustoAcademy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Estudiante implements Serializable, Comparable<Estudiante> {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;
	protected String nombre;
	protected String apellido;
	protected int telefono;
	protected String correo;
	protected String dni;
	protected String usuario;
	protected String contraseña;
	protected ArrayList<Idioma> idiomas;

	public Estudiante(String nombre, String apellido, int telefono, String correo, String dni, String usuario,
			String contraseña, ArrayList<Idioma> idiomas) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.correo = correo;
		this.dni = dni;
		this.usuario = usuario;
		this.contraseña = contraseña;
		this.idiomas = new ArrayList<Idioma>();
		for (Idioma idioma : idiomas) {
			this.idiomas.add(idioma);
		}
	}

	public Estudiante() {
		super();
		this.idiomas = new ArrayList<Idioma>();
	}
	
	public ArrayList<Idioma> getIdiomas() {
		return idiomas;
	}

	public void setIdiomas(ArrayList<Idioma> idiomas) {
		this.idiomas = idiomas;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	@Override
	public String toString() {
		return "Estudiante [nombre=" + nombre + ", apellido=" + apellido + ", telefono=" + telefono + ", correo="
				+ correo + ", dni=" + dni + ", usuario=" + usuario + ", contraseña=" + contraseña + ", idiomas="
				+ idiomas + "]";
	}

	@Override
	public int compareTo(Estudiante o) {
		
		return this.usuario.compareTo(o.usuario) & this.contraseña.compareTo(o.contraseña);
		
	}

}