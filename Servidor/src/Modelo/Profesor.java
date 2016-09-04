package Modelo;

import java.util.List;

public class Profesor extends Usuario {
	
	public Profesor(String nombre, String nick, List<Mensaje> mensajes_viejos, List<Mensaje> mensajes_nuevos) {
		super(nombre, nick, mensajes_viejos, mensajes_nuevos);
		// TODO Auto-generated constructor stub
	}

	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	//METODOS A IMPLEMENTAR
	public boolean enviarMensaje(String mensaje){return false;}
}
