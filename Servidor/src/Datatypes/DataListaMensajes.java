package Datatypes;

import java.util.ArrayList;

import antlr.collections.List;

public class DataListaMensajes {
	private ArrayList<DataMensaje> mensajes = new ArrayList<DataMensaje>();

	public DataListaMensajes(ArrayList<DataMensaje> mensajes) {
		this.mensajes = mensajes;
	}

	public ArrayList<DataMensaje> getMensajes() {
		return mensajes;
	}

	public void setMensajes(ArrayList<DataMensaje> mensajes) {
		this.mensajes = mensajes;
	}
	
	

	
}
