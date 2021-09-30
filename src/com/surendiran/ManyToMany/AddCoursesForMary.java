package com.surendiran.ManyToMany;

import com.surendiran.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class AddCoursesForMary {

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

            // get the student mary from database
            int studentId = 2;
            Student tempStudent = session.get(Student.class, studentId);

            System.out.println("\nLoaded Student: " + tempStudent);
            System.out.println("Course: " + tempStudent.getCourses());

            // create more courses
            Course tempCourse1 = new Course("Rubik's Cube");
            Course tempCourse2 = new Course("Chess");

            // add student to courses
            tempCourse1.addStudent(tempStudent);
            tempCourse2.addStudent(tempStudent);

            // save the courses
            session.save(tempCourse1);
            session.save(tempCourse2);

            // commit the transaction
            session.getTransaction().commit();
        }
        finally {
            session.close();
            factory.close();
        }
    }
}

