package Controladores;

import Datatypes.DataListaRanking;

public interface IControladorJugador {
	
	public void sumarPuntos(int exp, String id_jugador, int id_problema);
	public boolean yaRespondida(String id_jugador,int id_problema);
	public DataListaRanking obtenerRanking();
	
}
