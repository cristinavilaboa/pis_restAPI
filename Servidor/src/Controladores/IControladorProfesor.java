package Controladores;



import Datatypes.DataListaMensajes;

public interface IControladorProfesor {
	public DataListaMensajes verMensajes(String nick);
	public void responderMensaje(String nick_jugador,String asunto,String contenido,String id_profesor);
	public void mensajeleido(String nick_prof,int id_mensaje);
	
}