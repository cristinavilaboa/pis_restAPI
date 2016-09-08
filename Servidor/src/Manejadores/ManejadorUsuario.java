package Manejadores;

import java.util.Collections;
import Datatypes.DataPuntosJugador;
import java.util.Iterator;
import Modelo.Jugador;
import java.util.ArrayList;
import java.util.List;

public class ManejadorUsuario {
	
	private static ManejadorUsuario instancia = new ManejadorUsuario();
	
	private List<Jugador> jugadores = new ArrayList<Jugador>();
	
	private ManejadorUsuario(){};
	
	public static ManejadorUsuario getInstancia(){
		return instancia;
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

	
	public List<DataPuntosJugador> obtenerRanking(){
		List<DataPuntosJugador> list_dpj = new ArrayList<>();
		Iterator<Jugador> it = jugadores.iterator();
		while(it.hasNext()){
			Jugador j = it.next();
			String nombreJ = j.getNombre();
			DataPuntosJugador dpj = j.obtenerDataPuntosJugador(nombreJ);
			list_dpj.add(dpj);
		}
		Collections.sort(list_dpj);
		return list_dpj;
	}
}