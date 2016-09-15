package Modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
@Entity
@Table(name = "MUNDO")
public class Mundo {
	@Id
	private int id_nivel=1;
	private Integer id_mundo;
	private String nombre;
	private String imagen;
	private String descripcion;
	private int puntos_exp;
	@OneToMany(cascade=CascadeType.ALL)
	@JoinTable(name="mundo_siguiente", joinColumns={@JoinColumn (name="id_mundo_anterior", referencedColumnName= "id_mundo" )},inverseJoinColumns={@JoinColumn(name="id_mundo_sig",referencedColumnName="id_mundo")})
	private List<Mundo> mundos_siguientes= new ArrayList<Mundo>();
	@OneToMany (cascade = CascadeType.ALL, mappedBy = "mundo")
	private List<Nivel> niveles = new ArrayList<Nivel>(); 
	
	public Mundo(int id, String nombre, String imagen, String descripcion, int puntos_exp,
			ArrayList<Mundo> mundos_siguientes, List<Nivel> niveles) {
		this.id_mundo = id;
		this.nombre = nombre;
		this.imagen = imagen;
		this.descripcion = descripcion;
		this.puntos_exp = puntos_exp;
		this.mundos_siguientes = mundos_siguientes;
		this.niveles = niveles;
	}
	
	public Mundo(){}
	
	public int getId() {
		return id_mundo;
	}
	
	public void setId(int id) {
		this.id_mundo = id;
	}
	
	public String getNombre() {
		return nombre;
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
	
	//METODOS A IMPLEMENTAR
	public boolean ultimoNivelMundo(Nivel n){
		int largoMundo = niveles.size();
		Nivel ultimo_nivel = niveles.get(largoMundo - 1);
		return (ultimo_nivel.getDificultad() == n.getDificultad());
	}
	
	
	
	
	//////
	
	public Nivel siguienteNivel(Nivel nivel){//PRECONDICION nivel es un Nivel del MUNDO
		int nivel_actual = niveles.indexOf(nivel);
		return niveles.get(nivel_actual+1);
	}
	
	public void agregarNivel(Nivel n){
		n.setNivel(id_nivel);
		id_nivel++;
		niveles.add(n);
	}
	
}
