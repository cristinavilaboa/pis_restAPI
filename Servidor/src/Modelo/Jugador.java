package Modelo;

import Datatypes.DataPuntosJugador;
import Modelo.EstadoJugador;

public class Jugador extends Usuario{

	private String FBToken;
	private String imagen;
	private EstadoJugador estado;
	private Clase clase;
	
    public Jugador(String nick, String nombre, String FBToken, String imagen, EstadoJugador estado, Clase clase){
        super(nick, nombre);
        this.FBToken = FBToken;
        this.imagen = imagen;
        this.estado = estado;
        this.clase = clase;
    }
	
	public String getFBToken() {
		return FBToken;
	}
	
	public void setFBToken(String fBToken) {
		FBToken = fBToken;
	}
	
	public String getImagen() {
		return imagen;
	}
	
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public EstadoJugador getEstado() {
		return estado;
	}

	public void setEstado(EstadoJugador estado) {
		this.estado = estado;
	}

	public Clase getClase() {
		return clase;
	}

	public void setClase(Clase clase) {
		this.clase = clase;
	}
	
	public DataPuntosJugador obtenerDataPuntosJugador(String nombre){
		int puntos = estado.getPuntos_exp();
		DataPuntosJugador dpj = new DataPuntosJugador(nombre, puntos);
		return dpj;
	}
	
}
