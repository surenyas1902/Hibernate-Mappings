package com.surendiran.OneToManyUni;

import com.surendiran.entity.Course;
import com.surendiran.entity.Instructor;
import com.surendiran.entity.InstructorDetail;
import com.surendiran.entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCoursesAndReviewDemo {

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

            // create a course
            Course tempCourse = new Course("Java - Beginners Course");

            // add some reviews
            tempCourse.addReview(new Review("Great course... loved it"));
            tempCourse.addReview(new Review("Need to improve on hibernate"));
            tempCourse.addReview(new Review("Cool course. worth the money"));

            // save the course ... and leverage the cascade all
            System.out.println("Saving the course");
            System.out.println(tempCourse);
            System.out.println(tempCourse.getReviews());
            session.save(tempCourse);

            // commit the transaction
            session.getTransaction().commit();
        }
        finally {
            session.close();
            factory.close();
        }
    }
}
