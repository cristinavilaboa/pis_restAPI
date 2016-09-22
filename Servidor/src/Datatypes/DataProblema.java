package Datatypes;


public class DataProblema {
	
	private Integer id;
	private String descripcion;
	private String contenido;
		
	public DataProblema(Integer id, String descripcion,String contenido) {
		this.id = id;
		this.descripcion = descripcion;
		this.contenido = contenido;
	}
	public Integer getId() {
		return id;
	}
	public String getDescripcion() {
		return descripcion;
	}

	public String getContenido() {
		return contenido;
	}

	
}
