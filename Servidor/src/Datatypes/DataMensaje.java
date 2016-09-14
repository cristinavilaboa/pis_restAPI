package Datatypes;

import java.util.Date;

public class DataMensaje {
	private int id;
	private String asunto;
	private String contenido;
	private Date fecha;
	
	public DataMensaje(int id, String asunto, String contenido, Date fecha) {
		super();
		this.id = id;
		this.asunto = asunto;
		this.contenido = contenido;
		this.fecha = fecha;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAsunto() {
		return asunto;
	}
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
	public String getContenido() {
		return contenido;
	}
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	

}
