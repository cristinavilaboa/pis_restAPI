package Controladores;


import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.util.SocketUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Datatypes.DataAyuda;
import Datatypes.DataEstadoMensaje;
import Datatypes.DataExperiencia;
import Datatypes.DataProblema;
import Datatypes.DataTypeConstants;
import Manejadores.ManejadorProblema;
import Modelo.Problema;
import Persistencia.CargarDatosBD;
@RestController
public class ControladorProblema implements IControladorProblema{
	
	//METODOS A IMPLEMENTAR
	@RequestMapping(value="/responderPregunta", method=RequestMethod.GET)
	public DataExperiencia responderPregunta(@RequestParam(value="id_problema") int id_problema,@RequestParam(value="respuesta") String respuesta,@RequestParam(value="id_jugador") String id_jugador){//JUAN
		ManejadorProblema mp = ManejadorProblema.getInstancia();
		IControladorJugador cu = new ControladorJugador();
		IControladorSistemaJuego csj = new ControladorSistemaJuego();
		int exp_ganada = mp.verificarRespuesta(id_problema, respuesta);

		
		if(exp_ganada > 0 && !cu.yaRespondia(id_jugador, id_problema)){
			cu.sumarPuntos(exp_ganada, id_jugador, id_problema);
			//int id_mundo = mp.buscarProblema(id_problema).getNivel().getMundo().getId();
			//csj.avanzarJuego(id_jugador, id_problema, id_mundo);
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
		//int idMensaje=CargarDatosBD.PersistirMensaje(mensaje,fecha,asunto);
		//CargarDatosBD.PersistirUsuarioMensaje(nickProfesor, idMensaje);
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
	public DataAyuda getAyuda(@RequestParam(value="id_pregunta") int id_pregunta){
		ManejadorProblema manejador = ManejadorProblema.getInstancia();
		return new DataAyuda(manejador.getAyuda(id_pregunta));
	}
	

	
	

}// 
