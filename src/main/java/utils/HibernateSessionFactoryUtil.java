package utils;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class HibernateSessionFactoryUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();
    private static final String HibernatePropertiesPath = "conf/hibernate.db.properties";

    public static SessionFactory buildSessionFactory() {
        Properties externalDBConfig = new Properties();
        try (InputStream inS = new FileInputStream(HibernatePropertiesPath)) {
            externalDBConfig.load(inS);
        } catch (IOException ex) {
            System.out.println(ex);
        }
        try {
            StandardServiceRegistry standartRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(externalDBConfig).configure("hibernate.cfg.xml").build();
            Metadata metadata= new MetadataSources((standartRegistry)).getMetadataBuilder().build();
            return metadata.getSessionFactoryBuilder().build();
        } catch (HibernateException he){
            System.out.println("Error");
            throw he;
        }
    }
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
