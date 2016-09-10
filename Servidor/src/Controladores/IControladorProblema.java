package Controladores;

import java.util.Date;

import Datatypes.DataProblema;

public interface IControladorProblema {
	
	public int responderPregunta(int id_pregunta, String respuesta, String id_jugador);
	public void enviarMensaje(int id_problema, String mensaje, Date fecha, String asunto);
	public String getAyuda(int id_pregunta);
}
