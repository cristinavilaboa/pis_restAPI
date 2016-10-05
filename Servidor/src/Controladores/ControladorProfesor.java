package Controladores;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Datatypes.DataListaMensajes;
import Datatypes.DataListaMundos;
import Datatypes.DataListaNiveles;
import Datatypes.DataMensaje;
import Datatypes.DataMundo;
import Datatypes.DataNivel;
import Manejadores.ManejadorMundo;
import Manejadores.ManejadorUsuario;
import Modelo.Jugador;
import Modelo.Mensaje;
import Modelo.Mundo;
import Modelo.Nivel;
import Modelo.Profesor;

@RestController
public class ControladorProfesor implements IControladorProfesor{

	@RequestMapping(value="/vermensajesnuevos", method=RequestMethod.GET)
	public DataListaMensajes verMensajesNuevos(@RequestParam(value="nick")String nick){
		ManejadorUsuario mu = ManejadorUsuario.getInstancia();
		Profesor p = mu.buscarProfesor(nick);
		ArrayList<DataMensaje> lista_nuevos = new ArrayList<DataMensaje>();
		
		for (Mensaje m: p.getMensajes_nuevos()){
			lista_nuevos.add(new DataMensaje(m.getId(), m.getAsunto(), m.getContenido(), m.getFecha(),m.getRemitente()));
		}
		return new DataListaMensajes(lista_nuevos);
	}
	
	@RequestMapping(value="/vermensajesviejos", method=RequestMethod.GET)
	public DataListaMensajes verMensajesViejos(@RequestParam(value="nick")String nick){
		ManejadorUsuario mu = ManejadorUsuario.getInstancia();
		Profesor p = mu.buscarProfesor(nick);
		ArrayList<DataMensaje> lista = new ArrayList<DataMensaje>();
		
		for (Mensaje m: p.getMensajes_viejos()){
			lista.add(new DataMensaje(m.getId(), m.getAsunto(), m.getContenido(), m.getFecha(),m.getRemitente()));
		}
		return new DataListaMensajes(lista);
	}
	
	//SI NO ENCUENTRA EL MENSAJE CON DICHO ID, RETORNA NULL.
	@RequestMapping(value="/vermensaje", method=RequestMethod.GET)
	public DataMensaje verMensaje(@RequestParam(value="nick")String nick_prof,@RequestParam(value="id_mensaje")int id_mensaje){
		ManejadorUsuario mu = ManejadorUsuario.getInstancia();
		Profesor p = mu.buscarProfesor(nick_prof);
		//INICIALIZO COMO NULL PORQUE SINO ME SALE QUE NO ESTA INICIALIZADA LA VARIABLE
		DataMensaje dm=null;
		
		for(Mensaje m: p.getMensajes_nuevos()){
			if (m.getId() == id_mensaje){
				return new DataMensaje(m.getId(), m.getAsunto(), m.getContenido(), m.getFecha(), m.getRemitente());
			}
		}
		
		for(Mensaje m: p.getMensajes_viejos()){
			if (m.getId() == id_mensaje){
				return new DataMensaje(m.getId(), m.getAsunto(), m.getContenido(), m.getFecha(), m.getRemitente());
			}
		}
		return dm;
	}
	
	@RequestMapping(value="/respondermensaje", method=RequestMethod.POST) //Responde un mensaje
	public void responderMensaje(@RequestParam(value="nick_jugador")String nick_jugador,@RequestParam(value="asunto")String asunto,@RequestParam(value="contenido")String contenido,@RequestParam(value="id_profesor")String id_profesor){
		
		Date fecha = new Date();
		Mensaje m = new Mensaje(contenido, asunto, fecha, id_profesor);
		
		ManejadorUsuario mu = ManejadorUsuario.getInstancia();
		Jugador jugador = mu.buscarJugador(nick_jugador);
		jugador.agregar_mensaje_nuevo(m);
		
		mu.guardarMensaje(m);
		mu.guardarUsuario(jugador);
	}
	
	@RequestMapping(value="/mensajeleido", method=RequestMethod.POST) //Cambia un mensaje de nuevo a viejo.
	public void mensajeleido(@RequestParam(value="nick_prof")String nick_prof,@RequestParam(value="id_mensaje")int id_mensaje){
		ManejadorUsuario mu = ManejadorUsuario.getInstancia();
		Profesor p = mu.buscarProfesor(nick_prof);	
		if(p.esMensajeNuevo(id_mensaje)){
			p.mensajeLeido(id_mensaje);
			mu.guardarUsuario(p);
		}
		
	}
	
	@RequestMapping(value="/listarmundosprofesor", method=RequestMethod.GET)
	public DataListaMundos listarMundosProfesor(){
		List<DataMundo> lista = new ArrayList<DataMundo>();
		
		ManejadorMundo mm = ManejadorMundo.getInstancia();
		List<Mundo> mundos = mm.obtenerMundos();
		
		for(Mundo m: mundos){
			lista.add(new DataMundo(m.getId(), m.getNombre(), m.getImagen(), m.getDescripcion(),true, true, null));
		}																//Los dos true son de mundo completado y mundo disponible, para el profesor no tienen sentido pero si para el jugador
		return new DataListaMundos(lista);								// El null es la lista de mundos siguientes que creo que no es necesario en la web
	}
	
	@RequestMapping(value="/listarnivelesmundoprofesor", method=RequestMethod.GET)
	public DataListaNiveles listarNivelesMundoProfesor(@RequestParam(value="id_mundo")int id_mundo){
		ManejadorMundo mm = ManejadorMundo.getInstancia();
		Mundo mundo = mm.obtenerMundo(id_mundo);
		
		List<DataNivel> lista_niveles = new ArrayList<DataNivel>();
		for(Nivel n: mundo.getNiveles()){
			lista_niveles.add(new DataNivel(n.getId_nivel(), n.getNro_nivel(), true, true));
		}
		return new DataListaNiveles(lista_niveles);
	}
	
}
