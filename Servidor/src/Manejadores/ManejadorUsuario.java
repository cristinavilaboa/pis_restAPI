package Manejadores;



import java.util.ArrayList;
import java.util.Collections;
import Datatypes.DataPuntosJugador;
import java.util.List;


import Modelo.EstadoJugador;
import Modelo.Jugador;
import Modelo.Mensaje;
import Modelo.Mundo;
import Modelo.Profesor;
import Modelo.Usuario;
import Persistencia.HibernateUtility;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


import Datatypes.DataJugador;

public class ManejadorUsuario {
	
	private static ManejadorUsuario instancia = new ManejadorUsuario();

	private Map<String, Jugador> jugadores = new HashMap<String, Jugador>();
	private Map<String, Profesor> profesores = new HashMap<String, Profesor>();
	
	//private List<Jugador> jugadores = new ArrayList<Jugador>();
	
	private ManejadorUsuario(){};
	
	public static ManejadorUsuario getInstancia(){
		return instancia;
	}

	public DataJugador obtenerDatosJugador(String id_jugador)
	{
		Session session = null;
		Jugador j = null;
		try{
			session = HibernateUtility.getSessionFactory().openSession();
			j =(Jugador)session.get(Jugador.class,id_jugador);
		} catch (Exception e){
			System.out.println("error:" + e.getMessage());
		} finally {
			if (session != null && session.isOpen()){
				session.close();
			}
		}
		return j.obtenerDataJugador();
		
		
	}
	
	public void agregarJugador(Jugador jugador){
		SessionFactory factory= HibernateUtility.getSessionFactory();
		Session session=factory.openSession();
		org.hibernate.Transaction t= session.beginTransaction();
		session.saveOrUpdate(jugador);
		t.commit();
		session.close();
		System.out.println("successfully saved jugador");
	}
	

	public boolean existeJugador(String nick){
		return (buscarJugador(nick)!=null);
	}
	
	public Jugador buscarJugador(String nick){
		Session session = null;
		Jugador j = null;
		try{
			session = HibernateUtility.getSessionFactory().openSession();
			j =(Jugador)session.get(Jugador.class,nick);
		} catch (Exception e){
			System.out.println("error:" + e.getMessage());
		} finally {
			if (session != null && session.isOpen()){
				session.close();
			}
		}
		return j;
	}
	
	public Mensaje buscarMensaje(int id_mensaje){
		Session session = null;
		Mensaje m = null;
		try{
			session = HibernateUtility.getSessionFactory().openSession();
			m =(Mensaje)session.get(Mensaje.class,id_mensaje);
		} catch (Exception e){
			System.out.println("error:" + e.getMessage());
		} finally {
			if (session != null && session.isOpen()){
				session.close();
			}
		}
		return m;
	}
	
	public List<DataPuntosJugador> obtenerRanking(){
		/*
		List<DataPuntosJugador> list_dpj = new ArrayList<>();
		for (Entry<String, Jugador> j : jugadores.entrySet()) {
			String nombreJ = j.getValue().getNombre();
			DataPuntosJugador dpj = j.getValue().obtenerDataPuntosJugador(nombreJ);
			list_dpj.add(dpj);
		}
		*/
		
		SessionFactory factory= HibernateUtility.getSessionFactory();
		Session session=factory.openSession();
		List<DataPuntosJugador> list_dpj = new ArrayList<>();

	//try{
			//session = HibernateUtility.getSessionFactory().openSession();
			List<Jugador> lista = session.createCriteria(Jugador.class).list();	
			for (Jugador j:lista){
				String nombreJ = j.getNombre();
				DataPuntosJugador dpj = j.obtenerDataPuntosJugador(nombreJ);
				list_dpj.add(dpj);
			}
/*
		} catch (Exception e){
			System.out.println("error:" + e.getMessage());
		} finally {
			if (session != null && session.isOpen()){
				session.close();
			}
		}*/
			
			session.close();
		
		
		
		
		Collections.sort(list_dpj);
		return list_dpj;
	}
	
	
	
	public Profesor buscarProfesor(String nick){
		Session session = null;
		Profesor p = null;
		try{
			session = HibernateUtility.getSessionFactory().openSession();
			p =(Profesor)session.get(Profesor.class,nick);
		} catch (Exception e){
			System.out.println("error:" + e.getMessage());
		} finally {
			if (session != null && session.isOpen()){
				session.close();
			}
		}
		return p;
	}
	
	public void agregarProfesor(Profesor p){
		SessionFactory factory= HibernateUtility.getSessionFactory();
		Session session=factory.openSession();
		org.hibernate.Transaction t= session.beginTransaction();
		session.saveOrUpdate(p);
		t.commit();
		session.close();
		System.out.println("successfully saved profesor");
	}

	
	
	public void borrar(){
		SessionFactory factory= HibernateUtility.getSessionFactory();
		Session session=factory.openSession();
		org.hibernate.Transaction t= session.beginTransaction();
		
		List<Jugador> lista_jugadores = session.createCriteria(Jugador.class).list();
		
		for (Iterator<Jugador> iterator = lista_jugadores.iterator(); iterator.hasNext();) {
			Jugador j = (Jugador) iterator.next();
			session.delete(j);
			System.out.println("borrar los jugadores");
		}
		
		t.commit();
	
		session.close();
		System.out.println("successfully borrado jugadores");
	}
	
	
	public void borrarProfesores(){
		SessionFactory factory= HibernateUtility.getSessionFactory();
		Session session=factory.openSession();
		org.hibernate.Transaction t= session.beginTransaction();
		
		
		List<Profesor> lista_profe = session.createCriteria(Profesor.class).list();
		
		for (Iterator<Profesor> iterator = lista_profe.iterator(); iterator.hasNext();) {
			Profesor j = (Profesor) iterator.next();
			session.delete(j);
			System.out.println("borrar los jugadores");
		}
		
		t.commit();
	
		session.close();
		System.out.println("successfully borrado jugadores");
	}
	
	
	

	public void guardarEstado(EstadoJugador estado){
		SessionFactory factory= HibernateUtility.getSessionFactory();
		Session session=factory.openSession();
		org.hibernate.Transaction t= session.beginTransaction();
		session.saveOrUpdate(estado);
		t.commit();
		session.close();
		System.out.println("successfully saved estado");

	}
	
	public void guardarMensaje(Mensaje m){
		SessionFactory factory= HibernateUtility.getSessionFactory();
		Session session=factory.openSession();
		org.hibernate.Transaction t= session.beginTransaction();
		session.saveOrUpdate(m);
		t.commit();
		session.close();
		System.out.println("successfully saved mensaje");

	}
	
	
	public void guardarUsuario(Usuario p){
		SessionFactory factory= HibernateUtility.getSessionFactory();
		Session session=factory.openSession();
		org.hibernate.Transaction t= session.beginTransaction();
		session.saveOrUpdate(p);
		t.commit();
		session.close();
		System.out.println("successfully saved profe");

	}
	
		
	public List<Jugador> obtenerJugadores(){                              
		
		Session session = null;
		List<Jugador> lista_jugadores = new ArrayList<Jugador>();
		try
		{
			session = HibernateUtility.getSessionFactory().openSession();
			lista_jugadores = (List<Jugador>)session.createCriteria(Jugador.class).list();
			
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
		return lista_jugadores;				

	}
	
}