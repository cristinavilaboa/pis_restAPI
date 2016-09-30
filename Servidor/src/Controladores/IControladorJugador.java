package Controladores;


import Datatypes.DataListaDataProblema;
import Datatypes.DataListaMundos;
import Datatypes.DataListaNiveles;
import Datatypes.DataListaRanking;

public interface IControladorJugador {
	
	public void sumarPuntos(int exp, String id_jugador, int id_problema);
	public boolean yaRespondida(String id_jugador,int id_problema);
	public DataListaRanking obtenerRanking();
	public DataListaMundos loginJugador(String nick);
	public boolean registrarJugador(String nick,String fb_token,String nombre);
	public DataListaNiveles listarNivelesMundoJugador(String nick, int id_mundo);
	public DataListaDataProblema listarProblemasNivel(String nick,int id_mundo,int id_nivel);
}
