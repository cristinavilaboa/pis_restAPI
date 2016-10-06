package Modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import Datatypes.*;
import Datatypes.DataPuntosJugador;
import Manejadores.ManejadorMundo;
import Modelo.EstadoJugador;
@Entity
@Table(name = "JUGADOR")
@PrimaryKeyJoinColumn(name="nick")
public class Jugador extends Usuario{

	private String FBToken;
	private String imagen;
	@OneToOne(cascade=CascadeType.ALL) @LazyCollection(LazyCollectionOption.FALSE)
	private EstadoJugador estado;
	@ManyToOne @LazyCollection(LazyCollectionOption.FALSE)
	private Clase clase;
	
    public Jugador(String nombre, String nick, String FBToken, String imagen, EstadoJugador estado, Clase clase){
        super(nombre, nick);
        this.FBToken = FBToken;
        this.imagen = imagen;
        this.estado = estado;
        this.clase = clase;
    }
    
    public Jugador()
    {
    	
    }
    
	public String getFBToken() {
		return FBToken;
	}
	
	public void setFBToken(String fBToken) {
		FBToken = fBToken;
	}
	
	public String getImagen() {
		return imagen;
	}
	
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public EstadoJugador getEstado() {
		return estado;
	}

	public void setEstado(EstadoJugador estado) {
		this.estado = estado;
	}

	public Clase getClase() {
		return clase;
	}

	public void setClase(Clase clase) {
		this.clase = clase;
	}
	
	public DataPuntosJugador obtenerDataPuntosJugador(String nombre){
		int puntos = estado.getPuntos_exp();
		DataPuntosJugador dpj = new DataPuntosJugador(nombre, puntos);
		return dpj;
	}
	
	//************OPERACIONES*************//
	
	public DataJugador obtenerDataJugador()
	{
		EstadoJugador e = this.estado;
		List<Logro> logros = e.getLogros();
		Map <Integer,Nivel> mundos_niveles = e.getNiveles_actuales();
		int exp = e.getPuntos_exp();
		List<DataLogro> dataLogros = new ArrayList<DataLogro>();
		List<DataMundoNivel> dataMundosNiveles = new ArrayList<DataMundoNivel>();
		int cant = logros.size();
		for (int i = 0; i<cant; i++){
			dataLogros.add(logros.get(i).obtenerDataLogro());
		}
		Iterator<Integer> it = mundos_niveles.keySet().iterator();
		ManejadorMundo mm = ManejadorMundo.getInstancia();
		while(it.hasNext()){
		  int key = (Integer)it.next();
		  
		  Mundo mundo = mm.obtenerMundo(key);		  
		 
		  String d = mundo.getDescripcion();

		  
		  dataMundosNiveles.add(new DataMundoNivel(mundo.getNombre(),mundos_niveles.get(key).getNro_nivel()));		
		}
		
				
		return new DataJugador(this.getNick() , this.imagen,dataMundosNiveles,exp,dataLogros);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((FBToken == null) ? 0 : FBToken.hashCode());
		result = prime * result + ((clase == null) ? 0 : clase.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + ((imagen == null) ? 0 : imagen.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Jugador other = (Jugador) obj;
		if (FBToken == null) {
			if (other.FBToken != null)
				return false;
		} else if (!FBToken.equals(other.FBToken))
			return false;
		if (clase == null) {
			if (other.clase != null)
				return false;
		} else if (!clase.equals(other.clase))
			return false;
		if (estado == null) {
			if (other.estado != null)
				return false;
		} else if (!estado.equals(other.estado))
			return false;
		if (imagen == null) {
			if (other.imagen != null)
				return false;
		} else if (!imagen.equals(other.imagen))
			return false;
		return true;
	}

	
}
