package ma.meditel.reporting.util;

import org.hibernate.*;
import org.hibernate.cfg.*;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil {
    public static final SessionFactory sessionFactory;

    static {
        try {
            // Création de la SessionFactory à partir de hibernate.cfg.xml
            //sessionFactory = new Configuration().configure("Hibernate.cfg.xml").buildSessionFactory();
            
            Configuration configuration = new Configuration().configure("Hibernate.cfg.xml");
    		ServiceRegistryBuilder builder = new ServiceRegistryBuilder().
    		applySettings(configuration.getProperties());
    		sessionFactory = configuration.buildSessionFactory(builder.buildServiceRegistry());
    		
    		configuration.setProperty( Environment.USE_QUERY_CACHE, Boolean.FALSE.toString() );
    		configuration.setProperty( Environment.USE_SECOND_LEVEL_CACHE, Boolean.FALSE.toString() );
    		
        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

   // public static final ThreadLocal session = new ThreadLocal();

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
