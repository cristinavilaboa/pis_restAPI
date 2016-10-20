package Datatypes;

public class DataMundoNivel {
	public DataMundoNivel(String mundo, int nivel, boolean mundo_completo) {
		this.mundo = mundo;
		this.nivel = nivel;
		this.mundo_completo = mundo_completo;
	}
	private String mundo;
	private int nivel;
	private boolean mundo_completo;
	
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
	public boolean isMundo_completo() {
		return mundo_completo;
	}
	public void setMundo_completo(boolean mundo_completo) {
		this.mundo_completo = mundo_completo;
	}
	
	
}
