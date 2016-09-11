package Modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
@Entity
@Table(name = "LISTADENIVEL")
public class ListaDeNivel {
	@Id  @GeneratedValue(strategy=GenerationType.AUTO)
	private int id_lista_de_nivel;
	@ManyToMany
	private List<Problema> problemas;
	
	public ListaDeNivel(){
		problemas=new ArrayList<Problema>();
	}
	
	public List<Problema> getProblemas() {
		return problemas;
	}
	public void setProblemas(List<Problema> problemas) {
		this.problemas = problemas;
	} 
	 
	public void agregarProblema(Problema p){
		problemas.add(p);
	}
	
	public Problema obtenerProblema(int id){
		for (Problema p: problemas){
			if (p.getId()==id) {
				return p;
			}
		}
		return null;
	}
	

}
