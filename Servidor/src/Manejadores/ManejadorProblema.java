package Manejadores;


import java.util.HashMap;
import java.util.Map;

import Modelo.Ayuda;
import Modelo.Problema;

public class ManejadorProblema {

	private static ManejadorProblema instancia = new ManejadorProblema();

	private Map<Integer,Problema> problemas= new HashMap<Integer,Problema>();	
	private ManejadorProblema(){};	
	public static ManejadorProblema getInstancia(){
		return instancia;
	}
	
	public void agregarProblema(Problema p){
		problemas.put(p.getId(), p);
	}
	//METODOS A IMPLEMENTAR
	public boolean ultimaNivel(int id_problema){
		Problema pro = buscarProblema(id_problema);
		return pro.getNivel().esUltima(id_problema);
	}
	
	public int verificarRespuesta(int id_problema, String respuesta){
		Problema problema = buscarProblema(id_problema);
		if (problema.verificarRespuesta(respuesta)){
			return problema.getPuntos_exp();
		}else{
			return 0;
		}
	}
	
	public Problema buscarProblema(int id_problema){
		Problema p= problemas.get(id_problema);
		return p;
	}
	
	public String getAyuda(int id_problema){
		Problema problema = problemas.get(id_problema);
		Ayuda ayuda = problema.getAyuda();
		return ayuda.getInfo();
	}
	
	public void borrar(){
		
		this.problemas.clear();
	}
	
}
