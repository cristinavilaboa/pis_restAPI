package Modelo;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "CONTENIDO")
public class Contenido {
	@Id  @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String texto;

	public Contenido(String texto){
		this.texto = texto;
	}
	
	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}
}
