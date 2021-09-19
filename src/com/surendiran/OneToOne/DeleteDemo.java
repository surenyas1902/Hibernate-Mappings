package com.surendiran.OneToOne;

import com.surendiran.entity.Instructor;
import com.surendiran.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteDemo {

    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        // Create a session
        Session session = factory.getCurrentSession();

        try {
            // start a transaction
            session.beginTransaction();

            // get Instrtuctor by Primary Key

            Instructor tmpInstructor = session.get(Instructor.class, 1);

            if(tmpInstructor != null) {
                System.out.println("Deleting " + tmpInstructor);

                // Will also delete details object because of CascadeType.ALL
                session.delete(tmpInstructor);
            }

            session.getTransaction().commit();
        }
        finally {
            factory.close();
        }
    }
}
