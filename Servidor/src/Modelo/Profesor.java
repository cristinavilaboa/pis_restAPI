package Modelo;

public class Profesor extends Usuario {
	
	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	//METODOS A IMPLEMENTAR
	public boolean enviarMensaje(String mensaje){return false;}
}
