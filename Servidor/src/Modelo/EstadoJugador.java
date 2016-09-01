package Modelo;
//Estado
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class EstadoJugador {

	private int puntos_exp;
	private List<Logro> logros;
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
		Nivel nivelPreg = p.getNivel();
		List<Problema> listaP = nivel_problema.get(nivelPreg);
		listaP.add(p);
		nivel_problema.put(nivelPreg, listaP);
	}
	
	public void ganarLogro(Logro l){
		logros.add(l);
	}
	
	public void agregarMundoActivo(){}
	
	public void agregarNivelActivo(){}
	
		
}
