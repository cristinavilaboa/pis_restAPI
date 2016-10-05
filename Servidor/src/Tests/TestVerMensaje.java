package Tests;

import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import Manejadores.ManejadorMundo;
import Manejadores.ManejadorUsuario;
import Modelo.Mensaje;
import Modelo.Profesor;

public class TestVerMensaje {
	Profesor profe;
	Mensaje m;
	ManejadorUsuario mu;
	
	@Before
	public void setUp() throws Exception {
		mu = ManejadorUsuario.getInstancia();
		
		mu.borrar();
		
		profe = new Profesor("nick", "nombre", "password");
		m = new Mensaje("contenido1", "asunto1", new Date(), "id_remitente1");
		
		profe.agregar_mensaje_nuevo(m);
		mu.agregarProfesor(profe);
		
	}
	
	@Test
	public void test() {
		mu = ManejadorUsuario.getInstancia();
		//HAY QUE FIJARSE EN LA BASE UN ID VALIDO PARA PODER OBTENER EL MENSAJE. 
		Mensaje mensaje = mu.buscarMensaje(94);
		
		assertEquals("contenido1",mensaje.getContenido());
		assertEquals("asunto1",mensaje.getAsunto());
		assertEquals("id_remitente1",mensaje.getRemitente());
		
	}
	
}
