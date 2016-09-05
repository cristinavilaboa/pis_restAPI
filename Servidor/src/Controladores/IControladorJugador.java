package Controladores;

import java.util.SortedSet;

import Datatypes.DataPuntosJugador;

public interface IControladorJugador {
	
	public void sumarPuntos(int exp, int id_jugador, int id_pregunta);
	public SortedSet<DataPuntosJugador> obtenerRanking();
	
}
