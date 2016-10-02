package Manejadores;


import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import Modelo.Ayuda;
import Modelo.Jugador;
import Modelo.Mundo;
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
		SessionFactory factory= HibernateUtility.getSessionFactory();
		Session session=factory.openSession();
		org.hibernate.Transaction t= session.beginTransaction();
		session.persist(p);
		t.commit();
		session.close();
		System.out.println("successfully saved problema");
	}
		
	public Map<Integer, Problema> getProblemas() {    ///////// Esto no sirve para nada
		List<Problema> problemas = null;
		Map<Integer, Problema> mapProblemas = null;
		Session session = null;
		try{
	       session = HibernateUtility.getSessionFactory().openSession();
	       org.hibernate.Transaction tx = session.beginTransaction();
	       problemas = session.createCriteria(Problema.class).list();
		} catch (Exception e){
			System.out.println("error:" + e.getMessage());
		} finally {
			if (session != null && session.isOpen()){
				session.close();
			}
		}
		if (problemas != null){
			mapProblemas = new HashMap<Integer,Problema>();
			for (Problema p : problemas){
				mapProblemas.put(p.getId(), p);
			}
			problemas = null;
		}
		return mapProblemas;
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
		Session session = null;
		Problema p = null;
		//Ayuda ayuda = null;
		try{
			session = HibernateUtility.getSessionFactory().openSession();
			p =(Problema)session.get(Problema.class,id_problema);
			//ayuda = (Ayuda)session.get(Ayuda.class, p.get)
			
		} catch (Exception e){
			System.out.println("error:" + e.getMessage());
		} finally {
			if (session != null && session.isOpen()){
				session.close();
			}
		}
		return p;
	}
	
	public String getAyuda(int id_problema){		
		return buscarProblema(id_problema).getAyuda().getInfo();
	}
		
	
	public void borrar(){
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
