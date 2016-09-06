package Manejadores;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import DataTypes.DataJugador;
import Modelo.Jugador;

import java.util.ArrayList;
import java.util.List;
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
	
	public void agregarJugador(Jugador jugador){
		jugadores.add(jugador);
	}
	
	public boolean existeJugador(String id_jugador){
		for(Jugador j: jugadores){
			if(j.getNick().equalsIgnoreCase(id_jugador)){
				return true;
			}
		}
		return false;
	}
	
	public Jugador buscarJugador(String id_jugador){
		for(Jugador j: jugadores){
			if(j.getNick().equalsIgnoreCase(id_jugador)){
				return j;
			}
		}
		return null;//SE SUPONE QUE EL JUGADOR YA EXISTE
	}

}
