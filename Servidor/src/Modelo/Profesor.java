package Modelo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import Persistencia.HibernateUtility;
@Entity
@Table(name = "PROFESOR")
@PrimaryKeyJoinColumn(name="nick")
public class Profesor extends Usuario {
	
	
	//private static int num_mensaje = 0;

	private String password;
	public Profesor(){
		super();
	};
	public Profesor(String nombre, String nick, String password){
		super (nombre,nick);
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	//METODOS A IMPLEMENTAR
	public void enviarMensaje(String mensaje,Date fecha, String asunto,String id_jugador){
		Mensaje m=new Mensaje(/*num_mensaje,*/mensaje,asunto, fecha,id_jugador);
		this.getMensajes_nuevos().add(m);
		//mensajes_nuevos.add(m);
		
	}
}
