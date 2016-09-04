package Modelo;


import java.util.List;
import java.util.Date;

public class Profesor extends Usuario {
	
	

	public Profesor(String nombre, String nick) {
		super(nombre, nick);
		// TODO Auto-generated constructor stub
	}

	private String password;
	
	

	public Profesor(String nombre, String nick, String password) {
		super(nombre, nick);
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	//METODOS A IMPLEMENTAR
	public void enviarMensaje(String mensaje,Date fecha, String asunto){
		Mensaje m=new Mensaje(mensaje,asunto, fecha);
		mensajes_nuevos.add(m);
	}
}
