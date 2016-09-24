package Manejadores;



import java.util.ArrayList;
import java.util.Collections;
import Datatypes.DataPuntosJugador;
import java.util.List;

import Modelo.Clase;
import Modelo.Jugador;
import Modelo.Mundo;
import Modelo.Profesor;
import Persistencia.HibernateUtility;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

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
		Jugador j = jugadores.get(id_jugador);
		DataJugador dj = j.obtenerDataJugador();
		return dj;
	}
	public void agregarJugador(Jugador jugador){
		jugadores.put(jugador.getNick(), jugador);

	}
	public void agregarJugadorBD(Jugador jugador){
		SessionFactory factory= HibernateUtility.getSessionFactory();
		Session session=factory.openSession();
		org.hibernate.Transaction t= session.beginTransaction();
		session.persist(jugador);
		t.commit();
		session.close();
		System.out.println("successfully saved jugador");
	}
	

	public boolean existeJugador(String nick){
		Jugador j = jugadores.get(nick);
		if (j!=null){
			return true;	
		}
		return false;
	}
	
	public boolean existeJugadorBD(String nick){
		return (buscarJugadorBD(nick)!=null);
	}
	
	public Jugador buscarJugador(String nick){
		return jugadores.get(nick); //ver de tirar excepcion si es null
	}
	
	public Jugador buscarJugadorBD(String nick){
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
	
	public Profesor buscarProfesor(String nick){
		return profesores.get(nick);
	}
	public Profesor buscarProfesorBD(String nick){
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
		profesores.put(p.getNick(), p);
	}
	public void agregarProfesorBD(Profesor p){
		SessionFactory factory= HibernateUtility.getSessionFactory();
		Session session=factory.openSession();
		org.hibernate.Transaction t= session.beginTransaction();
		session.persist(p);
		t.commit();
		session.close();
		System.out.println("successfully saved profesor");
	}
	public void agregarClaseBD(Clase clase){
		SessionFactory factory= HibernateUtility.getSessionFactory();
		Session session=factory.openSession();
		org.hibernate.Transaction t= session.beginTransaction();
		session.persist(clase);
		t.commit();
		session.close();
		System.out.println("successfully saved clase");
	}
	
	
	public void borrar(){
		
		this.jugadores.clear();
	}
	public void borrarBD(){
		SessionFactory factory= HibernateUtility.getSessionFactory();
		Session session=factory.openSession();
		org.hibernate.Transaction t= session.beginTransaction();
		List<Jugador> lista_jugadores = session.createCriteria(Jugador.class).list();
		for (Iterator<Jugador> iterator = lista_jugadores.iterator(); iterator.hasNext();) {
			Jugador j = (Jugador) iterator.next();
			session.delete(j);
		}
		t.commit();
		session.close();
		System.out.println("successfully borrado jugadores");
	}
	
}