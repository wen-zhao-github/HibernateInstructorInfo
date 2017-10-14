package com.example.wen.instructor;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MainApp {
	public static void main(String args []){
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class).addAnnotatedClass(InstructorDetail.class).buildSessionFactory();
	    Session session = factory.getCurrentSession();
		Instructor instructor = new Instructor("George","Lee","glee@gmail.com");
	    InstructorDetail insDetail = new InstructorDetail("http://youtube.com/yy","complaining");
	    instructor.setInstructorDetail(insDetail);
	    
	    try{
	    	System.out.println(instructor);
	    	System.out.println(insDetail);
	    	System.out.println("begin transaction:");
	    	session.beginTransaction();
		    session.save(instructor);
		    
		    session.getTransaction().commit();
		    System.out.println("finished!");
		    System.out.println(instructor);
	    	System.out.println(insDetail);
	    }finally{
	    	factory.close();
	    }
	    
	}

}
