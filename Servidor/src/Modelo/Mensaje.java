package Modelo;

import java.util.Date;

public class Mensaje {

	private int id;             //mejor id autogenerado
	private String contenido;
	private String asunto;
	private Date fecha;
	//private Usuario destinatario;
	//private Usuario remitente;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getContenido() {
		return contenido;
	}
	
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	
	public String getAsunto() {
		return asunto;
	}
	
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
	
	public Date getFecha() {
		return fecha;
	}
	
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/*public Usuario getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(Usuario destinatario) {
		this.destinatario = destinatario;
	}

	public Usuario getRemitente() {
		return remitente;
	}

	public void setRemitente(Usuario remitente) {
		this.remitente = remitente;
	}*/
	
}
