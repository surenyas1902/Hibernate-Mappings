package com.surendiran.OneToOne;

import com.surendiran.entity.Instructor;
import com.surendiran.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetInstructorDetailDemo {

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

            // get the instructor detail object
            InstructorDetail tmpInstructorDetail = session.get(InstructorDetail.class, 2999);

            // print the instructor detail
            System.out.println(tmpInstructorDetail);

            // print the associated instructor
            System.out.println("Associated Instructor: " + tmpInstructorDetail.getInstructor());

            // commit the transaction
            session.getTransaction().commit();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            session.close();
            factory.close();
        }
    }
}
