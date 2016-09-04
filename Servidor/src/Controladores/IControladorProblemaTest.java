package Controladores;

import static org.junit.Assert.*;

//import java.awt.List;

//import java.util.List;

import org.junit.*;

import Manejadores.ManejadorProblema;
import Modelo.Ayuda;
import Modelo.Contenido;
import Modelo.Nivel;
import Modelo.Problema;
import Modelo.Profesor;   


public class IControladorProblemaTest {

	@Test
	public void testGetAyuda() {
		ManejadorProblema manejador = ManejadorProblema.getInstancia();
		Ayuda ayuda = new Ayuda("La derivada es cuanto varia la funcion, cuando varia x");
		Contenido contenido = new Contenido("d(8x)/dx");
		Nivel nivel = new Nivel(1);
		Profesor profesor = new Profesor("Pedro", "pedro04", "1234");
		int id_pregunta = 1;
		Problema problema = new Problema(id_pregunta, "Resolver la siguiente derivada", "8", 1, ayuda, contenido, nivel, profesor);
		//nivel.setProblemas(problemas);
		manejador.agregarProblema(problema);
		assertEquals ("La derivada es cuanto varia la funcion, cuando varia x", manejador.getAyuda(id_pregunta));
		
		assertEquals ("second atempt", "La derivada es cuanto varia la funcion, cuando varia x", manejador.getAyuda(id_pregunta));
	}

}


