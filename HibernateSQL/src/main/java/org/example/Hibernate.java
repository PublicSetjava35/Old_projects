package org.example;

import org.example.grains.Grains;
import org.example.parrots.Parrot;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
public class Hibernate {
    public static void main(String[] args) {
        Configuration configuration = new Configuration()
                .addAnnotatedClass(Grains.class)
                .addAnnotatedClass(Parrot.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        try (sessionFactory) {
            Session session = sessionFactory.openSession();
            session.beginTransaction();

            Parrot parrot = new Parrot("KoKo", 100, true, true);
            Grains grains = new Grains("Sunflower",  "corn", "peanut", "nut");
            parrot.addGrains(grains);
            grains.addParrot(parrot);
            for(Object save : new Object[]{parrot, grains}) session.merge(save);

            session.beginTransaction().commit();
        }
    }
}