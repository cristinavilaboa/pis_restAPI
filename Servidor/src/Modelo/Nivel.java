package Modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Nivel {

	private int dificultad;
	private List<Problema> problemas = new ArrayList<Problema>();
	
	public int getDificultad() {
		return dificultad;
	}
	
	public void setDificultad(int dificultad) {
		this.dificultad = dificultad;
	}
	
	public List<Problema> getProblemas() {
		return problemas;
	}
	
	public void setProblemas(List<Problema> problemas) {
		this.problemas = problemas;
	}
	
	//METODOS A IMPLEMENTAR
	public boolean esUltima(int id_pregunta){//Retorna true si la pregunta es la ultima del nivel
		int it = 0;
		boolean encontre = false;
		int tama�o = problemas.size();
		
		while (it < tama�o ||  !encontre ){
			encontre = (problemas.get(it).getId() == id_pregunta);
			it++;
		}
		
		return (encontre && (it < tama�o));//Si it es menor que el tama�o es que queda alguna pregunta m�s
		//HAY QUE VER QUE PASA SI NO ENCUENTRA EL IDPREGUNTA
		
	} 
	
	
}
