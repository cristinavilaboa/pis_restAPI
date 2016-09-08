package Manejadores;

import java.util.ArrayList;
import java.util.Collections;
import Datatypes.DataPuntosJugador;
import java.util.List;
import Modelo.Jugador;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import Datatypes.DataJugador;

public class ManejadorUsuario {
	
	private static ManejadorUsuario instancia = new ManejadorUsuario();

	private Map<String, Jugador> jugadores = new HashMap<String, Jugador>();
	
	//private List<Jugador> jugadores = new ArrayList<Jugador>();
	
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

	
	public List<DataPuntosJugador> obtenerRanking(){
		List<DataPuntosJugador> list_dpj = new ArrayList<>();
		for (Entry<String, Jugador> j : jugadores.entrySet()) {
			String nombreJ = j.getValue().getNombre();
			DataPuntosJugador dpj = j.getValue().obtenerDataPuntosJugador(nombreJ);
			list_dpj.add(dpj);
		}
		Collections.sort(list_dpj);
		return list_dpj;
	}
	
	
	public void borrar(){
		
		this.jugadores.clear();
	}
	
}