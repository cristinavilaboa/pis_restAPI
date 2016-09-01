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
		int tamaño = problemas.size();
		
		while (it < tamaño ||  !encontre ){
			encontre = (problemas.get(it).getId() == id_pregunta);
			it++;
		}
		
		return (encontre && (it < tamaño));//Si it es menor que el tamaño es que queda alguna pregunta más
		//HAY QUE VER QUE PASA SI NO ENCUENTRA EL IDPREGUNTA
		
	} 
	
	
}
