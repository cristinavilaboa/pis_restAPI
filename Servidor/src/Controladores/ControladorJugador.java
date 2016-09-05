package Controladores;

import java.util.SortedSet;

import Datatypes.DataPuntosJugador;
import Manejadores.ManejadorUsuario;

public class ControladorJugador implements IControladorJugador{
	
	//METODOS A IMPLEMENTAR
	public void sumarPuntos(int exp, int id_jugador, int id_pregunta){}
	
	public SortedSet<DataPuntosJugador> obtenerRanking(){
		ManejadorUsuario manUs = ManejadorUsuario.getInstancia();
		SortedSet<DataPuntosJugador> set_dpj = manUs.obtenerRanking();
		return set_dpj;
	}
	
}