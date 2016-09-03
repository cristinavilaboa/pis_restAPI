package Controladores;

import java.util.Date;

public interface IControladorProblema {
	
	public int responderPregunta(int id_pregunta, String respuesta);
	public void enviarMensaje(int id_pregunta, String mensaje, Date fecha, String asunto);
	public String getAyuda(int id_pregunta);
}
