package Controladores;

import java.util.List;

import Datatypes.DataListaRanking;
import Datatypes.DataPuntosJugador;

public interface IControladorJugador {
	
	public void sumarPuntos(int exp, String id_jugador, int id_problema);
	public boolean yaRespondida(String id_jugador,int id_problema);
	public DataListaRanking obtenerRanking();
	
}
