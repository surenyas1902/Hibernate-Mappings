package com.surendiran.ManyToMany;

import com.surendiran.entity.*;
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
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // Create a session
        Session session = factory.getCurrentSession();

        try {
            // start a transaction
            session.beginTransaction();

            // create a course
            Course tempCourse = new Course("Java - Beginners Course");

            session.save(tempCourse);

            // create the studnets
            Student tempStudent1 = new Student("John", "Doe", "john@luv2code.com");
            Student tempStudent2 = new Student("Mary", "Doe", "john@luv2code.com");

            tempCourse.addStudent(tempStudent1);
            tempCourse.addStudent(tempStudent2);

            System.out.println("\nSaving the Students");
            session.save(tempStudent1);
            session.save(tempStudent2);
            System.out.println("Saved studnets: " + tempCourse.getStudents());
            // commit the transaction
            session.getTransaction().commit();
        }
        finally {
            session.close();
            factory.close();
        }
    }
}
