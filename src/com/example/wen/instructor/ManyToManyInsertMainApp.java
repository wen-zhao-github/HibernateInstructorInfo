package com.example.wen.instructor;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ManyToManyInsertMainApp {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure()
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Review.class)
				.addAnnotatedClass(Student.class)
				.addAnnotatedClass(Course.class).buildSessionFactory();
		Session session = factory.getCurrentSession();	
		
		
	    try{
	    	session.beginTransaction();
	    	System.out.println("main app >> ");	    	
	    	Student john = new Student("John","Doe");
	    	Student mary = new Student("mary","Public");
	    	Student sally = new Student("sally","Dimitris");
	    	
	    	Instructor instru1 = new Instructor("shod","chad","shod@luv2code.com");
	    	Instructor instru2 = new Instructor("debbie","knight","debbie@luv2code.com");
	    	Instructor instru3 = new Instructor("catherine","mantikou","mantikou@luv2code.com");
	    	john.addInstructor(instru1);
	    	//instru1.addStudent(john);
	    	john.addInstructor(instru2);
	    	//instru2.addStudent(john);
	    	
	    	mary.addInstructor(instru3);
	    	//instru3.addStudent(mary);
	    	sally.addInstructor(instru3);
	    	//instru3.addStudent(sally);
	    	session.save(john);
	    	session.save(mary);
	    	session.save(sally);
	    	session.save(instru1);
	    	session.save(instru2);
	    	session.save(instru3);
	    	session.flush();
	    	session.getTransaction().commit();
	    	System.out.println("main app >> "+john);	
	    	System.out.println("main app >> "+mary);
	    	System.out.println("main app >> "+sally);
	    	System.out.println("main app >> "+instru1);	 
	    	System.out.println("main app >> "+instru2);
	    	System.out.println("main app >> "+instru3);
	    	
	    	
	   }catch (Exception e){
		   e.printStackTrace();
	   }finally{
		   session.close();
		   factory.close();
	   }

	}

}
