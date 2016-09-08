package Modelo;

import java.util.ArrayList;
import java.util.List;

public abstract class Usuario {  //mejor ponerle protected en los atributos para herencia
	
	protected String nombre;
	protected String nick;
	protected List<Mensaje> mensajes_viejos;
	protected List<Mensaje> mensajes_nuevos;
	
	public Usuario(String nick, String nombre) {
		
		this.nombre = nombre;
		this.nick = nick;
		mensajes_viejos= new ArrayList<Mensaje>();
		mensajes_nuevos= new ArrayList<Mensaje>();
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
