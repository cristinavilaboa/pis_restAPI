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
	//private  Map<Integer,Mundo> mundos= new HashMap<Integer,Mundo>();
	
	private ManejadorMundo(){};
	
	public static ManejadorMundo getInstancia(){
		return instancia;
	}
	
	public Mundo obtenerMundo(int id_mundo){
		Session session = null;
		Mundo mundo = null;
		try{
			session = HibernateUtility.getSessionFactory().openSession();
			mundo =(Mundo)session.get(Mundo.class,id_mundo);
			
			/*List<Nivel> niveles = new ArrayList<Nivel>();
			List<Object[]> o_niveles= (List<Object[]>)session.createQuery("select id_nivel, nro_nivel from Nivel where mundo_id_mundo="+Integer.toString(mundo.getId())).list();
			for (Object[] Nivel:o_niveles)
			{
				Integer id_nivel=(Integer)Nivel[0];
				Integer nro_nivel=(Integer)Nivel[1];
				Nivel aux_nivel= new Nivel();
				aux_nivel.setId(id_nivel);
				aux_nivel.setNivel(nro_nivel);
				aux_nivel.setMundo(mundo);
				List<Problema> Problemas = new ArrayList<Problema>();
				List<Object[]> ProblemasQuery= (List<Object[]>)session.createQuery("select id_problema from Problema where nivel_id_nivel="+Integer.toString(id_nivel)).list();
				for(Object[]ProblemaQIter : ProblemasQuery)
				{
					Problema problemaActual = ManejadorProblema.getInstancia().buscarProblema((Integer)ProblemaQIter[0]);
					Problemas.add(problemaActual);
					/*
					problemaActual.setNivel(aux_nivel);
					Profesor autor = ManejadorUsuario.getInstancia().buscarProfesor((String)ProblemaQIter[1]);
					problemaActual.setAutor(autor);
					Ayuda ayuda = 
					problemaActual.setAyuda(ayuda);
				}
				aux_nivel.setProblemas(Problemas);
				niveles.add(aux_nivel);
			};
		mundo.setNiveles(niveles);
		//////////////////////////////////////////////////////
	
			session = HibernateUtility.getSessionFactory().openSession();
			m =(Mundo)session.get(Mundo.class,id_mundo);
			List<Nivel> niveles= new ArrayList<Nivel>();
			List<Object[]> o_niveles= (List<Object[]>)session.createQuery("select id_nivel, nro_nivel from Nivel where mundo_id_mundo="+Integer.toString(m.getId())).list();
			for (Object[] Nivel:o_niveles){
				Integer id_nivel=(Integer)Nivel[0];
				Integer nro_nivel=(Integer)Nivel[1];
				Nivel aux_nivel= new Nivel();
				aux_nivel.setId(id_nivel);
				aux_nivel.setNivel(nro_nivel);
				niveles.add(aux_nivel);
			};
			m.setNiveles(niveles);*/
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
		session.persist(m);
		t.commit();
		session.close();
		System.out.println("successfully saved mundo");
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
	
	public List<Mundo> obtenerMundos(){
		Session session = null;
		List<Mundo> mundos= new ArrayList<Mundo>();
		try
		{
			session = HibernateUtility.getSessionFactory().openSession();
			List<Integer> mundosQuery = (List<Integer>)session.createQuery("select id_mundo from Mundo").list();
			for (Integer mundoIDQIter: mundosQuery)
			{
				Mundo mundoActual = this.obtenerMundo(mundoIDQIter);
				mundos.add(mundoActual);
			}
			
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
		return mundos;
	}
	/*	List<Mundo> lista = new ArrayList<Mundo>();
		for(Mundo m : mundos.values()){
			lista.add(m);
		}
		return lista;
	}*/
}
