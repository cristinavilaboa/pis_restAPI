package Tests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import Datatypes.DataJugador;
import Modelo.Clase;
import Modelo.EstadoJugador;
import Modelo.Jugador;
import Modelo.Logro;
import Modelo.Mundo;
import Modelo.Nivel;

import static org.junit.Assert.*;

public class TestVerPerfil {
	
	@Test
	public void testVerPerfil(){
		//CREO LOS LOGROS PARA EL JUGADOR
		Logro l = new Logro();
		l.setId(1);
		l.setDescripcion("genio");
		Logro l2 = new Logro();
		l2.setId(3);
		l2.setDescripcion("capo");
		List<Logro> logros = new ArrayList<Logro> ();
		logros.add(l);
		logros.add(l2);
		
		//CREO LOS MUNDOS Y NIVELES
		Mundo m = new Mundo();
		m.setDescripcion("mundo de prueba");
		m.setId(1);
		m.setImagen("ruta de imagen");
		//m.setMundos_siguientes(new HashMap());
		//m.setNiveles([]);
		m.setNombre("Integrales");
		m.setPuntos_exp(8); //puntos de exp del mundo??????
		
		Mundo m2 = new Mundo();
		m2.setDescripcion("mundo de prueba");
		m2.setId(1);
		m2.setImagen("ruta de imagen");
		//m2.setMundos_siguientes([]);
		//m2.setNiveles([]);
		m2.setNombre("Integrales");
		m2.setPuntos_exp(8);
		
		Nivel n = new Nivel();
		n.setDificultad(5);
		//n.setProblemas([]);
		
		Nivel n2 = new Nivel();
		n2.setDificultad(5);
		//n2.setProblemas([]);
		
		Map<Mundo,Nivel> m_n = new HashMap<Mundo,Nivel>();
		m_n.put(m, n);
		m_n.put(m2, n2);
		
		//CREO EL ESTADO JUGADOR		
		EstadoJugador ej = new EstadoJugador();
		ej.setPuntos_exp(100);
		ej.setLogros(logros);
		ej.setMundo_nivel(m_n);
		
		//CREO LA CLASE DEL JUGADOR
		Clase c = new Clase();
		c.setId(5);
		c.setNombre("Cálculo"); //me falta el profesor pero no creo que de problema.

		//CREO EL JUGADOR
		Jugador j = new Jugador("Juan","juancito", "fb", "imagen", ej, c);
		
		DataJugador dj = j.obtenerDataJugador();
		assertEquals(100,dj.getExperiencia());
		assertEquals("imagen",dj.getimagen());
		assertEquals("juancito",dj.getNick());
		assertEquals(ej.getLogros().get(0).getDescripcion(),dj.getLogros().get(0).getDesc());
		assertEquals(ej.getLogros().get(1).getId(),dj.getLogros().get(1).getCant());
		//assertEquals(ej.getMundo_nivel().,dj.getMundosNiveles());
	}

}
