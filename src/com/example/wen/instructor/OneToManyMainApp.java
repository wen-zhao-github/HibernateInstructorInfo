package com.example.wen.instructor;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class OneToManyMainApp {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure().addAnnotatedClass(Instructor.class).addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		
		Instructor instructor = new Instructor("sally","kun","sally@gamil.com");
		InstructorDetail detail = new InstructorDetail("youtube.com","tv");
		instructor.setInstructorDetail(detail);
		Course javaCourse = new Course("java advanced");
		Course cplus = new Course("c++ advanced");
		List<Course> courses = new ArrayList<>();
		javaCourse.setInstructor(instructor);
		cplus.setInstructor(instructor);
		courses.add(javaCourse);
		courses.add(cplus);        
		instructor.setCourses(courses);
		
	    try{
	    	session.beginTransaction();
	    	session.save(instructor);
	    	session.save(javaCourse);
	    	session.save(cplus);
	    	session.getTransaction().commit();
		   
	   }catch (Exception e){
		   e.printStackTrace();
	   }finally{
		   session.close();
		   factory.close();
	   }

	}

}
