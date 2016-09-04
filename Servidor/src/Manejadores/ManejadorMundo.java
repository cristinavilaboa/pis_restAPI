package Manejadores;

import java.util.ArrayList;
import java.util.List;

import Modelo.Mundo;
import Modelo.Nivel;

public class ManejadorMundo {
	
	private static ManejadorMundo instancia = new ManejadorMundo();
	private List<Mundo> mundos = new ArrayList<Mundo>();
	
	private ManejadorMundo(){};
	
	public static ManejadorMundo getInstancia(){
		return instancia;
	}
	
	public Mundo obtenerMundo(int id_mundo){
		for(Mundo m: mundos){
			if(m.getId() == id_mundo){
				return m;
			}
		}
		return null;//NO TENDRIA PORQUE ENTRAR ACA, SE SUPONE QUE NO TE PASAN UN ID INVENTADO
	}
	
	public void agregarMundo(Mundo m){
		mundos.add(m);
	}

}
