package bc.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

//public class HibernateConfig {
//    private static SessionFactory sessionFactory;
//
//    static {
//        Configuration configuration = new Configuration();
//        configuration.configure();
//
//        // get Session factory from config above
//        sessionFactory = configuration.buildSessionFactory();
//    }
//    public static SessionFactory getSessionFactory(){
//        return sessionFactory;
//    }
//}
public class HibernateUtil {
    public static SessionFactory getSessionFactory() {
        SessionFactory sessionFactory = null;
        try {
            Configuration configuration = new Configuration();
            sessionFactory = configuration.configure().buildSessionFactory();
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
        return sessionFactory;
    }
}
