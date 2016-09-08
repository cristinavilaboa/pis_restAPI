package Datatypes;

public class DataPuntosJugador implements Comparable<DataPuntosJugador>{
	
	private String nombre;
	private Integer puntos;

	public DataPuntosJugador(){
		nombre = null;
		puntos = 0;
	}
	
	public DataPuntosJugador(String nombre, Integer puntos){
		this.nombre = nombre;
		this.puntos = puntos;
	}	
	
	public String getNombre(){
		return nombre;
	}
	
	public Integer getPuntos(){
		return puntos;
	}

	public void setNombre(String nombre){
		this.nombre = nombre;
	}
	
	public void setPuntos(Integer puntos){
		this.puntos = puntos;
	}
	
    @Override
    public int compareTo(DataPuntosJugador dpj) {
    	return dpj.puntos - this.puntos;
    }
	
}
