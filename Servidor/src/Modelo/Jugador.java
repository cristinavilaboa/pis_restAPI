package Modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import Datatypes.*;
import Datatypes.DataPuntosJugador;
import Modelo.EstadoJugador;
@Entity
@Table(name = "JUGADOR")
@PrimaryKeyJoinColumn(name="nick")
public class Jugador extends Usuario{

	private String FBToken;
	private String imagen;
	@OneToOne
	private EstadoJugador estado;
	@ManyToOne
	private Clase clase;
	
    public Jugador(String nombre, String nick, String FBToken, String imagen, EstadoJugador estado, Clase clase){
        super(nombre, nick);
        this.FBToken = FBToken;
        this.imagen = imagen;
        this.estado = estado;
        this.clase = clase;
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
		Map <Mundo,Nivel> mundos_niveles = e.getMundo_nivel();
		int exp = e.getPuntos_exp();
		List<DataLogro> dataLogros = new ArrayList<DataLogro>();
		List<DataMundoNivel> dataMundosNiveles = new ArrayList<DataMundoNivel>();
		int cant = logros.size();
		for (int i = 0; i<cant; i++){
			dataLogros.add(logros.get(i).obtenerDataLogro());
		}
		Iterator<Mundo> it = mundos_niveles.keySet().iterator();
		while(it.hasNext()){
		  Mundo key = (Mundo)it.next();
		  dataMundosNiveles.add(new DataMundoNivel(key.getNombre(),mundos_niveles.get(key).getDificultad()));		
		}
		
				
		return new DataJugador(this.nick, this.imagen,dataMundosNiveles,exp,dataLogros);
	}

	
}
