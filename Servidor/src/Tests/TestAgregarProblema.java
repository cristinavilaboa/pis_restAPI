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
	
	@Before
	public void setUp() throws Exception {	
		mundo = new Mundo("nombreMundo", "imagen", "descripcion", 1,mundos_siguientes,niveles);
		nivel = new Nivel(problemas, mundo);
		mundo.agregarNivel(nivel);
		profe = new Profesor("nick", "nombre", "password");
		ManejadorMundo mm = ManejadorMundo.getInstancia();
		mm.agregarMundo(mundo);
		
		ManejadorUsuario mu = ManejadorUsuario.getInstancia();
		mu.agregarProfesor(profe);
	}

	@Test
	public void test() {
		IControladorProblema cp = new ControladorProblema();
		cp.agregarProblema(0, "descripcion", "respuest", 1, "cont_ayuda", "cont",mundo.getId(),nivel.getNivel(), profe.getNick());
		
		ManejadorProblema mp = ManejadorProblema.getInstancia();
		
		assertEquals(1,mp.getProblemas().size());
		Problema p = mp.buscarProblema(0);
		assertEquals(0,p.getId());
		assertEquals("descripcion",p.getDescripcion());
		assertEquals("respuest",p.getRespuesta());
		assertEquals(1,p.getPuntos_exp());
		assertEquals("cont_ayuda",p.getAyuda().getInfo());
		assertEquals("cont",p.getContenido().getTexto());
		assertEquals(nivel.getNivel(),p.getNivel().getNivel());
		assertEquals(profe.getNick(),p.getAutor().getNick());
		
		
		
	}

}
