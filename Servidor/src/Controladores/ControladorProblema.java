package Controladores;


import java.util.Date;

import org.springframework.util.SocketUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Datatypes.DataProblema;
import Manejadores.ManejadorProblema;
import Modelo.Problema;
@RestController
public class ControladorProblema implements IControladorProblema{

	//METODOS A IMPLEMENTAR
	@RequestMapping(value="/responderPregunta", method=RequestMethod.GET)
	public int responderPregunta(@RequestParam(value="id_problema") int id_problema,@RequestParam(value="respuesta") String respuesta,@RequestParam(value="id_jugador") String id_jugador){//JUAN
		ManejadorProblema mp = ManejadorProblema.getInstancia();
		IControladorJugador cu = new ControladorJugador();
		IControladorSistemaJuego csj = new ControladorSistemaJuego();
		int exp_ganada = mp.verificarRespuesta(id_problema, respuesta);

		
		if(exp_ganada > 0 && !cu.yaRespondia(id_jugador, id_problema)){
			cu.sumarPuntos(exp_ganada, id_jugador, id_problema);
			int id_mundo = mp.buscarProblema(id_problema).getNivel().getMundo().getId();
			csj.avanzarJuego(id_jugador, id_problema, id_mundo);
		}
		return exp_ganada;
	}
	@RequestMapping(value="/enviarmensaje", method=RequestMethod.POST)
	public void enviarMensaje(@RequestParam(value="id_problema") int id_problema,@RequestParam(value="mensaje") String mensaje,@RequestParam(value="fecha") Date fecha,@RequestParam(value="asunto") String asunto){ 
		ManejadorProblema mp=ManejadorProblema.getInstancia();
		Problema p=mp.buscarProblema(id_problema);
		p.enviarMensaje(mensaje, fecha, asunto);
	}
	
	@RequestMapping(value="/getayuda", method=RequestMethod.GET)
	public String getAyuda(@RequestParam(value="id_pregunta") int id_pregunta){
		ManejadorProblema manejador = ManejadorProblema.getInstancia();
		return manejador.getAyuda(id_pregunta);
	}
	

	
	

}// 
