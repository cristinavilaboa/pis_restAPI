package Controladores;

import java.util.Date;

import Datatypes.DataAyuda;
import Datatypes.DataExperiencia;

public interface IControladorProblema {
	
	public DataExperiencia responderPregunta(int id_pregunta, String respuesta, String id_jugador);
	public void enviarMensaje(int id_problema, String mensaje, Date fecha, String asunto);
	public DataAyuda getAyuda(int id_pregunta);
}
