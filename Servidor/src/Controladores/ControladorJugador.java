package Controladores;


import Datatypes.DataPuntosJugador;
import Manejadores.ManejadorUsuario;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Datatypes.DataJugador;
import Datatypes.DataListaDataProblema;
import Datatypes.DataListaMundos;
import Datatypes.DataListaNiveles;
import Datatypes.DataListaRanking;
import Datatypes.DataLogin;
import Datatypes.DataMundo;
import Datatypes.DataNivel;
import Datatypes.DataProblema;
import Manejadores.ManejadorMundo;
import Manejadores.ManejadorProblema;
import Modelo.EstadoJugador;
import Modelo.Jugador;
import Modelo.Logro;
import Modelo.Mundo;
import Modelo.Nivel;
import Modelo.Problema;

@RestController
public class ControladorJugador implements IControladorJugador{
	
	@RequestMapping(value="/verperfil", method=RequestMethod.GET)
	public DataJugador verPerfil(@RequestParam(value="nick")String id_jugador)
	{
		ManejadorUsuario mu=ManejadorUsuario.getInstancia();
		DataJugador dj=mu.obtenerDatosJugador(id_jugador);
		return dj;
	}
	//METODOS A IMPLEMENTAR
	
	public void sumarPuntos( int exp, String id_jugador,int id_problema){
		ManejadorUsuario mu = ManejadorUsuario.getInstancia();
		ManejadorProblema mp = ManejadorProblema.getInstancia();
		EstadoJugador estado = mu.buscarJugador(id_jugador).getEstado();
		Problema pro = mp.buscarProblema(id_problema);
		estado.ganarExperiencia(exp);
		estado.agregarProblema(pro);
		ArrayList<Logro> nuevos_logros = estado.nuevosLogros();
		for(Logro l:nuevos_logros){
			estado.ganarLogro(l);
		}
		
		
	}
	
	
	public boolean yaRespondida(String id_jugador,int id_problema){
		ManejadorUsuario mu = ManejadorUsuario.getInstancia();
		EstadoJugador estado = mu.buscarJugador(id_jugador).getEstado();
		for(Problema pro: estado.getProblemas_resueltos()){
			if (pro.getId() == id_problema){
				return true;
			}
		}
		return false;
	}
	
	@RequestMapping(value="/verranking", method=RequestMethod.GET)
	public DataListaRanking obtenerRanking(){
		ManejadorUsuario manUs = ManejadorUsuario.getInstancia();
		List<DataPuntosJugador> list_dpj = manUs.obtenerRanking();
		return new DataListaRanking(list_dpj);
	}
	
	@RequestMapping(value="/listarmundosjugador", method=RequestMethod.GET)
	public DataListaMundos listarMundosJugador(@RequestParam(value="nick")String nick){
		List<DataMundo> lista = new ArrayList<DataMundo>();
		
		ManejadorMundo mm = ManejadorMundo.getInstancia();
		List<Mundo> mundos = mm.obtenerMundos();
		
		ManejadorUsuario mu = ManejadorUsuario.getInstancia();
		EstadoJugador estado = mu.buscarJugador(nick).getEstado();
		
		List<Mundo> mundos_completos = estado.getMundos_completos();
		List<Integer> mundos_disponibles = new ArrayList<Integer>();
		
		for(Integer id_mundo: estado.getNiveles_actuales().keySet()){
			mundos_disponibles.add(id_mundo);
		}
		
		for(Mundo m: mundos){
			boolean completo = mundos_completos.contains(m);
			boolean disponible = mundos_disponibles.contains(m.getId());
			List<Integer> mundos_siguientes = new ArrayList<Integer>();
			
			for(Mundo sig : m.getMundos_siguientes()){
				mundos_siguientes.add(sig.getId());
			}
					
			lista.add(new DataMundo(m.getId(), m.getNombre(), m.getImagen(), m.getDescripcion(),completo, disponible,mundos_siguientes));
		}										
		return new DataListaMundos(lista);
	}
	
