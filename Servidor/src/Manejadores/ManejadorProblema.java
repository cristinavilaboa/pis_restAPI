package Manejadores;


import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import Modelo.Ayuda;
import Modelo.Problema;
import Persistencia.HibernateUtility;

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
	public void agregarProblemaBD(Problema p){
		SessionFactory factory= HibernateUtility.getSessionFactory();
		Session session=factory.openSession();
		org.hibernate.Transaction t= session.beginTransaction();
		session.persist(p);
		t.commit();
		session.close();
		System.out.println("successfully saved mundo");
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
	public void borrarBD(){
		SessionFactory factory= HibernateUtility.getSessionFactory();
		Session session=factory.openSession();
		org.hibernate.Transaction t= session.beginTransaction();
		List<Problema> lista_problemas = session.createCriteria(Problema.class).list();
		for (Iterator<Problema> iterator = lista_problemas.iterator(); iterator.hasNext();) {
			Problema p = (Problema) iterator.next();
			session.delete(p);
		}
		t.commit();
		session.close();
		System.out.println("successfully borrado problemas");
	}
	
}
