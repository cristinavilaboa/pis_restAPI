package Controladores;


import java.io.UnsupportedEncodingException;

import java.net.URLDecoder;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
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
import Modelo.Estadistica;
import Modelo.EstadoJugador;
import Modelo.Jugador;
import Modelo.Mensaje;
import Modelo.Nivel;
import Modelo.Problema;
import Modelo.Profesor;
import Persistencia.CargarDatosBD;

@CrossOrigin
@RestController
public class ControladorProblema implements IControladorProblema{
	
	//METODOS A IMPLEMENTAR
	@RequestMapping(value="/responderProblema", method=RequestMethod.GET)
	public DataExperiencia responderProblema(@RequestParam(value="id_problema") int id_problema,@RequestParam(value="respuesta") String respuesta,@RequestParam(value="id_jugador") String id_jugador){//JUAN
		ManejadorProblema mp = ManejadorProblema.getInstancia();
		IControladorJugador cu = new ControladorJugador();
		IControladorSistemaJuego csj = new ControladorSistemaJuego();
		int exp_ganada = mp.verificarRespuesta(id_problema, respuesta);
		Problema p = mp.buscarProblema(id_problema);
		Estadistica estadisticas = p.getEstadisticas();
		if(exp_ganada == 0){
			ManejadorUsuario mu = ManejadorUsuario.getInstancia();
			Jugador jugador = mu.buscarJugador(id_jugador);
			List<Integer> lista = jugador.getEstado().getProblemas_tutorial_activo();
			if(!lista.contains(id_problema)){
				lista.add(id_problema);
			}
			mu.agregarJugador(jugador);
		}
		if(!cu.yaRespondida(id_jugador, id_problema)){
			estadisticas.aumentarIntentos();
			if(exp_ganada > 0){
				estadisticas.aumentarAciertos();
				cu.sumarPuntos(exp_ganada, id_jugador, id_problema);
				int id_mundo = p.getNivel().getMundo().getId();
				csj.avanzarJuego(id_jugador, id_problema, id_mundo);
			}
			mp.agregarProblema(p);
		}
		
		return new DataExperiencia(exp_ganada);
	}
	
	@RequestMapping(value="/enviarmensaje", method=RequestMethod.GET)
	public DataEstadoMensaje enviarMensaje(@RequestParam(value="id_problema") int id_problema,@RequestParam(value="nick") String nick,@RequestParam(value="mensaje") String mensaje,@RequestParam(value="fecha") String fechaStr,@RequestParam(value="asunto") String asunto){ 
		Date fecha;
		try {
			fecha = DataTypeConstants.getDateFormat().parse(fechaStr);
			ManejadorProblema mp=ManejadorProblema.getInstancia();
			Problema p=mp.buscarProblema(id_problema);
			p.enviarMensaje(URLDecoder.decode(mensaje, "UTF-8") , fecha, asunto,nick);
			return new DataEstadoMensaje(true);
		
		} catch (ParseException e) {			
			e.printStackTrace();
			return new DataEstadoMensaje(false);
		} catch (UnsupportedEncodingException e) {
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
	public void agregarProblema(@RequestParam(value="desc")String descripcion, @RequestParam(value="resp")String respuesta,
			@RequestParam(value="exp")int puntos_exp, @RequestParam(value="ayuda")String cont_ayuda, @RequestParam(value="cont")String cont,
			@RequestParam(value="id_mundo") int id_mundo,@RequestParam(value="num_nivl")int num_nivel, @RequestParam(value="nick_prof")String nick_prof){
		
		Ayuda ayuda = new Ayuda(cont_ayuda);
		Contenido contenido = new Contenido(cont);
		ManejadorMundo mm = ManejadorMundo.getInstancia();
		
		
		ManejadorUsuario mu = ManejadorUsuario.getInstancia();
		Profesor profe = mu.buscarProfesor(nick_prof);
		Nivel nivel = mm.obtenerMundo(id_mundo).buscarNivelPorNro(num_nivel);
	
		Problema problema = new Problema( descripcion, respuesta, puntos_exp, ayuda, contenido, nivel, profe, new Estadistica(0,0));

		ManejadorProblema mp = ManejadorProblema.getInstancia();
		mp.agregarProblema(problema);
	}
	@RequestMapping(value="/reportarproblema", method=RequestMethod.POST)
	public void reportarProblema(@RequestParam(value="id_problema") int id_problema,@RequestParam(value="nick") String nick,@RequestParam(value="mensaje") String mensaje){
	try {
	ManejadorProblema mp = ManejadorProblema.getInstancia();
	Problema p = mp.buscarProblema(id_problema);
	Profesor profe = p.getAutor();
	Mensaje reporte = new Mensaje( URLDecoder.decode(mensaje, "UTF-8"), "Reporte Problema: "+ id_problema, new Date(), nick);
	profe.agregarReporte(reporte);
	ManejadorUsuario.getInstancia().agregarProfesor(profe);
	}catch(Exception e){
		e.printStackTrace();
	}
	
	
	}
	
	@RequestMapping(value="/modificarproblema", method=RequestMethod.POST)
	public void modificarProblema(@RequestParam(value="id_problema")int id_problema, @RequestParam(value="desc")String descripcion, @RequestParam(value="resp")String respuesta,
			@RequestParam(value="exp")int puntos_exp, @RequestParam(value="ayuda")String cont_ayuda, @RequestParam(value="cont")String cont){

		ManejadorProblema mp = ManejadorProblema.getInstancia();
		Problema problema = mp.buscarProblema(id_problema);
		problema.setDescripcion(descripcion);
		problema.setRespuesta(respuesta);
		problema.setPuntos_exp(puntos_exp);
		problema.getAyuda().setInfo(cont_ayuda);
		problema.getContenido().setURL(cont);
		mp.agregarProblema(problema);
	}

} 
