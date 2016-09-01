package Manejadores;

import java.util.List;

import Modelo.Problema;

public class ManejadorProblema {

	private static ManejadorProblema instancia = new ManejadorProblema();
	private List<Problema> problemas;
	
	private ManejadorProblema(){};
	
	public static ManejadorProblema getInstancia(){
		return instancia;
	}
	
	//METODOS A IMPLEMENTAR
	public boolean ultimaNivel(int id_pregunta){return false;}
	
	public int verificarRespuesta(int id_pregunta, String respuesta){return 0;}
	
	public Problema buscarProblema(int id_pregunta){return null;}
	
	public String getAyuda(int id_pregunta){return null;}
}
