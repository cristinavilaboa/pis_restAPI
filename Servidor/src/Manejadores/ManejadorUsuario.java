package Manejadores;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import DataTypes.DataJugador;
import Modelo.Jugador;

import java.util.ArrayList;
import java.util.List;

import java.util.Map;

import Modelo.Jugador;

public class ManejadorUsuario {
	
	private static ManejadorUsuario instancia = new ManejadorUsuario();

	private Map<String, Jugador> jugadores = new HashMap<String, Jugador>();
	
	private ManejadorUsuario(){};
	
	public static ManejadorUsuario getInstancia(){
		return instancia;
	}

	public DataJugador obtenerDatosJugador(String id_jugador)
	{
		Jugador j = jugadores.get(id_jugador);
		DataJugador dj = j.obtenerDataJugador();
		return dj;
	}
	public void agregarJugador(Jugador jugador){
		jugadores.put(jugador.getNick(), jugador);

	}
	

	public boolean existeJugador(String nick){
		Jugador j = jugadores.get(nick);
		if (j!=null){
			return true;	
		}
		return false;
	}
	
	public Jugador buscarJugador(String nick){
		return jugadores.get(nick); //ver de tirar excepcion si es null
	}
	
}
