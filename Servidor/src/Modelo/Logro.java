package Modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import Datatypes.DataLogro;
@Entity
@Table(name = "LOGRO")
public class Logro {
	 @Id  @GeneratedValue(strategy=GenerationType.AUTO)
	private int id_logro;
	private String descripcion;
	private int id; //habría q borrar este
	
	public Logro(String descripcion){
		this.descripcion=descripcion;
	}
	
	public Logro (){
		
	}
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public DataLogro obtenerDataLogro(){
		DataLogro dl = new DataLogro(descripcion, id);
		return dl;
	}
}
