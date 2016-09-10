package calc4fun.cliente.DataTypes;



public class DataProblema {
	
	private Integer id;
	private String descripcion;


	public DataProblema() {}
	public DataProblema(Integer id, String descripcion) {
		this.id = id;
		this.descripcion = descripcion;
	}
	public Integer getId() {
		return id;
	}
	public String getDescripcion() {
		return descripcion;
	}


	
}
