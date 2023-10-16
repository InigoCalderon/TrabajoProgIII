package DeustoAcademy;

public class DatosFalsosParaProbarSiFuncionaAñadirLoQueNecesiteisPeroNoBorreisLoQueHalla {
	
	protected String direccion;

	public DatosFalsosParaProbarSiFuncionaAñadirLoQueNecesiteisPeroNoBorreisLoQueHalla(String direccion) {
		super();
		this.direccion = direccion;
	}
	
	public DatosFalsosParaProbarSiFuncionaAñadirLoQueNecesiteisPeroNoBorreisLoQueHalla() {
		super();
		this.direccion = "";
	}
	
	public DatosFalsosParaProbarSiFuncionaAñadirLoQueNecesiteisPeroNoBorreisLoQueHalla(DatosFalsosParaProbarSiFuncionaAñadirLoQueNecesiteisPeroNoBorreisLoQueHalla x ) {
		super();
		this.direccion = x.direccion;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	@Override
	public String toString() {
		return "DatosFalsosParaProbarSiFuncionaAñadirLoQueNecesiteisPeroNoBorreisLoQueHalla [direccion=" + direccion
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
	
	
}
