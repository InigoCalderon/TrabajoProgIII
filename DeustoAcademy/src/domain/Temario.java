package domain;

import java.util.ArrayList;
import java.util.HashMap;

public class Temario {

	protected Grupo grupo;
	protected HashMap<String, ArrayList<String>> data;
	
	public Temario(Grupo grupo, HashMap<String, ArrayList<String>> data) {
		super();
		this.grupo = grupo;
		this.data = data;
	}
	
	public Temario() {
		super();
		this.grupo = null;
		this.data = null;
	}
	
	public Temario(Temario t) {
		super();
		this.grupo = t.grupo;
		this.data = t.data;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public HashMap<String, ArrayList<String>> getData() {
		return data;
	}

	public void setData(HashMap<String, ArrayList<String>> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Temario [grupo=" + grupo + ", data=" + data + "]";
	}
	
}
