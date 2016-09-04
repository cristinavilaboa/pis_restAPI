package Modelo;

import java.util.List;

public class Nivel {

	private int dificultad;
	private List<Problema> problemas;
	
	public Nivel(int dificultad){
		this.dificultad = dificultad;
	}
	
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
	public boolean esUltima(int id_pregunta){return false;} 
}
