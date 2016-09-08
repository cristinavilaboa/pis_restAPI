package Persistencia;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import Modelo.Contenido;


public class CargarBD {	
	int i = 0;	
	public static void main(String[] args) throws SecurityException, HeuristicMixedException, HeuristicRollbackException, RollbackException, SystemException {
		//creating configuration object
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");//populates the data of the configuration file	
		//creating seession factory object 
		SessionFactory factory=cfg.buildSessionFactory();
		//creating session object
		Session session=factory.openSession();
		//creating transaction object
		org.hibernate.Transaction t= session.beginTransaction();
		Contenido e1= new Contenido("contenido");
		session.persist(e1);//persisting the object 
		t.commit();//transaction is commited 
		session.close();
		System.out.println("successfully saved");

		
}
}
