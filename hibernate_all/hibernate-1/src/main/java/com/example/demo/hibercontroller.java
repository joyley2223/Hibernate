package com.example.demo;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class hibercontroller {

	public static void main(String[] args) {
		
		Scanner src = new Scanner(System.in);
		
		int ch;
		
		do {
//			System.out.println("*");
			
			displaymenu();
			ch = Integer.parseInt(src.nextLine());
			
			switch (ch) {
			case 1:
				insertion();
				break;
			case 2:
				delete();
				break;
			case 3:
				update();
				break;
			case 4:
				getall();
				break;
			case 5:
				getbyid();
				break;
			case 6:
				System.exit(0);
				break;
			default:
				System.out.println("invalid operation");
				break;
				
			}
		}
		while(ch>0);
	}

	private static void getbyid() {
		
		Scanner src = new Scanner(System.in);
		
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		Metadata md = new MetadataSources(ssr).getMetadataBuilder().build();
		SessionFactory sf = md.buildSessionFactory();
		Session s = sf.openSession();
		
		System.out.println("Enter id: ");
		
		int id = src.nextInt();
		
		Transaction t = s.beginTransaction();
		
		hiber h = s.get(hiber.class, id);
		
		if(h!=null) {
			System.out.println("id: "+h.getId());
			
			System.out.println("name: "+h.getName());
			
			System.out.println("email: "+h.getEmail());
		}
		else {
			System.out.println("Error");
		}
		t.commit();
		
	}

	private static void getall() {
		
		Scanner src = new Scanner(System.in);
		
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		Metadata md = new MetadataSources(ssr).getMetadataBuilder().build();
		SessionFactory sf = md.buildSessionFactory();
		Session s = sf.openSession();
		Transaction t = s.beginTransaction();
		
		List<hiber> li = s.createQuery("from hiber",hiber.class).list();
		
		t.commit();
		
		for(hiber h:li) {
			
			System.out.println("Id: "+h.getId());
			
			System.out.println("Name: "+h.getName());
			
			System.out.println("Email: "+h.getEmail());
		}
		
	}

	@SuppressWarnings("deprecation")
	private static void update() {
		
		Scanner src = new Scanner(System.in);
		
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		Metadata md = new MetadataSources(ssr).getMetadataBuilder().build();
		SessionFactory sf = md.buildSessionFactory();
		Session s = sf.openSession();
		
		System.out.println("enter id: ");
		
		int id = src.nextInt();
		
		Transaction t = s.beginTransaction();
		
		hiber h = s.get(hiber.class, id);
		
		if(h!=null) {
			
			System.out.println("Enter new name: ");
			
			String name = src.next();
			
			System.out.println("Enter new email: ");
			
			String email = src.next();
			
		    h.setName(name);
		
			h.setEmail(email);
		
		    s.update(h);
			
			System.out.println("successfully updated");
		}
		
		else {
			
			System.out.println("Error");
		}
		t.commit();

	}

	private static void delete() {
		Scanner src = new Scanner(System.in);
		
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		Metadata md = new MetadataSources(ssr).getMetadataBuilder().build();
		SessionFactory sf = md.buildSessionFactory();
		Session s = sf.openSession();
		
		System.out.println("Enter id: ");
		
		int id = src.nextInt();
	
		Transaction t = s.beginTransaction();
		
		hiber d = s.get(hiber.class, id);
		
		s.delete(d);
		
		t.commit();
		
		System.out.println("successfully Deleted");
		
	}

	private static void insertion() {
		
		Scanner src = new Scanner(System.in);
		
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		Metadata md = new MetadataSources(ssr).getMetadataBuilder().build();
		SessionFactory sf = md.buildSessionFactory();
		Session s = sf.openSession();
		Transaction t = s.beginTransaction();
		hiber h = new hiber();
		
		System.out.println("Enter name");
		
		String name = src.nextLine();
		
		h.setName(name);
		
		System.out.println("Enter email");
		
		String email = src.nextLine();
		h.setEmail(email);
		
		s.save(h);
		
		t.commit();
		
		System.out.println("successfully Inserted");
		
	}

	private static void displaymenu() {
		
		System.out.println("___");
		System.out.println("\t1.insertion");
		System.out.println("\t2.delete");
		System.out.println("\t3.update");
		System.out.println("\t4.getall");
		System.out.println("\t5.getbyid");
		System.out.println("\t6.exit");
		
		
	}

}
