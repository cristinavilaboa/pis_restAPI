package Controladores;



import Datatypes.DataListaMensajes;
import Datatypes.DataListaMundos;
import Datatypes.DataListaNiveles;
import Datatypes.DataMensaje;

public interface IControladorProfesor {
	public DataMensaje verMensaje(String nick_prof,int id_mensaje);
	public DataListaMensajes verMensajesNuevos(String nick);
	public DataListaMensajes verMensajesViejos(String nick);
	public void responderMensaje(String nick_jugador,String asunto,String contenido,String id_profesor);
	public void mensajeleido(String nick_prof,int id_mensaje);
	public DataListaMundos listarMundosProfesor();
	public DataListaNiveles listarNivelesMundoProfesor(int id_mundo);
}