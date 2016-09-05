package TestResponderPregunta;

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
	ArrayList<Mundo> mundos_completos = new ArrayList<Mundo>();
	ArrayList<Logro> logros = new ArrayList<Logro>();
	Map<Mundo, Nivel> mundo_nivel = new HashMap<Mundo, Nivel>();
	Map<Nivel, List<Problema>> nivel_problema = new HashMap<Nivel, List<Problema>>();
	
	IControladorProblema cp = new ControladorProblema();
	ManejadorProblema mp = ManejadorProblema.getInstancia();
	ManejadorUsuario mu = ManejadorUsuario.getInstancia();
	ManejadorMundo mm = ManejadorMundo.getInstancia();
	
	Mundo mundo;
	Nivel nivel;
	Problema problema;
	Jugador jugador;
	EstadoJugador estado;
	
	Clase clase = new Clase();
	@Before
	public void setUp() throws Exception {
		estado = new EstadoJugador(0, mundos_completos, logros, mundo_nivel, nivel_problema);
		jugador = new Jugador("n", "nick", "fBToken", "imagen", estado, clase);

		mundo = new Mundo(1, "Jupiter", "imagen", "descripcion",0,mundos_siguientes, niveles);
		nivel = new Nivel(0,listaP,mundo);
		mundo.agregarNivel(nivel);
		problema = new Problema(1,"Problema 1","respuesta",10,null,null,nivel,null);
		nivel.agregarProblema(problema);
		
		mm.agregarMundo(mundo);
		mu.agregarJugador(jugador);
		mp.agregarProblema(problema);
	}

	@Test
	public void test() {
		cp.responderPregunta(1, "respue22sta", "nick");
		assertEquals(0,estado.getPuntos_exp());
		assertEquals(0,estado.cantCorrectas());
		assertEquals(0,estado.getLogros().size());
		
		cp.responderPregunta(1, "respuesta", "nick");
		assertEquals(10,estado.getPuntos_exp());
		assertEquals(1,estado.cantCorrectas());
		assertEquals(2,estado.getLogros().size());//DOS LOGROS UNO POR PRIMERA RESPUESTA OTRO POR MUNDO COMPLETO
		assertEquals(estado.getMundo_nivel().get(mundo),nivel);
		assertEquals(estado.getMundos_completos().get(0),mundo);
		
		cp.responderPregunta(1, "respuesta", "nick");
		assertEquals(10,estado.getPuntos_exp());
		assertEquals(1,estado.cantCorrectas());
		assertEquals(2,estado.getLogros().size());//DOS LOGROS UNO POR PRIMERA RESPUESTA OTRO POR MUNDO COMPLETO
		assertEquals(estado.getMundo_nivel().get(mundo),nivel);
		assertEquals(estado.getMundos_completos().get(0),mundo);
	}

}
