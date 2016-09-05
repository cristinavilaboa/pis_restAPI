package Tests;

import Modelo.EstadoJugador;
import Modelo.Profesor;
import org.junit.Test;
import Modelo.Clase;
import Modelo.Jugador;
public class TestVerRanking {
	private String info;

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}	
	
	@Test	
	public void testObtenerRanking() {
		System.out.println("VerRanking");
		
		Profesor Juliana = new Profesor("Juli", "Juliana", "123456");
		Clase clase1 = new Clase(1, "clase1", Juliana);
		
		EstadoJugador estadoFufi = new EstadoJugador(12);
        Jugador j = new Jugador("fufi", "Estefania", "estefaniaD", "imagenA", estadoFufi, clase1);
        
        EstadoJugador estadoCaro = new EstadoJugador(33);
        j = new Jugador("Caro", "Carolina", "carolinaC", "imagenB", estadoCaro, clase1);
		
        EstadoJugador estadoPau = new EstadoJugador(2);
        j = new Jugador("Pau", "Paula", "paulaM", "imagenC", estadoPau, clase1);  
	
        
        
	}
	
}
