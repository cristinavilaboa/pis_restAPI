package Modelo;

import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
@Entity
@Table(name = "PROFESOR")
@PrimaryKeyJoinColumn(name="nick")
public class Profesor extends Usuario {
	
	

	public Profesor(String nombre, String nick) {
		super(nombre, nick);
		// TODO Auto-generated constructor stub
	}

	private String password;
	
	public Profesor(String nick, String nombre, String password){
		super (nick, nombre);
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
