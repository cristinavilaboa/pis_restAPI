package Datatypes;

import java.util.ArrayList;

public class DataListaMensajes {
	private ArrayList<DataMensaje> mensajes_nuevos = new ArrayList<DataMensaje>();
	private ArrayList<DataMensaje> mensajes_viejos = new ArrayList<DataMensaje>();
	
	public DataListaMensajes(ArrayList<DataMensaje> mensajes_nuevos, ArrayList<DataMensaje> mensajes_viejos) {
		this.mensajes_nuevos = mensajes_nuevos;
		this.mensajes_viejos = mensajes_viejos;
	}

	public ArrayList<DataMensaje> getMensajes_nuevos() {
		return mensajes_nuevos;
	}

	public ArrayList<DataMensaje> getMensajes_viejos() {
		return mensajes_viejos;
	}
		
}
