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
import Datatypes.DataListaRanking;
import Manejadores.ManejadorProblema;
import Modelo.EstadoJugador;
import Modelo.Logro;
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
	
}