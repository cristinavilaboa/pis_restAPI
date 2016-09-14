package Controladores;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Datatypes.DataListaMensajes;
import Datatypes.DataMensaje;
import Manejadores.ManejadorUsuario;
import Modelo.Mensaje;
import Modelo.Profesor;

@RestController
public class ControladorProfesor implements IControladorProfesor{

	@RequestMapping(value="/vermensajes", method=RequestMethod.GET)
	public DataListaMensajes verMensajes(@RequestParam(value="nick")String nick){
		ManejadorUsuario mu = ManejadorUsuario.getInstancia();
		Profesor p = mu.buscarProfesor(nick);
		ArrayList<DataMensaje> lista = new ArrayList<DataMensaje>();
		
		for (Mensaje m: p.getMensajes_nuevos()){
			lista.add(new DataMensaje(m.getId(), m.getAsunto(), m.getContenido(), m.getFecha()));
		}
		for (Mensaje m: p.getMensajes_viejos()){
			lista.add(new DataMensaje(m.getId(), m.getAsunto(), m.getContenido(), m.getFecha()));
		}
		
		return new DataListaMensajes(lista);
	}
}
