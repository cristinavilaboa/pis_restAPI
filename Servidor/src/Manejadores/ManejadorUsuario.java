package Manejadores;

import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import Datatypes.DataPuntosJugador;
import Modelo.Usuario;
import java.util.Iterator;
import Modelo.Jugador;

public class ManejadorUsuario {
	
	private Set<Usuario> usuarios;
	
	private static ManejadorUsuario instancia = new ManejadorUsuario();
	
	private ManejadorUsuario(){};
	
	public static ManejadorUsuario getInstancia(){
		return instancia;
	}
	
	public SortedSet<DataPuntosJugador> obtenerRanking(){
		SortedSet<DataPuntosJugador> set_dpj = new TreeSet<>();
		Iterator<Usuario> it = usuarios.iterator();
		while(it.hasNext()){
			Usuario u = it.next();
			if (u instanceof Jugador){
				Jugador j = (Jugador) u;
				DataPuntosJugador dpj = j.obtenerDataPuntosJugador(u.getNombre());
				set_dpj.add(dpj);
			}
		}
		return set_dpj;
	}
	
}