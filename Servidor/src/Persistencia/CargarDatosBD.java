package Persistencia;

import java.util.Date;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import Manejadores.ManejadorProblema;
import Modelo.Contenido;
import Modelo.Mensaje;
import Modelo.Problema;
import Modelo.Profesor;


public class CargarDatosBD {	
	static boolean  Cargado=false;	
	private static SessionFactory factory;
	public static boolean getCargado(){
		return Cargado;	
	}
	
	public static int PersistirMensaje(String contenido, Date fecha,String asunto){
		Session session=factory.openSession();
		org.hibernate.Transaction t= session.beginTransaction();
		Mensaje mensaje=new Mensaje(contenido,asunto,fecha);
		int idMensaje=(int)session.save(mensaje);
		//session.persist(mensaje);
		t.commit();//transaction is commited 
		session.close();
		System.out.println("successfully saved mensaje");
		return idMensaje;
	}
	public static void PersistirUsuarioMensaje(String nickProfesor, int id_mensaje){
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
	}
	
	public static void Cargar() throws SecurityException, HeuristicMixedException, HeuristicRollbackException, RollbackException, SystemException {
		//creating configuration object
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");//populates the data of the configuration file	
		//creating seession factory object 
		factory=cfg.buildSessionFactory();
		//creating session object
		Session session=factory.openSession();
		//creating transaction object
		org.hibernate.Transaction t= session.beginTransaction();
		Contenido e1= new Contenido("contenido");
		session.persist(e1);//persisting the object 
		
		Profesor profesor = new Profesor("Juan","pepe","123");
		Problema problema= new Problema(1,"problema1","resp",12,null,null,null,profesor);
		Profesor profesor2 = new Profesor("Juan","otroProfe","123");
		Problema problema2= new Problema(2,"problema2","resp",12,null,null,null,profesor2);
		session.persist(profesor);
		session.persist(problema);
		session.persist(profesor2);
		session.persist(problema2);
		
		t.commit();//transaction is commited 
		session.close();
		System.out.println("successfully saved datos iniciales");
        Cargado=true;
		
}
}
