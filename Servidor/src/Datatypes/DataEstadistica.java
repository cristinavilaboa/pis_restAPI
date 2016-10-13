package Datatypes;

public class DataEstadistica {
	private int cant_intentos;
	private int cant_aciertos;
	
	public DataEstadistica(int cant_intentos, int cant_aciertos) {
		this.cant_intentos = cant_intentos;
		this.cant_aciertos = cant_aciertos;
	}
	
	public DataEstadistica() {}

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
