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

	public Idioma(Idioma i) {
		super();
		this.idioma = i.idioma;
	}

	public String getIdioma() {
		return idioma;
	}


	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}


	@Override
	public String toString() {
		return "Idioma [idioma=" + idioma + ", getIdioma()=" + getIdioma() + "]";
	}
	
}
