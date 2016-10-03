package Tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import Controladores.ControladorProblema;
import Controladores.IControladorProblema;
import Manejadores.ManejadorMundo;
import Manejadores.ManejadorProblema;
import Manejadores.ManejadorUsuario;
import Modelo.*;

public class TestAgregarProblema {
	Ayuda ayuda;
	Contenido contenido;
	Mundo mundo;
	Nivel nivel;
	Profesor profe;
	List<Mundo> mundos_siguientes = new ArrayList<Mundo>();
	List<Nivel> niveles = new ArrayList<Nivel>();
	List<Problema> problemas = new ArrayList<Problema>();
	ManejadorMundo mm = ManejadorMundo.getInstancia();
	@Before
	public void setUp() throws Exception {	
		mundo = new Mundo(1,"nombreMundo", "imagen", "descripcion", 1,mundos_siguientes,niveles);
		nivel = new Nivel(problemas, mundo);
		mundo.agregarNivel(nivel);
		profe = new Profesor("nick", "nombre", "password");
		mm.agregarMundo(mundo);
		
		ManejadorUsuario mu = ManejadorUsuario.getInstancia();
		mu.agregarProfesor(profe);
	}

	@Test
	public void test() {
		IControladorProblema cp = new ControladorProblema();
		cp.agregarProblema( "descripcion", "respuest", 1, "cont_ayuda", "cont",mundo.getId(),nivel.getNro_nivel(), profe.getNick());
		
		ManejadorProblema mp = ManejadorProblema.getInstancia();
		
		assertEquals(1,mp.getProblemas().size());
		Problema p = mp.buscarProblema(0);
		assertEquals(0,p.getId());
		assertEquals("descripcion",p.getDescripcion());
		assertEquals("respuest",p.getRespuesta());
		assertEquals(1,p.getPuntos_exp());
		assertEquals("cont_ayuda",p.getAyuda().getInfo());
		assertEquals("cont",p.getContenido().getTexto());
		assertEquals(nivel.getNro_nivel(),p.getNivel().getNro_nivel());
		assertEquals(profe.getNick(),p.getAutor().getNick());
		
		mm.borrar();
		
	}

}
