package Modelo;

public class Problema {
	private int id;
	private String descripcion;
	private String respuesta;
	private int puntos_exp;
	private Ayuda ayuda;
	private Contenido contenido;
	private Nivel nivel;
	private Profesor autor; 
	
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
	public boolean verificarRespuesta(String respuesta){return false;}
	
	public boolean enviarMensaje(String mensaje){
		boolean ok=autor.enviarMensaje(mensaje);
		return ok;
	}
	
}
