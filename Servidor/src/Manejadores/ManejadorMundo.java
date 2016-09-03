package Manejadores;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Modelo.Mundo;
import Modelo.Nivel;
import Modelo.Problema;

public class ManejadorMundo {
	
	private static ManejadorMundo instancia = new ManejadorMundo();
	private  Map<Integer,Mundo> mundos= new HashMap<Integer,Mundo>();
	
	private ManejadorMundo(){};
	
	public static ManejadorMundo getInstancia(){
		return instancia;
	}
	
	//METODOS A IMPLEMENTAR
	public int ultimoNivelMundo(Nivel n){return 0;}
}
