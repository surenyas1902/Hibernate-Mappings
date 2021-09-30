package com.surendiran.ManyToMany;

import com.surendiran.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteMaryStudentDemo {

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

            // get the Java course from db

            int studentId = 2;
            Student tempStudent = session.get(Student.class, studentId);

            System.out.println("\nLoaded Student: " + tempStudent);
            System.out.println("Course: " + tempStudent.getCourses());

            System.out.println("Deleting Student: " + tempStudent);
            session.delete(tempStudent);

            // commit the transaction
            session.getTransaction().commit();
        }
        finally {
            session.close();
            factory.close();
        }
    }
}

