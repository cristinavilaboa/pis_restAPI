package Controladores;

import java.util.List;

import Datatypes.DataListaDataProblema;
import Datatypes.DataProblema;

public interface IControladorSistemaJuego {
	
	public void avanzarJuego(String id_jugador, int id_pregunta, int id_mundo);

	public DataProblema siguienteProblema(String nick,int nivel, int id_mundo);
	
	public DataListaDataProblema siguienteProblemaGeneral(String nick,int nivel, int id_mundo);

	public void iniciarJuego();


}
