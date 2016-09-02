package Manejadores;

import java.util.HashMap;
import java.util.Map;

import Modelo.Problema;

public class ManejadorProblema {

	private static ManejadorProblema instancia = new ManejadorProblema();
	private Map<Integer,Problema> problemas= new HashMap<Integer,Problema>();
	
	private ManejadorProblema(){};
	
	public static ManejadorProblema getInstancia(){
		return instancia;
	}
	
	//METODOS A IMPLEMENTAR
	public boolean ultimaNivel(int id_pregunta){return false;}
	
	public int verificarRespuesta(int id_pregunta, String respuesta){return 0;}
	
	public Problema buscarProblema(int id_pregunta){
		Problema p= problemas.get(id_pregunta);
		return p;
	}
	
	public String getAyuda(int id_pregunta){return null;}
}
