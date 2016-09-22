package Modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
@Entity
@Table(name = "NIVEL")
public class Nivel {
	@Id  @GeneratedValue(strategy=GenerationType.AUTO)
	private int id_nivel;
	private int nro_nivel;//seria el num de nivel dentro de un mundo
	@OneToMany (cascade = CascadeType.ALL, mappedBy = "nivel")
	private List<Problema> problemas = new ArrayList<Problema>();
	@ManyToOne
	private Mundo mundo;
	
	public Nivel(List<Problema> problemas, Mundo mundo) {
		this.problemas = problemas;
		this.mundo = mundo;
	}
	
	public Nivel(){}
	
	
	public void setNivel(int n){
		nro_nivel=n;
	}
	
	public int getNivel(){
		return nro_nivel;
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
	public boolean esUltima(int id_problema){//Retorna true si le problema es el ultima del nivel
		int it = 0;
		boolean encontre = false;
		int tama�o = problemas.size();
		
		while (it < tama�o ||  !encontre ){
			encontre = (problemas.get(it).getId() == id_problema);
			it++;
		}
		
		return (encontre && (it <= tama�o));//Si it es menor que el tama�o es que queda algun problema m�s
		//HAY QUE VER QUE PASA SI NO ENCUENTRA EL id_problema
		
	}
	
	public void agregarProblema(Problema p){
		problemas.add(p);
	}
	
	
}
