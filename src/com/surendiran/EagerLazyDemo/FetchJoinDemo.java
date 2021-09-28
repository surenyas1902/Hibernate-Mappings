package com.surendiran.EagerLazyDemo;

import com.surendiran.entity.Course;
import com.surendiran.entity.Instructor;
import com.surendiran.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class FetchJoinDemo {

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
            // start a transaction
            session.beginTransaction();

            //get the instructor from db
            int theId = 1;
            Query<Instructor> query = session.createQuery("select i from Instructor i "
                    +"JOIN FETCH i.courses "
                    +"where i.id=:theInstructorId",
                    Instructor.class);
            query.setParameter("theInstructorId", theId);

            Instructor tempInstructor = query.getSingleResult();

            System.out.println("Instructor: " + tempInstructor);

            session.getTransaction().commit();

            session.close();

            System.out.println("Session closed now");

            // get the course for the instructor
            System.out.println("Courses: " + tempInstructor.getCourses());
        }
        finally {
            session.close();
            factory.close();
        }
    }
}
