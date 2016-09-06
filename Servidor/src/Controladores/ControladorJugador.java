package Controladores;

import DataTypes.DataJugador;
import Manejadores.ManejadorProblema;
import Manejadores.ManejadorUsuario;
import Modelo.Problema;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Manejadores.ManejadorProblema;
import Manejadores.ManejadorUsuario;
import Modelo.EstadoJugador;
import Modelo.Logro;
import Modelo.Problema;

@RestController
public class ControladorJugador implements IControladorJugador{
	
	
	public DataJugador verPerfil(String id_jugador)
	{
		ManejadorUsuario mu=ManejadorUsuario.getInstancia();
		DataJugador dj=mu.obtenerDatosJugador(id_jugador);
		return dj;
	}
	//METODOS A IMPLEMENTAR
	@RequestMapping(value="/sumarpuntos", method=RequestMethod.PUT)
	public void sumarPuntos(@RequestParam(value="exp") int exp, @RequestParam(value="id_jugador")String id_jugador, @RequestParam(value="id_pregunta")int id_pregunta){
		ManejadorUsuario mu = ManejadorUsuario.getInstancia();
		ManejadorProblema mp = ManejadorProblema.getInstancia();
		EstadoJugador estado = mu.buscarJugador(id_jugador).getEstado();
		Problema pro = mp.buscarProblema(id_pregunta);
		estado.ganarExperiencia(exp);
		estado.agregarPregunta(pro);
		ArrayList<Logro> nuevos_logros = estado.nuevosLogros();
		for(Logro l:nuevos_logros){
			estado.ganarLogro(l);
		}
		
		
	}
	
	@RequestMapping(value="/yarespondia", method=RequestMethod.GET)
	public boolean yaRespondia(@RequestParam(value="nick")String id_jugador,@RequestParam(value="id_pregunta")int id_pregunta){
		ManejadorUsuario mu = ManejadorUsuario.getInstancia();
		EstadoJugador estado = mu.buscarJugador(id_jugador).getEstado();
		for(List<Problema> n: estado.getNivel_problema().values()){
			for(Problema pro: n){
				if(pro.getId() == id_pregunta){
					return true;
				}
			}
		}
		return false;
	}
}
