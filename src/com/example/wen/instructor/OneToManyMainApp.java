package com.example.wen.instructor;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class OneToManyMainApp {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure().addAnnotatedClass(Instructor.class).addAnnotatedClass(Course.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		
		Instructor instructor = new Instructor("wen","li","wen@gamil.com");
		InstructorDetail detail = new InstructorDetail("youtube.com","yogo");
		instructor.setInstructorDetail(detail);
		Course javaCourse = new Course("java");
		Course cplus = new Course("c++");
		List<Course> courses = new ArrayList<>();
		courses.add(javaCourse);
		courses.add(cplus);        
		instructor.setCourses(courses);
	    try{
	    	session.beginTransaction();
	    	session.save(instructor);
	    	
	    	session.getTransaction().commit();
		   
	   }catch (Exception e){
		   e.printStackTrace();
	   }finally{
		   session.close();
		   factory.close();
	   }

	}

}
