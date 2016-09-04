package Controladores;

import Manejadores.ManejadorProblema;

public class ControladorProblema implements IControladorProblema{

	//METODOS A IMPLEMENTAR
	public int responderPregunta(int id_pregunta, String respuesta, String id_jugador){//JUAN
		ManejadorProblema mp = ManejadorProblema.getInstancia();
		IControladorJugador cu = new ControladorJugador();
		IControladorSistemaJuego csj = new ControladorSistemaJuego();
		int exp_ganada = mp.verificarRespuesta(id_pregunta, respuesta);
		if(exp_ganada > 0 && !cu.yaRespondia(id_jugador, id_pregunta)){
			cu.sumarPuntos(exp_ganada, id_jugador, id_pregunta);
			int id_mundo = mp.buscarProblema(id_pregunta).getNivel().getMundo().getId();
			csj.avanzarJuego(id_jugador, id_pregunta, id_mundo);
		}
		return exp_ganada;
	}
	
	
	
	
	
	public boolean enviarMensaje(int id_pregunta, String mensaje){return false;}
	public String getAyuda(int id_pregunta){return null;}

}
