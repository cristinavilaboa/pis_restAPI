package Modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.InheritanceType;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
@Entity
@Table(name = "USUARIO")
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Usuario {  
	
	@Id
	@Column(name="nick")
	private String nick;
	private String nombre;
	@OneToMany(cascade=CascadeType.ALL)  
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name="usuario_mensajes_viejos", joinColumns={@JoinColumn (name="id_usuario", referencedColumnName= "nick" )},inverseJoinColumns={@JoinColumn(name="id_mensaje",referencedColumnName="id_mensaje")})
	private List<Mensaje> mensajes_viejos;
	@OneToMany(cascade=CascadeType.ALL) 
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name="usuario_mensajes_nuevos", joinColumns={@JoinColumn (name="id_usuario", referencedColumnName= "nick" )},inverseJoinColumns={@JoinColumn(name="id_mensaje",referencedColumnName="id_mensaje")})
	private List<Mensaje> mensajes_nuevos;
	
	public Usuario(String nombre, String nick) {
		
		this.nombre = nombre;
		this.nick = nick;
		mensajes_viejos= new ArrayList<Mensaje>();
		mensajes_nuevos= new ArrayList<Mensaje>();
	}
	public Usuario(){};

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
	
	public void agregar_mensaje_nuevo(Mensaje mensaje) {
		this.mensajes_nuevos.add(mensaje);
	}
	
	public boolean esMensajeNuevo(int id_mensaje){
		for(Mensaje m: mensajes_nuevos){
			if(m.getId() == id_mensaje){
				return true;
			}
		}
		return false;
	}
	
	public void mensajeLeido(int id_mensaje){ // Se supone que antes se uso la operacion esMensajeNuevo
		for(Mensaje m: mensajes_nuevos){
			if(m.getId() == id_mensaje){
				mensajes_viejos.add(m);
				mensajes_nuevos.remove(m);
				break;
			}
		}
	}
}
