package com.surendiran.OneToOne;

import com.surendiran.entity.Instructor;
import com.surendiran.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteInstructorDetailDemo {

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
            InstructorDetail tmpInstructorDetail = session.get(InstructorDetail.class, 3);

            // print the instructor detail
            System.out.println(tmpInstructorDetail);

            // print the associated instructor
            System.out.println("Associated Instructor: " + tmpInstructorDetail.getInstructor());

            // Setting the Instructor Detail id as null in Instructor table
            tmpInstructorDetail.getInstructor().setInstructorDetail(null);
            session.delete(tmpInstructorDetail);

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
