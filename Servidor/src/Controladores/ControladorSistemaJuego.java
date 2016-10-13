package Controladores;


import java.util.ArrayList;
import java.util.List;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.RestController;

import Datatypes.DataListaDataProblema;
import Datatypes.DataProblema;
import Manejadores.ManejadorMundo;
import Manejadores.ManejadorProblema;
import Manejadores.ManejadorUsuario;
//import Modelo.CargarDatos;
import Modelo.EstadoJugador;
import Modelo.Jugador;
import Modelo.Logro;
import Modelo.Mundo;
import Modelo.Nivel;

import Persistencia.CargarDatosBD;

import Modelo.Problema;

@CrossOrigin
@RestController
public class ControladorSistemaJuego implements IControladorSistemaJuego {
	@RequestMapping(value="/cargardatos", method=RequestMethod.POST)
	public void CargarDatos() throws SecurityException, HeuristicMixedException, HeuristicRollbackException, RollbackException, SystemException{
			CargarDatosBD.Cargar();
	}
	

	
	public void avanzarJuego(String id_jugador, int id_problema, int id_mundo){
		ManejadorProblema mp = ManejadorProblema.getInstancia();
		ManejadorUsuario mu = ManejadorUsuario.getInstancia();
		ManejadorMundo mm = ManejadorMundo.getInstancia();
		
		Jugador j=mu.buscarJugador(id_jugador);
		EstadoJugador estado = j.getEstado();
		Nivel nivel = mp.buscarProblema(id_problema).getNivel();
		if(estado.nivelCompleto(nivel)){
			Mundo mundo = mm.obtenerMundo(id_mundo);
			estado.agregarNivelActivo(mundo);
			if(mundo.ultimoNivelMundo(nivel)){//Si es el ultimo problema del mundo, avanzo el mundo
				List<Mundo> mundos_siguientes = mundo.getMundos_siguientes();
				boolean encontre = false;
				for(Mundo m: estado.getMundos_completos()){
					encontre = (m.getId() == mundo.getId());
				}
				if(!encontre){
					estado.agregarMundoCompleto(mundo);
					estado.ganarExperiencia(mundo.getPuntos_exp());
					Logro mundo_terminado = new Logro("Mundo "+mundo.getNombre()+" completado");
					estado.ganarLogro(mundo_terminado);		
;
				}
				for(Mundo m: mundos_siguientes){//Desbloqueo todos los mundos siguientes
					if(!estado.getNiveles_actuales().containsKey(m.getId())){//Solo se desbloque si no esta desbloqueado de antes
						estado.agregarMundoActivo(m);
					}
					
				}
			}
			
			mu.guardarUsuario(j);

		}
		
		
	}


}
