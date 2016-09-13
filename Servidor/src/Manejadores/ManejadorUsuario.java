package Manejadores;



import java.util.ArrayList;
import java.util.Collections;
import Datatypes.DataPuntosJugador;
import java.util.List;

import Modelo.Clase;
import Modelo.EstadoJugador;
import Modelo.Jugador;
import Modelo.Logro;
import Modelo.Profesor;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import Datatypes.DataJugador;

public class ManejadorUsuario {
	
	private static ManejadorUsuario instancia = new ManejadorUsuario();

	private Map<String, Jugador> jugadores = new HashMap<String, Jugador>();
	
	private ManejadorUsuario(){};
	
	public static ManejadorUsuario getInstancia(){
		return instancia;
	}

	public DataJugador obtenerDatosJugador(String id_jugador)
	{
		Jugador j = jugadores.get(id_jugador);
		DataJugador dj = j.obtenerDataJugador();
		return dj;
	}

	public void agregarJugador(Jugador jugador) {
		try {
			SessionFactory factory = new Configuration().configure().buildSessionFactory();
			Session session = factory.openSession();
			Transaction tx = null;
			try {
				tx = session.beginTransaction();
				session.persist(jugador);
				tx.commit();
			} catch (HibernateException e) {
				if (tx != null)
					tx.rollback();
				e.printStackTrace();
			} finally {
				jugadores.put(jugador.getNick(), jugador);
				session.close();
			}
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
	
	public void agregarProfesor(Profesor p) {
		try {
			SessionFactory factory = new Configuration().configure().buildSessionFactory();
			Session session = factory.openSession();
			Transaction tx = null;
			try {
				tx = session.beginTransaction();
				session.persist(p);
				tx.commit();
			} catch (HibernateException e) {
				if (tx != null)
					tx.rollback();
				e.printStackTrace();
			} finally {
				session.close();
			}
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
	
	public void agregarClase(Clase clase) {
		try {
			SessionFactory factory = new Configuration().configure().buildSessionFactory();
			Session session = factory.openSession();
			Transaction tx = null;
			try {
				tx = session.beginTransaction();
				session.persist(clase);
				tx.commit();
			} catch (HibernateException e) {
				if (tx != null)
					tx.rollback();
				e.printStackTrace();
			} finally {
				session.close();
			}
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
	
	public void agregarLogro(Logro logro) {
		try {
			SessionFactory factory = new Configuration().configure().buildSessionFactory();
			Session session = factory.openSession();
			Transaction tx = null;
			try {
				tx = session.beginTransaction();
				session.persist(logro);
				tx.commit();
			} catch (HibernateException e) {
				if (tx != null)
					tx.rollback();
				e.printStackTrace();
			} finally {
				session.close();
			}
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
	
	public void agregarEstadoJugador(EstadoJugador estado) {
		try {
			SessionFactory factory = new Configuration().configure().buildSessionFactory();
			Session session = factory.openSession();
			Transaction tx = null;
			try {
				tx = session.beginTransaction();
				session.persist(estado);
				tx.commit();
			} catch (HibernateException e) {
				if (tx != null)
					tx.rollback();
				e.printStackTrace();
			} finally {
				session.close();
			}
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
	

	public boolean existeJugador(String nick){
		Jugador j = jugadores.get(nick);
		if (j!=null){
			return true;	
		}
		return false;
	}
	
	public Jugador buscarJugador(String nick){
		return jugadores.get(nick); //ver de tirar excepcion si es null
	}

	
	public List<DataPuntosJugador> obtenerRanking(){
		List<DataPuntosJugador> list_dpj = new ArrayList<>();
		for (Entry<String, Jugador> j : jugadores.entrySet()) {
			String nombreJ = j.getValue().getNombre();
			DataPuntosJugador dpj = j.getValue().obtenerDataPuntosJugador(nombreJ);
			list_dpj.add(dpj);
		}
		Collections.sort(list_dpj);
		return list_dpj;
	}
	
	public void borrar(){
		try {
			SessionFactory factory = new Configuration().configure().buildSessionFactory();
			Session session = factory.openSession();
			Transaction tx = null;
			try {
				tx = session.beginTransaction();
				List<Jugador> lista_jugadores = session.createCriteria(Jugador.class).list();
				for (Iterator<Jugador> iterator = lista_jugadores.iterator(); iterator.hasNext();) {
					Jugador j = (Jugador) iterator.next();
					session.delete(j);
				}
				tx.commit();
			} catch (HibernateException e) {
				if (tx != null)
					tx.rollback();
				e.printStackTrace();
			} finally {
				this.jugadores.clear();
				session.close();
			}
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
}