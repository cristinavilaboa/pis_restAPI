package Modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name = "NIVEL")
public class Nivel {
	@Id  @GeneratedValue(strategy=GenerationType.AUTO)
	private int id_nivel;
	private int dificultad;
	@OneToMany (cascade = CascadeType.ALL, mappedBy = "nivel")
	private List<Problema> problemas = new ArrayList<Problema>();
	@ManyToOne
	private Mundo mundo;
	
	public Nivel(int dificultad, List<Problema> problemas, Mundo mundo) {
		this.dificultad = dificultad;
		this.problemas = problemas;
		this.mundo = mundo;
	}
	
	public Nivel(){}

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
	
	public Mundo getMundo() {
		return mundo;
	}

	public void setMundo(Mundo mundo) {
		this.mundo = mundo;
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
		
		return (encontre && (it <= tama�o));//Si it es menor que el tama�o es que queda alguna pregunta m�s
		//HAY QUE VER QUE PASA SI NO ENCUENTRA EL IDPREGUNTA
		
	}
	
	public void agregarProblema(Problema p){
		problemas.add(p);
	}
	
	
}
