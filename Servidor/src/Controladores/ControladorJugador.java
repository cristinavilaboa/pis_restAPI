package Controladores;

import DataTypes.DataJugador;
import Manejadores.ManejadorProblema;
import Manejadores.ManejadorUsuario;
import Modelo.Problema;

public class ControladorJugador implements IControladorJugador{
	
	
	public DataJugador verPerfil(int id_jugador)
	{
		ManejadorUsuario mu=ManejadorUsuario.getInstancia();
		DataJugador dj=mu.obtenerDatosJugador(id_jugador);
		return dj;
	}
	//METODOS A IMPLEMENTAR
	public void sumarPuntos(int exp, int id_jugador, int id_pregunta){}
}
