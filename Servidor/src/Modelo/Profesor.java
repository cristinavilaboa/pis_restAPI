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
	public boolean enviarMensaje(String mensaje){
		Mensaje m=new Mensaje();
		m.setContenido(mensaje);
		m.setId(m.getId()+1);       //me parece mejor que el id sea autogenerado 
		mensajes_nuevos.add(m);
		return false;
	}
}
