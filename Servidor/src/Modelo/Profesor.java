package Modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import Persistencia.HibernateUtility;
@Entity
@Table(name = "PROFESOR")
@PrimaryKeyJoinColumn(name="nick")
public class Profesor extends Usuario {
	
	
	//private static int num_mensaje = 0;

	private String password;
	
	@OneToMany(cascade=CascadeType.ALL) 
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name="profesor_mensajes_reportes", joinColumns={@JoinColumn (name="id_usuario", referencedColumnName= "nick" )},inverseJoinColumns={@JoinColumn(name="id_mensaje",referencedColumnName="id_mensaje")})
	private List<Mensaje> mensajes_reportes = new ArrayList<Mensaje>();
	
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
	
	public List<Mensaje> getMensajes_reportes() {
		return mensajes_reportes;
	}
	public void setMensajes_reportes(List<Mensaje> mensajes_reportes) {
		this.mensajes_reportes = mensajes_reportes;
	}
	//METODOS A IMPLEMENTAR
	public void enviarMensaje(String mensaje,Date fecha, String asunto,String id_jugador){
		Mensaje m=new Mensaje(/*num_mensaje,*/mensaje,asunto, fecha,id_jugador);
		this.getMensajes_nuevos().add(m);
		//mensajes_nuevos.add(m);
		
	}
	
	public void agregarReporte(Mensaje reporte){
		this.mensajes_reportes.add(reporte);
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
