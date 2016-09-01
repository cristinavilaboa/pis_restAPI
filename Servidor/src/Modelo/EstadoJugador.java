package Modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class EstadoJugador {

	private int puntos_exp;
	private List<Mundo> mundos_completos = new ArrayList<Mundo>();
	private List<Logro> logros = new ArrayList<Logro>();
	private Map<Mundo, Nivel> mundo_nivel = new HashMap<Mundo, Nivel>(); //CONSULTAR
	private Map<Nivel, List<Problema>> nivel_problema = new HashMap<Nivel, List<Problema>>(); //CONSULTAR
	
	public int getPuntos_exp() {
		return puntos_exp;
	}
	
	public void setPuntos_exp(int puntos_exp) {
		this.puntos_exp = puntos_exp;
	}
	
	public List<Logro> getLogros() {
		return logros;
	}

	public void setLogros(List<Logro> logros) {
		this.logros = logros;
	}
	
	public Set<Mundo> getMundosActivos(){
		return mundo_nivel.keySet();
	}
	
	public Set<Nivel> getNivelesActivos(){
		return nivel_problema.keySet();
	}
	
	

	//METODOS A IMPLEMENTAR
	public void ganarExperiencia(int exp) {
		int total = exp + this.puntos_exp;
		setPuntos_exp(total);
	}
	
	public void agregarPregunta(Problema p){
		Nivel nivel_preg = p.getNivel();
		if(nivel_problema.containsKey(nivel_preg)){
			List<Problema> listaP = nivel_problema.get(nivel_preg);
			listaP.add(p);
			nivel_problema.put(nivel_preg, listaP);
		}else{
			List<Problema> nuevaLista = new ArrayList<Problema>();
			nuevaLista.add(p);
			nivel_problema.put(nivel_preg, nuevaLista);
		}
	}
	
	public void ganarLogro(Logro l){
		logros.add(l);
	}
	
	public void agregarMundoActivo(Mundo mundo){//Se agrega un nuevo mundo, en su primer nivel
		Nivel primer_nivel = mundo.getNiveles().get(0);
		mundo_nivel.put(mundo, primer_nivel);
	}
	
	public void agregarNivelActivo(Mundo mundo){//PRECONDICION: EL MUNDO PERTENECE A mundo_nivel
		Nivel nivel_actual = mundo_nivel.get(mundo);
		Nivel siguiente_nivel = mundo.siguienteNivel(nivel_actual);
		mundo_nivel.put(mundo, siguiente_nivel);
		
	}
	
		
}
