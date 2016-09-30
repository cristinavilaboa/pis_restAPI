package Tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import Controladores.ControladorProblema;
import Controladores.IControladorProblema;
import Manejadores.ManejadorMundo;
import Manejadores.ManejadorProblema;
import Manejadores.ManejadorUsuario;
import Modelo.*;

public class TestResponder {
	ArrayList<Mundo> mundos_siguientes = new ArrayList<Mundo>();
	ArrayList<Nivel> niveles = new ArrayList<Nivel>();
	ArrayList<Problema> listaP= new ArrayList<Problema>();
	ArrayList<Problema> listaP2= new ArrayList<Problema>();
	
	ArrayList<Mundo> mundos_completos = new ArrayList<Mundo>();
	ArrayList<Logro> logros = new ArrayList<Logro>();
	Map<Integer, Nivel> mundo_nivel = new HashMap<Integer, Nivel>();
	List<Problema> nivel_problema = new ArrayList<Problema>();
	
	IControladorProblema cp = new ControladorProblema();
	ManejadorProblema mp = ManejadorProblema.getInstancia();
	ManejadorUsuario mu = ManejadorUsuario.getInstancia();
	ManejadorMundo mm = ManejadorMundo.getInstancia();
	
	Mundo mundo;
	Nivel nivel;
	Nivel nivel2;
	Problema problema;
	Problema problema2;
	Jugador jugador;
	EstadoJugador estado;
	
	Clase clase = new Clase();
	@Before
	public void setUp() throws Exception {
		estado = new EstadoJugador(0, mundos_completos, logros, mundo_nivel, nivel_problema);
		jugador = new Jugador("ni", "nick", "fBToken", "imagen", estado, clase);

		mundo = new Mundo("Jupiter", "imagen", "descripcion",0,mundos_siguientes, niveles);
		nivel = new Nivel(listaP,mundo);
		
		nivel2 = new Nivel(listaP2,mundo);
		
		mundo.agregarNivel(nivel);
		mundo.agregarNivel(nivel2);
		
		problema = new Problema(1,"Problema 1","respuesta",10,null,null,nivel,null);
		nivel.agregarProblema(problema);
		
		problema2 = new Problema(2,"Problema 2","respuesta",10,null,null,nivel2,null);
		nivel2.agregarProblema(problema2);
		
		mm.agregarMundo(mundo);
		mu.agregarJugador(jugador);
		mp.agregarProblema(problema);
		mp.agregarProblema(problema2);
	}
	

	@Test
	public void testManejadorProblema(){
		
		assertEquals(problema,mp.buscarProblema(1));
		
		assertTrue(mp.ultimaNivel(1));
		assertTrue(mp.ultimaNivel(2));
		
		assertEquals(10,mp.verificarRespuesta(1, "respuesta"));
		
		mu.borrar();
		mp.borrar();
		mm.borrar();
		
	}

	@Test
	public void testEstado(){
		estado.ganarExperiencia(10);
		assertEquals(10, estado.getPuntos_exp());
		
		assertEquals(0,estado.cantCorrectas());
		
		estado.agregarProblema(problema);
		assertEquals(problema,estado.getProblemas_resueltos().get(0));
		
		estado.agregarMundoActivo(mundo);
		assertTrue(estado.getNiveles_actuales().containsKey(mundo.getId()));
		
		estado.agregarNivelActivo(mundo);
		assertEquals(nivel2,estado.getNiveles_actuales().get(mundo.getId()));//Si respondi el primer nivel bien, mi niviel actual es el 2do
		
		mu.borrar();
		mp.borrar();
		mm.borrar();
		
	}
	
	@Test
	public void testGeneral() {
		cp.responderProblema(1, "respue22sta", "nick");
		assertEquals(0,estado.getPuntos_exp());
		assertEquals(0,estado.cantCorrectas());
		assertEquals(0,estado.getLogros().size());
		
		//Estado es = mu.buscarJugador("nick").getEstado();
		
		cp.responderProblema(1, "respuesta", "nick");
		assertEquals(10,estado.getPuntos_exp());
		assertEquals(1,estado.cantCorrectas());
		assertEquals(1,estado.getLogros().size());//PRIMERA RESPUESTA
		assertEquals(estado.getNiveles_actuales().get(mundo.getId()),nivel2);
		assertTrue(estado.getMundos_completos().isEmpty());
		
		cp.responderProblema(1, "respuesta", "nick");
		assertEquals(10,estado.getPuntos_exp());
		assertEquals(1,estado.cantCorrectas());
		assertEquals(1,estado.getLogros().size());//PRIMERA RESPUESTA
		assertEquals(estado.getNiveles_actuales().get(mundo.getId()),nivel2);
		assertTrue(estado.getMundos_completos().isEmpty());
		
		cp.responderProblema(2, "respuesta", "nick");
		assertEquals(20,estado.getPuntos_exp());
		assertEquals(2,estado.cantCorrectas());
		assertEquals(2,estado.getLogros().size());//PRIMERA RESPUESTA Y MUNDO COMPLETO
		assertEquals(estado.getNiveles_actuales().get(mundo.getId()),nivel2);
		assertEquals(mundo,estado.getMundos_completos().get(0));
		
		mu.borrar();
		mp.borrar();
		mm.borrar();
		
	}


}
