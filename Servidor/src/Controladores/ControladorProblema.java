package Controladores;

import Manejadores.ManejadorProblema;
import Modelo.Problema;

public class ControladorProblema implements IControladorProblema{

	//METODOS A IMPLEMENTAR
	public int responderPregunta(int id_pregunta, String respuesta){return 0;}
	
	public boolean enviarMensaje(int id_pregunta, String mensaje){ 
		//la fecha y asunto del mensaje tendrían q venir como parametro desde el cel? 
		// el parámetro de retorno ok me parece que no va 
		ManejadorProblema mp=ManejadorProblema.getInstancia();
		Problema p=mp.buscarProblema(id_pregunta);
		boolean ok= p.enviarMensaje(mensaje);
		return ok;
	}
	
	public String getAyuda(int id_pregunta){return null;}

}// 
