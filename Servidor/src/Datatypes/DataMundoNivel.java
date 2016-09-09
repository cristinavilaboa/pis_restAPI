package Datatypes;

public class DataMundoNivel {
	public DataMundoNivel(String mundo, int nivel) {
		this.mundo = mundo;
		this.nivel = nivel;
	}
	private String mundo;
	private int nivel;
	
	public String getMundo() {
		return mundo;
	}
	public void setMundo(String mundo) {
		this.mundo = mundo;
	}
	public int getNivel() {
		return nivel;
	}
	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
	

}
