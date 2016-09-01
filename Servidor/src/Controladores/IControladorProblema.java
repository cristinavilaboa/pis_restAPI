package Controladores;

public interface IControladorProblema {
	
	public int responderPregunta(int id_pregunta, String respuesta);
	public boolean enviarMensaje(int id_pregunta, String mensaje);
	public String getAyuda(int id_pregunta);
}
