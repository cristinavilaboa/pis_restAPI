package Modelo;

import java.util.Date;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import Datatypes.DataProblema;
@Entity
@Table(name = "PROBLEMA")
public class Problema {
	@Id
	private Integer id;
	private String descripcion;
	private String respuesta;
	private int puntos_exp;
	@OneToOne
	private Ayuda ayuda;
	@OneToOne
	private Contenido contenido;
	@ManyToOne
	private Nivel nivel;
	@ManyToOne
	private Profesor autor; 
	
	
	
	public Problema(Integer id, String descripcion, String respuesta, int puntos_exp, Ayuda ayuda, Contenido contenido,
			Nivel nivel, Profesor autor) {
		this.id = id;
		this.descripcion = descripcion;
		this.respuesta = respuesta;
		this.puntos_exp = puntos_exp;
		this.ayuda = ayuda;
		this.contenido = contenido;
		this.nivel = nivel;
		this.autor = autor;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getRespuesta() {
		return respuesta;
	}
	
	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}
	
	public int getPuntos_exp() {
		return puntos_exp;
	}
	
	public void setPuntos_exp(int puntos_exp) {
		this.puntos_exp = puntos_exp;
	}

	public Ayuda getAyuda() {
		return ayuda;
	}

	public void setAyuda(Ayuda ayuda) {
		this.ayuda = ayuda;
	}

	public Contenido getContenido() {
		return contenido;
	}

	public void setContenido(Contenido contenido) {
		this.contenido = contenido;
	}

	public Nivel getNivel() {
		return nivel;
	}

	public void setNivel(Nivel nivel) {
		this.nivel = nivel;
	}

	public Profesor getAutor() {
		return autor;
	}

	public void setAutor(Profesor autor) {
		this.autor = autor;
	}
	
	//METODOS A IMPLEMENTAR
	public boolean verificarRespuesta(String respuesta){
		return respuesta.equalsIgnoreCase(this.respuesta);
	}
	
	public void enviarMensaje(String mensaje,Date fecha, String asunto){
		autor.enviarMensaje(mensaje, fecha, asunto);
	}
	
	public DataProblema getDataProblema(){
		
		
		return new DataProblema(this.id,this.descripcion,this.contenido.getTexto());
		
	}
	
}
