package Controladores;

import java.util.SortedSet;

import Modelo.DataPuntosJugador;

public interface IControladorJugador {
	
	public void sumarPuntos(int exp, int id_jugador, int id_pregunta);
	public SortedSet<DataPuntosJugador> obtenerRanking();
	
}