	@RequestMapping(value="/registrarjugador", method=RequestMethod.GET)
	public boolean registrarJugador(@RequestParam(value="nick")String nick,@RequestParam(value="fb_token")String fb_token,@RequestParam(value="nombre")String nombre){
		
		ManejadorUsuario mu = ManejadorUsuario.getInstancia();
		if(mu.existeJugador(nick)){
			return false;
		}else{
			
			EstadoJugador estado = new EstadoJugador(0, new ArrayList<Mundo>(), new ArrayList<Logro>(), new HashMap<Integer,Nivel>(), new ArrayList<Problema>());
			//Suponemos que el mundo con el id 0 es el inicial, porque lo necesitamos para inicializar el mundo.
			ManejadorMundo mm = ManejadorMundo.getInstancia();
			estado.agregarMundoActivo(mm.obtenerMundo(0));
			
			Jugador jugador = new Jugador(nombre, nick, fb_token, null, estado, null);
			mu.agregarJugador(jugador);
			return true;
		}	
	}
	
	@RequestMapping(value="/listarnivelesmundojugador", method=RequestMethod.GET)
	public DataListaNiveles listarNivelesMundoJugador(@RequestParam(value="nick")String nick,@RequestParam(value="id_mundo") int id_mundo){
		ManejadorMundo mm = ManejadorMundo.getInstancia();
		Mundo mundo = mm.obtenerMundo(id_mundo);
		
		ManejadorUsuario mu = ManejadorUsuario.getInstancia();
		EstadoJugador estado = mu.buscarJugador(nick).getEstado();
		
		List<DataNivel> lista_niveles = new ArrayList<DataNivel>();
		for(Nivel n: mundo.getNiveles()){
			boolean completo;
			boolean disponible;
			if(estado.getNiveles_actuales().containsKey(id_mundo)){
				completo = estado.getNiveles_actuales().get(id_mundo).getNro_nivel() > n.getNro_nivel();
				disponible = estado.getNiveles_actuales().get(id_mundo).getNro_nivel() >= n.getNro_nivel();
				
			}else{
				completo = false;
				disponible = false;
			}
			
			lista_niveles.add(new DataNivel(n.getId_nivel(), n.getNro_nivel(), completo, disponible));
		}
		return new DataListaNiveles(lista_niveles);
	}
	
	@RequestMapping(value="/listarproblemasnivel", method=RequestMethod.GET)
	public DataListaDataProblema listarProblemasNivel(@RequestParam(value="nick")String nick,@RequestParam(value="id_mundo")int id_mundo,@RequestParam(value="id_nivel")int id_nivel){
		List<DataProblema> lista_problemas = new ArrayList<DataProblema>();
		
		ManejadorUsuario mu = ManejadorUsuario.getInstancia();
		ManejadorMundo mm = ManejadorMundo.getInstancia();
		
		Nivel nivel = mm.obtenerMundo(id_mundo).buscarNivel(id_nivel);
		EstadoJugador estado = mu.buscarJugador(nick).getEstado();
		List<Problema> problemas_resueltos = estado.getProblemas_resueltos();
		
		for(Problema p: nivel.getProblemas()){
			boolean resuelto = problemas_resueltos.contains(p);
			lista_problemas.add(new DataProblema(p.getId(), p.getDescripcion(), p.getRespuesta(), p.getPuntos_exp(), p.getAyuda().getInfo(), p.getContenido().getTexto(), p.getAutor().getNick(), resuelto));
		}
		
		return new DataListaDataProblema(lista_problemas);
	}
	
	@RequestMapping(value="/loginjugador", method=RequestMethod.GET)
	public DataLogin loginJugador(@RequestParam(value="fb_token")String fb_token){
		ManejadorUsuario mu = ManejadorUsuario.getInstancia();
		List<Jugador> jugadores = mu.obtenerJugadores();
		
		for(Jugador j: jugadores){
			
			if(j.getFBToken().equalsIgnoreCase(fb_token)){
				
				return new DataLogin(j.getNick(),true);
			}
		}
		return new DataLogin(null, false);
	}
	
	
	
}