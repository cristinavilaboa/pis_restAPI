package Controladores;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Datatypes.DataEstadistica;
import Datatypes.DataListEstadisticas;
import Datatypes.DataListaMensajes;
import Datatypes.DataListaMundos;
import Datatypes.DataListaNiveles;
import Datatypes.DataMensaje;
import Datatypes.DataMundo;
import Datatypes.DataNivel;
import Manejadores.ManejadorMundo;
import Manejadores.ManejadorProblema;
import Manejadores.ManejadorUsuario;
import Modelo.Estadistica;
import Modelo.Jugador;
import Modelo.Mensaje;
import Modelo.Mundo;
import Modelo.Nivel;
import Modelo.Problema;
import Modelo.Profesor;
import Modelo.Usuario;

@CrossOrigin
@RestController
public class ControladorProfesor implements IControladorProfesor{

	@RequestMapping(value="/vermensajesnuevos", method=RequestMethod.GET)
	public DataListaMensajes verMensajesNuevos(@RequestParam(value="nick")String nick){
		ManejadorUsuario mu = ManejadorUsuario.getInstancia();
		Usuario usuario = mu.buscarUsuario(nick);
		ArrayList<DataMensaje> lista_nuevos = new ArrayList<DataMensaje>();
		
		for (Mensaje m: usuario.getMensajes_nuevos()){
			lista_nuevos.add(new DataMensaje(m.getId(), m.getAsunto(), m.getContenido(), m.getFecha(),m.getRemitente()));
		}
		return new DataListaMensajes(lista_nuevos);
	}
	
	@RequestMapping(value="/vermensajesviejos", method=RequestMethod.GET)
	public DataListaMensajes verMensajesViejos(@RequestParam(value="nick")String nick){
		ManejadorUsuario mu = ManejadorUsuario.getInstancia();
		Usuario usuario = mu.buscarUsuario(nick);
		ArrayList<DataMensaje> lista = new ArrayList<DataMensaje>();
		
		for (Mensaje m: usuario.getMensajes_viejos()){
			lista.add(new DataMensaje(m.getId(), m.getAsunto(), m.getContenido(), m.getFecha(),m.getRemitente()));
		}
		return new DataListaMensajes(lista);
	}
	
	//SI NO ENCUENTRA EL MENSAJE CON DICHO ID, RETORNA NULL.
	@RequestMapping(value="/vermensaje", method=RequestMethod.GET)
	public DataMensaje verMensaje(@RequestParam(value="id_mensaje")int id_mensaje){
		ManejadorUsuario mu = ManejadorUsuario.getInstancia();
		Mensaje m = mu.buscarMensaje(id_mensaje);
		return new DataMensaje(m.getId(), m.getAsunto(), m.getContenido(), m.getFecha(), m.getRemitente());
	}
	
	/*
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
	*/
	
	 @RequestMapping(value="/respondermensaje", method=RequestMethod.POST) //Responde un mensaje
	public void responderMensaje(@RequestParam(value="destinatario")String destinatario,@RequestParam(value="asunto")String asunto,@RequestParam(value="contenido")String contenido,@RequestParam(value="remitente")String remitente){
		
		Date fecha = new Date();
		Mensaje m = new Mensaje(contenido, asunto, fecha, remitente);
		
		ManejadorUsuario mu = ManejadorUsuario.getInstancia();
		Usuario u =mu.buscarUsuario(destinatario);
		u.agregar_mensaje_nuevo(m);
		
		mu.guardarMensaje(m);
		mu.guardarUsuario(u);
	}
	
	
	@RequestMapping(value="/mensajeleido", method=RequestMethod.POST) //Cambia un mensaje de nuevo a viejo.
	public void mensajeleido(@RequestParam(value="nick")String nick,@RequestParam(value="id_mensaje")int id_mensaje){
		ManejadorUsuario mu = ManejadorUsuario.getInstancia();
		Usuario usuario = mu.buscarUsuario(nick);	
		if(usuario.esMensajeNuevo(id_mensaje)){
			usuario.mensajeLeido(id_mensaje);
			mu.guardarUsuario(usuario);
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
	
	@RequestMapping(value="/verestadisticas", method=RequestMethod.GET)
	public DataListEstadisticas verEstadisticas(){
		List<DataEstadistica> lista = new ArrayList<DataEstadistica>();
		ManejadorProblema mp =ManejadorProblema.getInstancia();
		for(Problema p : mp.getProblemas().values()){
			DataEstadistica dt = new DataEstadistica(p.getNivel().getMundo().getNombre(), p.getNivel().getNro_nivel(), p.getId(), p.getEstadisticas().getCant_intentos(), p.getEstadisticas().getCant_aciertos(),p.getContenido().getURL());
			lista.add(dt);
		}
		
		return new DataListEstadisticas(lista);
	}
	@RequestMapping(value="/agregarnivel", method=RequestMethod.POST)
	public void agregarNivel(@RequestParam(value="id_mundo")int id_mundo){
		ManejadorMundo mm = ManejadorMundo.getInstancia();
		ManejadorUsuario mu = ManejadorUsuario.getInstancia();

		String nick = null;
		
		Mundo mundo_nivel =  mm.obtenerMundo(id_mundo);
		Nivel nuevo_nivel = new Nivel(new ArrayList<Problema>(), mundo_nivel);
		mundo_nivel.agregarNivel(nuevo_nivel);
		
		mm.agregarMundo(mundo_nivel);//NO ESTAMOS SEGUROS DE ESTO
		
		if(nuevo_nivel.getNro_nivel() == 0 && id_mundo > 0){
			int id_m_anterior =id_mundo - 1;
			
			for(Jugador j : mu.obtenerJugadores()){
				boolean encontre = false;
				for(Mundo m_comp : j.getEstado().getMundos_completos()){
					if(m_comp.getId() == id_m_anterior){
						encontre = true;
						break;
					}
				}
				if(encontre){
					j.getEstado().agregarMundoActivo(mundo_nivel);
					mu.agregarJugador(j);
					break;
				}
				
			}
			
			
		}
		
		
		
		
	}
	
	@RequestMapping(value="/agregarmundo", method=RequestMethod.POST)
	public void agregarMundo(@RequestParam(value="nombre")String nombre,@RequestParam(value="imagen")String imagen,@RequestParam(value="exp")int exp,@RequestParam(value="desc")String desc){
		ManejadorMundo mm = ManejadorMundo.getInstancia();
		int max_id = -1;
		for(Mundo m: mm.obtenerMundos()){
			if(m.getId() > max_id){
				max_id = m.getId();
			}
		}
		Mundo nuevo_mundo = new Mundo(max_id+1, nombre, imagen, desc, exp, new ArrayList<Mundo>(), new ArrayList<Nivel>());
		mm.agregarMundo(nuevo_mundo);
		
		if(max_id >= 0){
			Mundo m_anterior = mm.obtenerMundo(max_id);
			m_anterior.getMundos_siguientes().add(nuevo_mundo);	
			mm.agregarMundo(m_anterior);
		}
		
		
		
	}
	
}
