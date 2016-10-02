package Controladores;


import Datatypes.DataPuntosJugador;
import Manejadores.ManejadorUsuario;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Datatypes.DataJugador;
import Datatypes.DataListaMundos;
import Datatypes.DataListaRanking;
import Datatypes.DataMundo;
import Manejadores.ManejadorMundo;
import Manejadores.ManejadorProblema;
import Modelo.EstadoJugador;
import Modelo.Logro;
import Modelo.Mundo;
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
		
		mu.guardarEstado(estado);
		
		
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
					
			lista.add(new DataMundo(m.getId(), m.getNombre(), m.getImagen(), m.getDescripcion(),completo, disponible));
		}										
		return new DataListaMundos(lista);
	}
}