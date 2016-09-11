package Modelo;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "AYUDA")
public class Ayuda {
	@Id  @GeneratedValue(strategy=GenerationType.AUTO)
	private int id_ayuda;
	private String info;
	
	public Ayuda(String info){
		this.info = info;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
}
