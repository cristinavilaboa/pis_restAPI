package Manejadores;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import Modelo.Mundo;
import Modelo.Nivel;
import Modelo.Problema;
import Modelo.Profesor;
import Persistencia.HibernateUtility;

public class ManejadorMundo {
	
	private static ManejadorMundo instancia = new ManejadorMundo();
	
	//----CONSTRUCTOR----//
	private ManejadorMundo(){};
	
	//----GETTERS----//
	public static ManejadorMundo getInstancia(){
		return instancia;
	}
	
	//----OPERACIONES----//
	public Mundo obtenerMundo(int id_mundo){
		Session session = null;
		Mundo mundo = null;
		try{
			session = HibernateUtility.getSessionFactory().openSession();
			mundo =(Mundo)session.get(Mundo.class,id_mundo);
		} catch (Exception e){
			System.out.println("error:" + e.getMessage());
		} finally {
			if (session != null && session.isOpen()){
				session.close();
			}
		}
		return mundo;
	}
	
	public void agregarMundo(Mundo m){
		SessionFactory factory= HibernateUtility.getSessionFactory();
		Session session=factory.openSession();
		org.hibernate.Transaction t= session.beginTransaction();
		session.saveOrUpdate(m);
		t.commit();
		session.close();
		System.out.println("successfully saved mundo");
	}
	

	
	public List<Mundo> obtenerMundos(){
		Session session = null;
		List<Mundo> mundosQuery= new ArrayList<Mundo>();
		try
		{
			session = HibernateUtility.getSessionFactory().openSession();
			 mundosQuery = (List<Mundo>)session.createCriteria(Mundo.class).list();
			
		}
		catch (Exception e)
		{
			System.out.println("error:" + e.getMessage());
		}
		finally
		{
			if (session != null && session.isOpen())
			{
				session.close();
			}
		}
		return mundosQuery;
	}
	
	
	public void borrar(){
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

}
