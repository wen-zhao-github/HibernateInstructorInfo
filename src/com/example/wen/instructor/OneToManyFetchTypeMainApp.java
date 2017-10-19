package com.example.wen.instructor;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class OneToManyFetchTypeMainApp {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure()
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		
		Instructor instructor = new Instructor("mary","public","mary@gamil.com");
		InstructorDetail detail = new InstructorDetail("youtube.com","game");
		instructor.setInstructorDetail(detail);
		Course javaCourse = new Course("java guru");
		javaCourse.setInstructor(instructor);
		Course cplus = new Course("c++ guru");
		cplus.setInstructor(instructor);	
		
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
