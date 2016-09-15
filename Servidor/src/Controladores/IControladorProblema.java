package Controladores;

import java.util.Date;

import Datatypes.DataAyuda;
import Datatypes.DataEstadoMensaje;
import Datatypes.DataExperiencia;
import Datatypes.DataProblema;

public interface IControladorProblema {
	
	public DataExperiencia responderPregunta(int id_pregunta, String respuesta, String id_jugador);
	public DataEstadoMensaje enviarMensaje(int id_problema, String mensaje, String fecha, String asunto);
	public DataAyuda getAyuda(int id_pregunta);
}
