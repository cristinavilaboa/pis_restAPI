package Controladores;


import java.util.ArrayList;

import org.springframework.web.bind.annotation.RestController;

import Manejadores.ManejadorMundo;
import Manejadores.ManejadorProblema;
import Manejadores.ManejadorUsuario;
import Modelo.EstadoJugador;
import Modelo.Logro;
import Modelo.Mundo;
import Modelo.Nivel;
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
				ArrayList<Mundo> mundos_siguientes = mundo.getMundos_siguientes();
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

}
