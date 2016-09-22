package Controladores;

import Datatypes.DataAyuda;
import Datatypes.DataEstadoMensaje;
import Datatypes.DataExperiencia;

public interface IControladorProblema {
	
	public DataExperiencia responderProblema(int id_problema, String respuesta, String id_jugador);
	public DataEstadoMensaje enviarMensaje(int id_problema, String mensaje, String fecha, String asunto);
	public DataAyuda getAyuda(int id_problema);
}
