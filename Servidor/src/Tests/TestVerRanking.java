package Tests;

import Modelo.EstadoJugador;
import Modelo.Profesor;
import org.junit.Test;
import Modelo.Clase;
import Modelo.Jugador;
import static org.junit.Assert.*;
import java.util.List;

import Manejadores.ManejadorUsuario;
import Controladores.ControladorJugador;
import Controladores.IControladorJugador;
import Datatypes.DataPuntosJugador;

import org.junit.Before;

public class TestVerRanking {
	
	@Before
	public void setUp() throws Exception {
		
		Profesor Juliana = new Profesor("Juli", "Juliana", "123456");
		Clase clase1 = new Clase(1, "clase1", Juliana);
		
		EstadoJugador estadoFufi = new EstadoJugador(12);
        Jugador j = new Jugador("fufi", "Estefania", "estefaniaD", "imagenA", estadoFufi, clase1);
        
    	ManejadorUsuario manUs = ManejadorUsuario.getInstancia();
        manUs.agregarJugador(j);
        
        EstadoJugador estadoCaro = new EstadoJugador(33);
        j = new Jugador("Caro", "Carolina", "carolinaC", "imagenB", estadoCaro, clase1);
		
        manUs.agregarJugador(j);
        
        EstadoJugador estadoPau = new EstadoJugador(2);
        j = new Jugador("Pau", "Paula", "paulaM", "imagenC", estadoPau, clase1);

        manUs.agregarJugador(j);
        
        EstadoJugador estadoRodri = new EstadoJugador(2);
        j = new Jugador("Rodri", "Rodrigo", "rodrigoH", "imagenD", estadoRodri, clase1);

        manUs.agregarJugador(j);
        
        EstadoJugador estadoRau = new EstadoJugador(28);
        j = new Jugador("Rau", "Raul", "raulF", "imagenE", estadoRau, clase1);

        manUs.agregarJugador(j);
        
	}
	
	@Test	
	public void test() {
		IControladorJugador conUs = new ControladorJugador();
		
		List<DataPuntosJugador> resultado = conUs.obtenerRanking();
		
		DataPuntosJugador[] array_res = resultado.toArray(new DataPuntosJugador[resultado.size()]);

		assertEquals(array_res[0].getNombre(),"Carolina");
		assertEquals(Integer.toString(array_res[0].getPuntos()),Integer.toString(33));
		assertEquals(array_res[1].getNombre(),"Raul");
		assertEquals(Integer.toString(array_res[1].getPuntos()),Integer.toString(28));
		assertEquals(array_res[2].getNombre(),"Estefania");
		assertEquals(Integer.toString(array_res[2].getPuntos()),Integer.toString(12));
		assertEquals(array_res[3].getNombre(),"Paula");
		assertEquals(Integer.toString(array_res[3].getPuntos()),Integer.toString(2));
		assertEquals(array_res[4].getNombre(),"Rodrigo");
		assertEquals(Integer.toString(array_res[4].getPuntos()),Integer.toString(2));
		
	}
	
}

