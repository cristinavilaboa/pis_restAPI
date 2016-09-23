package Modelo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
@Entity
@Table(name = "PROFESOR")
@PrimaryKeyJoinColumn(name="nick")
public class Profesor extends Usuario {
	
	
	//private static int num_mensaje = 0;

	private String password;
	public Profesor(){
		super();
	};
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
		Mensaje m=new Mensaje(/*num_mensaje,*/mensaje,asunto, fecha,this.nick);
		mensajes_nuevos.add(m);
		//num_mensaje++;
	}
}
