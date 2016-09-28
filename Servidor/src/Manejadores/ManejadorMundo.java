package Manejadores;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import Modelo.Mundo;
import Persistencia.HibernateUtility;

public class ManejadorMundo {
	private static ManejadorMundo instancia = new ManejadorMundo();
	private  Map<Integer,Mundo> mundos= new HashMap<Integer,Mundo>();
	
	private ManejadorMundo(){};
	
	public static ManejadorMundo getInstancia(){
		return instancia;
	}
	
	public Mundo obtenerMundo(int id_mundo){
		return mundos.get(id_mundo);
	}
	public Mundo obtenerMundoBD(int id_mundo){
		Session session = null;
		Mundo m = null;
		try{
			session = HibernateUtility.getSessionFactory().openSession();
			m =(Mundo)session.get(Mundo.class,id_mundo);
		} catch (Exception e){
			System.out.println("error:" + e.getMessage());
		} finally {
			if (session != null && session.isOpen()){
				session.close();
			}
		}
		return m;
	}
	public void agregarMundo(Mundo m){
		mundos.put(m.getId(), m);
	}
	
	public void agregarMundoBD(Mundo m){
		SessionFactory factory= HibernateUtility.getSessionFactory();
		Session session=factory.openSession();
		org.hibernate.Transaction t= session.beginTransaction();
		session.persist(m);
		t.commit();
		session.close();
		System.out.println("successfully saved mundo");
	}
	public void borrar(){
		
		this.mundos.clear();
	}
	
	public void borrarBD(){
		SessionFactory factory= HibernateUtility.getSessionFactory();
		Session session=factory.openSession();
		org.hibernate.Transaction t= session.beginTransaction();
		List<Mundo> lista_mundos = session.createCriteria(Mundo.class).list();
		for (Iterator<Mundo> iterator = lista_mundos.iterator(); iterator.hasNext();) {
			Mundo m = (Mundo) iterator.next();
			session.delete(m);
		}
		t.commit();
		session.close();
		System.out.println("successfully borrado mundos");
		
	}
	
	public List<Mundo> obtenerMundos(){
		List<Mundo> lista = new ArrayList<Mundo>();
		for(Mundo m : mundos.values()){
			lista.add(m);
		}
		return lista;
	}
}
