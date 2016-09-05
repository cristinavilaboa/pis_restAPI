package Controladores;

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
	
	//METODOS A IMPLEMENTAR
	@RequestMapping("/sumarpuntos")
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
	
	public boolean yaRespondia(String id_jugador,int id_pregunta){
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
