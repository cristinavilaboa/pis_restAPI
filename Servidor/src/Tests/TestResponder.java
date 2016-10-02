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
	Contenido contenidoP1;
	Contenido contenidoP2;
	Problema problema;
	Problema problema2;
	Jugador jugador;
	Ayuda ayudaP1;
	Ayuda ayudaP2;
	EstadoJugador estado;
	Profesor profesor;
	Clase clase;
	
	@Before
	public void setUp() throws Exception {
		
		
		profesor = new Profesor("NickProfesorUnico", "Profesor", "Contrarofesor");
		clase = new Clase("Calculo 1", profesor);
		
		estado = new EstadoJugador(0, mundos_completos, logros, mundo_nivel, nivel_problema);
		jugador = new Jugador("nombre", "nick", "fBToken", "imagen", estado, clase);
		
		//Creaer el mundo en cascada...
		
		ayudaP1 = new Ayuda("Ayuda para el problema 1");
		ayudaP2 = new Ayuda("Ayuda para el problema 2");
		
		contenidoP1 = new Contenido("Este texto explica el contenido del problema 1");
		contenidoP2 = new Contenido("Este texto explica el contenido del problema 2");
		
		problema = new Problema(1,"Problema 1","respuesta",10, ayudaP1,contenidoP1,null,profesor); // el nivel todavia no se creeo 
		problema2 = new Problema(2,"Problema 2","respuesta",10,ayudaP1,contenidoP2,null,profesor);
		
		listaP.add(problema);
		listaP2.add(problema2);
		
		nivel = new Nivel(listaP,null);		//el mundo todavia no fue creado
		nivel2 = new Nivel(listaP2,null);
		
		problema.setNivel(nivel);   // Ahora si ya se creeo el nivel...
		problema2.setNivel(nivel2);
		
		niveles.add(nivel);
		niveles.add(nivel2);
		
		mundo = new Mundo("Jupiter", "imagen", "descripcion",0,mundos_siguientes, niveles);
		
		nivel.setMundo(mundo);		// ahora si ya se creeo el mundo...
		nivel2.setMundo(mundo);
		
		mu.agregarProfesor(profesor);
		mu.agregarClase(clase);
		mu.agregarJugador(jugador);
		mm.agregarMundo(mundo);
		//mp.agregarProblema(problema);
		//mp.agregarProblema(problema2);
	}
	

/*	@Test
	public void testManejadorProblema(){
		
		assertEquals(problema,mp.buscarProblema(1));
		
		assertTrue(mp.ultimaNivel(1));
		assertTrue(mp.ultimaNivel(2));
		
		assertEquals(10,mp.verificarRespuesta(1, "respuesta"));
		
		mu.borrar();
//		mp.borrar();
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
		
	}*/
	
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
