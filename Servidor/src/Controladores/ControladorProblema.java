package Controladores;


import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.util.Date;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Datatypes.DataAyuda;
import Datatypes.DataEstadoMensaje;
import Datatypes.DataExperiencia;
import Datatypes.DataTypeConstants;
import Manejadores.ManejadorMundo;
import Manejadores.ManejadorProblema;
import Manejadores.ManejadorUsuario;
import Modelo.Ayuda;
import Modelo.Contenido;
import Modelo.Mensaje;
import Modelo.Nivel;
import Modelo.Problema;
import Modelo.Profesor;
import Persistencia.CargarDatosBD;
@RestController
public class ControladorProblema implements IControladorProblema{
	
	//METODOS A IMPLEMENTAR
	@RequestMapping(value="/responderProblema", method=RequestMethod.GET)
	public DataExperiencia responderProblema(@RequestParam(value="id_problema") int id_problema,@RequestParam(value="respuesta") String respuesta,@RequestParam(value="id_jugador") String id_jugador){//JUAN
		ManejadorProblema mp = ManejadorProblema.getInstancia();
		IControladorJugador cu = new ControladorJugador();
		IControladorSistemaJuego csj = new ControladorSistemaJuego();
		int exp_ganada = mp.verificarRespuesta(id_problema, respuesta);

		
		if(exp_ganada > 0 && !cu.yaRespondida(id_jugador, id_problema)){
			cu.sumarPuntos(exp_ganada, id_jugador, id_problema);
			int id_mundo = mp.buscarProblema(id_problema).getNivel().getMundo().getId();
			csj.avanzarJuego(id_jugador, id_problema, id_mundo);
		}
		return new DataExperiencia(exp_ganada);
	}
	@RequestMapping(value="/enviarmensaje", method=RequestMethod.GET)
	public DataEstadoMensaje enviarMensaje(@RequestParam(value="id_problema") int id_problema,@RequestParam(value="mensaje") String mensaje,@RequestParam(value="fecha") String fechaStr,@RequestParam(value="asunto") String asunto){ 
		Date fecha;
		try {
			fecha = DataTypeConstants.getDateFormat().parse(fechaStr);
		
		ManejadorProblema mp=ManejadorProblema.getInstancia();
		Problema p=mp.buscarProblema(id_problema);
		p.enviarMensaje(URLDecoder.decode(mensaje, "UTF-8") , fecha, asunto);
		//String nickProfesor= p.getAutor().getNick();
		Profesor profesor= p.getAutor();
		Mensaje mens=CargarDatosBD.PersistirMensaje(mensaje,fecha,asunto,profesor);
		//CargarDatosBD.PersistirUsuarioMensaje(nickProfesor, idMensaje);
		//CargarDatosBD.PersistirUsuarioMensaje2(profesor, mens);
		return new DataEstadoMensaje(true);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
			return new DataEstadoMensaje(false);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new DataEstadoMensaje(false);
		}
		
		
	}
	
	@RequestMapping(value="/getayuda", method=RequestMethod.GET)
	public DataAyuda getAyuda(@RequestParam(value="id_problema") int id_problema){
		ManejadorProblema manejador = ManejadorProblema.getInstancia();
		return new DataAyuda(manejador.getAyuda(id_problema));
	}
	
	@RequestMapping(value="/agregarproblema", method=RequestMethod.POST)
	public void agregarProblema(@RequestParam(value="id_problema") int id_problema, @RequestParam(value="desc")String descripcion, @RequestParam(value="resp")String respuesta,
			@RequestParam(value="exp")int puntos_exp, @RequestParam(value="ayuda")String cont_ayuda, @RequestParam(value="cont")String cont,
			@RequestParam(value="id_mundo") int id_mundo,@RequestParam(value="num_nivl")int num_nivel, @RequestParam(value="nick_prof")String nick_prof){
	Ayuda ayuda = new Ayuda(cont_ayuda);
	Contenido contenido = new Contenido(cont);
	ManejadorMundo mm = ManejadorMundo.getInstancia();
	
	
	ManejadorUsuario mu = ManejadorUsuario.getInstancia();
	Profesor profe = mu.buscarProfesor(nick_prof);
		Nivel nivel = mm.obtenerMundo(id_mundo).buscarNivel(num_nivel);

	Problema problema = new Problema(id_problema, descripcion, respuesta, puntos_exp, ayuda, contenido, nivel, profe);
	
	ManejadorProblema mp = ManejadorProblema.getInstancia();
	mp.agregarProblema(problema);
	}
	

}// 
