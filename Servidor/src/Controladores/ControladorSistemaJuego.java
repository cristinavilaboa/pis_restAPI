package Controladores;


import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Datatypes.DataProblema;
import Manejadores.ManejadorMundo;
import Manejadores.ManejadorProblema;
import Manejadores.ManejadorUsuario;
import Modelo.CargarDatos;
import Modelo.EstadoJugador;
import Modelo.Logro;
import Modelo.Mundo;
import Modelo.Nivel;
import Modelo.Problema;
@RestController
public class ControladorSistemaJuego implements IControladorSistemaJuego {
	
	//METODOS A IMPLEMENTAR
	public void avanzarJuego(String id_jugador, int id_pregunta, int id_mundo){
		ManejadorProblema mp = ManejadorProblema.getInstancia();
		ManejadorUsuario mu = ManejadorUsuario.getInstancia();
		if (mp.ultimaNivel(id_pregunta)){//Si es la ultima pregunta del nivel, paso de nivel
			ManejadorMundo mm = ManejadorMundo.getInstancia();
			EstadoJugador estado = mu.buscarJugador(id_jugador).getEstado();
			Mundo mundo = mm.obtenerMundo(id_mundo);
			estado.agregarNivelActivo(mundo);
			Nivel nivel = mp.buscarProblema(id_pregunta).getNivel();
			if(mundo.ultimoNivelMundo(nivel)){//Si es la ultima pregunta del mundo, avanzo el mundo
				List<Mundo> mundos_siguientes = mundo.getMundos_siguientes();
				if(!estado.getMundos_completos().contains(mundo)){
					estado.getMundos_completos().add(mundo);
					estado.ganarExperiencia(mundo.getPuntos_exp());
					Logro mundo_terminado = new Logro("Mundo "+mundo.getNombre()+" completado");
					estado.ganarLogro(mundo_terminado);		
				}
				
				
				for(Mundo m: mundos_siguientes){//Desbloqueo todos los mundos siguientes
					if(!estado.getMundosActivos().contains(m)){//Solo se desbloque si no esta desbloqueado de antes
						estado.agregarMundoActivo(m);
					}
					
				}
			}
		}
	}
	
	@RequestMapping(value="/siguienteProblema", method=RequestMethod.GET)
	public DataProblema siguienteProblema(@RequestParam(value="id_jugador") String id_jugador,@RequestParam(value="id_problema") int id_problema){
		// la id_jugador en esta iteracion no se usa
		// para esta iteracion se devuelve el problema siguiente, siendo id_problema el problema resuelto correctamente.
		
		ManejadorProblema mp=ManejadorProblema.getInstancia();	
		Problema p=mp.buscarProblema((id_problema + 1));
		return p.getDataProblema();
	}
	

	@RequestMapping(value="/iniciarjuego", method=RequestMethod.POST)
	public void iniciarJuego()
	{
		CargarDatos cd = new CargarDatos();
	}


}
