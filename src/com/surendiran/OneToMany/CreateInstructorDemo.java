package com.surendiran.OneToMany;

import com.surendiran.entity.Course;
import com.surendiran.entity.Instructor;
import com.surendiran.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateInstructorDemo {

    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        // Create a session
        Session session = factory.getCurrentSession();

        try {
            Instructor tempInstructor = new Instructor("Captain", "America", "captainamerica@gmail.com");
            InstructorDetail tempInstructorDetail = new InstructorDetail("http://luv2code.com/youtube", "Gamer");

            tempInstructor.setInstructorDetail(tempInstructorDetail);

            // start a transaction
            session.beginTransaction();

            // This will also save the details object
            System.out.println("Saving instructor: " + tempInstructor);
            session.save(tempInstructor);

            session.getTransaction().commit();
        }
        finally {
            session.close();
            factory.close();
        }
    }
}
