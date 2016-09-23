package Persistencia;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import Manejadores.ManejadorMundo;
import Manejadores.ManejadorProblema;
import Manejadores.ManejadorUsuario;
import Modelo.Ayuda;
import Modelo.Clase;
import Modelo.Contenido;
import Modelo.EstadoJugador;
import Modelo.Jugador;
import Modelo.Logro;
import Modelo.Mensaje;
import Modelo.Mundo;
import Modelo.Nivel;
import Modelo.Problema;
import Modelo.Profesor;


public class CargarDatosBD {	
	
	public static Mensaje PersistirMensaje(String contenido, Date fecha,String asunto,Profesor profesor){
		SessionFactory factory= HibernateUtility.getSessionFactory();
		Session session=factory.openSession();
		org.hibernate.Transaction t= session.beginTransaction();
		Mensaje mensaje=new Mensaje(contenido,asunto,fecha,profesor.getNick());
		session.persist(mensaje);
		//profesor.agregar_mensaje_nuevo(mensaje);
		session.saveOrUpdate(profesor);
		t.commit();//transaction is commited 
		session.close();
		System.out.println("successfully saved mensaje");
		return mensaje;
	}
	
	public static void CargarTestSolicitarAyuda() throws SecurityException, HeuristicMixedException, HeuristicRollbackException, RollbackException, SystemException {
		SessionFactory factory= HibernateUtility.getSessionFactory();
		//creating session object
		Session session=factory.openSession();
		//creating transaction object
		org.hibernate.Transaction t= session.beginTransaction();
		
		Profesor profe = new Profesor("Juan","pepe","123");
		Problema problema= new Problema(1,"problema1","resp",12,null,null,null,profe);
		
		session.persist(profe);
		session.persist(problema);
		t.commit();//transaction is commited 
		session.close();
		System.out.println("successfully saved datos Test Solicitar Ayuda");
	}
	
