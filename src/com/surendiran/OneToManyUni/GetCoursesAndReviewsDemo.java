package com.surendiran.OneToManyUni;

import com.surendiran.entity.Course;
import com.surendiran.entity.Instructor;
import com.surendiran.entity.InstructorDetail;
import com.surendiran.entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetCoursesAndReviewsDemo {

    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .buildSessionFactory();

        // Create a session
        Session session = factory.getCurrentSession();

        try {
            // start a transaction
            session.beginTransaction();

            // get the course
            int theId = 10;
            Course tempCourse = session.get(Course.class, theId);

            // print the course

            System.out.println(tempCourse);
            // print the course reviews
            System.out.println(tempCourse.getReviews());

            // commit the transaction
            session.getTransaction().commit();
        }
        finally {
            session.close();
            factory.close();
        }
    }
}
