package Tests;

import static org.junit.Assert.*;

//import java.awt.List;

//import java.util.List;

import org.junit.*;

import Controladores.ControladorProblema;
import Controladores.IControladorProblema;
import Manejadores.ManejadorProblema;
import Modelo.Ayuda;
import Modelo.Contenido;
import Modelo.Nivel;
import Modelo.Problema;
import Modelo.Profesor;   


public class TestVerAyuda {

	@Test
	public void testGetAyuda() {
		ManejadorProblema manejador = ManejadorProblema.getInstancia();
		Ayuda ayuda = new Ayuda("La derivada es cuanto varia la funcion, cuando varia x");
		Contenido contenido = new Contenido("d(8x)/dx");
		Nivel nivel = new Nivel(null, null);
		Profesor profesor = new Profesor("Pedro", "pedro04", "1234");
		int id_problema = 1;
		Problema problema = new Problema(id_problema, "Resolver la siguiente derivada", "8", 1, ayuda, contenido, nivel, profesor);
		//nivel.setProblemas(problemas);
		manejador.agregarProblema(problema);
		IControladorProblema _cproblema = new ControladorProblema();
		assertEquals ("La derivada es cuanto varia la funcion, cuando varia x", _cproblema.getAyuda(id_problema).getAyuda());
		
		assertEquals ("second atempt", "La derivada es cuanto varia la funcion, cuando varia x", _cproblema.getAyuda(id_problema).getAyuda());
	}

}