	public static void Cargar(){
		ManejadorProblema mp = ManejadorProblema.getInstancia();
		ManejadorUsuario mu = ManejadorUsuario.getInstancia();
		ManejadorMundo mm = ManejadorMundo.getInstancia();
		
		mp.borrarBD();
		mu.borrarBD();
		mm.borrarBD();
		
		List<Mundo> mundos_siguientes = new ArrayList<Mundo>();
		List<Nivel> niveles = new ArrayList<Nivel>();
		List<Problema> listaPN1= new ArrayList<Problema>();
		List<Problema> listaPN2= new ArrayList<Problema>();
		List<Problema> listaPN3= new ArrayList<Problema>();
		List<Problema> listaPN4= new ArrayList<Problema>();
		List<Problema> listaPN5= new ArrayList<Problema>();
		List<Mundo> mundos_completosJ1 = new ArrayList<Mundo>();
		List<Mundo> mundos_completosJ2 = new ArrayList<Mundo>();
		List<Mundo> mundos_completosJ3 = new ArrayList<Mundo>();
		List<Logro> logrosJ1 = new ArrayList<Logro>();
		List<Logro> logrosJ2 = new ArrayList<Logro>();
		List<Logro> logrosJ3 = new ArrayList<Logro>();
		Map<Integer, Nivel> niveles_actualesJ1 = new HashMap<Integer, Nivel>();
		Map<Integer, Nivel> niveles_actualesJ2 = new HashMap<Integer, Nivel>();
		Map<Integer, Nivel> niveles_actualesJ3 = new HashMap<Integer, Nivel>();
		List<Problema> problemas_resueltosJ1 = new ArrayList<Problema>();
		List<Problema> problemas_resueltosJ2 = new ArrayList<Problema>();
		List<Problema> problemas_resueltosJ3 = new ArrayList<Problema>();
		
		Mundo mundo;
		Nivel nivel1,nivel2,nivel3,nivel4,nivel5;
		Problema problema1,problema2,problema3,problema4;
		Jugador jugador1,jugador2,jugador3;
		EstadoJugador estado1,estado2,estado3;
		Ayuda ayudaP1,ayudaP2,ayudaP3,ayudaP4;
		Contenido contenidoP1, contenidoP2, contenidoP3, contenidoP4;
		Logro logJ1,logJ21,logJ22;
		
		Profesor profesor = new Profesor("Marcelo", "marce_fing", "1234");
		mu.agregarProfesorBD(profesor);
		Clase clase = new Clase(1,"clase de Calculo",profesor);
		mu.agregarClaseBD(clase);
		
		mundo = new Mundo("Calculo", "imagen", "Mundo de Calculo 1",20,mundos_siguientes, niveles);
		nivel1 = new Nivel(listaPN1,mundo);
		nivel2 = new Nivel(listaPN2,mundo);
		nivel3 = new Nivel(listaPN3,mundo);
		nivel4 = new Nivel(listaPN4,mundo);
		nivel5 = new Nivel(listaPN5,mundo);
		
		ayudaP1 = new Ayuda("La derivada es cuanto varia la funcion, cuando varia x");
		contenidoP1 = new Contenido("d(8x)/dx");
		problema1 = new Problema(1,"Resolver la siguiente derivada","8",10,ayudaP1,contenidoP1,nivel1,profesor);
		
		ayudaP2 = new Ayuda("La derivada es cuanto varia la funcion, cuando varia x");
		contenidoP2 = new Contenido("d(x^2/2)/dx");
		problema2 = new Problema(2,"Resolver la siguiente derivada","x",10,ayudaP2,contenidoP2,nivel2,profesor);
		
		ayudaP3 = new Ayuda("La integral es el area bajo la curva de una función");
		contenidoP3 = new Contenido("integral de (1) en [1,5]");
		problema3 = new Problema(3,"Resolver la siguiente integral","4",10,ayudaP3,contenidoP3,nivel3,profesor);
		
		ayudaP4 = new Ayuda("La integral es el area bajo la curva de una función");
		contenidoP4 = new Contenido("integral de (2x dx) en [2,8]");
		problema4 = new Problema(4,"Resolver la siguiente integral","60",10,ayudaP4,contenidoP4,nivel4,profesor);
		
		//ayudaP5 = new Ayuda("La integral es el area bajo la curva de una función");
		//contenidoP5 = new Contenido("integral de (cos(x) + 5x dx) en [a,b]");
		//problema5 = new Problema(5,"Resolver la siguiente integral","(-5/2)(a^2-b^2) - sin(a) + sin(b)",10,ayudaP5,contenidoP5,nivel5,profesor);
		
		nivel1.agregarProblema(problema1);
		nivel2.agregarProblema(problema2);
		nivel3.agregarProblema(problema3);
		nivel4.agregarProblema(problema4);
		//nivel5.agregarProblema(problema5);
		
		mundo.agregarNivel(nivel1);
		mundo.agregarNivel(nivel2);
		mundo.agregarNivel(nivel3);
		mundo.agregarNivel(nivel4);
		mundo.agregarNivel(nivel5);
		
		mm.agregarMundoBD(mundo);
		
		logJ1 = new Logro("Maestro de las derivadas");
		logrosJ1.add(logJ1);
		niveles_actualesJ1.put(mundo.getIdBD(), nivel3);
		estado1 = new EstadoJugador(10, mundos_completosJ1, logrosJ1, niveles_actualesJ1, problemas_resueltosJ1);
		jugador1 = new Jugador("Nicolas", "nico_fing", "fBTokenJ1", "imagenJ1", estado1, clase);
		
		mu.agregarJugadorBD(jugador1);
		
		logJ21 = new Logro("Maestro de las ingrales");
		logJ22 = new Logro("Maestro de las derivadas");
		logrosJ2.add(logJ21);
		logrosJ2.add(logJ22);
		niveles_actualesJ2.put(mundo.getIdBD(), nivel5);
		estado2 = new EstadoJugador(25, mundos_completosJ2, logrosJ2, niveles_actualesJ2, problemas_resueltosJ2);
		jugador2 = new Jugador("Maria", "mari_fing", "fBTokenJ2", "imagenJ2", estado2, clase);
		
		mu.agregarJugadorBD(jugador2);
		
		niveles_actualesJ3.put(mundo.getIdBD(), nivel1);
		estado3 = new EstadoJugador(7, mundos_completosJ3, logrosJ3, niveles_actualesJ3, problemas_resueltosJ3);
		jugador3 = new Jugador("Facundo", "fa_cu_fing", "fBTokenJ2", "imagenJ2", estado3, clase);
		
		mu.agregarJugadorBD(jugador3);
		
	}
}
