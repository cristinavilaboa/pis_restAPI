package Modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name = "ESTADO")
public class EstadoJugador {
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int id_estado_jugador;
	private int puntos_exp;
	@ManyToMany(cascade=CascadeType.ALL)
	private List<Mundo> mundos_completos = new ArrayList<Mundo>();
	@OneToMany(cascade=CascadeType.ALL)
	private List<Logro> logros = new ArrayList<Logro>();
	@ManyToMany
	@MapKeyColumn(name="id_mundo")
	private Map<Integer, Nivel> niveles_actuales = new HashMap<Integer, Nivel>(); //El int es el id de un mundo,
																	//y el map representa el nivel actual en cada mundo
	
	@ManyToMany
	private List<Problema> problemas_resueltos = new ArrayList<Problema>();//Lista de problemas resueltos
	
	
	
	public EstadoJugador(int puntos_exp){
		this.puntos_exp = puntos_exp;
	}
	
	public EstadoJugador(int puntos_exp, List<Mundo> mundos_completos, List<Logro> logros,
			Map<Integer, Nivel> niveles_actuales, List<Problema> problemas_resueltos) {
		this.puntos_exp = puntos_exp;
		this.mundos_completos = mundos_completos;
		this.logros = logros;
		this.niveles_actuales = niveles_actuales;
		this.problemas_resueltos = problemas_resueltos;
	}
	
	public EstadoJugador(){
		
	}

	public int getId_estado_jugador() {
		return id_estado_jugador;
	}

	public void setId_estado_jugador(int id_estado_jugador) {
		this.id_estado_jugador = id_estado_jugador;
	}

	public int getPuntos_exp() {
		return puntos_exp;
	}

	public void setPuntos_exp(int puntos_exp) {
		this.puntos_exp = puntos_exp;
	}

	public List<Mundo> getMundos_completos() {
		return mundos_completos;
	}

	public void setMundos_completos(List<Mundo> mundos_completos) {
		this.mundos_completos = mundos_completos;
	}

	public List<Logro> getLogros() {
		return logros;
	}

	public void setLogros(List<Logro> logros) {
		this.logros = logros;
	}

	public Map<Integer, Nivel> getNiveles_actuales() {
		return niveles_actuales;
	}

	public void setNiveles_actuales(Map<Integer, Nivel> niveles_actuales) {
		this.niveles_actuales = niveles_actuales;
	}

	public List<Problema> getProblemas_resueltos() {
		return problemas_resueltos;
	}

	public void setProblemas_resueltos(List<Problema> problemas_resueltos) {
		this.problemas_resueltos = problemas_resueltos;
	}

	//METODOS A IMPLEMENTAR
	public void ganarExperiencia(int exp) {
		int total = exp + this.puntos_exp;
		setPuntos_exp(total);
	}
	
	public void agregarProblema(Problema p){
		Nivel nivel_problema = p.getNivel();
		if(!problemas_resueltos.contains(p)){//Si no lo respondi antes, lo agrego
			problemas_resueltos.add(p);
		}
	}
	
	public void ganarLogro(Logro l){
		logros.add(l);
	}
	
	public void agregarMundoActivo(Mundo mundo){//Se agrega un nuevo mundo, en su primer nivel
		Nivel primer_nivel = mundo.getNiveles().get(0);
		niveles_actuales.put(mundo.getId(), primer_nivel);
	}
	
	public void agregarNivelActivo(Mundo mundo){//PRECONDICION: EL MUNDO PERTENECE A mundo_nivel
		int id_mundo = mundo.getId();
		Nivel nivel_actual;
		if(niveles_actuales.isEmpty()){
			nivel_actual = mundo.getNiveles().get(0);
			niveles_actuales.put(mundo.getId(),nivel_actual);
		}else{
			nivel_actual = niveles_actuales.get(id_mundo);
		}
		if(!mundo.ultimoNivelMundo(nivel_actual)){
			Nivel siguiente_nivel = mundo.siguienteNivel(nivel_actual);
			niveles_actuales.put(id_mundo, siguiente_nivel);
		}
	}
	
	public ArrayList<Logro> nuevosLogros(){
		ArrayList<Logro> nuevos_logros = new ArrayList<Logro>();
		int cant_correctas = cantCorrectas();
		if(cant_correctas == 1){
			Logro primeraRespuesta = new Logro("Primera respuesta correcta");
			nuevos_logros.add(primeraRespuesta);
		}
		if(cant_correctas % 5 == 0){
			Logro logro = new Logro("Has logrado "+cant_correctas+"problemas correctos");
			nuevos_logros.add(logro);
		}
		return nuevos_logros;
	}
	
	public int cantCorrectas(){
		return problemas_resueltos.size();
	}
	

		
}
