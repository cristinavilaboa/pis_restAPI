package Manejadores;

import java.util.List;

import Modelo.Mundo;
import Modelo.Nivel;

public class ManejadorMundo {
	
	private static ManejadorMundo instancia = new ManejadorMundo();
	private List<Mundo> mundos;
	
	private ManejadorMundo(){};
	
	public static ManejadorMundo getInstancia(){
		return instancia;
	}
	
	//METODOS A IMPLEMENTAR
	public int ultimoNivelMundo(Nivel n){return 0;}
}
