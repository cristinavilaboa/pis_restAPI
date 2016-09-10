package Controladores;

import Datatypes.DataProblema;

public interface IControladorSistemaJuego {
	
	public void avanzarJuego(String id_jugador, int id_pregunta, int id_mundo);

	public DataProblema siguienteProblema(String id_jugador,int id_problema);

	public void iniciarJuego();


}
