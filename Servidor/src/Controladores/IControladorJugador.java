package Controladores;

import java.util.List;

import Datatypes.DataListaRanking;
import Datatypes.DataPuntosJugador;

public interface IControladorJugador {
	
	public void sumarPuntos(int exp, String id_jugador, int id_pregunta);
	public boolean yaRespondia(String id_jugador,int id_pregunta);
	public DataListaRanking obtenerRanking();
	
}
