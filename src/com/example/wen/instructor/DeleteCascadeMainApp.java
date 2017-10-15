package com.example.wen.instructor;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteCascadeMainApp {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure().addAnnotatedClass(Instructor.class).addAnnotatedClass(InstructorDetail.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		
		try{
			session.beginTransaction();
			/*
			 //delete from instructor to instructordetail one direction
			int id = 2;
			Instructor instructor = session.get(Instructor.class, id);
			session.delete(instructor);*/
			//delete from the other direction: from instructor detail to instructor
			int id = 3;
			InstructorDetail detail = session.get(InstructorDetail.class, id);
			//need to break link when not cascadetype.all
			detail.getInstructor().setInstructorDetail(null);
			session.delete(detail);			
			session.getTransaction().commit();
		}catch(Exception exc){
			exc.printStackTrace();
		}finally{
			//prevent leaking issues
			session.close();
			factory.close();
		}

	}

}
