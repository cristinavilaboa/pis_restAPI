package Modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.MapKey;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name = "ESTADO")
public class EstadoJugador {
	@Id
	private int id;
	private int puntos_exp;
	@ManyToMany
	private List<Mundo> mundos_completos = new ArrayList<Mundo>();
	@OneToMany
	private List<Logro> logros = new ArrayList<Logro>();
	@ManyToMany
	@MapKeyJoinColumn(name="id")
	private Map<Mundo, Nivel> mundo_nivel = new HashMap<Mundo, Nivel>(); //CONSULTAR
	@ManyToMany
	@MapKeyJoinColumn(name="id")
	private Map<Nivel,ListaDeNivel> nivel_problema = new HashMap<Nivel, ListaDeNivel>(); //CONSULTAR
	
	public EstadoJugador(int puntos_exp){
		this.puntos_exp = puntos_exp;
	}
	
	public EstadoJugador(int puntos_exp, List<Mundo> mundos_completos, List<Logro> logros,
			Map<Mundo, Nivel> mundo_nivel, Map<Nivel, List<Problema>> nivel_problema) {
		super();
		this.puntos_exp = puntos_exp;
		this.mundos_completos = mundos_completos;
		this.logros = logros;
		//this.mundo_nivel = mundo_nivel;
		//this.nivel_problema = nivel_problema;
	}
	
	public EstadoJugador(){}

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
		
		Map<Nivel, List<Problema>> mapa = new HashMap<Nivel, List<Problema>>();
		Set<Nivel> llaves=nivel_problema.keySet();
		for (Nivel n: llaves){
			ListaDeNivel ln=nivel_problema.get(n);
			mapa.put(n,ln.getProblemas());
			
		}	
		return mapa;
	}

	public void setNivel_problema(Map<Nivel, List<Problema>> nivel_problema) {
		Map<Nivel,ListaDeNivel> mapa = new HashMap<Nivel, ListaDeNivel>();
		
		Set<Nivel> llaves=nivel_problema.keySet();
		
		for (Nivel n: llaves){
			List<Problema> ln=nivel_problema.get(n);
			ListaDeNivel lista = new ListaDeNivel();
			lista.setProblemas(ln);
			mapa.put(n,lista);
		}	
		this.nivel_problema=mapa;
	}
	

	//METODOS A IMPLEMENTAR
	public void ganarExperiencia(int exp) {
		int total = exp + this.puntos_exp;
		setPuntos_exp(total);
	}
	
	public void agregarPregunta(Problema p){
		Nivel nivel_preg = p.getNivel();
		if(nivel_problema.containsKey(nivel_preg)){//Si ya respondi algun problema de ese nivel
			ListaDeNivel lista_nivel = this.nivel_problema.get(nivel_preg);
			List<Problema> listaP = nivel_problema.get(nivel_preg).getProblemas();
			if(!listaP.contains(p)){//Si no se respondio antes
				lista_nivel.agregarProblema(p);
			}
		}else{//Si no respondi ningun problema
			ListaDeNivel lista_nivel = new ListaDeNivel();
			List<Problema> nuevaLista = new ArrayList<Problema>();
			nuevaLista.add(p);
			lista_nivel.setProblemas(nuevaLista);
			this.nivel_problema.put(nivel_preg, lista_nivel);
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
		for(ListaDeNivel n: nivel_problema.values()){
			cant += n.getProblemas().size();
		}
		return cant;
	}

		
}
