package Tests;

import static org.junit.Assert.*;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;

import Manejadores.ManejadorMundo;
import Manejadores.ManejadorProblema;
import Manejadores.ManejadorUsuario;
import Modelo.Mensaje;
import Modelo.Problema;
import Modelo.Profesor;
import Persistencia.HibernateUtility;

public class TestSolicitarAyuda {
	Profesor profesor;
	Problema problema;

	@Before
	public void setUp() throws Exception {
		ManejadorUsuario mu=ManejadorUsuario.getInstancia();
		mu.borrar();
		ManejadorMundo mm=ManejadorMundo.getInstancia();
		mm.borrar();
		profesor = new Profesor("Juan","nickJuan","123");
		mu.agregarProfesor(profesor);
		problema= new Problema("problema1","resp",12,null,null,null,profesor);
		ManejadorProblema mp=ManejadorProblema.getInstancia();
		mp.agregarProblema(problema);
		System.out.println("successfully saved datos Test Solicitar Ayuda");
	}

	@Test
	public void test() {
		@SuppressWarnings("deprecation")
		Date date =new Date(1,1,1);
		ManejadorProblema mp=ManejadorProblema.getInstancia();
		Problema p=mp.buscarProblema(problema.getId());
		p.enviarMensaje("contenido",date,"asunto","nickJuan");
		ManejadorUsuario mu = ManejadorUsuario.getInstancia();
		profesor = mu.buscarProfesor("nickJuan");
		Mensaje m =profesor.getMensajes_nuevos().get(0);
		assertEquals(m.getAsunto(),"asunto");
		assertEquals(m.getContenido(),"contenido");
		Date dateBD = new Date(m.getFecha().getTime());
		assertEquals(dateBD,date);
		
		mu.borrar();
		ManejadorMundo mm=ManejadorMundo.getInstancia();
		mm.borrar();
	}

}
