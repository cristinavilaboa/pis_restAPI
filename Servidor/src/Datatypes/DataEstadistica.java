package Datatypes;

public class DataEstadistica {
	private int id_mundo;
	private int nro_nivel;
	private int id_problema;
	private int cant_intentos;
	private int cant_aciertos;
	
	
	
	public DataEstadistica(int id_mundo, int nro_nivel, int id_problema, int cant_intentos, int cant_aciertos) {
		this.id_mundo = id_mundo;
		this.nro_nivel = nro_nivel;
		this.id_problema = id_problema;
		this.cant_intentos = cant_intentos;
		this.cant_aciertos = cant_aciertos;
	}
	
	
	public DataEstadistica() {}


	public int getId_mundo() {
		return id_mundo;
	}
	public void setId_mundo(int id_mundo) {
		this.id_mundo = id_mundo;
	}
	public int getNro_nivel() {
		return nro_nivel;
	}
	public void setNro_nivel(int nro_nivel) {
		this.nro_nivel = nro_nivel;
	}
	public int getId_problema() {
		return id_problema;
	}
	public void setId_problema(int id_problema) {
		this.id_problema = id_problema;
	}
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
	

}
