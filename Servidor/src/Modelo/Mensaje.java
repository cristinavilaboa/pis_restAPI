package Modelo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "MENSAJE")
public class Mensaje {

	@Id  @GeneratedValue(strategy=GenerationType.AUTO)
	private int id_mensaje;
	private String contenido;
	private String asunto;
	private Date fecha;

	//private Usuario destinatario;
	private String id_remitente;
	
	
	
	public int getId() {
		return id_mensaje;
	}
	
	public Mensaje(){}
	
	public Mensaje(String contenido, String asunto, Date fecha, String id_remitente) {
		this.contenido = contenido;
		this.asunto = asunto;
		this.fecha = fecha;
		this.id_remitente = id_remitente;
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
	
	public String getRemitente(){
		return id_remitente;
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
