package Modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import Datatypes.DataEstadistica;

@Entity
@Table(name = "ESTADISTICA")
	
public class Estadistica {
	@Id  @GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private int cant_intentos;
	private int cant_aciertos;
	
	public Estadistica(int cant_intentos, int cant_aciertos) {
		this.cant_intentos = cant_intentos;
		this.cant_aciertos = cant_aciertos;
	}
	
	public Estadistica() {}

	public int getCant_intentos() {
		return cant_intentos;
	}

	public void setCant_intentos(int cant_intentos) {
		this.cant_intentos = cant_intentos;
	}

	public int getCant_aciertos() {
		return cant_aciertos;
	}

	public void setCant_aciertos(int cant_aciertos) {
		this.cant_aciertos = cant_aciertos;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void aumentarIntentos(){
		cant_intentos++;
	}
	
	public void aumentarAciertos(){
		cant_aciertos++;
	}
	
	public DataEstadistica obtenerDataEst(){
		return new DataEstadistica(this.cant_intentos, this.cant_aciertos);
	}
	

}
