package Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.objenesis.instantiator.sun.MagicInstantiator;

import Controladores.ControladorProfesor;
import Controladores.IControladorProfesor;
import Manejadores.ManejadorUsuario;
import Modelo.Clase;
import Modelo.EstadoJugador;
import Modelo.Jugador;
import Modelo.Profesor;

public class TestResponderMensaje {
	Profesor profe;
	Jugador j;
	EstadoJugador estado;
	Clase clase;
	
	@Before
	public void setUp() throws Exception {
		profe = new Profesor("nickProfe", "nombreProfe", "passwordProfe");
		estado = new EstadoJugador(0);
		clase = new Clase(0, "nombre", profe);
		j = new Jugador("nombre", "nick", "FBToken", "imagen", estado, clase);
		
		ManejadorUsuario mu = ManejadorUsuario.getInstancia();
		mu.agregarJugador(j);
		mu.agregarProfesor(profe);
	}

	@Test
	public void test() {
		IControladorProfesor cp = new ControladorProfesor();
		cp.responderMensaje(j.getNick(), "asunto", "contenido", profe.getNick());
		
		assertEquals(1,j.getMensajes_nuevos().size());
		assertEquals("asunto",j.getMensajes_nuevos().get(0).getAsunto());
		assertEquals("contenido",j.getMensajes_nuevos().get(0).getContenido());
		assertEquals(profe.getNick(),j.getMensajes_nuevos().get(0).getRemitente());
	}

}
