package Modelo;

import java.util.List;

public abstract class Usuario {
	
	private String nombre;
	private String nick;
	private List<Mensaje> mensajes_viejos;
	private List<Mensaje> mensajes_nuevos;
	
	public Usuario(String nombre, String nick, List<Mensaje> mensajes_viejos, List<Mensaje> mensajes_nuevos) {
		super();
		this.nombre = nombre;
		this.nick = nick;
		this.mensajes_viejos = mensajes_viejos;
		this.mensajes_nuevos = mensajes_nuevos;
	}

	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getNick() {
		return nick;
	}
	
	public void setNick(String nick) {
		this.nick = nick;
	}

	public List<Mensaje> getMensajes_viejos() {
		return mensajes_viejos;
	}

	public void setMensajes_viejos(List<Mensaje> mensajes_viejos) {
		this.mensajes_viejos = mensajes_viejos;
	}

	public List<Mensaje> getMensajes_nuevos() {
		return mensajes_nuevos;
	}

	public void setMensajes_nuevos(List<Mensaje> mensajes_nuevos) {
		this.mensajes_nuevos = mensajes_nuevos;
	}
	
}
