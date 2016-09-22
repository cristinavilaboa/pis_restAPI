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
	static boolean  Cargado=false;	
	//private static SessionFactory factory;
	public static boolean getCargado(){
		return Cargado;	
	}
	
	public static Mensaje PersistirMensaje(String contenido, Date fecha,String asunto,Profesor profesor){
		SessionFactory factory= HibernateUtility.getSessionFactory();
		Session session=factory.openSession();
		org.hibernate.Transaction t= session.beginTransaction();
		Mensaje mensaje=new Mensaje(contenido,asunto,fecha);
		session.persist(mensaje);
		//profesor.agregar_mensaje_nuevo(mensaje);
		session.saveOrUpdate(profesor);
		t.commit();//transaction is commited 
		session.close();
		System.out.println("successfully saved mensaje");
		return mensaje;
	}
	/*public static void PersistirUsuarioMensaje(String nickProfesor, int id_mensaje){
		Session session=factory.openSession();
		org.hibernate.Transaction t= session.beginTransaction();
		Query query= session.createSQLQuery("INSERT INTO usuario_mensajes_nuevos(id_usuario, id_mensaje) values (?,?)");
		query.setParameter(0, nickProfesor);
		query.setParameter(1, id_mensaje);
		query.executeUpdate();
		//session.persist(mensaje);
		t.commit();//transaction is commited 
		session.close();
		System.out.println("successfully saved usuario_mensaje");
	}*/
	
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
		System.out.println("successfully saved datos iniciales");
        //Cargado=true;	
	//}
	//public static void Cargar(){
		ManejadorProblema mp = ManejadorProblema.getInstancia();
		ManejadorUsuario mu = ManejadorUsuario.getInstancia();
		ManejadorMundo mm = ManejadorMundo.getInstancia();
		
		mp.borrarBD();
		mu.borrarBD();
		mm.borrarBD();
		
		List<Mundo> mundos_siguientes = new ArrayList<Mundo>();
		List<Nivel> niveles = new ArrayList<Nivel>();
		ArrayList<Problema> listaPN1= new ArrayList<Problema>();
		ArrayList<Problema> listaPN2= new ArrayList<Problema>();
		ArrayList<Problema> listaPN3= new ArrayList<Problema>();
		ArrayList<Problema> listaPN4= new ArrayList<Problema>();
		ArrayList<Problema> listaPN5= new ArrayList<Problema>();
		/*ArrayList<Mundo> mundos_completosJ1 = new ArrayList<Mundo>();
		ArrayList<Mundo> mundos_completosJ2 = new ArrayList<Mundo>();
		ArrayList<Mundo> mundos_completosJ3 = new ArrayList<Mundo>();
		ArrayList<Logro> logrosJ1 = new ArrayList<Logro>();
		ArrayList<Logro> logrosJ2 = new ArrayList<Logro>();
		ArrayList<Logro> logrosJ3 = new ArrayList<Logro>();
		Map<Integer, Nivel> mundo_nivelJ1 = new HashMap<Integer, Nivel>();
		Map<Integer, Nivel> mundo_nivelJ2 = new HashMap<Integer, Nivel>();
		Map<Integer, Nivel> mundo_nivelJ3 = new HashMap<Integer, Nivel>();
		ArrayList<Problema> nivel_problemaJ1 = new ArrayList<Problema>();
		ArrayList<Problema> nivel_problemaJ2 = new ArrayList<Problema>();
		ArrayList<Problema> nivel_problemaJ3 = new ArrayList<Problema>();
		
		Clase clase = new Clase();*/
		
		Mundo mundo;
		Nivel nivel1,nivel2,nivel3,nivel4,nivel5;
		Problema problema1,problema2,problema3,problema4,problema5;
		Jugador jugador1,jugador2,jugador3;
		EstadoJugador estado1,estado2,estado3;
		Ayuda ayudaP1,ayudaP2,ayudaP3,ayudaP4,ayudaP5;
		Contenido contenidoP1, contenidoP2, contenidoP3, contenidoP4, contenidoP5;
		Logro logJ1,logJ21,logJ22;
		
		Profesor profesor = new Profesor("Marcelo", "marce_fing", "1234");
		mu.agregarProfesorBD(profesor);
		
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
		
		/*logJ1 = new Logro("Maestro de las derivadas");
		logJ1.setId(1);
		logrosJ1.add(logJ1);
		mundo_nivelJ1.put(mundo.getId(), nivel3);
		estado1 = new EstadoJugador(1,10, mundos_completosJ1, logrosJ1, mundo_nivelJ1, nivel_problemaJ1);
		jugador1 = new Jugador("Nicolas", "nico_fing", "fBTokenJ1", "imagenJ1", estado1, clase);
		
		logJ21 = new Logro("Maestro de las ingrales");
		logJ21.setId(1);
		logJ22 = new Logro("Maestro de las derivadas");
		logJ22.setId(2);
		logrosJ2.add(logJ21);
		logrosJ2.add(logJ22);
		mundo_nivelJ2.put(mundo.getId(), nivel5);
		estado2 = new EstadoJugador(2,25, mundos_completosJ2, logrosJ2, mundo_nivelJ2, nivel_problemaJ2);
		jugador2 = new Jugador("Maria", "mari_fing", "fBTokenJ2", "imagenJ2", estado2, clase);
		
		mundo_nivelJ3.put(mundo.getId(), nivel1);
		estado3 = new EstadoJugador(3,7, mundos_completosJ3, logrosJ3, mundo_nivelJ3, nivel_problemaJ3);
		jugador3 = new Jugador("Facundo", "fa_cu_fing", "fBTokenJ2", "imagenJ2", estado3, clase);
		
		mu.agregarJugador(jugador1);
		mu.agregarJugador(jugador2);
		mu.agregarJugador(jugador3);
		
		mm.agregarMundo(mundo);
		
		mp.agregarProblema(problema1);
		mp.agregarProblema(problema2);
		mp.agregarProblema(problema3);
		mp.agregarProblema(problema4);
		//mp.agregarProblema(problema5);
		
		*/
		
	}
}
