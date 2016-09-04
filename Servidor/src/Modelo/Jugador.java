package Modelo;

import java.util.List;

public class Jugador extends Usuario{

	private String FBToken;
	private String imagen;
	private EstadoJugador estado;
	private Clase clase;
	
	public Jugador(String nombre, String nick, String fBToken, String imagen, EstadoJugador estado, Clase clase) {
		super(nombre, nick);
		FBToken = fBToken;
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
	
}
