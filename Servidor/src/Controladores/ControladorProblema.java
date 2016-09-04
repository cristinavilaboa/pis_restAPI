package Controladores;

import java.util.Date;

import Manejadores.ManejadorProblema;
import Modelo.Problema;

public class ControladorProblema implements IControladorProblema{

	//METODOS A IMPLEMENTAR
	public int responderPregunta(int id_pregunta, String respuesta){return 0;}
	
	public void enviarMensaje(int id_pregunta, String mensaje, Date fecha, String asunto){ 
		ManejadorProblema mp=ManejadorProblema.getInstancia();
		Problema p=mp.buscarProblema(id_pregunta);
		p.enviarMensaje(mensaje, fecha, asunto);
	}
	
	public String getAyuda(int id_pregunta){
		ManejadorProblema manejador = ManejadorProblema.getInstancia();
		return manejador.getAyuda(id_pregunta);
	}

}// 
