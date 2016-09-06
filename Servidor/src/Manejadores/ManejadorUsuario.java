package Manejadores;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import DataTypes.DataJugador;
import Modelo.Jugador;

public class ManejadorUsuario {
	
	private static ManejadorUsuario instancia = new ManejadorUsuario();
	private Map<Integer,Jugador> jugadores = new HashMap <Integer,Jugador> (); 
	
	private ManejadorUsuario(){};
	
	public static ManejadorUsuario getInstancia(){
		return instancia;
	}
	
	public DataJugador obtenerDatosJugador(int id_jugador)
	{
		Jugador j = jugadores.get(id_jugador);
		DataJugador dj = j.obtenerDataJugador();
		return dj;
	}
}
