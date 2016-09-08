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
	
	public EstadoJugador(int puntos_exp){
		this.puntos_exp = puntos_exp;
	}
	
	public EstadoJugador(int puntos_exp, List<Mundo> mundos_completos, List<Logro> logros,
			Map<Mundo, Nivel> mundo_nivel, Map<Nivel, List<Problema>> nivel_problema) {
		super();
		this.puntos_exp = puntos_exp;
		this.mundos_completos = mundos_completos;
		this.logros = logros;
		this.mundo_nivel = mundo_nivel;
		this.nivel_problema = nivel_problema;
	}

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
	

	public List<Mundo> getMundos_completos() {
		return mundos_completos;
	}

	public void setMundos_completos(List<Mundo> mundos_completos) {
		this.mundos_completos = mundos_completos;
	}

	public Map<Mundo, Nivel> getMundo_nivel() {
		return mundo_nivel;
	}

	public void setMundo_nivel(Map<Mundo, Nivel> mundo_nivel) {
		this.mundo_nivel = mundo_nivel;
	}

	public Map<Nivel, List<Problema>> getNivel_problema() {
		return nivel_problema;
	}

	public void setNivel_problema(Map<Nivel, List<Problema>> nivel_problema) {
		this.nivel_problema = nivel_problema;
	}
	

	//METODOS A IMPLEMENTAR
	public void ganarExperiencia(int exp) {
		int total = exp + this.puntos_exp;
		setPuntos_exp(total);
	}
	
	public void agregarPregunta(Problema p){
		Nivel nivel_preg = p.getNivel();
		if(nivel_problema.containsKey(nivel_preg)){//Si ya respondi algun problema de ese nivel
			List<Problema> listaP = nivel_problema.get(nivel_preg);
			if(!listaP.contains(p)){//Si no se respondio antes
				listaP.add(p);
				nivel_problema.put(nivel_preg, listaP);
			}
		}else{//Si no respondi ningun problema
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
	
	public ArrayList<Logro> nuevosLogros(){
		ArrayList<Logro> nuevos_logros = new ArrayList<Logro>();
		int cant_correctas = cantCorrectas();
		if(cant_correctas == 1){
			Logro primeraRespuesta = new Logro("Primera respuesta correcta");
			nuevos_logros.add(primeraRespuesta);
		}
		if(cant_correctas % 5 == 0){
			Logro logro = new Logro("Has logrado "+cant_correctas+"pregutas correctas");
			nuevos_logros.add(logro);
		}
		return nuevos_logros;
	}
	
	public int cantCorrectas(){
		int cant = 0;
		for(List<Problema> n: nivel_problema.values()){
			cant += n.size();
		}
		return cant;
	}
	
		
}
