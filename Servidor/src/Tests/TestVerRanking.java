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
		ManejadorUsuario manUs = ManejadorUsuario.getInstancia();
        
		manUs.borrar();
		
		
		Profesor Juliana = new Profesor("Juli", "Juliana", "123456");
		Clase clase1 = new Clase("clase1", Juliana);
		
		manUs.agregarProfesor(Juliana);
	    manUs.agregarClase(clase1);
		
		EstadoJugador estadoFufi = new EstadoJugador(12);
        Jugador j = new Jugador("Estefania", "fuffi", "estefaniaD", "imagenA", estadoFufi, clase1);
        
        manUs.guardarEstado(estadoFufi);
    	manUs.agregarJugador(j);
        
        EstadoJugador estadoCaro = new EstadoJugador(33);
        j = new Jugador("Carolina", "Caro", "carolinaC", "imagenB", estadoCaro, clase1);
		
        manUs.guardarEstado(estadoCaro);
        manUs.agregarJugador(j);
        
        EstadoJugador estadoPau = new EstadoJugador(2);
        j = new Jugador("Paula", "Pau", "paulaM", "imagenC", estadoPau, clase1);

        manUs.guardarEstado(estadoPau);
        manUs.agregarJugador(j);
        
        EstadoJugador estadoRodri = new EstadoJugador(2);
        j = new Jugador("Rodrigo", "Rodri", "rodrigoH", "imagenD", estadoRodri, clase1);

        manUs.guardarEstado(estadoRodri);
        manUs.agregarJugador(j);
        
        EstadoJugador estadoRau = new EstadoJugador(28);
        j = new Jugador("Raul", "Rau", "raulF", "imagenE", estadoRau, clase1);

        manUs.guardarEstado(estadoRau);
        manUs.agregarJugador(j);
        
	}
	
	@Test	
	public void test() {
		
		ManejadorUsuario manUs = ManejadorUsuario.getInstancia();
		List<DataPuntosJugador> resultado = manUs.obtenerRanking();
		
		//DataPuntosJugador[] array_res = resultado.toArray(new DataPuntosJugador[resultado.size()]);
		
		assertEquals(resultado.get(0).getNombre(),"Carolina");
		assertTrue(resultado.get(0).getPuntos() == 33);
		
		assertEquals(resultado.get(1).getNombre(),"Raul");
		assertTrue(resultado.get(1).getPuntos() == 28);
		
		assertEquals(resultado.get(2).getNombre(),"Estefania");
		assertTrue(resultado.get(2).getPuntos() == 12);
		
		assertEquals(resultado.get(3).getNombre(),"Paula");
		assertTrue(resultado.get(3).getPuntos() == 2);
		
		assertEquals(resultado.get(4).getNombre(),"Rodrigo");
		assertTrue(resultado.get(4).getPuntos() == 2);
		
	}
	
}

