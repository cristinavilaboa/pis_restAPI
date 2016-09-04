package Manejadores;

import java.util.ArrayList;
import java.util.List;

import Modelo.Problema;

public class ManejadorProblema {

	private static ManejadorProblema instancia = new ManejadorProblema();
	private List<Problema> problemas = new ArrayList<Problema>();
	
	private ManejadorProblema(){};
	
	public static ManejadorProblema getInstancia(){
		return instancia;
	}
	
	//METODOS A IMPLEMENTAR
	public boolean ultimaNivel(int id_pregunta){
		Problema pro = buscarProblema(id_pregunta);
		return pro.getNivel().esUltima(id_pregunta);
	}
	
	public int verificarRespuesta(int id_pregunta, String respuesta){
		Problema problema = buscarProblema(id_pregunta);
		if (problema.verificarRespuesta(respuesta)){
			return problema.getPuntos_exp();
		}else{
			return 0;
		}
	}
	
	public Problema buscarProblema(int id_pregunta){
		for(Problema pro: problemas){
			if(pro.getId()==id_pregunta){
				return pro;
			}
		}
		return null;
	}
	
	public String getAyuda(int id_pregunta){return null;}
	
	public void agregarProblema(Problema p){
		problemas.add(p);
	}
}
