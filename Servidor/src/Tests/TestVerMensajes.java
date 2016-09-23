package Tests;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import Controladores.ControladorProfesor;
import Controladores.IControladorProfesor;
import Datatypes.DataListaMensajes;
import Datatypes.DataMensaje;
import Manejadores.ManejadorUsuario;
import Modelo.Mensaje;
import Modelo.Profesor;

public class TestVerMensajes {
	Profesor profe;
	Mensaje m1;
	Mensaje m2;
	Mensaje m3;
	
	@Before
	public void setUp() throws Exception {
		profe = new Profesor("nick", "nombre", "password");
		m1 = new Mensaje("contenido1", "asunto1", new Date(), "id_remitente1");
		m2 = new Mensaje("contenido2", "asunto2", new Date(), "id_remitente2");
		m3 = new Mensaje("contenido3", "asunto3", new Date(), "id_remitente3");
		
		ManejadorUsuario mu = ManejadorUsuario.getInstancia();
		mu.agregarProfesor(profe);
		profe.agregar_mensaje_nuevo(m1);
		profe.agregar_mensaje_nuevo(m2);
		profe.agregar_mensaje_nuevo(m3);
		
	}

	@Test
	public void test() {
		IControladorProfesor cp = new ControladorProfesor();
		DataListaMensajes dlm = cp.verMensajes(profe.getNick());
		assertEquals(3,dlm.getMensajes_nuevos().size());
		assertEquals(0,dlm.getMensajes_viejos().size());
		List<DataMensaje> nuevos = dlm.getMensajes_nuevos();
		
		assertEquals(m1.getContenido(),nuevos.get(0).getContenido());
		assertEquals(m1.getAsunto(),nuevos.get(0).getAsunto());
		assertEquals(m1.getFecha(),nuevos.get(0).getFecha());
		assertEquals(m1.getRemitente(),nuevos.get(0).getRemitente());
		
		assertEquals(m2.getContenido(),nuevos.get(1).getContenido());
		assertEquals(m2.getAsunto(),nuevos.get(1).getAsunto());
		assertEquals(m2.getFecha(),nuevos.get(1).getFecha());
		assertEquals(m2.getRemitente(),nuevos.get(1).getRemitente());
		
		assertEquals(m3.getContenido(),nuevos.get(2).getContenido());
		assertEquals(m3.getAsunto(),nuevos.get(2).getAsunto());
		assertEquals(m3.getFecha(),nuevos.get(2).getFecha());
		assertEquals(m3.getRemitente(),nuevos.get(2).getRemitente());
		
		profe.mensajeLeido(m1.getId());
		profe.mensajeLeido(m2.getId());
		profe.mensajeLeido(m3.getId());
		
		DataListaMensajes dlm2 = cp.verMensajes(profe.getNick());
		assertEquals(0,dlm2.getMensajes_nuevos().size());
		assertEquals(3,dlm2.getMensajes_viejos().size());
		
		List<DataMensaje> leidos = dlm.getMensajes_nuevos();
		
		assertEquals(m1.getContenido(),leidos.get(0).getContenido());
		assertEquals(m1.getAsunto(),leidos.get(0).getAsunto());
		assertEquals(m1.getFecha(),leidos.get(0).getFecha());
		assertEquals(m1.getRemitente(),leidos.get(0).getRemitente());
		
		assertEquals(m2.getContenido(),leidos.get(1).getContenido());
		assertEquals(m2.getAsunto(),leidos.get(1).getAsunto());
		assertEquals(m2.getFecha(),leidos.get(1).getFecha());
		assertEquals(m2.getRemitente(),leidos.get(1).getRemitente());
		
		assertEquals(m3.getContenido(),leidos.get(2).getContenido());
		assertEquals(m3.getAsunto(),leidos.get(2).getAsunto());
		assertEquals(m3.getFecha(),leidos.get(2).getFecha());
		assertEquals(m3.getRemitente(),leidos.get(2).getRemitente());
		
		
	}

}
