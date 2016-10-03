package Tests;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import Controladores.ControladorProblema;
import Controladores.IControladorProblema;
import Datatypes.DataTypeConstants;
import Manejadores.ManejadorProblema;
import Modelo.Mensaje;
import Modelo.Problema;
import Modelo.Profesor;
import Persistencia.CargarDatosBD;

public class TestSolicitarAyuda {
	Profesor profesor;
	Problema problema;

	@Before
	public void setUp() throws Exception {
		 CargarDatosBD.CargarTestSolicitarAyuda();
		 profesor = new Profesor("Juan","pepe","123");
		 problema= new Problema("problema1","resp",12,null,null,null,profesor);
		 ManejadorProblema mp=ManejadorProblema.getInstancia();
		 mp.agregarProblema(problema);
		
	}

	@Test
	public void test() {
		IControladorProblema cp= new ControladorProblema();
		@SuppressWarnings("deprecation")
		Date date =new Date(1,1,1);
		cp.enviarMensaje(1,"nick", "contenido", DataTypeConstants.getDateFormat().format(date), "asunto");
		Mensaje m=profesor.getMensajes_nuevos().get(0);
		
		assertEquals(m.getAsunto(),"asunto");
		assertEquals(m.getContenido(),"contenido");
		//assertEquals(m.getId(),1);
		assertEquals(m.getFecha(),date);
	}

}
