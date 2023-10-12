package DeustoAcademy;

public class Estudiante {
	protected String nombre;
	protected String apellido;
	protected int edad;
	protected int teléfono;
	protected String correo;
	protected String dni;
	
	protected String usuario;
	protected String contraseña;
	public Estudiante(String nombre, String apellido, int edad, int teléfono, String correo, String dni, String usuario,
			String contraseña) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.edad = edad;
		this.teléfono = teléfono;
		this.correo = correo;
		this.dni = dni;
		this.usuario = usuario;
		this.contraseña = contraseña;
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
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public int getTeléfono() {
		return teléfono;
	}
	public void setTeléfono(int teléfono) {
		this.teléfono = teléfono;
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
	
	
	
	
	
}
