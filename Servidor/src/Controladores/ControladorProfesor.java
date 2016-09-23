package Controladores;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Datatypes.DataListaMensajes;
import Datatypes.DataMensaje;
import Manejadores.ManejadorUsuario;
import Modelo.Jugador;
import Modelo.Mensaje;
import Modelo.Profesor;

@RestController
public class ControladorProfesor implements IControladorProfesor{

	@RequestMapping(value="/vermensajes", method=RequestMethod.GET)
	public DataListaMensajes verMensajes(@RequestParam(value="nick")String nick){
		ManejadorUsuario mu = ManejadorUsuario.getInstancia();
		Profesor p = mu.buscarProfesor(nick);
		ArrayList<DataMensaje> lista_nuevos = new ArrayList<DataMensaje>();
		ArrayList<DataMensaje> lista_viejos = new ArrayList<DataMensaje>();
		
		for (Mensaje m: p.getMensajes_nuevos()){
			lista_nuevos.add(new DataMensaje(m.getId(), m.getAsunto(), m.getContenido(), m.getFecha(),m.getRemitente()));
		}
		for (Mensaje m: p.getMensajes_viejos()){
			lista_viejos.add(new DataMensaje(m.getId(), m.getAsunto(), m.getContenido(), m.getFecha(),m.getRemitente()));
		}
		
		return new DataListaMensajes(lista_nuevos,lista_viejos);
	}
	
	@RequestMapping(value="/respondermensaje", method=RequestMethod.POST) //Responde un mensaje
	public void responderMensaje(@RequestParam(value="nick_jugador")String nick_jugador,@RequestParam(value="asunto")String asunto,@RequestParam(value="contenido")String contenido,@RequestParam(value="id_profesor")String id_profesor){
		
		Date fecha = new Date();
		Mensaje m = new Mensaje(contenido, asunto, fecha, id_profesor);
		
		ManejadorUsuario mu = ManejadorUsuario.getInstancia();
		Jugador jugador = mu.buscarJugador(nick_jugador);
		jugador.agregar_mensaje_nuevo(m);
	}
	
	@RequestMapping(value="/mensajeleido", method=RequestMethod.POST) //Cambia un mensaje de nuevo a viejo.
	public void mensajeleido(@RequestParam(value="nick_prof")String nick_prof,@RequestParam(value="id_mensaje")int id_mensaje){
		ManejadorUsuario mu = ManejadorUsuario.getInstancia();
		Profesor p = mu.buscarProfesor(nick_prof);	
		if(p.esMensajeNuevo(id_mensaje)){
			p.mensajeLeido(id_mensaje);
		}
		
	}
}
