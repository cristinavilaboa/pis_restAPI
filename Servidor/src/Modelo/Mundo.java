package Modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
@Entity
@Table(name = "MUNDO")
public class Mundo {
	@Id  // @GeneratedValue(strategy=GenerationType.AUTO)
	private int id_mundo;
	private int nro_nivel;
	private String nombre;
	private String imagen;
	private String descripcion;
	private int puntos_exp;
	@OneToMany(cascade=CascadeType.ALL)
	@JoinTable(name="mundo_siguiente", joinColumns={@JoinColumn (name="id_mundo_anterior", referencedColumnName= "id_mundo" )},inverseJoinColumns={@JoinColumn(name="id_mundo_sig",referencedColumnName="id_mundo")})
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Mundo> mundos_siguientes= new ArrayList<Mundo>();
	@OneToMany (cascade = CascadeType.ALL, mappedBy = "mundo") @LazyCollection(LazyCollectionOption.FALSE)
	private List<Nivel> niveles = new ArrayList<Nivel>(); 
	
	public Mundo(int id_mundo,String nombre, String imagen, String descripcion, int puntos_exp,
			List<Mundo> mundos_siguientes, List<Nivel> niveles) {
		this.id_mundo=id_mundo;
		this.nombre = nombre;
		this.imagen = imagen;
		this.descripcion = descripcion;
		this.puntos_exp = puntos_exp;
		this.mundos_siguientes = mundos_siguientes;
		this.niveles = niveles;
		this.nro_nivel = 0;
	}
	
	public Mundo(){}
	
	public int getId() {
		return id_mundo;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setId(int id) {
		this.id_mundo = id;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getImagen() {
		return imagen;
	}
	
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public int getPuntos_exp() {
		return puntos_exp;
	}
	
	public void setPuntos_exp(int puntos_exp) {
		this.puntos_exp = puntos_exp;
	}

	public List<Mundo> getMundos_siguientes() {
		return mundos_siguientes;
	}

	public void setMundos_siguientes(ArrayList<Mundo> mundos_siguientes) {
		this.mundos_siguientes = mundos_siguientes;
	}

	public List<Nivel> getNiveles() {
		return niveles;
	}

	public void setNiveles(List<Nivel> niveles) {
		this.niveles = niveles;
	}
	
	
	
	public int getNro_nivel() {
		return nro_nivel;
	}

	public void setNro_nivel(int nro_nivel) {
		this.nro_nivel = nro_nivel;
	}

	//METODOS A IMPLEMENTAR
	public boolean ultimoNivelMundo(Nivel n){

		if(n.getMundo().getId() == this.id_mundo ){
			int largoMundo = niveles.size();
			Nivel ultimo_nivel = niveles.get(largoMundo - 1);
			return (ultimo_nivel.getId_nivel() == n.getId_nivel());
		}else{
			return false;
		}
	}
	
	
	
	//////
	
	public Nivel siguienteNivel(Nivel nivel){//PRECONDICION nivel es un Nivel del MUNDO
		/*int nivel_actual = niveles.indexOf(nivel);
		return niveles.get(nivel_actual+1);*/
		
		return niveles.get(nivel.getNro_nivel() + 1);
	}
	
	public void agregarNivel(Nivel n){
		n.setNivel(nro_nivel);
		nro_nivel++;
		niveles.add(n);
	}
	
	public Nivel buscarNivel(int id_nivel){//Puede retornar NULL, pasar un num_nivel que exista en el mundo a buscar
		for(Nivel n: niveles){
			if(n.getId_nivel() == id_nivel){
				return n;
			}
		}
		return null;
	}
	
}
