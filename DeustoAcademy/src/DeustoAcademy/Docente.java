package DeustoAcademy;

import java.io.Serializable;

public class Docente implements Serializable {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;
	protected String nombre;
	protected String apellido;
	protected String dni;
	protected String correo;
	protected int telefono;
	protected String usuario;
	protected String contraseña;
	protected Idioma idioma;

	
	public Docente(String nombre, String apellido, String dni, String correo, int telefono, String usuario,
			String contraseña, Idioma idioma) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.correo = correo;
		this.telefono = telefono;
		this.usuario = usuario;
		this.contraseña = contraseña;
		this.idioma = idioma;
	}
	
	public Docente() {
		super();
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

	public Idioma getIdioma() {
		return idioma;
	}

	public void setIdioma(Idioma idioma) {
		this.idioma = idioma;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
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
		return "Docente [nombre=" + nombre + ", apellido=" + apellido + ", dni=" + dni + ", correo=" + correo
				+ ", telefono=" + telefono + ", usuario=" + usuario + ", contraseña=" + contraseña + ", idioma="
				+ idioma + "]";
	}

}
