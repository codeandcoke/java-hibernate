package org.sfsoft.hibernate.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * Clase que facilita el trabajo con Hibernate
 * @author Santiago Faci
 * @version 1.0
 *
 */
public class HibernateUtil {

	private static SessionFactory sessionFactory;
	private static Session session;
	
	/**
	 * Crea la factoria de sesiones
	 */
	public static void buildSessionFactory() {
	
		Configuration configuration = new Configuration();
		configuration.configure();
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(
						configuration.getProperties()).buildServiceRegistry();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	}
	
	/**
	 * Abre una nueva sesión
	 */
	public static void openSession() {
		
		session = sessionFactory.openSession();
	}
	
	/**
	 * Devuelve la sesi�n actual
	 * @return
	 */
	public static Session getCurrentSession() {
		
		if ((session == null) || (!session.isOpen()))
			openSession();
			
		return session;
	}
	
	/**
	 * Cierra Hibernate
	 */
	public static void closeSessionFactory() {
		
		if (session != null)
			session.close();
		
		if (sessionFactory != null)
			sessionFactory.close();
	}
}
