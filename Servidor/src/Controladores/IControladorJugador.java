package Controladores;

import Datatypes.DataListaRanking;

public interface IControladorJugador {
	
	public void sumarPuntos(int exp, String id_jugador, int id_pregunta);
	public boolean yaRespondia(String id_jugador,int id_pregunta);
	public DataListaRanking obtenerRanking();
	
}
