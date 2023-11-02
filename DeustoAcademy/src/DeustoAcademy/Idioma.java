package DeustoAcademy;

public class Idioma {
	protected String idioma;

	public Idioma(String idioma) {
		super();
		this.idioma = idioma;
	}
	
	
	public Idioma() {
		super();
		this.idioma = "Sin asignar";
	}


	public String getIdioma() {
		return idioma;
	}


	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}
	
}
