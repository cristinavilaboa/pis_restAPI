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

	private String password;
	
	//----CONSTRUCTORES----//
	public Profesor(){
		super();
	};
	public Profesor(String nombre, String nick, String password){
		super (nombre,nick);
		this.password = password;
	}

	//----GETTERS----//
	public String getPassword() {
		return password;
	}
	
	//----SETTERS----//
	public void setPassword(String password) {
		this.password = password;
	}
	
	//----OPERACIONES----//
	public void enviarMensaje(String mensaje,Date fecha, String asunto,String id_jugador){
		Mensaje m=new Mensaje(mensaje,asunto, fecha,id_jugador);
		this.getMensajes_nuevos().add(m);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Profesor other = (Profesor) obj;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}
}
